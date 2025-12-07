package advent.of.code;

import org.junit.jupiter.api.Test;

import advent.of.code.day07.Day07;

import static org.junit.jupiter.api.Assertions.*;

class Day07Test {
    @Test
    void day07_exampleA() {
        Day07 classUnderTest = new Day07();
        assertEquals(21, classUnderTest.solvePartA("example07.txt"));
    }

    @Test
    void day07_inputA() {
        Day07 classUnderTest = new Day07();
        assertEquals(1566, classUnderTest.solvePartA("day07.txt")); 
    }

    @Test
    void day07_exampleB() {
        Day07 classUnderTest = new Day07();
        assertEquals(40, classUnderTest.solvePartB("example07.txt"));
    }

    @Test
    void day07_inputB() {
        Day07 classUnderTest = new Day07();
        assertEquals(5921061943075l, classUnderTest.solvePartB("day07.txt"));
    }
}
