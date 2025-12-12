package advent.of.code.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import advent.of.code.util.InputUtils;
import advent.of.code.util.InputUtils.LongPair;

public class Day12 {
   
    private record ParsedInput(List<List<String>> shapes, List<LongPair> boardSizes, List<List<Integer>> shapeCounts) {}

    private ParsedInput getParsedInput(String path) {
        var mat = InputUtils.getContentAsLines(path);
        List<List<String>> shapes = new ArrayList<>();
        List<LongPair> boardSizes = new ArrayList<>();
        List<List<Integer>> shapeCounts = new ArrayList<>();
        for (int i = 0; i < mat.size(); i++) {
            if (mat.get(i).matches("^\\d:$")) {
                List<String> shape = new ArrayList<>();
                shape.add(mat.get(++i));
                shape.add(mat.get(++i));
                shape.add(mat.get(++i));
                i++; // Skip empty line
                shapes.add(shape);
                continue;
            }

            String line = mat.get(i);
            long boardHeight = Long.parseLong(line.split("x")[0]);
            long boardWidth = Long.parseLong(line.split("x")[1].split(":")[0]);
            boardSizes.add(new LongPair(boardHeight, boardWidth));

            List<Integer> shapeCountsCurrBoard = new ArrayList<>();
            for (String numAsString: line.split(":")[1].split(" ")) {
                if (numAsString == "") continue;
                shapeCountsCurrBoard.add(Integer.parseInt(numAsString));
            }
            shapeCounts.add(shapeCountsCurrBoard);
        }
        return new ParsedInput(shapes, boardSizes, shapeCounts);
    }

    private long getShapeSize(List<String> shape) {
        return shape.stream().flatMapToInt(String::chars).filter(c -> c == '#').count();
    }


    public long solvePartA(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        var parsedInput = getParsedInput(path);
        long sol = 0l;

        for (int boardIdx = 0; boardIdx < parsedInput.boardSizes.size(); boardIdx++) {
            long totalSlotsRequired = 0l;
            for (int shapeIdx = 0; shapeIdx < parsedInput.shapes.size(); shapeIdx++) {
                totalSlotsRequired += getShapeSize(parsedInput.shapes.get(shapeIdx)) * parsedInput.shapeCounts.get(boardIdx).get(shapeIdx);
            }
            if (totalSlotsRequired <= parsedInput.boardSizes.get(boardIdx).getMultiply()) {
                sol++;
            }
        }
        return sol;
    }
}
