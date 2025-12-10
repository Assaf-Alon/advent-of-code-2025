package advent.of.code.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import advent.of.code.util.InputUtils;
import advent.of.code.util.InputUtils.LongPair;

public class Day10 {
    // Approach: XOR things. Treat the target as a list of 0/1, and the buttons as values I can XOR with
    // Example row:  [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
    // Initial state:            0000
    // target:        .##.   --> 0110
    // (...) part:    (3)    --> 0001
    //                (1, 3) --> 0101
    //                (2)    --> 0010
    private record Row(int target, List<Integer> buttons, List<Integer> expectedJolts, List<List<Integer>> rawButtons) {}
    
    private List<Row> getParsedInput(String path) {
        var mat = InputUtils.getContentAsLines(path);
        List<Row> rows = new ArrayList<>();
        
        for (String line : mat) {
           
            // Parse [.##.]
            int target = parseTarget(line);
            
            // Parse all (...)  and {...} parts
            List<Integer> buttons = new ArrayList<>();
            List<Integer> expectedJolts = new ArrayList<>();
            List<List<Integer>> rawButtons = new ArrayList<>();
            
            // Pattern to match (x,y,...) or (x) groups and {x,y,...} group
            Pattern patternParen = Pattern.compile("\\(([^)]+)\\)");
            Pattern patternBrace = Pattern.compile("\\{([^}]+)\\}");
            
            Matcher matcherParen = patternParen.matcher(line);
            while (matcherParen.find()) {
                String content = matcherParen.group(1);
                int xorMask = parseIndices(content);
                buttons.add(xorMask);
                
                // Parse raw button values
                String[] parts = content.split(",");
                List<Integer> buttonValues = new ArrayList<>();
                for (String part : parts) {
                    buttonValues.add(Integer.parseInt(part.trim()));
                }
                rawButtons.add(buttonValues);
            }
            
            Matcher matcherBrace = patternBrace.matcher(line);
            if (matcherBrace.find()) {
                String content = matcherBrace.group(1);
                String[] parts = content.split(",");
                for (String part : parts) {
                    expectedJolts.add(Integer.parseInt(part.trim()));
                }
            }
            
            rows.add(new Row(target, buttons, expectedJolts, rawButtons));
        }
        
        return rows;
    }
    
    private int parseTarget(String line) {
        int start = line.indexOf('[');
        int end = line.indexOf(']');
        String targetStr = line.substring(start + 1, end);
        int result = 0;
        for (int i = 0; i < targetStr.length(); i++) {
            if (targetStr.charAt(i) == '#') {
                result |= (1 << i);
            }
        }
        return result;
    }
    
    private int parseIndices(String content) {
        String[] parts = content.split(",");
        int xorMask = 0;
        for (String part : parts) {
            int index = Integer.parseInt(part.trim());
            xorMask |= (1 << index);
        }
        return xorMask;
    }

    private long solvePart1ForRow(int target, List<Integer> buttons) {
        if (target == 0) {
            return 0l;
        }

        if (buttons.isEmpty()) {
            return 1000; // A reasonable bound (all valid solutions will be less than 1k)
        }

        int btnValue = buttons.get(0);
        int newTarget = target ^ btnValue;
        List<Integer> remainingButtons = buttons.subList(1, buttons.size());
        long countWithPressing = 1 + solvePart1ForRow(newTarget, remainingButtons);
        long countWithoutPressing = solvePart1ForRow(target, remainingButtons);
        
        return Math.min(countWithPressing, countWithoutPressing);
    }

    private long solvePart2ForRow(List<Integer> target, List<List<Integer>> buttons) {
        if (target.stream().allMatch(n -> n == 0)) {
            return 0l;
        }

        if (buttons.isEmpty()) {
            return 10000; // A reasonable bound (all valid solutions will be less than 1k)
        }

        List<Integer> btn = buttons.get(0);
        List<Integer> newTarget = new ArrayList<>(target); // Clone the list
        List<List<Integer>> remainingButtons = buttons.subList(1, buttons.size());
        long countWithoutPressing = solvePart2ForRow(newTarget, remainingButtons);
        
        // Apply button
        for (int index: btn) {
            int currValInIndex = newTarget.get(index);
            if (currValInIndex == 0) return countWithoutPressing; // Can't use button, will cause negative jolt
            newTarget.set(index, currValInIndex - 1);
        }
        long countWithPressing = 1 + solvePart2ForRow(newTarget, buttons); // Keep button, we may press it again
        
        return Math.min(countWithPressing, countWithoutPressing);
    }

    public long solvePartA(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        var parsedInput = getParsedInput(path);

        long sol = 0l;

        for (Row row : parsedInput) {
            sol += solvePart1ForRow(row.target, row.buttons);
        }

        return sol;
    }

    public long solvePartB(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        var parsedInput = getParsedInput(path);

        long sol = 0l;
        int x = 0;

        for (Row row : parsedInput) {
            System.out.println("Started iteration " + x + " / " + parsedInput.size());
            sol += solvePart2ForRow(row.expectedJolts, row.rawButtons);
        }

        return sol;
    }
}
