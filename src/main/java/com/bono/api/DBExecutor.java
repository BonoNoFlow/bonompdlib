package com.bono.api;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hendriknieuwenhuis on 02/12/15.
 *
 * Wellicht moet hier Exec de main class zijn en DBExecutor een inner class van Exec.
 *
 * zie ClientExecutor.
 */
@Deprecated
public class DBExecutor {

    private ExecutorService executor;

    private Connection connection;

    private String host;
    private int port;

    public DBExecutor() {
        this.executor = Executors.newFixedThreadPool(10);
    }

    public DBExecutor(String host, int port) {
        this();
        this.host = host;
        this.port = port;
    }

    public DBExecutor(Connection connection) {
        this();
        host = connection.getHost();
        port = connection.getPort();
    }

    /**
     * Execute a single command.
     * @param command
     * @return
     * @throws Exception
     */
    public String execute(Command command) throws Exception {
        ExecuteCommand executeCommand = new ExecuteCommand(command, null, new Endpoint(host, port));
        String reply = null;
        Future<String> future = executor.submit(executeCommand);
        reply = future.get();
        return  reply;
    }

    /**
     * Execute a List<Command> command list.
     * @param commands
     * @return
     * @throws Exception
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
        //r/eply = future.get();
        return  reply;
    }

    private class ExecuteCommand implements Callable<String> {

        private Command command;
        private CommandList commandList;
        private List<Command> commands;
        private Endpoint endpoint;

        public ExecuteCommand(Command command, List<Command> commands, Endpoint endpoint) {
            this.command = command;
            this.commandList = commandList;
            this.commands = commands;
            this.endpoint = endpoint;
        }
        @Override
        public String call() throws Exception {

            if (command != null) {
                return endpoint.command(command);
            } else if (commandList != null) {
                return endpoint.command(commandList);
            } else if(commands != null) {
                return null;
            }

            return null;
        }
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
}
