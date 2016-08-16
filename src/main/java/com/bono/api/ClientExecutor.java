package com.bono.api;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by hendriknieuwenhuis on 25/07/16.
 *
 * Dit kan simpeler!!!!! executcommand moet weg.
 */
public class ClientExecutor {

    private ExecutorService executor;

    private String host;
    private int port;

    private int timeout;

    public ClientExecutor() {
        executor = Executors.newFixedThreadPool(10);

    }

    public ClientExecutor(String host, String port, int timeout) {
        this();
        this.host = host;
        this.port = Integer.parseInt(port);
        this.timeout = timeout;
    }

    public ClientExecutor(String host, int port, int timeout) {
        this();
        this.host = host;
        this.port = port;
        this.timeout = timeout;
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

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /*
         * Execute a single command.
         */
    public List<String> execute(Command command) throws ACKException,
            IOException, ExecutionException, InterruptedException {

        CommandExecutor commandExecutor = new CommandExecutor(command, new Endpoint(host, port));
        Future<List<String>> future = executor.submit(commandExecutor);
        return  future.get();
    }

    /*
     * Execute a List<Command> command list.
     */
    public List<String> executeList(List<Command> commands) throws ACKException,
            IOException, ExecutionException, InterruptedException {
        CommandExecutor commandExecutor = new CommandExecutor(commands, new Endpoint(host, port));
        Future<List<String>> future = executor.submit(commandExecutor);
        return  future.get();
    }

    public void shutdownExecutor() {
        executor.shutdown();
    }


    private class CommandExecutor implements Callable<List<String>> {

        private Command command = null;
        private List<Command> commands = null;
        private Endpoint endpoint = null;

        public CommandExecutor(Command command, Endpoint endpoint) {
            this.command = command;
            this.endpoint = endpoint;
        }

        public CommandExecutor(List<Command> commands, Endpoint endpoint) {
            this.commands = commands;
            this.endpoint = endpoint;
        }
        @Override
        public List<String> call() throws Exception {
            if (endpoint == null) {
                throw new Exception("Endpoint cannot be null!");
            }
            if (command != null) {
                return endpoint.command(command, timeout);
            } else if (commands != null) {
                return endpoint.commands(commands, timeout);
            }
            return null;
        }
    }


}
