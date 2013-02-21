package com.akhettar.telnet.command;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * cd command handler
 * 
 * @author a.khettar
 * 
 */
public class CDHandler implements CommandHandler {

    private final String command;
    private final Logger logger = LogManager.getLogger(StatusHandler.class);
    private final String workingDir;

    public CDHandler(final String command, String workingDir) {
        this.command = command;
        this.workingDir = workingDir;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.akhettar.telnet.command.CommandHandler#handle()
     */
    @Override
    public String handle() {

        logger.info("Running the follwing command: " + command);
        String dirpath = command.split(" ")[1];
        StringBuilder builder = new StringBuilder();
        if (workingDir == null) {
            builder.append(dirpath);
            builder.append(File.separator);
            builder.append(workingDir);
        } else {
            builder.append(workingDir);
            builder.append(File.separator);
            builder.append(dirpath);
        }
        return dirpath;

    }

}
