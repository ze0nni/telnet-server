package com.akhettar.telnet.command;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author a.khettar
 * 
 */
public class ExitHandlerTest {

    private CommandHandler handler;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        handler = new ExitHandler();
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.ExitHandler#handle()}.
     */
    @Test
    public void testHandle() {
        assertEquals("Goodbye", handler.handle());
    }

}
