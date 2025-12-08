package advent.of.code.day08;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

import advent.of.code.util.InputUtils;
import advent.of.code.util.UnionFind;
import advent.of.code.util.InputUtils.LongTriplet;
import advent.of.code.util.InputUtils.LongTripletPair;

public class Day08 {

    public long solvePartA(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        List<LongTriplet> triplets = InputUtils.getTriplets(path);

        PriorityQueue<LongTripletPair> pointPairs = new PriorityQueue<>();

        for (int i = 0; i < triplets.size(); i++) {
            for (int j = i + 1; j < triplets.size(); j++) {
                pointPairs.add(new LongTripletPair(triplets.get(i), triplets.get(j)));
            }
        }

        HashMap<LongTriplet, Integer> tripletToId = new HashMap<>();
        AtomicInteger idCounter = new AtomicInteger(0);
        for (LongTriplet triplet : triplets) {
            tripletToId.put(triplet, idCounter.getAndIncrement());
        }

        UnionFind uf = new UnionFind(triplets.size());
        int limit = triplets.size() == 1000 ? 1000 : 10;
        for (int i = 0; i < limit; i++) {
            LongTripletPair closestTriplet = pointPairs.poll();
            LongTriplet triplet1 = closestTriplet.first();
            LongTriplet triplet2 = closestTriplet.second();

            int id1 = tripletToId.get(triplet1);
            int id2 = tripletToId.get(triplet2);
            uf.union(id1, id2);
        }

        var x = uf.getTop3Sizes();

        return x.get(0) * x.get(1) * x.get(2);
    }

    public long solvePartB(String resourceFileName) {
        String path = resourceFileName.startsWith("inputs/") ? resourceFileName : "inputs/" + resourceFileName;
        List<LongTriplet> triplets = InputUtils.getTriplets(path);

        PriorityQueue<LongTripletPair> pointPairs = new PriorityQueue<>();

        for (int i = 0; i < triplets.size(); i++) {
            for (int j = i + 1; j < triplets.size(); j++) {
                pointPairs.add(new LongTripletPair(triplets.get(i), triplets.get(j)));
            }
        }

        HashMap<LongTriplet, Integer> tripletToId = new HashMap<>();
        AtomicInteger idCounter = new AtomicInteger(0);
        for (LongTriplet triplet : triplets) {
            tripletToId.put(triplet, idCounter.getAndIncrement());
        }

        UnionFind uf = new UnionFind(triplets.size());
        int limit = triplets.size();
        while (true) {
            LongTripletPair closestTriplet = pointPairs.poll();
            LongTriplet triplet1 = closestTriplet.first();
            LongTriplet triplet2 = closestTriplet.second();

            int id1 = tripletToId.get(triplet1);
            int id2 = tripletToId.get(triplet2);
            uf.union(id1, id2);
            if (uf.getLargestGroupSize() == limit) {
                return triplet1.first() * triplet2.first();
            }
        }
    }
}
