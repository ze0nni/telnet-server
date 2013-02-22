package com.akhettar.telnet.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
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

        return "Directory [" + workingDir + "] has been successfully created";

    }

}
