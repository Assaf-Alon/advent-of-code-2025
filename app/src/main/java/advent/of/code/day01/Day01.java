package advent.of.code.day01;

import advent.of.code.util.InputUtils;

public class Day01 {
    public String getGreeting() {
        return "Hello World!";
    }

    public int parseRowDiff(char[] row) {
        int num = 0;
        int modifier = row[0] == 'L' ? -1 : 1;
        for (int i = 1; i < row.length; i++) {
            int digit = row[i];
            num *= 10;
            num += Character.getNumericValue(digit);
        }
        return num * modifier;
    }

    public int solvePartA(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;

        char[][] m = InputUtils.getContentAsMatrix(path);
        int count = 0;
        int currentNum = 50;
        int rows = m.length;
        for (int r = 0; r < rows; r++) {
            int diff = parseRowDiff(m[r]);
            currentNum += diff;
            currentNum %= 100;
            if (currentNum == 0) count++;
        }

        return count;
    }

    public int solvePartB(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;

        char[][] m = InputUtils.getContentAsMatrix(path);
        int count = 0;
        int currentNum = 50;
        int rows = m.length;
        for (int r = 0; r < rows; r++) {
            int diff = parseRowDiff(m[r]);
            if (Math.abs(diff) > 500) {
                System.out.println(diff);
            }
            if (diff > 0) {
                count += (currentNum + diff) / 100;
            }
            else if (currentNum + diff <= 0) {
                int additionalCircle = currentNum > 0 ? 1 : 0; // As long as we weren't at 0 beforehand, we encountered 0 once when going negative for the first time
                count += additionalCircle + Math.abs(currentNum + diff) / 100;
            }

            currentNum = Math.floorMod(currentNum + diff, 100);
        }

        return count;
    }

}
