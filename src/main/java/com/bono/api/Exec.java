package com.bono.api;

/**
 * Created by hendriknieuwenhuis on 08/04/16.
 */
abstract class Exec {

    protected DBExecutor dbExecutor;

    public Exec(DBExecutor dbExecutor) {
        this.dbExecutor = dbExecutor;
    }

    protected String execCommand(String command, String... args) throws Exception {
        DefaultCommand defaultCommand = new DefaultCommand(command, args);
        //if (args != null) {
        //    for (String arg : args) {
        //        defaultCommand.addArg(arg);
        //    }
        //}
        return dbExecutor.execute(defaultCommand);
    }

    protected String execCommand(String command) throws Exception {
        return execCommand(command, null);
    }

    @Deprecated
    protected String execSingleArgCommand(String command, String arg) throws Exception {
        DefaultCommand defaultCommand = new DefaultCommand(command);
        if (arg != null) {
            defaultCommand.addArg(arg);
        }
        return dbExecutor.execute(defaultCommand);
    }

    @Deprecated
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

    @Deprecated
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
