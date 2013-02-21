package com.akhettar.telnet.command;

/**
 * @author a.khettar
 * 
 */
public class UnknownCommandHandler implements CommandHandler {

    private final String command;

    /**
     * @param command
     */
    public UnknownCommandHandler(final String command) {

        this.command = command;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.akhettar.telnet.command.CommandHandler#handle()
     */
    @Override
    public String handle() {

        StringBuilder builder = new StringBuilder();
        builder.append("Unknown command [" + command + "]");
        builder.append("\n");
        builder.append("Here are the list of commands you could run:");
        builder.append("\n");
        builder.append("cd [directory name]");
        builder.append("\n");
        builder.append("ls");
        builder.append("\n");
        builder.append("mkdir");
        builder.append("\n");
        builder.append("status");
        builder.append("\n");
        builder.append("pwd");
        return builder.toString();
    }

}
