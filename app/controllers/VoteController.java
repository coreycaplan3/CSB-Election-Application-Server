package controllers;

import play.db.Database;

import javax.inject.Inject;

public class VoteController extends AbstractBaseController {

    @Inject
    public VoteController(Database database) {
        super(database);
    }



}
