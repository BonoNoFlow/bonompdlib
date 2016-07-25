package com.bono.api;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hendriknieuwenhuis on 25/07/16.
 *
 * Dit kan simpeler!!!!! executcommand moet weg.
 */
public class ClientExecutor {

    private String host;
    private int port;

    public ClientExecutor(String host, String port) {
        this.host = host;
        this.port = Integer.parseInt(port);
    }

    public ClientExecutor(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String testConnection() throws IOException {

        // test the connection settings.
        Endpoint endpoint = new Endpoint(host, port);
        String version = endpoint.getVersion(1000);
        return version;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private class DBExecutor {

        private ExecutorService executor;

        public DBExecutor() {
            this.executor = Executors.newFixedThreadPool(10);
        }

        public DBExecutor(String host, int port) {
            this();
            ClientExecutor.this.host = host;
            ClientExecutor.this.port = port;
        }

        /*
         * Execute a single command.
         */
        public String execute(Command command) throws Exception {
            ExecuteCommand executeCommand = new ExecuteCommand(command, null, new Endpoint(host, port));
            String reply = null;
            Future<String> future = executor.submit(executeCommand);
            reply = future.get();
            return  reply;
        }

        /*
         * Execute a List<Command> command list.
         */
        public String executeList(List<Command> commands) throws Exception {

            for (Command c : commands) {

            }

            return null;
        }

        public String executeList(CommandList commandList) throws Exception {
            //ExecuteCommand executeCommand = new ExecuteCommand(null, commandList, new Endpoint(host, port));
            String reply = null;
            //Future<String> future = executor.submit(executeCommand);
            //reply = future.get();
            return  reply;
        }
    }

    private class ExecuteCommand implements Callable<String> {

        private Command command;
        private CommandList commandList;
        private Endpoint endpoint;

        public ExecuteCommand(Command command, List<Command> commands, Endpoint endpoint) {
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
