package com.akhettar.telnet.util;

import com.akhettar.telnet.server.TelnetServer;

/**
 * @author a.khettar
 * 
 */
public class ServerLauncherHelper implements Runnable {

    private final TelnetServer server;

    /**
     * Initialise the TelnetServer
     */
    public ServerLauncherHelper(final TelnetServer server) {
        this.server = server;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

        server.run();
    }

    /**
     * 
     */
    public void shutdown() throws Exception {
        server.shutDown();
    }

}
