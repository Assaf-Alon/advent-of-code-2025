package advent.of.code.day04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import advent.of.code.util.InputUtils;

public class Day04 {

    private List<List<Character>> parseInput(String path) {
        return InputUtils.getContentAsMatrix2(path);
    }

    private int countNeighboringPaper(List<List<Character>> mat, int row, int col) {
        int cnt = 0;
        for (var direction : InputUtils.STAR_DIRECTIONS) {
            int dr = direction.get(0);
            int dc = direction.get(1);
            if (!InputUtils.isPositionInBoard(mat, row + dr, col + dc)) {
                continue;
            }

            if (mat.get(row + dr).get(col + dc) == '@') cnt++;
        }
        return cnt;
    }


    public long solvePartA(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        var input = parseInput(path);
        int rows = input.size();
        int cols = input.get(0).size();

        long sol = 0;


        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (input.get(row).get(col) == '@' && countNeighboringPaper(input, row, col) < 4) {
                    sol++;
                }
            }
        }

        return sol;
    }

    public long solvePartB(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        var input = parseInput(path);
        int rows = input.size();
        int cols = input.get(0).size();

        long sol = 0;

        boolean changed = true;

        while (changed) {
            changed = false;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (input.get(row).get(col) == '@' && countNeighboringPaper(input, row, col) < 4) {
                        input.get(row).set(col, '.');
                        sol++;
                        changed = true;
                    }
                }
            }
        }

        return sol;
    }
}
