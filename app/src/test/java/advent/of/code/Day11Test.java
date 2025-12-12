package advent.of.code;

import org.junit.jupiter.api.Test;

import advent.of.code.day11.Day11;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {
    @Test
    void day11_exampleA() {
        Day11 classUnderTest = new Day11();
        assertEquals(5, classUnderTest.solvePartA("example11.txt"));
    }

    @Test
    void day11_inputA() {
        Day11 classUnderTest = new Day11();
        assertEquals(603, classUnderTest.solvePartA("day11.txt")); 
    }

    @Test
    void day11_exampleB() {
        Day11 classUnderTest = new Day11();
        assertEquals(2, classUnderTest.solvePartB("example11_2.txt"));
    }

    @Test
    void day11_inputB() {
        Day11 classUnderTest = new Day11();
        assertEquals(380961604031372l, classUnderTest.solvePartB("day11.txt"));
    }
}
