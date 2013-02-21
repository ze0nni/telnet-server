package com.akhettar.telnet.command;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author a.khettar
 * 
 */
public class StatusHandlerTest {

    private CommandHandler handler;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        handler = new StatusHandler("status");
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.StatusHandler#handle()}.
     */
    @Test
    public void testHandle() {

        assertEquals("Server running", handler.handle());
    }

}
