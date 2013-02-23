package com.akhettar.telnet.command;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void testHandleSecondCDCommand() {

        assert new File(workingDir + File.separator + "tmp").mkdirs();
        CommandHandler handler = new CDHandler("cd " + "tmp", workingDir);
        assertEquals(workingDir + File.separator + "tmp", handler.handle());
    }

}
