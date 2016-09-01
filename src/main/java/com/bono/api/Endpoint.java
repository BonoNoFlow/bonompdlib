package com.bono.api;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by hendriknieuwenhuis on 28/09/15.
 *
 * Class creates an endpoint to the server. It can
 * be used to send Command objects to the server.
 * Command objects can be send singular or as a
 * list of commands.
 */


public class Endpoint {


    private InetSocketAddress address;
    private int timeout = 10000;

    private String version = null;

    private Socket socket;

    public Endpoint(String host, int port) {
        address = new InetSocketAddress(host, port);
    }

    public Endpoint(String host, int port, int timeout) {
        this(host, port);
        this.timeout = timeout;
    }

    public Endpoint(Server server) {
        this(server.getHost(), server.getPort());
    }

    public Endpoint(InetSocketAddress address) {
        this.address = address;
    }

    public Endpoint(InetSocketAddress address, int timeout) {
        this(address);
    }

    private Collection<String> send(byte[] bytes) throws ACKException, IOException {
        Collection<String> reply = new ArrayList<>();
        String line;
        OutputStream out;
        BufferedReader reader;

        try {
            if (socket.isConnected()) {

                out = socket.getOutputStream();
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.write(bytes);
                out.flush();
                line = reader.readLine();

                while (line != null) {

                    if (line.startsWith("OK")) {
                        break;
                    } else if (line.startsWith("ACK")) {
                        throw new ACKException(line);
                    }
                    reply.add(line);
                    line = reader.readLine();
                }

            } else {
                return null;
            }
        } finally {
            socket.close();
        }
        return reply;
    }

    public Collection<String> command(Command command) throws ACKException, IOException {
        byte[] bytes = command.getCommandBytes();

        connect();

        return send(bytes);
    }

    public Collection<String> commands(Collection<Command> commands) throws ACKException, IOException {

        // collect all the command bytes to send at once.
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        for (Command c : commands) {
            bytes.write(c.getCommandBytes());
        }

        connect();

        return send(bytes.toByteArray());
    }


    // connect to server
    private void connect() throws IOException {
        BufferedReader reader;
        if (address.getHostName() != null && address.getPort() != 0) {
            socket = new Socket();
            socket.connect(address, timeout);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            version = reader.readLine();
        } else {
            throw new UnknownHostException();
        }
    }

    public String ping() throws IOException {
        connect();
        return version;
    }

    public void closeEndpoint() throws IOException {
        socket.close();
    }

    public String getVersion() throws IOException {
        connect();
        return version;
    }


}
