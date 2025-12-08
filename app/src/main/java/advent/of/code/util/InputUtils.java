package advent.of.code.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class InputUtils {
    private InputUtils() { }

    /**
     * Load a text file by path and return its contents as a 2D char matrix.
     * example: "inputs/example00.txt"
     */
    public static char[][] getContentAsMatrix(String resourcePath) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
        if (is == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        try (BufferedReader r = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = r.readLine()) != null) {
                lines.add(line);
            }

            char[][] matrix = new char[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                matrix[i] = lines.get(i).toCharArray();
            }

            return matrix;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read resource: " + resourcePath, e);
        }
    }

    public static List<List<Character>> getContentAsMatrix2(String resourcePath) {
        String content = getContentAsString(resourcePath);
        List<List<Character>> matrix = new ArrayList<>();
        
        for (String line : content.split("\\r?\\n")) {
            List<Character> row = new ArrayList<>();
            for (char c : line.toCharArray()) {
                row.add(c);
            }
            matrix.add(row);
        }
        
        return matrix;
    }

    public static List<String> getContentAsLines(String resourcePath) {
        String content = getContentAsString(resourcePath);
        return List.of(content.split("\\r?\\n"));
    }

    public static List<String> transposeMatrixAsLines(List<String> matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix cannot be null");
        }

        int rows = matrix.size();
        if (rows == 0) {
            return List.of();
        }

        int maxCols = 0;
        for (String row : matrix) {
            if (row != null && row.length() > maxCols) {
                maxCols = row.length();
            }
        }

        List<String> lines = new ArrayList<>();
        for (int c = 0; c < maxCols; c++) {
            StringBuilder sb = new StringBuilder();
            for (int r = 0; r < rows; r++) {
                String row = matrix.get(r);
                if (row != null && c < row.length()) {
                    sb.append(row.charAt(c));
                }
            }
            lines.add(sb.toString());
        }

        return lines;
    }

    public static String getContentAsString(String resourcePath) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
        if (is == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        try (InputStream in = is) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read resource: " + resourcePath, e);
        }
    }

    public static <T> boolean isPositionInBoard(List<List<T>> arr, int row, int col) {
        int rows = arr.size();
        int cols = arr.get(0).size();
        return 0 <= row && row < rows && 0 <= col && col < cols;
    }


    public static List<List<Integer>> STAR_DIRECTIONS = List.of(
        List.of(-1, -1),
        List.of(-1, 0),
        List.of(-1, 1),
        List.of(0, -1),
        List.of(0, 1),
        List.of(1, -1),
        List.of(1, 0),
        List.of(1, 1)
    );
    
    public static List<List<Integer>> ULRD_DIRECTIONS = List.of(
        List.of(-1, 0),
        List.of(0, -1),
        List.of(0, 1),
        List.of(1, 0)
    );

    public record LongPair(Long first, Long second) implements Comparable<LongPair> {
        @Override
        public int compareTo(LongPair other) {
            int firstComparison = this.first.compareTo(other.first);
            if (firstComparison != 0) {
                return firstComparison;
            }
            return this.second.compareTo(other.second);
        }
    }

    public record LongTriplet(Long first, Long second, Long third) implements Comparable<LongTriplet> {
        @Override
        public int compareTo(LongTriplet other) {
            int firstComparison = this.first.compareTo(other.first);
            if (firstComparison != 0) {
                return firstComparison;
            }
            int secondComparison = this.second.compareTo(other.second);
            if (secondComparison != 0) {
                return secondComparison;
            }
            return this.third.compareTo(other.third);
        }
    }

    public record LongTripletPair(LongTriplet first, LongTriplet second) implements Comparable<LongTripletPair> {
        private double getDistance() {
            double x = Math.pow(first.first - second.first, 2);
            double y = Math.pow(first.second - second.second, 2);
            double z = Math.pow(first.third - second.third, 2);
            return Math.sqrt(x + y + z);
        }
        
        @Override
        public int compareTo(LongTripletPair other) {
            double firstVal = getDistance();
            double secondVal = other.getDistance();
            if (firstVal == secondVal) return 0;
            return firstVal > secondVal ? 1 : -1;
        }
    }
    
    public static List<LongTriplet> getTriplets(String resourcePath) {
        List<String> lines = getContentAsLines(resourcePath);
        List<LongTriplet> triplets = new ArrayList<>();

        for (String line : lines) {
            if (line == null || line.isBlank()) {
                continue;
            }
            String[] parts = line.split(",");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid triplet line (expected 3 values): " + line);
            }

            List<Long> t = new ArrayList<>(3);
            for (String p : parts) {
                t.add(Long.parseLong(p.trim()));
            }
            triplets.add(new LongTriplet(t.get(0), t.get(1), t.get(2)));
        }

        return triplets;
    }
    
}
