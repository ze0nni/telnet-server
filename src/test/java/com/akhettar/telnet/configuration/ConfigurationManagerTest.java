package com.akhettar.telnet.configuration;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.akhettar.telnet.Constants;

import static org.junit.Assert.assertEquals;

/**
 * Unit Test {@link ConfigurationManager}.
 * 
 * @author a.khettar
 * 
 */
public class ConfigurationManagerTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        System.setProperty(Constants.FORCE_LOAD, "true");
    }

    /**
     * Test method for {@link com.akhettar.telnet.configuration.ConfigurationManager#getPort()}.
     */
    @Test
    public void testGetMaxThreadFromExternalFile() throws Exception {

        setSystemProperty(Constants.CONFIG_FILE_NAME);
        assertEquals(12667, ConfigurationManager.INSTANCE.getPort());
        System.clearProperty(Constants.CONFIG_FILE_ENV_PROPERTY);
    }

    /**
     * Test method for {@link com.akhettar.telnet.configuration.ConfigurationManager#getPort()}.
     */
    @Test
    public void testGetMaxThreadFromExternalFileNotFoundShouldGetFromClassPath() throws Exception {

        setSystemProperty("ser.properties");
        assertEquals(12667, ConfigurationManager.INSTANCE.getPort());
        System.clearProperty(Constants.CONFIG_FILE_ENV_PROPERTY);
    }

    /**
     * Test method for {@link com.akhettar.telnet.configuration.ConfigurationManager#getMaxThreads()}.
     */
    @Test
    public void testGetMaxThreads() {

        assertEquals(120, ConfigurationManager.INSTANCE.getMaxThreads());

    }

    /**
     * Test method for {@link com.akhettar.telnet.configuration.ConfigurationManager#getPort()}.
     */
    @Test
    public void testGetPort() {
        assertEquals(12667, ConfigurationManager.INSTANCE.getPort());
    }

    /**
     * Set system property.
     */
    private void setSystemProperty(final String filename) {
        // mocking the external property file.
        StringBuilder path = new StringBuilder();
        path.append(System.getProperty("user.dir"));
        path.append(File.separator);
        path.append("src");
        path.append(File.separator);
        path.append("test");
        path.append(File.separator);
        path.append("resources");
        path.append(File.separator);
        path.append(filename);

        System.setProperty(Constants.CONFIG_FILE_ENV_PROPERTY, path.toString());
    }

}
