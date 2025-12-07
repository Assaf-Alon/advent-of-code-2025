package advent.of.code.day07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import advent.of.code.util.InputUtils;

public class Day07 {

    public long solvePartA(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        long sol = 0;
        char[][] mat = InputUtils.getContentAsMatrix(path);

        for (int i = 0; i < mat.length - 1; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] != 'S') continue;

                // BEAM!!
                
                if (mat[i + 1][j] != '^') {
                    mat[i + 1][j] = 'S';
                }
                else {
                    sol++;
                    if (j > 0) {
                        mat[i + 1][j - 1] = 'S';
                    }
                    if (j < mat[i].length - 1) {
                        mat[i + 1][j + 1] = 'S';
                    }
                }
            }
        }

        return sol;
    }

    public long solvePartB(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        char[][] mat = InputUtils.getContentAsMatrix(path);

        List<Long> curr = new ArrayList<>();
        List<Long> next = new ArrayList<>();
        // List<Long> next = new ArrayList<>(Collections.nCopies(mat[0].length, 0l));

        for (int i = 0; i < mat[0].length; i++) {
            curr.add(mat[0][i] == 'S' ? 1l : 0l);
            next.add(0l);
        }

        for (int i = 0; i < mat.length - 1; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                long currVal = curr.get(j);
                if (currVal == 0) continue;

                // BEAM!!
                
                if (mat[i + 1][j] != '^') {
                    next.set(j, next.get(j) + currVal);
                }
                else {
                    if (j > 0) {
                        next.set(j - 1, next.get(j - 1) + currVal);
                    }
                    if (j < mat[i].length - 1) {
                        next.set(j + 1, next.get(j + 1) + currVal);
                    }
                }
            }
            curr = next;
            next = new ArrayList<>(Collections.nCopies(mat[0].length, 0l));
            var sumSoFar = curr.stream().mapToLong(Long::longValue).sum();
            System.out.println("Sum so far: " + sumSoFar);
        }

        // for (int j = 0; j < mat[mat.length - 1].length; j++) {
        //     sol += 
        // }

        return curr.stream().mapToLong(Long::longValue).sum();
    }
}
