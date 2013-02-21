package com.akhettar.telnet.command;

/**
 * Factory generating command handlers
 * 
 * @author a.khettar
 * 
 */
public class CommandHandlerFactory {

    private static CommandHandlerFactory factory;

    /**
     * Factory method.
     * 
     * @return
     */
    public static CommandHandlerFactory getInstance() {
        if (factory != null) {
            return factory;
        }
        return new CommandHandlerFactory();
    }

    /**
     * Get handler for given command.
     * 
     * @param command
     * @return
     */
    public CommandHandler getHandler(String command) {
        if (command.matches("^cd.*")) {
            return new CDHandler(command);
        } else if (command.matches("^ls.*")) {
            return new LSHandler(command);
        } else if (command.matches("^pwd.*")) {
            return new PWDHandler(command);
        } else if (command.matches("^mkdir.*")) {
            return new MKDIRHandler(command);
        } else if (command.equalsIgnoreCase("status")) {
            return new StatusHandler(command);
        } else if (command.equalsIgnoreCase("exit")) {
            return new ExitHandler();
        } else {
            return new UnknownCommandHandler(command);
        }

    }

}
