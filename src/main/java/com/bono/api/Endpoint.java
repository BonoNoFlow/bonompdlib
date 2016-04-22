package com.bono.api;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

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

    public String command(Command command) throws Exception {

        String reply = "";
        //boolean end = false;
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
                //buffer.flip();

                /*
                Doel is om volledige feedback te lezen alvorens er wordt
                gekeken of het een error is of ok is

                Dus break uit loop zou moeten worden aangeroepen door
                einde feedback.Wanneer reply begint met ACK en eindigt met '\n'
                break moet worden uitgevoerd als error en wanneer feedback
                eindigt met 'OK\n' break en feedback return.
                 */
                int count = 0;
                while ((read = in.read(buffer.array())) != -1) {
                    reply += new String(buffer.array(), 0, read);
                    //System.out.println("amount of loops: " + ++count);
                    if (reply.startsWith("ACK") && reply.endsWith("\n")) {
                        /*
                        Errors moeten hier afgehandeld worden!
                        of doorgegeven worden en later behandeld worden als zodanig.
                         */
                        //System.out.println("Endpoint read loop broken by error feedback! " + reply);
                        //break;
                        throw new ACKException("Endpoint read loop broken by error feedback! " + reply);
                    } else if (reply.endsWith("OK\n")) {
                        reply = reply.replaceAll("OK\n", "");
                        //System.out.println("Endpoint read loop broken by OK feedback!");
                        break;
                    }
                }
            } else {
                return null;
            }
        } finally {
            buffer.clear();
            socket.close();
            //System.out.println(reply + " closed " + this.getClass().getName());
        }
        return reply;
    }
    public String command(Command[] commands) throws Exception {

        String reply = "";
        //boolean end = false;
        int read = 0;
        DataOutputStream out;
        DataInputStream in;

        connect();

        try {
            if (version.startsWith("OK")) {
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                for (Command command : commands) {
                    out.write(command.getCommandBytes());
                }
                out.flush();
                //buffer.flip();

                /*
                Doel is om volledige feedback te lezen alvorens er wordt
                gekeken of het een error is of ok is

                Dus break uit loop zou moeten worden aangeroepen door
                einde feedback.Wanneer reply begint met ACK en eindigt met '\n'
                break moet worden uitgevoerd als error en wanneer feedback
                eindigt met 'OK\n' break en feedback return.
                 */
                int count = 0;
                while ((read = in.read(buffer.array())) != -1) {
                    reply += new String(buffer.array(), 0, read);
                    //System.out.println("amount of loops: " + ++count);
                    if (reply.startsWith("ACK") && reply.endsWith("\n")) {
                        /*
                        Errors moeten hier afgehandeld worden!
                        of doorgegeven worden en later behandeld worden als zodanig.
                         */
                        System.out.println("Endpoint read loop broken by error feedback! " + reply);
                        break;
                    } else if (reply.endsWith("OK\n")) {
                        reply = reply.replaceAll("OK\n", "");
                        System.out.println("Endpoint read loop broken by OK feedback!");
                        break;
                    }
                }
            } else {
                return null;
            }
        } finally {
            buffer.clear();
            socket.close();
            //System.out.println(reply + " closed " + this.getClass().getName());
        }
        return reply;
    }

    // connect to server
    private void connect() throws IOException {
        byte[] versionBuffer = new byte[18];
        if (host != null && port != 0) {
            socket = new Socket(host, port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            int read = in.read(versionBuffer);
            version = new String(versionBuffer, 0, read);
        } else {
            throw new UnknownHostException();
        }
    }

    public void closeEndpoint() throws IOException {
        socket.close();
    }

    public String getVersion() throws IOException {
        connect();
        return version;
    }
}