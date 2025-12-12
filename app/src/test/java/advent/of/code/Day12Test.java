package advent.of.code;

import org.junit.jupiter.api.Test;

import advent.of.code.day12.Day12;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {
    @Test
    void day12_exampleA() {
        Day12 classUnderTest = new Day12();
        assertEquals(2, classUnderTest.solvePartA("example12.txt")); // Example fails lol
    }

    @Test
    void day12_inputA() {
        Day12 classUnderTest = new Day12();
        assertEquals(437, classUnderTest.solvePartA("day12.txt")); 
    }
}
