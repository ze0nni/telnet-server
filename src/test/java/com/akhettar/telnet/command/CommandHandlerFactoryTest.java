package com.akhettar.telnet.command;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * {@link CommandHandlerFactory} Unit test.
 * 
 * @author a.khettar
 * 
 */
public class CommandHandlerFactoryTest {

    /**
     * Test method for {@link com.akhettar.telnet.command.CommandHandlerFactory#getInstance()}.
     */
    @Test
    public void testGetInstance() {

        assertNotNull(CommandHandlerFactory.getInstance());
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.CommandHandlerFactory#getHandler(java.lang.String)}.
     */
    @Test
    public void testGetCDHandler() {

        assertTrue(CommandHandlerFactory.getInstance().getHandler("cd") instanceof CDHandler);
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.CommandHandlerFactory#getHandler(java.lang.String)}.
     */
    @Test
    public void testGetLSHandler() {

        assertTrue(CommandHandlerFactory.getInstance().getHandler("ls") instanceof LSHandler);
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.CommandHandlerFactory#getHandler(java.lang.String)}.
     */
    @Test
    public void testGetMkdirHandler() {

        assertTrue(CommandHandlerFactory.getInstance().getHandler("mkdir") instanceof MKDIRHandler);
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.CommandHandlerFactory#getHandler(java.lang.String)}.
     */
    @Test
    public void testGetPWDHandler() {

        assertTrue(CommandHandlerFactory.getInstance().getHandler("pwd") instanceof PWDHandler);
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.CommandHandlerFactory#getHandler(java.lang.String)}.
     */
    @Test
    public void testGetExitHandler() {

        assertTrue(CommandHandlerFactory.getInstance().getHandler("exit") instanceof ExitHandler);
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.CommandHandlerFactory#getHandler(java.lang.String)}.
     */
    @Test
    public void testGetStatusDHandler() {

        assertTrue(CommandHandlerFactory.getInstance().getHandler("status") instanceof StatusHandler);
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.CommandHandlerFactory#getHandler(java.lang.String)}.
     */
    @Test
    public void testGetUnknownHandler() {

        assertTrue(CommandHandlerFactory.getInstance().getHandler("hello") instanceof UnknownCommandHandler);
    }

}
