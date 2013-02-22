package com.akhettar.telnet.command;

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
    private String dirpath;

    @Before
    public void setUp() {
        final String path = ClassLoader.getSystemResource("server.properties").getPath();
        dirpath = path.substring(0, path.indexOf("server.properties"));

    }

    /**
     * Test method for {@link com.akhettar.telnet.command.CDHandler#handle()}.
     */
    @Test
    public void testHandleFirstCDCommand() {

        logger.info("****** " + dirpath + "******");

        CommandHandler handler = new CDHandler("cd " + dirpath, null);
        assertEquals(dirpath, handler.handle());
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.CDHandler#handle()}.
     */
    @Test
    public void testHandleSecondCDCommand() {

        final String workingDir = dirpath.substring(0, dirpath.indexOf("/target/"));
        logger.info("****** " + workingDir + "******");
        CommandHandler handler = new CDHandler("cd target/test-classes/", workingDir);
        assertEquals(dirpath, handler.handle());
    }

}
