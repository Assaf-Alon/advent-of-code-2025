package advent.of.code;

import org.junit.jupiter.api.Test;

import advent.of.code.day04.Day04;

import static org.junit.jupiter.api.Assertions.*;

class Day04Test {
    @Test
    void day04_exampleA() {
        Day04 classUnderTest = new Day04();
        assertEquals(13, classUnderTest.solvePartA("example04.txt"));
    }

    @Test
    void day04_inputA() {
        Day04 classUnderTest = new Day04();
        assertEquals(1505, classUnderTest.solvePartA("day04.txt")); 
    }

    @Test
    void day04_exampleB() {
        Day04 classUnderTest = new Day04();
        assertEquals(43, classUnderTest.solvePartB("example04.txt"));
    }

    @Test
    void day04_inputB() {
        Day04 classUnderTest = new Day04();
        assertEquals(9182, classUnderTest.solvePartB("day04.txt"));
    }
}
