class DisjointSet {
    int size;
    int[] parent, rank;

    public DisjointSet(int size) {
        this.size = size;
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int size() {
        return this.size;
    }

    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void union(int i, int j) {
        int p1 = find(i);
        int p2 = find(j);
        if (rank[p1] < rank[p2]) {
            parent[p1] = p2;
        } else {
            parent[p2] = p1;
            if (rank[p1] == rank[p2]) rank[p1] += 1;
        }
    }

    public int countSets() {
        HashSet<Integer> s = new HashSet<Integer>();
        for (int i = 0; i < size; i++) {
            s.add(parent[i]);
        }
        return s.size();
    }
}