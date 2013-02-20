package com.akhettar.telnet.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: a.khettar
 * Date: 20/02/2013
 * Time: 18:02
 * To change this template use File | Settings | File Templates.
 */
public class TelnetServer {

    /**
     * The main method to start the telnet server
     */
    public static void run() {
        int port = 18876;
        ServerSocket server = null;
        Socket socket = null;

        try {

            while (true) {
                server = new ServerSocket(port);
                System.out.println("Server running");

                socket = server.accept();

                final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                boolean exit = false;
                while (!exit) {
                    final String message = reader.readLine();
                    if (message.equals("status")) {
                        out.println("Server running...");
                    } else if (message.equals("exit")) {
                        exit = true;
                    } else {
                        out.println("Echo: " + message);
                    }
                }

            }
            // Initialise socket and start the server

        } catch (IOException e) {

        } finally {
            if (socket == null)
                try {
                    socket.close();
                } catch (IOException e) {

                }

        }

    }
}
