package advent.of.code.day06;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import advent.of.code.util.InputUtils;

public class Day06 {

    public long solvePartA(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        long sol = 0;
        List<String> mat = InputUtils.getContentAsLines(path);
        List<String> numsAsStrings = mat.subList(0, mat.size() - 1);
        List<List<Long>> nums = new ArrayList<>();
        List<String> operations =
            new ArrayList<>(List.of(mat.getLast().split("\s+")))
            .stream()
            .filter(t -> !t.equals(""))
            .collect(Collectors.toList());

        for (int i = 0; i < numsAsStrings.size(); i++) {
            String[] currNumsAsStrings = numsAsStrings.get(i).split("\s+");
            List<Long> currNums = new ArrayList<>();
            for (int j = 0; j < currNumsAsStrings.length; j++) {
                if (!currNumsAsStrings[j].equals("")) {
                    currNums.add(Long.parseLong(currNumsAsStrings[j]));
                }
            }
            nums.add(currNums);
        }

        for (int col = 0; col < nums.get(0).size(); col++) {
            long colVal = operations.get(col).equals("+") ? 0 : 1; // operation identity for addition is 0, for multiplication is 1
            for (int row = 0; row < nums.size(); row++) {
                long currVal = nums.get(row).get(col);
                if (operations.get(col).equals("+")) {
                    colVal += currVal;
                } else {
                    colVal *= currVal;
                }
            }
            sol += colVal;
        }

        return sol;
    }

    public long solvePartB(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        long sol = 0;
        
        List<String> originalMat = InputUtils.getContentAsLines(path);
        List<String> regularNumsAsStrings = originalMat.subList(0, originalMat.size() - 1);
        List<String> numsAsStrings = InputUtils.transposeMatrixAsLines(regularNumsAsStrings);
        List<List<Long>> nums = new ArrayList<>();
        List<String> operations =
            new ArrayList<>(List.of(originalMat.getLast().split("\s+")))
            .stream()
            .filter(t -> !t.equals(""))
            .collect(Collectors.toList());

        for (int i = 0; i < numsAsStrings.size(); i++) {
            List<Long> currNums = new ArrayList<>();
            while (i < numsAsStrings.size() && !numsAsStrings.get(i).isBlank()) {
                currNums.add(Long.parseLong(numsAsStrings.get(i).strip()));
                i++;
            }
            nums.add(currNums);
        }

        for (int row = 0; row < nums.size(); row++) {
            long rowVal = operations.get(row).equals("+") ? 0 : 1; // operation identity for addition is 0, for multiplication is 1
            for (int col = 0; col < nums.get(row).size(); col++) {
                long currVal = nums.get(row).get(col);
                if (operations.get(row).equals("+")) {
                    rowVal += currVal;
                } else {
                    rowVal *= currVal;
                }
            }
            sol += rowVal;
        }

        return sol;
    }
}
