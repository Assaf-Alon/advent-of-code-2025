package advent.of.code;

import org.junit.jupiter.api.Test;

import advent.of.code.day08.Day08;

import static org.junit.jupiter.api.Assertions.*;

class Day08Test {
    @Test
    void day08_exampleA() {
        Day08 classUnderTest = new Day08();
        assertEquals(40, classUnderTest.solvePartA("example08.txt"));
    }

    @Test
    void day08_inputA() {
        Day08 classUnderTest = new Day08();
        assertEquals(54600, classUnderTest.solvePartA("day08.txt")); 
    }

    @Test
    void day08_exampleB() {
        Day08 classUnderTest = new Day08();
        assertEquals(25272, classUnderTest.solvePartB("example08.txt"));
    }

    @Test
    void day08_inputB() {
        Day08 classUnderTest = new Day08();
        assertEquals(107256172, classUnderTest.solvePartB("day08.txt"));
    }
}
