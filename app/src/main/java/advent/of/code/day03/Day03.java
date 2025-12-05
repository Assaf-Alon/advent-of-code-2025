package advent.of.code.day03;

import java.util.ArrayList;
import java.util.List;

import advent.of.code.util.InputUtils;

public class Day03 {

    public ArrayList<ArrayList<Integer>> parseInput(String path) {
        char[][] charArray = InputUtils.getContentAsMatrix(path);
        ArrayList<ArrayList<Integer>> intList = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < charArray[i].length; j++) {
                row.add(Character.getNumericValue(charArray[i][j]));
            }
            intList.add(row);
        }
        return intList;
    }

    public long duplicateNum(long num) {
        return Long.parseLong(String.valueOf(num) + String.valueOf(num));
    }

    public long solvePartA(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        ArrayList<ArrayList<Integer>> parsedNumbers = parseInput(path);
        long sol = 0;

        for (int rowIndex = 0; rowIndex < parsedNumbers.size(); rowIndex++) {
            ArrayList<Integer> row = parsedNumbers.get(rowIndex);
            int firstDigitIndex = findFirstIndexOfMaxDigit(row);
            int secondDigitIndex;
            
            if (firstDigitIndex < row.size() - 1) {
                secondDigitIndex = firstDigitIndex + 1 + findFirstIndexOfMaxDigit(row.subList(firstDigitIndex + 1, row.size()));
            } else {
                // Case: can't use the max digit because it's the last
                secondDigitIndex = firstDigitIndex;
                firstDigitIndex = findFirstIndexOfMaxDigit(row.subList(0, row.size() - 1));
            }
            int batteryValue = 10 * row.get(firstDigitIndex) + row.get(secondDigitIndex);
            System.out.println("Row " + rowIndex + " has battery: " + batteryValue);
            sol += batteryValue;
        }

        return sol;
    }

    public long solvePartB(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        ArrayList<ArrayList<Integer>> parsedNumbers = parseInput(path);
        long sol = 0;

        for (int rowIndex = 0; rowIndex < parsedNumbers.size(); rowIndex++) {
            ArrayList<Integer> row = parsedNumbers.get(rowIndex);

            long batteryValue = 0l;
            int currentIndexInRow = -1;

            for (int batteryDigitIndex = 0; batteryDigitIndex < 12; batteryDigitIndex++) {
                int digitsToLeaveAtStart = currentIndexInRow + 1;
                int digitsToLeaveAtEnd = 12 - batteryDigitIndex;
                currentIndexInRow = findLargestDigitInRange(row, digitsToLeaveAtStart, row.size() - digitsToLeaveAtEnd);
                batteryValue *= 10l;
                batteryValue += row.get(currentIndexInRow);
            }
            sol += batteryValue;
        } // 987654321111111


        return sol;
    }

    private int findFirstIndexOfMaxDigit(List<Integer> row) {
        int digitIndex = 0;
        for (int i = 1; i < row.size(); i++) {
            if (row.get(digitIndex) < row.get(i)) {
                digitIndex = i;
            }
        }
        return digitIndex;
    }

    private int findLargestDigitInRange(List<Integer> row, int rangeStartIndex, int rangeEndIndex) {
        int digitIndex = rangeStartIndex;
        for (int i = rangeStartIndex + 1; i <= rangeEndIndex; i++) {
            if (row.get(digitIndex) < row.get(i)) {
                digitIndex = i;
            }
        }
        return digitIndex;
    }

}
