package TestsUtilite;

import Utilite.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tests {
    private Path wayToTestGrep = Paths.get("src","main", "resources", "TestGrep.txt");
    private String address = wayToTestGrep.toString();
    private Path wayToTestGrepEmpty = Paths.get("src","main", "resources", "Empty.txt");
    private String addressEmpty = wayToTestGrepEmpty.toString();
    private ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private PrintStream out = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(out);
    }

    @Test
    public void testGrepWord() {
        Main.main(new String[] { "Potter", address });
        assertEquals("Harry Potter" + System.lineSeparator() +
                "Harry Potter Potter" + System.lineSeparator(), outStream.toString());
    }

    @Test
    public void testGrepRegex() {
        Main.main(new String[] { "-r", "(t+)", address });
        assertEquals("Harry Potter" + System.lineSeparator() +
                "It's time to run!" + System.lineSeparator() +
                "Harry Potter Potter" + System.lineSeparator(), outStream.toString());
    }

    @Test
    public void testGrepInv() {
        Main.main(new String[] { "-v", "Potter", address });
        assertEquals("wasd" + System.lineSeparator() +
                "It's time to run!" + System.lineSeparator() +
                "7:00 o'clock" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "p" + System.lineSeparator(), outStream.toString());
    }

    @Test
    public void testGrepIns() {
        Main.main(new String[] { "-i", "p", address });
        assertEquals("Harry Potter" + System.lineSeparator() +
                "Harry Potter Potter" + System.lineSeparator() +
                "p" + System.lineSeparator(), outStream.toString());
    }

    @Test
    public void testGrepRegexInv() {
        Main.main(new String[] { "-v", "-r", "(t+)", address });
        assertEquals("wasd" + System.lineSeparator() +
                "7:00 o'clock" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "p" + System.lineSeparator(), outStream.toString());
    }
    @Test
    public void testGrepInvIns() {
        Main.main(new String[] { "-v", "-i", "p", address });
        assertEquals("wasd" + System.lineSeparator() +
                "It's time to run!" + System.lineSeparator() +
                "7:00 o'clock" + System.lineSeparator() +
                "" + System.lineSeparator(), outStream.toString());
    }
    @Test
    public void testGrepEmpty() {
        Main.main(new String[] { "Potter", addressEmpty });
        assertEquals("", outStream.toString());
    }
}
