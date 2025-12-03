package advent.of.code;

import org.junit.jupiter.api.Test;

import advent.of.code.day03.Day03;

import static org.junit.jupiter.api.Assertions.*;

class Day03Test {
    @Test
    void day03_exampleA() {
        Day03 classUnderTest = new Day03();
        assertEquals(357l, classUnderTest.solvePartA("example03.txt"));
    }

    @Test
    void day03_inputA() {
        Day03 classUnderTest = new Day03();
        assertEquals(17263l, classUnderTest.solvePartA("day03.txt")); 
    }

    @Test
    void day03_exampleB() {
        Day03 classUnderTest = new Day03();
        assertEquals(3121910778619l, classUnderTest.solvePartB("example03.txt"));
    }

    @Test
    void day03_inputB() {
        Day03 classUnderTest = new Day03();
        assertEquals(170731717900423l, classUnderTest.solvePartB("day03.txt"));
    }
}
