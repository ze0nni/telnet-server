package com.akhettar.telnet.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.akhettar.telnet.configuration.ConfigurationManager;
import com.akhettar.telnet.util.ServerLauncherHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 
 * {@link TelnetServer} Integration Test. The test suite kicks off the telnet server
 * and the client issue commands which will be asserted.
 * 
 * @author a.khettar
 * 
 */
public class TelnetServerTest {

    private final SocketAddress socketAddress = new InetSocketAddress("localhost",
            ConfigurationManager.INSTANCE.getPort());
    private Socket socket;
    private ServerLauncherHelper helper;
    private String workingDir;

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

        // reading welcome message
        assertNotNull(new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine());

        workingDir = System.getProperty("user.dir");

    }

    @After
    public void teardown() throws Exception {

        // close the client.
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
     * Test method for {@link com.akhettar.telnet.server.TelnetServer#run()}.
     * 
     * @throws IOException
     */
    @Test
    public void testInvokeUnknownCommand() throws IOException {

        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println("hello");
        final String message = in.readLine();
        assertTrue(message.contains("Unknown command [hello]"));

    }

    /**
     * Test method for {@link com.akhettar.telnet.server.TelnetServer#run()}.
     * 
     * @throws IOException
     */
    @Test
    public void testCDCommandDirectoryDoesNotExsit() throws IOException {

        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        final String randomDirName = UUID.randomUUID().toString();
        writer.println("cd /usr/" + randomDirName);
        assertEquals("cd: /usr/" + randomDirName + ": No such file or directory", in.readLine());

    }

    /**
     * Test method for {@link com.akhettar.telnet.server.TelnetServer#run()}.
     * 
     * @throws IOException
     */
    @Test
    public void testMKDIRInWorkingDirectory() throws Exception {

        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        final String randomDirName = UUID.randomUUID().toString();
        writer.println("mkdir " + randomDirName);

        assertEquals("Directory [" + randomDirName + "] has been successfully created", in.readLine());

        delete(workingDir + File.separator + randomDirName);
    }

    /**
     * Test method for {@link com.akhettar.telnet.server.TelnetServer#run()}.
     * 
     * @throws IOException
     */
    @Test
    public void testMKDIRFromRootDirNotExistShouldReturnErrorMessage() throws Exception {

        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println("mkdir /user/rubbish");

        assertEquals(
                "Failed to created the following directory: /user/rubbish. Check the path exist or you have the right permissions",
                in.readLine());

    }

    /**
     * Test method for {@link com.akhettar.telnet.server.TelnetServer#run()}.
     * 
     * @throws IOException
     */
    @Test
    public void testMKDIRFromRoot() throws Exception {

        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        final String randomDirName = UUID.randomUUID().toString();
        writer.println("mkdir " + workingDir + File.separator + randomDirName);

        assertEquals("Directory [" + workingDir + File.separator + randomDirName + "] has been successfully created",
                in.readLine());

        delete(workingDir + File.separator + randomDirName);

    }

    /**
     * Integration test for LS command.
     * 
     * @throws Exception
     */
    @Test
    public void testExitCommand() throws Exception {

        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        writer.println("exit");
        assertEquals("Goodbye...", in.readLine());

    }

    /**
     * Integration test for LS command.
     * 
     * @throws Exception
     */
    @Test
    public void testPWDFromWorkingDir() throws Exception {

        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        writer.println("pwd");
        assertEquals(workingDir, in.readLine());

    }

    /**
     * Integration test for LS command.
     * 
     * @throws Exception
     */
    @Test
    public void testPWDFromtmpDir() throws Exception {

        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println("cd target");
        assertEquals(workingDir + File.separator + "target", in.readLine());
        writer.println("pwd");
        assertEquals(workingDir + File.separator + "target", in.readLine());

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //                                                  Private Methods
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @throws InterruptedException
     */
    private void startServerInBackground() throws InterruptedException {
        helper = new ServerLauncherHelper(new TelnetServer());
        new Thread(helper).start();
        Thread.sleep(3000);
    }

    /**
     * delete file or dir for given path.
     * 
     * @param path
     * @throws Exception
     */
    private void delete(String path) throws Exception {
        new File(path).delete();
    }

}
