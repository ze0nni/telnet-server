package com.akhettar.telnet.command;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author a.khettar
 * 
 */
public class PWDHandlerTest {

    private CommandHandler handler;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        handler = new PWDHandler("pwd", "/usr/local/jetty");
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.PWDHandler#handle()}.
     */
    @Test
    public void testHandle() {

        assertEquals("/usr/local/jetty", handler.handle());
    }

}
