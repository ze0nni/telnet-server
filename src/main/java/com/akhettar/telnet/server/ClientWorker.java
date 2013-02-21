package com.akhettar.telnet.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
                final CommandHandler handler = fac.getHandler(command);
                out.println(handler.handle());

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
