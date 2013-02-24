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
    private String workingDir;
    private final Logger logger = LogManager.getLogger(ClientWorker.class);

    /**
     * @param socket
     */
    public ClientWorker(final Socket socket, String homeDir) {
        this.socket = socket;
        this.workingDir = homeDir;
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

            // display welcome screen
            //out.println(buildWelcomeScreen());

            boolean cancel = false;
            CommandHandlerFactory fac = CommandHandlerFactory.getInstance();
            while (!cancel) {

                final String command = reader.readLine();
                if (command == null) {

                    continue;
                }

                //handle the command
                final CommandHandler handler = fac.getHandler(command, workingDir);
                String response = handler.handle();

                // setting the working directory
                if (handler instanceof CDHandler) {

                    workingDir = response.contains("No such file or directory") ? workingDir : response;
                    logger.info("Working directory set to: " + workingDir);
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

    /**
     * Builds welcome screen.
     * 
     * @return
     */
    private String buildWelcomeScreen() {
        String os = System.getProperty("os.name");
        String CARRIAGE_RETURN = System.getProperty("os.name").contains("Windows") ? "\r": "\n";
        StringBuilder builder = new StringBuilder();
        builder.append(CARRIAGE_RETURN);
        builder.append("======================================================");
        builder.append(CARRIAGE_RETURN);
        builder.append(CARRIAGE_RETURN);
        builder.append("   Welcome to Telnet Server: Version 1.0   ");
        builder.append(CARRIAGE_RETURN);
        builder.append(CARRIAGE_RETURN);
        builder.append("======================================================");
        builder.append(CARRIAGE_RETURN);
        builder.append(CARRIAGE_RETURN);
        builder.append("List of possible commands:");
        builder.append(CARRIAGE_RETURN);
        builder.append(CARRIAGE_RETURN);
        builder.append("cd : [ cd /usr/local]");
        builder.append(CARRIAGE_RETURN);
        builder.append("pwd");
        builder.append(CARRIAGE_RETURN);
        builder.append("ls");
        builder.append(CARRIAGE_RETURN);
        builder.append("mkdir : [ mkdir /usr/local/tmp]");
        builder.append(CARRIAGE_RETURN);
        builder.append("exit : to quit the program");
        builder.append(CARRIAGE_RETURN);

        return builder.toString();
    }

}
