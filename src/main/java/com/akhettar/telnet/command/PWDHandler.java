/**
 * 
 */
package com.akhettar.telnet.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author a.khettar
 * 
 */
public class PWDHandler implements CommandHandler {

    private final String command;
    private final Logger logger = LogManager.getLogger(StatusHandler.class);

    public PWDHandler(final String command) {
        this.command = command;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.akhettar.telnet.command.CommandHandler#handle()
     */
    @Override
    public String handle() {

        logger.info("running the follwoing commnad:" + command);

        return null;

    }

}
