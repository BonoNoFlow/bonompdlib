package com.bono.api;

/**
 * Created by hendriknieuwenhuis on 08/04/16.
 */
abstract class Exec {

    protected DBExecutor dbExecutor;

    public Exec(DBExecutor dbExecutor) {
        this.dbExecutor = dbExecutor;
    }
}
