package advent.of.code.day02;

import java.util.ArrayList;

import advent.of.code.util.InputUtils;

public class Day02 {

    public ArrayList<Long> parseInput(String path) {
        var l = new ArrayList<Long>();
        String[] splittedRow = InputUtils.getContentAsString(path).split(",");
        for (int i = 0; i < splittedRow.length; i++) {
            long first = Long.parseLong(splittedRow[i].split("-")[0]);
            long second = Long.parseLong(splittedRow[i].split("-")[1]);
            l.add(first);
            l.add(second);
        }
        return l;
    }

    public long duplicateNum(long num) {
        return Long.parseLong(String.valueOf(num) + String.valueOf(num));
    }

    public long solvePartA(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        ArrayList<Long> parsedNumbers = parseInput(path);
        long count = 0;

        for (int i = 0; i < parsedNumbers.size(); i += 2) {
            long first = parsedNumbers.get(i);
            long second = parsedNumbers.get(i+1);

            String firstAsString = String.valueOf(first);

            long leftmostPartOfNum = Long.parseLong(first > 9 ? firstAsString.substring(0, firstAsString.length() / 2) : firstAsString);
            long invalidCandidate = duplicateNum(leftmostPartOfNum);
            while (invalidCandidate <= second) {
                if (first <= invalidCandidate && invalidCandidate <= second) {
                    count += invalidCandidate;
                }
                leftmostPartOfNum++;
                invalidCandidate = duplicateNum(leftmostPartOfNum);
            }
        }

        return count;
    }

    public long solvePartB(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        ArrayList<Long> parsedNumbers = parseInput(path);
        long count = 0;

        for (int i = 0; i < parsedNumbers.size(); i += 2) {
            long first = parsedNumbers.get(i);
            long second = parsedNumbers.get(i+1);


            for (long candidate = first; candidate <= second; candidate++) {
                if (isIllegalNumberPartB(candidate)) {
                    count += candidate;
                }
            }
        }

        return count;
    }

    private boolean isIllegalNumberPartB(long num) {
        String numAsString = String.valueOf(num);
        for (int repeats = 2; repeats <= numAsString.length(); repeats++) {
            String prefix = numAsString.substring(0, numAsString.length() / repeats);
            if (prefix.repeat(repeats).equals(numAsString)) {
                return true;
            }
        }
        return false;
    }

}
