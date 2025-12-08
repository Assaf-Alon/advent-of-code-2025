package advent.of.code.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnionFind {
    private int[] parent;
    private int[] rank;
    private int[] size;

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        this.size = new int[size];
        
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
            this.size[i] = 1;
        }
    }

    public int find(int p) {
        if (parent[p] != p) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
            size[rootQ] = 0;
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
            size[rootQ] = 0;
            rank[rootP]++;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int getSize(int p) {
        return size[find(p)];
    }

    public int getLargestGroupSize() {
        int m = 0;
        for (int i = 0; i < size.length; i++) {
            if (size[i] > m) m = size[i];
        }
        return m;
    }

    public List<Integer> getTop3Sizes() {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < size.length; i++) {
            if (size[i] > 0) {
                l.add(size[i]);
            }
        }
        Collections.sort(l, Collections.reverseOrder());
        return l.size() > 3 ? l.subList(0, 3) : l;
    }
}