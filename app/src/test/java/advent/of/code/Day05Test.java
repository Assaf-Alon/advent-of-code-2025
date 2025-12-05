package advent.of.code;

import org.junit.jupiter.api.Test;

import advent.of.code.day05.Day05;

import static org.junit.jupiter.api.Assertions.*;

class Day05Test {
    @Test
    void day05_exampleA() {
        Day05 classUnderTest = new Day05();
        assertEquals(3, classUnderTest.solvePartA("example05.txt"));
    }

    @Test
    void day05_inputA() {
        Day05 classUnderTest = new Day05();
        assertEquals(720, classUnderTest.solvePartA("day05.txt")); 
    }

    @Test
    void day05_exampleB() {
        Day05 classUnderTest = new Day05();
        assertEquals(14, classUnderTest.solvePartB("example05.txt"));
    }

    @Test
    void day05_inputB() {
        Day05 classUnderTest = new Day05();
        assertEquals(357608232770687l, classUnderTest.solvePartB("day05.txt"));
    }
}
