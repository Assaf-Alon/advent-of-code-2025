package advent.of.code;

import org.junit.jupiter.api.Test;

import advent.of.code.day09.Day09;

import static org.junit.jupiter.api.Assertions.*;

class Day09Test {
    @Test
    void day09_exampleA() {
        Day09 classUnderTest = new Day09();
        assertEquals(50, classUnderTest.solvePartA("example09.txt"));
    }

    @Test
    void day09_inputA() {
        Day09 classUnderTest = new Day09();
        assertEquals(4759420470l, classUnderTest.solvePartA("day09.txt")); 
    }

    @Test
    void day09_exampleB() {
        Day09 classUnderTest = new Day09();
        assertEquals(25272, classUnderTest.solvePartB("example09.txt"));
    }

    @Test
    void day09_inputB() {
        Day09 classUnderTest = new Day09();
        assertEquals(107256172, classUnderTest.solvePartB("day09.txt"));
    }
}
