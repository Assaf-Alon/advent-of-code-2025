package advent.of.code;

import org.junit.jupiter.api.Test;

import advent.of.code.day02.Day02;

import static org.junit.jupiter.api.Assertions.*;

class Day02Test {
    @Test
    void day02_exampleA() {
        Day02 classUnderTest = new Day02();
        assertEquals(1227775554l, classUnderTest.solvePartA("example02.txt"));
    }

    @Test
    void day02_inputA() {
        Day02 classUnderTest = new Day02();
        assertEquals(54641809925l, classUnderTest.solvePartA("day02.txt"));
    }

    @Test
    void day02_exampleB() {
        Day02 classUnderTest = new Day02();
        assertEquals(4174379265l, classUnderTest.solvePartB("example02.txt"));
    }

    @Test
    void day02_inputB() {
        Day02 classUnderTest = new Day02();
        // assertEquals(2404, classUnderTest.solvePartB("day02.txt"));
        assertEquals(73694270688l, classUnderTest.solvePartB("day02.txt"));
    }
}
