package com.akhettar.telnet;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author a.khettar
 * 
 */
public class UtilTest {

    private StringBuilder expected;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        String cr = System.getProperty("os.name").matches("(W|w)indows.*") ? "\r\n" : "\n";
        expected = new StringBuilder();
        expected.append(cr);
        expected.append("======================================================");
        expected.append(cr);
        expected.append(cr);
        expected.append("   Welcome to Telnet Server: Version 1.0   ");
        expected.append(cr);
        expected.append(cr);
        expected.append("======================================================");
        expected.append(cr);
        expected.append(cr);
        expected.append("List of possible commands:");
        expected.append(cr);
        expected.append(cr);
        expected.append("cd : [ cd /usr/local]");
        expected.append(cr);
        expected.append("pwd: displays the working directory");
        expected.append(cr);
        expected.append("ls: displays list of files in the working directory");
        expected.append(cr);
        expected.append("mkdir : [ mkdir /usr/local/tmp]");
        expected.append(cr);
        expected.append("exit : quit this programme");
        expected.append(cr);
    }

    /**
     * Test method for {@link com.akhettar.telnet.Util#buildWelcomeScreen()}.
     */
    @Test
    public void testBuildWelcomeScreen() {

        assertEquals(expected.toString(), Util.buildWelcomeScreen());
    }

}
