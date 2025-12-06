package advent.of.code;

import org.junit.jupiter.api.Test;

import advent.of.code.day06.Day06;

import static org.junit.jupiter.api.Assertions.*;

class Day06Test {
    @Test
    void day06_exampleA() {
        Day06 classUnderTest = new Day06();
        assertEquals(4277556l, classUnderTest.solvePartA("example06.txt"));
    }

    @Test
    void day06_inputA() {
        Day06 classUnderTest = new Day06();
        assertEquals(6209956042374l, classUnderTest.solvePartA("day06.txt")); 
    }

    @Test
    void day06_exampleB() {
        Day06 classUnderTest = new Day06();
        assertEquals(3263827l, classUnderTest.solvePartB("example06.txt"));
    }

    @Test
    void day06_inputB() {
        Day06 classUnderTest = new Day06();
        assertEquals(12608160008022l, classUnderTest.solvePartB("day06.txt"));
    }
}
