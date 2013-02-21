package com.akhettar.telnet.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * ls command handler
 * 
 * @author a.khettar
 * 
 */
public class LSHandler implements CommandHandler {

    private final String command;
    private final Logger logger = LogManager.getLogger(StatusHandler.class);
    private final String workingDir;

    public LSHandler(final String command, String workingDir) {
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

        return workingDir;

    }

}
