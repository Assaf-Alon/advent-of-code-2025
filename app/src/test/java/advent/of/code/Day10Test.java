package advent.of.code;

import org.junit.jupiter.api.Test;

import advent.of.code.day10.Day10;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {
    @Test
    void day10_exampleA() {
        Day10 classUnderTest = new Day10();
        assertEquals(7, classUnderTest.solvePartA("example10.txt"));
    }

    @Test
    void day10_inputA() {
        Day10 classUnderTest = new Day10();
        assertEquals(401, classUnderTest.solvePartA("day10.txt")); 
    }

    @Test
    void day10_exampleB() {
        Day10 classUnderTest = new Day10();
        assertEquals(33, classUnderTest.solvePartB("example10.txt"));
    }

    @Test
    void day10_inputB() {
        Day10 classUnderTest = new Day10();
        assertEquals(107256172, classUnderTest.solvePartB("day10.txt"));
    }
}
