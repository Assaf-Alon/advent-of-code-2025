package advent.of.code.day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import advent.of.code.util.InputUtils;

public class Day11 {
   
    private static final Logger logger =
        Logger.getLogger(Day11.class.getName());

    private HashMap<String, List<String>> getParsedInput(String path) {
        var mat = InputUtils.getContentAsLines(path);
        var graphAsMap = new HashMap<String, List<String>>();
        
        for (String line : mat) {
            String vertex = line.split(":")[0];
            List<String> edges = new ArrayList<>();
            for (String edge: line.split(":")[1].split(" ")) {
                if (edge.equals("")) continue;
                edges.add(edge);
            }
            graphAsMap.put(vertex, edges);
        }
        return graphAsMap;
    }

    private long countPathsFromSrcToDst(HashMap<String, List<String>> graph, String src, String dst) {
        if (src.equals(dst)) {
            return 1;
        }

        long total = 0;
        var edges = graph.get(src);

        if (edges == null) return 0;

        for (String edgeDst: graph.get(src)) {
            total += countPathsFromSrcToDst(graph, edgeDst, dst);
        }
        return total;
    }

    private HashMap<String, Integer> getVerticesInDegrees(HashMap<String, List<String>> graph) {
        HashMap<String, Integer> inDegrees = new HashMap<>();
        for (String v1: graph.keySet()) {
            var edges = graph.get(v1);
            if (edges == null) continue;
            for (String v2: edges) {
                var prevInDegree = inDegrees.getOrDefault(v2, 0);
                inDegrees.put(v2, prevInDegree + 1);
            }
        }

        for (String v1: graph.keySet()) {
            logger.info(v1 + " inDeg = " + inDegrees.getOrDefault(v1, 0));
        }
        return inDegrees;
    }

    private List<String> topologicalSortVertices(HashMap<String, List<String>> graph) {
        HashMap<String, Integer> inDegrees = getVerticesInDegrees(graph);
        List<String> inDegreeZero = new ArrayList<>();
        inDegreeZero.add("svr");
        List<String> sorted = new ArrayList<>();
        while (!inDegreeZero.isEmpty()) {
            String curr = inDegreeZero.removeFirst();
            logger.info("curr = " + curr);
            sorted.add(curr);
            
            var edges = graph.get(curr);
            if (edges == null) continue;

            for (String edgeDst: edges) {
                var dstInDegree = inDegrees.get(edgeDst);
                inDegrees.put(edgeDst, dstInDegree - 1);
                if (inDegrees.get(edgeDst) == 0) {
                    logger.info("Vertex " + edgeDst + " is now with inDegree 0");
                    inDegreeZero.add(edgeDst);
                }
                if (inDegrees.get(edgeDst) < 0) {
                    logger.warning("UNEXPECTED NEGATIVE IN DEGREE FROM " + curr + " TO " + edgeDst);
                    try { Thread.sleep(1000); } catch (Exception e) {}
                }
            }
        }
        return sorted;
    }

    public long solvePartA(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        var graph = getParsedInput(path);

        return countPathsFromSrcToDst(graph, "you", "out");
    }


    public long solvePartB(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        var graph = getParsedInput(path);
        var sortedVertices = topologicalSortVertices(graph);
        assert(sortedVertices.getFirst().equals("svr"));
        assert(sortedVertices.getLast().equals("out"));
        sortedVertices.removeLast();

        HashMap<String, Long> numOfPathsToOutNoDacOrFft = new HashMap<>();
        HashMap<String, Long> numOfPathsToOutFromFft = new HashMap<>();
        HashMap<String, Long> numOfPathsToOutFromDac = new HashMap<>();
        HashMap<String, Long> numOfPathsToOutFromDacAndFft = new HashMap<>();
        numOfPathsToOutNoDacOrFft.put("svr", 1l);
        // sortedVertices.removeLast(); // Remove out

        // OH GOD WHAT HAVE I DONE 😭
        for (String v1: sortedVertices) {
            var pathsToV1_0 = numOfPathsToOutNoDacOrFft.getOrDefault(v1, 0l);
            var pathsToV1_1 = numOfPathsToOutFromFft.getOrDefault(v1, 0l);
            var pathsToV1_2 = numOfPathsToOutFromDac.getOrDefault(v1, 0l);
            var pathsToV1_3 = numOfPathsToOutFromDacAndFft.getOrDefault(v1, 0l);
            for (String v2: graph.get(v1)) {
                var pathsToV2_0 = numOfPathsToOutNoDacOrFft.getOrDefault(v2, 0l);
                var pathsToV2_1 = numOfPathsToOutFromFft.getOrDefault(v2, 0l);
                var pathsToV2_2 = numOfPathsToOutFromDac.getOrDefault(v2, 0l);
                var pathsToV2_3 = numOfPathsToOutFromDacAndFft.getOrDefault(v2, 0l);
                if (!v2.equals("fft") && !v2.equals("dac")) {
                    numOfPathsToOutNoDacOrFft.put(v2, pathsToV1_0 + pathsToV2_0);
                    numOfPathsToOutFromFft.put(v2, pathsToV1_1 + pathsToV2_1);
                    numOfPathsToOutFromDac.put(v2, pathsToV1_2 + pathsToV2_2);
                    numOfPathsToOutFromDacAndFft.put(v2, pathsToV1_3 + pathsToV2_3);
                } else if (v2.equals("fft")) {
                    numOfPathsToOutFromFft.put(v2, pathsToV1_0 + pathsToV2_1);
                    numOfPathsToOutFromDacAndFft.put(v2, pathsToV1_2 + pathsToV2_3);
                } else if (v2.equals("dac")) {
                    numOfPathsToOutFromDac.put(v2, pathsToV1_0 + pathsToV2_2);
                    numOfPathsToOutFromDacAndFft.put(v2, pathsToV1_1 + pathsToV2_3);
                }
            }
        }

        return numOfPathsToOutFromDacAndFft.get("out");

    }
}
