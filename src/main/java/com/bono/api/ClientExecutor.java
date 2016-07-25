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

    private ExecutorService executor;

    private String host;
    private int port;

    public ClientExecutor() {
        executor = Executors.newFixedThreadPool(10);
    };

    public ClientExecutor(String host, String port) {
        this();
        this.host = host;
        this.port = Integer.parseInt(port);
    }

    public ClientExecutor(String host, int port) {
        this();
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

        // if first command is COMMAND_LIST_BEGIN.
        // else if first command is COMMAND_LIST_BEGIN_OK.

        for (Command c : commands) {

        }

        return null;
    }


    private class ExecuteCommand implements Callable<String> {

        private byte[] bytes;
        private Command command;
        private List<Command> commands;
        private Endpoint endpoint;

        public ExecuteCommand(byte[] bytes, Endpoint endpoint) {

        }

        public ExecuteCommand(Command command, List<Command> commands, Endpoint endpoint) {
            this.command = command;
            this.commands = commands;
            this.endpoint = endpoint;
        }
        @Override
        public String call() throws Exception {
            if (command != null) {

            } else if (commands != null) {
                for (Command c : commands) {

                }
            }
            return endpoint.command(command);
        }
    }


}
