package com.akhettar.telnet.command;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * {@link CDHandler} Unit test.
 * 
 * @author a.khettar
 * 
 */
public class CDHandlerTest {

    private final Logger logger = LogManager.getLogger(CDHandlerTest.class);
    /**
     * Holds a path to an existing directory derived from the server.properties which is stored in class path
     * to allow these tests to be run in any machine successfully.
     */
    private String workingDir;

    @Before
    public void setUp() throws Exception {

        workingDir = System.getProperty("user.dir");

        // create two tmp dir
        boolean created = new File(workingDir + File.separator + "tmp1").mkdirs();
        assert created;
        assertTrue(new File(workingDir + File.separator + "tmp2").mkdirs());

    }

    @After
    public void tearDown() throws Exception {
        new File(workingDir + File.separator + "tmp1").delete();
        new File(workingDir + File.separator + "tmp2").delete();

    }

    /**
     * Test method for {@link com.akhettar.telnet.command.CDHandler#handle()}.
     */
    @Test
    public void testHandleFirstCDCommand() {

        CommandHandler handler = new CDHandler("cd " + workingDir, null);
        assertEquals(workingDir, handler.handle());
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.CDHandler#handle()}.
     */
    @Test
    public void testHandleSubsequentCDCommand() {

        CommandHandler handler = new CDHandler("cd " + "tmp1", workingDir);
        assertEquals(workingDir + File.separator + "tmp1", handler.handle());

    }

    /**
     * Test method for {@link com.akhettar.telnet.command.CDHandler#handle()}.
     */
    @Test
    public void testHandleCDCommandRootDir() {

        CommandHandler handler = new CDHandler("cd " + "tmp1", workingDir);
        assertEquals(workingDir + File.separator + "tmp1", handler.handle());

        // cd from root dir
        handler = new CDHandler("cd " + workingDir + File.separator + "tmp2", workingDir);
        assertEquals(workingDir + File.separator + "tmp2", handler.handle());

    }

}
