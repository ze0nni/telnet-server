package com.akhettar.telnet.command;

import java.io.File;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * {@link MKDIRHandler} Unit test.
 * 
 * @author a.khettar
 * 
 */
public class MKDIRHandlerTest {

    private String workingDir;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        workingDir = System.getProperty("user.home");
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.MKDIRHandler#handle()}.
     */
    @Test
    public void testHandleInCurrentWorkingDir() {

        String newDir = UUID.randomUUID().toString();
        CommandHandler handler = new MKDIRHandler("mkdir " + newDir, workingDir);

        assertEquals("Directory [" + newDir + "] has been successfully created", handler.handle());
        assertTrue(new File(workingDir + File.separator + newDir).exists());

        new File(workingDir + File.separator + newDir).delete();

    }

    /**
     * Test method for {@link com.akhettar.telnet.command.MKDIRHandler#handle()}.
     */
    @Test
    public void testHandleMkdirFromRootDir() {

    }

}
