package com.akhettar.telnet.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.akhettar.telnet.command.CDHandler;
import com.akhettar.telnet.command.CommandHandler;
import com.akhettar.telnet.command.CommandHandlerFactory;
import com.akhettar.telnet.command.ExitHandler;

/**
 * 
 * Client Worker handling request of a client.
 * 
 * @author a.khettar
 * 
 */
public class ClientWorker implements Runnable {

    private final Socket socket;
    private String WORKING_DIR = null;
    private final Logger logger = LogManager.getLogger(ClientWorker.class);

    /**
     * @param socket
     */
    public ClientWorker(final Socket socket) {
        this.socket = socket;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            boolean cancel = false;
            CommandHandlerFactory fac = CommandHandlerFactory.getInstance();
            while (!cancel) {

                final String command = reader.readLine();
                if (command == null) {
                    continue;
                }

                //handle the command
                final CommandHandler handler = fac.getHandler(command, WORKING_DIR);
                String response = handler.handle();

                // setting the working directory
                if (handler instanceof CDHandler) {

                    WORKING_DIR = response.contains("No such file or directory") ? WORKING_DIR : response;
                    logger.info("Working directory set to: " + WORKING_DIR);
                }
                out.println(response);

                // command issuing an exit.
                if (handler instanceof ExitHandler) {
                    cancel = true;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {

            }
        }
    }

}
