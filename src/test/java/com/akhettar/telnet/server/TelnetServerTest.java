package com.akhettar.telnet.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.akhettar.telnet.Constants;
import com.akhettar.telnet.util.ServerLauncherHelper;

import static org.junit.Assert.assertEquals;

/**
 * 
 * {@link TelnetServer} Integration Test.
 * 
 * @author a.khettar
 * 
 */
public class TelnetServerTest {

    private final SocketAddress socketAddress = new InetSocketAddress("localhost", Constants.PORT_NUM);
    private Socket socket;
    private ServerLauncherHelper helper;

    /**
     * Prepare the clients
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

        // starting the server in the background
        startServerInBackground();

        // create the client
        socket = new Socket();
        socket.connect(socketAddress, 10000);

    }

    @After
    public void teardown() throws Exception {

        // create the client.
        socket.close();

        // shutdown the server
        helper.shutdown();

    }

    /**
     * Test method for {@link com.akhettar.telnet.server.TelnetServer#run()}.
     * 
     * @throws IOException
     */
    @Test
    public void testInvokeStatus() throws IOException {

        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println("status");
        final String message = in.readLine();
        assertEquals("Server running", message);

    }

    /**
     * @throws InterruptedException
     */
    private void startServerInBackground() throws InterruptedException {
        helper = new ServerLauncherHelper(new TelnetServer());
        new Thread(helper).start();
        Thread.sleep(3000);
    }

}
