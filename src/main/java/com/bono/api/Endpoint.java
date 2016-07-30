package com.bono.api;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 28/09/15.
 */


public class Endpoint {

    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    private String host = null;
    private int port = 0;

    private String version = null;

    private Socket socket;

    public Endpoint(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private List<String> send(byte[] bytes, int timeout) throws Exception {
        List<String> reply = new ArrayList<>();
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

    @Deprecated
    public String command(Command command) throws Exception {
        String reply = "";
        int read = 0;
        DataOutputStream out;
        DataInputStream in;

        connect();

        try {
            if (version.startsWith("OK")) {
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                out.write(command.getCommandBytes());
                out.flush();

                while ((read = in.read(buffer.array())) != -1) {
                    reply += new String(buffer.array(), 0, read);

                    if (reply.startsWith("ACK") && reply.endsWith("\n")) {

                        throw new ACKException("Endpoint read loop broken by error feedback! " + reply);
                    } else if (reply.endsWith("OK\n")) {
                        reply = reply.substring(0, (reply.length() - 3));
                        break;
                    }
                }
            } else {
                return null;
            }
        } finally {
            buffer.clear();
            socket.close();

        }
        return reply;
    }


    public List<String> command(Command command, int timeout) throws Exception {
        byte[] bytes = command.getCommandBytes();

        connect(timeout);

        return send(bytes, timeout);
    }

    public List<String> commands(List<Command> commands, int timeout) throws Exception {
        //List<String> reply = new ArrayList<>();

        // collect all the command bytes to send at once.
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        for (Command c : commands) {
            bytes.write(c.getCommandBytes());
        }

        connect(timeout);

        return send(bytes.toByteArray(), 4000);
    }

    @Deprecated
    public String command(CommandList commands) throws Exception {
        String reply = "";
        DataOutputStream out;
        DataInputStream in;
        BufferedReader reader;

        connect();

        try {
            if (version.startsWith("OK")) {
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                reader = new BufferedReader(new InputStreamReader(in));

                Iterator<Command> i = commands.iterator();
                while (i.hasNext()) {
                    Command command = i.next();
                    out.write(command.getCommandBytes());
                }
                out.flush();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    reply += line;
                    if (line.startsWith("ACK")) {
                        throw new ACKException(line);
                    } else if (reply.endsWith("OK")) {
                        break;
                    }
                }
            } else {
                return null;
            }
        } finally {
            buffer.clear();
            socket.close();
        }
        return reply;
    }

    private void connect() throws IOException {
        connect(0);
    }

    // connect to server
    private void connect(int timeout) throws IOException {
        BufferedReader reader;
        if (host != null && port != 0) {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), timeout);
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
        connect(0);
        return version;
    }

    public String getVersion(int timeout) throws IOException {
        connect(timeout);
        return version;
    }
}
