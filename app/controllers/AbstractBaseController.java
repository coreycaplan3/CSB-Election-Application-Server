package controllers;

import play.db.Database;
import play.mvc.Controller;

import javax.inject.Inject;

public class AbstractBaseController extends Controller {

    private final Database database;

    @Inject
    public AbstractBaseController(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }
}
