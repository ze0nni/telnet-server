package com.akhettar.telnet.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.akhettar.telnet.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: a.khettar
 * Date: 20/02/2013
 * Time: 18:02
 * To change this template use File | Settings | File Templates.
 */
public class TelnetServer {

    private final int NUMBER_OF_THREADS = 120;
    private ServerSocket server = null;
    private final ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * The main method to start the telnet server
     */
    public void run() {

        try {

            while (true) {

                // establish the connection
                server = new ServerSocket(Constants.PORT_NUM);
                System.out.println("Server running and listening on port : " + Constants.PORT_NUM);

                // send the job the to the client worker
                executor.execute(new ClientWorker(server.accept()));

            }

        } catch (IOException e) {

        } finally {
            executor.shutdown();

        }

    }

    /**
     * Checks if the server is running.
     * 
     * @return
     */
    public boolean isRunning() {
        return !server.isClosed();
    }

    /**
     * Shutdowns all the connection and the server
     * 
     * @throws IOException
     */
    public void shutDown() throws IOException {

        server.close();
    }
}
