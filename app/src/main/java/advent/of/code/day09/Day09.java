package advent.of.code.day09;

import java.util.List;

import advent.of.code.util.InputUtils;
import advent.of.code.util.InputUtils.LongPair;

public class Day09 {

    public long solvePartA(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        List<LongPair> pairs = InputUtils.getPairs(path);

        long bestArea = 0;

        for (int i = 0; i < pairs.size(); i++) {
            for (int j = i; j < pairs.size(); j++) {
                LongPair point1 = pairs.get(i);
                LongPair point2 = pairs.get(j);
                long area = Math.abs(1 + point1.first() - point2.first()) * Math.abs(1 + point1.second() - point2.second());
                if (area > bestArea) {
                    bestArea = area;
                }
            }
        }

        return bestArea;
    }

    public long solvePartB(String resourceFileName) {
        // String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        // List<LongPair> pairs = InputUtils.getPairs(path);

        return 0l;
    }
}
