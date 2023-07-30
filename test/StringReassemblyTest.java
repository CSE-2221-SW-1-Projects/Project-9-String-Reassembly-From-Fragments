import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * @author Zheyuan Gao
 *
 */

public class StringReassemblyTest {

    /*
     * tests for combination
     */
    @Test
    public void testCombination_abc() {
        String str1 = "ab";
        String str2 = "bc";
        String combination = StringReassembly.combination(str1, str2, 1);
        assertEquals("abc", combination);
    }

    @Test
    public void testCombination_abcde() {
        String str1 = "abcd";
        String str2 = "bcde";
        String combination = StringReassembly.combination(str1, str2, 3);
        assertEquals("abcde", combination);
    }

    @Test
    public void testCombination_12345677654321() {
        String str1 = "123456776";
        String str2 = "677654321";
        String combination = StringReassembly.combination(str1, str2, 4);
        assertEquals("12345677654321", combination);
    }

    @Test
    public void testCombination_Atago() {
        String str1 = "Atag";
        String str2 = "tago";
        String combination = StringReassembly.combination(str1, str2, 3);
        assertEquals("Atago", combination);
    }

    @Test
    public void testCombination_combination() {
        String str1 = "combina";
        String str2 = "bination";
        String combination = StringReassembly.combination(str1, str2, 4);
        assertEquals("combination", combination);
    }

    @Test
    public void testCombination_NoCombination() {
        String str1 = "No";
        String str2 = "Combination";
        String combination = StringReassembly.combination(str1, str2, 0);
        assertEquals("NoCombination", combination);
    }

    /*
     * tests for addToSetAvoidingSubstrings
     */
    @Test
    public void testAddToSetAvoidingSubstrings_basecaseIsSub() {
        Set<String> s1 = new Set1L<>();
        s1.add("hello");
        String str = "ello";
        StringReassembly.addToSetAvoidingSubstrings(s1, str);
        Set<String> expectS1 = new Set1L<>();
        expectS1.add("hello");
        assertEquals(expectS1, s1);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_basecaseIsNotSub() {
        Set<String> s1 = new Set1L<>();
        s1.add("hello");
        String str = "friend";
        StringReassembly.addToSetAvoidingSubstrings(s1, str);
        Set<String> expectS1 = new Set1L<>();
        expectS1.add("hello");
        expectS1.add("friend");
        assertEquals(expectS1, s1);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_replaceSetElement() {
        Set<String> s1 = new Set1L<>();
        s1.add("ll");
        String str = "Hello";
        StringReassembly.addToSetAvoidingSubstrings(s1, str);
        Set<String> expectS1 = new Set1L<>();
        expectS1.add("Hello");
        assertEquals(expectS1, s1);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_MoreElementIsSub() {
        Set<String> s1 = new Set1L<>();
        s1.add("hello");
        s1.add("friend");
        s1.add("good");
        s1.add("morning");
        String str = "ning";
        StringReassembly.addToSetAvoidingSubstrings(s1, str);
        Set<String> expectS1 = new Set1L<>();
        expectS1.add("hello");
        expectS1.add("friend");
        expectS1.add("good");
        expectS1.add("morning");
        assertEquals(expectS1, s1);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_MoreElementIsNotSub() {
        Set<String> s1 = new Set1L<>();
        s1.add("hello");
        s1.add("friend");
        s1.add("good");
        s1.add("morning");
        String str = "a";
        StringReassembly.addToSetAvoidingSubstrings(s1, str);
        Set<String> expectS1 = new Set1L<>();
        expectS1.add("hello");
        expectS1.add("friend");
        expectS1.add("good");
        expectS1.add("morning");
        expectS1.add("a");
        assertEquals(expectS1, s1);
    }

    /*
     * test for linesFromInput
     */
    @Test
    public void testLinesFromInput_basecaseSubFirst() {
        SimpleReader input = new SimpleReader1L("test/file1");
        Set<String> setStr = StringReassembly.linesFromInput(input);
        Set<String> expectSetStr = new Set1L<>();
        expectSetStr.add("hello");
        assertEquals(expectSetStr, setStr);
    }

    @Test
    public void testLinesFromInput_basecaseSubLast() {
        SimpleReader input = new SimpleReader1L("test/file2");
        Set<String> setStr = StringReassembly.linesFromInput(input);
        Set<String> expectSetStr = new Set1L<>();
        expectSetStr.add("hello");
        assertEquals(expectSetStr, setStr);
    }

    @Test
    public void testLinesFromInput_basecaseNoSub() {
        SimpleReader input = new SimpleReader1L("test/file3");
        Set<String> setStr = StringReassembly.linesFromInput(input);
        Set<String> expectSetStr = new Set1L<>();
        expectSetStr.add("friend");
        expectSetStr.add("hello");
        assertEquals(expectSetStr, setStr);
    }

    @Test
    public void testLinesFromInput_repeatElementsSub() {
        SimpleReader input = new SimpleReader1L("test/file4");
        Set<String> setStr = StringReassembly.linesFromInput(input);
        Set<String> expectSetStr = new Set1L<>();
        expectSetStr.add("friend");
        assertEquals(expectSetStr, setStr);
    }

    @Test
    public void testLinesFromInput_mixedTest() {
        SimpleReader input = new SimpleReader1L("test/file5");
        Set<String> setStr = StringReassembly.linesFromInput(input);
        Set<String> expectSetStr = new Set1L<>();
        expectSetStr.add("hello");
        expectSetStr.add("friend");
        expectSetStr.add("good");
        expectSetStr.add("morning");
        assertEquals(expectSetStr, setStr);
    }

    /*
     * tests for printWithLineSeparators
     */
    @Test
    public void testPrintWithLineSeparators_ab() {
        SimpleWriter output = new SimpleWriter1L("test/test1");
        String str = "a~b";
        StringReassembly.printWithLineSeparators(str, output);
        SimpleReader in = new SimpleReader1L("test/test1");
        SimpleReader in1 = new SimpleReader1L("test/expectTest1");
        while (!in.atEOS()) {
            String str1 = in.nextLine();
            String expectStr = in1.nextLine();
            assertEquals(str1, expectStr);
        }
    }

    @Test
    public void testPrintWithLineSeparators_abc() {
        SimpleWriter output = new SimpleWriter1L("test/test2");
        String str = "a~b~c";
        StringReassembly.printWithLineSeparators(str, output);
        SimpleReader in = new SimpleReader1L("test/test2");
        SimpleReader in1 = new SimpleReader1L("test/expectTest2");
        while (!in.atEOS()) {
            String str1 = in.nextLine();
            String expectStr = in1.nextLine();
            assertEquals(str1, expectStr);
        }
    }

    @Test
    public void testPrintWithLineSeparators_abcde() {
        SimpleWriter output = new SimpleWriter1L("test/test3");
        String str = "~a~b~c~~~de";
        StringReassembly.printWithLineSeparators(str, output);
        SimpleReader in = new SimpleReader1L("test/test3");
        SimpleReader in1 = new SimpleReader1L("test/expectTest3");
        while (!in.atEOS()) {
            String str1 = in.nextLine();
            String expectStr = in1.nextLine();
            assertEquals(str1, expectStr);
        }
    }

    @Test
    public void testPrintWithLineSeparators_helloworld() {
        SimpleWriter output = new SimpleWriter1L("test/test4");
        String str = "hello~world";
        StringReassembly.printWithLineSeparators(str, output);
        SimpleReader in = new SimpleReader1L("test/test4");
        SimpleReader in1 = new SimpleReader1L("test/expectTest4");
        while (!in.atEOS()) {
            String str1 = in.nextLine();
            String expectStr = in1.nextLine();
            assertEquals(str1, expectStr);
        }
    }

    @Test
    public void testPrintWithLineSeparators_mixedLong() {
        SimpleWriter output = new SimpleWriter1L("test/test5");
        String str = "Software Components Course Sequence Home Page~Official "
                + "Syllabus~Course Policies~Detailed Schedule (with links to "
                + "readings, assignments, slides, etc.)~Piazza (for discussions)"
                + "~Carmen (for project assignment submissions and grades)";
        StringReassembly.printWithLineSeparators(str, output);
        SimpleReader in = new SimpleReader1L("test/test5");
        SimpleReader in1 = new SimpleReader1L("test/expectTest5");
        while (!in.atEOS()) {
            String str1 = in.nextLine();
            String expectStr = in1.nextLine();
            assertEquals(str1, expectStr);
        }
    }

}
