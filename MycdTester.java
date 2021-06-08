import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;


public class MycdTester {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMycd1() {
        String[] args = {"/", "abc"};
        Mycd.main(args);
        assertEquals("/abc\n", outContent.toString());

    }

    @Test
    public void testMycd2() {
        String[] args = {"/abc/def", "ghi"};
        Mycd.main(args);
        assertEquals("/abc/def/ghi\n", outContent.toString());

    }

    @Test
    public void testMycd3() {
        String[] args = {"/abc/def", ".."};
        Mycd.main(args);
        assertEquals("/abc\n", outContent.toString());

    }

    @Test
    public void testMycd4() {
        String[] args = {"/abc/def", "/abc"};
        Mycd.main(args);
        assertEquals("/abc\n", outContent.toString());

    }

    @Test
    public void testMycd5() {
        String[] args = {"/abc/def", "/abc/klm"};
        Mycd.main(args);
        assertEquals("/abc/klm\n", outContent.toString());

    }

    @Test
    public void testMycd6() {
        String[] args = {"/abc/def", "../.."};
        Mycd.main(args);
        assertEquals("/\n", outContent.toString());

    }

    @Test
    public void testMycd7() {
        String[] args = {"/abc/def", "../../.."};
        Mycd.main(args);
        assertEquals("/\n", outContent.toString());

    }

    @Test
    public void testMycd8() {
        String[] args = {"/abc/def", "."};
        Mycd.main(args);
        assertEquals("/abc/def\n", outContent.toString());

    }

    @Test
    public void testMycd9() {
        String[] args = {"/abc/def", "..klm"};
        Mycd.main(args);
        assertEquals("..klm: No such file or directory\n", outContent.toString());

    }

    @Test
    public void testMycd10() {
        String[] args = {"/abc/def", "///////"};
        Mycd.main(args);
        assertEquals("/\n", outContent.toString());

    }

    @Test
    public void testMycd11() {
        String[] args = {"/abc/def", "......"};
        Mycd.main(args);
        assertEquals("......: No such file or directory\n", outContent.toString());

    }

    @Test
    public void testMycd12() {
        String[] args = {"/abc/def", "../gh///../klm/."};
        Mycd.main(args);
        assertEquals("/abc/klm\n", outContent.toString());

    }

}
