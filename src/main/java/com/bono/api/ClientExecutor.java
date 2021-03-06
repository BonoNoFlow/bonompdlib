package com.bono.api;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by hendriknieuwenhuis on 25/07/16.
 *
 *
 */
public class ClientExecutor {

    private ExecutorService executor;

    private String host;
    private int port;

    private Server server = null;

    private int timeout = 10000;

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

    public ClientExecutor(Server server) {
        this();
        this.server = server;
    }

    public String testConnection() throws IOException {

        // test the connection settings.
        Endpoint endpoint = this.getEndpoint();
        String version = endpoint.getVersion();
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
    public Collection<String> execute(Command command) throws IOException {

        CommandExecutor commandExecutor = new CommandExecutor(command, getEndpoint());
        Future<Collection<String>> future = executor.submit(commandExecutor);
        try {
            return  future.get();

        } catch (ExecutionException | InterruptedException e) {
            throw new IOException(e);
        }
    }

    public Collection<String> execute(String command, String... args) throws IOException {

        Command comm = new DefaultCommand(command, args);
        return execute(comm);
    }

    /*
     * Execute a List<Command> command list.
     */
    public Collection<String> executeList(Collection<Command> commands) throws IOException {
        CommandExecutor commandExecutor = new CommandExecutor(commands, getEndpoint());
        Future<Collection<String>> future = executor.submit(commandExecutor);

        try {
            return  future.get();

        } catch (ExecutionException | InterruptedException e) {
            throw new IOException(e);
        }
    }
    private Endpoint getEndpoint() {

        if (server != null) {
            return new Endpoint(server);
        } else {
            return new Endpoint(host, port);
        }
    }

    public void shutdownExecutor() {
        executor.shutdown();
    }


    private class CommandExecutor implements Callable<Collection<String>> {

        private Command command = null;
        private Collection<Command> commands = null;
        private Endpoint endpoint = null;

        public CommandExecutor(Command command, Endpoint endpoint) {
            this.command = command;
            this.endpoint = endpoint;
        }

        public CommandExecutor(Collection<Command> commands, Endpoint endpoint) {
            this.commands = commands;
            this.endpoint = endpoint;
        }
        @Override
        public Collection<String> call() throws Exception {
            // ---- kan wel weg? ---- (nullpointerexception throwing)
            if (endpoint == null) {
                throw new NullPointerException("Endpoint cannot be null!");
            }

            if (command != null) {
                return endpoint.command(command);
            } else if (commands != null) {
                return endpoint.commands(commands);
            }
            return null;
        }
    }


}
