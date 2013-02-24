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

        StringBuilder newpath = new StringBuilder();

        // navigating from working dir or an absolute path
        if (dirpath.startsWith(File.separator) || dirpath.matches("(C|c):.*")) {
            return checkDirExist(dirpath);
        } else {
            newpath.append(workingDir);
            newpath.append(File.separator);
            newpath.append(dirpath);
        }
        return checkDirExist(newpath.toString());

    }

    /**
     * Checks if the working dir exist otherwise returns the approviate
     * message.
     * 
     * @param newpath
     * @return
     */
    private String checkDirExist(String newpath) {

        File file = new File(newpath.toString().endsWith(File.separator) ? newpath.toString() : newpath.toString()
                + File.separator);

        return file.isDirectory() ? newpath : "cd: " + newpath + ": No such file or directory";
    }

}
