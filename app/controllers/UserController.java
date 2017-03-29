package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import play.db.Database;
import play.mvc.BodyParser;
import play.mvc.Result;
import utilities.ResultUtility;

import javax.inject.Inject;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class UserController extends AbstractBaseController {

    private static final String CLIENT_ID = "58496910128-hgdkvstdspr847bvclhtdegmgl7s1u48.apps.googleusercontent.com";
    private static final String TOKEN = "id_token";

    @Inject
    public UserController(Database database) {
        super(database);
    }

    @BodyParser.Of(BodyParser.Json.class)
    public CompletionStage<Result> login() {
        JsonNode node = request().body().asJson();
        String idTokenString = node.get(TOKEN).asText();

        if (idTokenString == null) {
            return CompletableFuture.completedFuture(badRequest(ResultUtility.getNodeForMissingField(TOKEN)));
        }

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new ApacheHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        return CompletableFuture.supplyAsync(() -> {
            try {
                return verifier.verify(idTokenString);
            } catch (GeneralSecurityException | IOException e) {
                e.printStackTrace();
                return null;
            }
        }).thenApply(idToken -> {
            if (idToken == null) {
                return badRequest("Invalid ID token");
            } else {
                GoogleIdToken.Payload payload = idToken.getPayload();

                // Print user identifier
                String userId = payload.getSubject();
                System.out.println("User ID: " + userId);

                // Get profile information from payload
                String email = payload.getEmail();
                boolean emailVerified = payload.getEmailVerified();
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");
                String familyName = (String) payload.get("family_name");
                String givenName = (String) payload.get("given_name");

                return ok("EMAIL: " + email);
            }
        });
    }

    public Result standardGet() {
        return TODO;
    }

}
