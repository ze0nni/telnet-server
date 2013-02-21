package com.akhettar.telnet.command;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author a.khettar
 * 
 */
public class UnknownCommandHandlerTest {

    CommandHandler handler;
    StringBuilder builder;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        handler = new UnknownCommandHandler("hello");
        builder = new StringBuilder();
        builder.append("Unknown command [hello]");
        builder.append("\n");
        builder.append("Here are the list of commands you could run:");
        builder.append("\n");
        builder.append("cd [directory name]");
        builder.append("\n");
        builder.append("ls");
        builder.append("\n");
        builder.append("mkdir");
        builder.append("\n");
        builder.append("status");
        builder.append("\n");
        builder.append("pwd");
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.UnknownCommandHandler#handle()}.
     */
    @Test
    public void testHandle() {

        assertEquals(handler.handle().toString(), builder.toString());
    }

}
