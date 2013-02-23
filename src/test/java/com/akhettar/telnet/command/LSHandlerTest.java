package com.akhettar.telnet.command;

import java.io.File;
import java.io.FileWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * {@link LSHandler} Unit test.
 * 
 * @author a.khettar
 * 
 */
public class LSHandlerTest {

    private String workingDir;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        workingDir = System.getProperty("user.dir") + File.separator + "tmp";

        // create working directories
        assertTrue(new File(workingDir).mkdirs());
        assertTrue(new File(workingDir + File.separator + "tmp1").mkdirs());

        // add some files into the dir
        addFiles(workingDir);

    }

    /**
     * @param workingDir2
     * @throws Exception
     */
    private void addFiles(String workingDir) throws Exception {

        for (int i = 0; i < 10; i++) {
            String filename = "file" + i + ".txt";
            FileWriter writer = new FileWriter(new File(workingDir + File.separator + filename));
            writer.write("File for testing purposes");
            writer.close();
        }

    }

    @After
    public void tearDown() throws Exception {

        // delete all files
        for (String file : new File(workingDir).list()) {
            new File(workingDir + File.separator + file).delete();
        }

        // delete working dir.
        new File(workingDir).delete();
    }

    /**
     * Test method for {@link com.akhettar.telnet.command.LSHandler#handle()}.
     */
    @Test
    public void testHandle() {

        CommandHandler handler = new LSHandler("ls", workingDir);
        String response = handler.handle();
        for (int i = 0; i < 10; i++) {
            assertTrue(response.contains("f - file" + i + ".txt"));
        }

    }

}
