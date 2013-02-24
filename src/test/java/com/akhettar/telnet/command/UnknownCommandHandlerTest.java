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
        String cr = System.getProperty("os.name").matches("(W|w)indows.*") ? "\r" : "\n";
        handler = new UnknownCommandHandler("hello");
        builder = new StringBuilder();
        builder.append("Unknown command [hello]");
        builder.append(cr);
        builder.append("Here are the list of commands you could run:");
        builder.append(cr);
        builder.append("cd [directory name]");
        builder.append(cr);
        builder.append("ls");
        builder.append(cr);
        builder.append("mkdir");
        builder.append(cr);
        builder.append("status");
        builder.append(cr);
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
