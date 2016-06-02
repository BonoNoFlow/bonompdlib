package com.bono.api;

import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hendriknieuwenhuis on 02/12/15.
 */
public class DBExecutor {

    private ExecutorService executor;

    //private Config config;

    private Connection connection;

    public DBExecutor(Connection conection) {
        this.connection = connection;
        this.executor = Executors.newFixedThreadPool(10);
    }

    public String execute(Command command) throws Exception {
        ExecuteCommand executeCommand = new ExecuteCommand(command, null, new Endpoint(connection.getHost(), connection.getPort()));
        String reply = null;
        Future<String> future = executor.submit(executeCommand);
        reply = future.get();
        return  reply;
    }

    public String executeList(CommandList commandList) throws Exception {

        ExecuteCommand executeCommand = new ExecuteCommand(null, commandList, new Endpoint(connection.getHost(), connection.getPort()));
        String reply = null;
        Future<String> future = executor.submit(executeCommand);
        reply = future.get();
        return  reply;
    }

    private class ExecuteCommand implements Callable<String> {

        private Command command;
        private CommandList commandList;
        private Endpoint endpoint;

        public ExecuteCommand(Command command, CommandList commandList, Endpoint endpoint) {
            this.command = command;
            this.commandList = commandList;
            this.endpoint = endpoint;
        }
        @Override
        public String call() throws Exception {

            if (command != null) {
                return endpoint.command(command);
            } else if (commandList != null) {
                return endpoint.command(commandList);
            }

            return null;
        }
    }
}
