package advent.of.code.day05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import advent.of.code.util.InputUtils;
import advent.of.code.util.InputUtils.LongPair;

public class Day05 {

    private List<LongPair> parseInput(String path) {
        var mat = InputUtils.getContentAsLines(path);
        List<LongPair> ranges = new ArrayList<>();
        for (int i = 0; i < mat.size(); i++) {
            if (mat.get(i).length() == 0) {
                return ranges;
            }

            var rangeElements = mat.get(i).split("-");
            // ranges.add(List.of(Long.parseLong(rangeElements[0]), Long.parseLong(rangeElements[1])));
            ranges.add(new LongPair(Long.parseLong(rangeElements[0]), Long.parseLong(rangeElements[1])));
        }
        return ranges;
    }


    public long solvePartA(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        var mat = InputUtils.getContentAsLines(path);
        List<List<Long>> ranges = new ArrayList<>();
        // List<Long> ingredients = new ArrayList<>();
        long sol = 0;
        boolean parsingRanges = true;
        for (int i = 0; i < mat.size(); i++) {
            var row = mat.get(i);
            if (row.length() == 0) {
                parsingRanges = false;
                continue;
            }

            if (parsingRanges) {
                var rangeElements = row.split("-");
                ranges.add(List.of(Long.parseLong(rangeElements[0]), Long.parseLong(rangeElements[1])));
                continue;
            }

            long num = Long.parseLong(row);

            for (List<Long> range: ranges) {
                var start = range.get(0);
                var end = range.get(1);
                if (start <= num && num <= end) {
                    sol += 1;
                    break;
                }
            }
        }
        return sol;
    }

    public long solvePartB(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        var input = parseInput(path);
        Collections.sort(input);

        long sol = 0;
        var prevRangeStart = input.get(0).first();
        var prevRangeEnd = input.get(0).second();

        for (int i = 0; i < input.size(); i++) {
            var start = input.get(i).first();
            var end = input.get(i).second();
            
            // New range - count the previous mega-range and open the new one
            if (prevRangeEnd < start) {
                sol += (1 + prevRangeEnd - prevRangeStart);
                prevRangeStart = start;
                prevRangeEnd = end;
                continue;
            }

            // The next range starts before the previous one ends - merge them
            prevRangeEnd = Collections.max(List.of(prevRangeEnd, end));
        }

        // Count the last range which
        sol += (1 + prevRangeEnd - prevRangeStart);

        return sol;
    }
}
