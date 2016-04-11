package com.bono.api;

/**
 * Created by hendriknieuwenhuis on 08/04/16.
 */
abstract class Exec {

    protected DBExecutor dbExecutor;

    public Exec(DBExecutor dbExecutor) {
        this.dbExecutor = dbExecutor;
    }

    protected String execCommand(String command) throws Exception {
        DefaultCommand defaultCommand = new DefaultCommand(command);
        return dbExecutor.execute(defaultCommand);
    }

    protected String execSingleArgCommand(String command, String arg) throws Exception {
        DefaultCommand defaultCommand = new DefaultCommand(command);
        if (arg != null) {
            defaultCommand.addArg(arg);
        }
        return dbExecutor.execute(defaultCommand);
    }

    protected String execDoubleArgCommand(String command, String arg1, String arg2) throws Exception {
        DefaultCommand defaultCommand = new DefaultCommand(command);
        if (arg1 != null) {
            defaultCommand.addArg(arg1);
            if (arg2 != null) {
                defaultCommand.addArg(arg2);
            }
        }
        return dbExecutor.execute(defaultCommand);
    }

    protected String execTripleArgCommand(String command, String arg1, String arg2, String arg3) throws Exception {
        DefaultCommand defaultCommand = new DefaultCommand(command);
        if (arg1 != null) {
            defaultCommand.addArg(arg1);
            if (arg2 != null) {
                defaultCommand.addArg(arg2);
                if (arg3 != null) {
                    defaultCommand.addArg(arg3);
                }
            }
        }
        return dbExecutor.execute(defaultCommand);
    }
}
