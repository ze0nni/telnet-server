package com.akhettar.telnet.command;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Make directory handler.
 * 
 * @author a.khettar
 * 
 */
public class MKDIRHandler implements CommandHandler {

    private final String command;
    private final Logger logger = LogManager.getLogger(StatusHandler.class);
    private final String workingDir;

    public MKDIRHandler(final String command, String workingDir) {
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

        logger.info("running the follwoing commnad:" + command);
        boolean created = false;
        final String newDir = command.split(" ")[1];

        // TODO handle windows system dir
        if (newDir.startsWith(File.separator)) {
            created = new File(newDir).mkdirs();
        } else {
            created = new File(workingDir + File.separator + newDir).mkdirs();
        }
        return created ? "Directory [" + newDir + "] has been successfully created"
                : "Failed to created the following directory: " + newDir;

    }

}