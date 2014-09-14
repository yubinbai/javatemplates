import java.util.*;
class BellmanFord {
    int V;
    int[][] adj;
    int[] dist;
    public boolean bellmanFord(int source) {
        this.dist = new int[this.V];
        Arrays.fill(dist, Integer.MAX_VALUE - this.V * 2);
        dist[source] = 0;

        for (int iter = 0; iter < this.V - 1; ++iter) {
            for (int u = 0; u < this.V; ++u) {
                for (int v = 0; v < this.V; ++v) {
                    if (adj[u][v] != -1) dist[v] = Math.min(dist[v], dist[u] + adj[u][v]);
                }
            }
        }
        for (int u = 0; u < this.V; ++u) {
            for (int v = 0; v < this.V; ++v) {
                if (dist[v] > dist[u] + adj[u][v]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Generate adj list
        BellmanFord bf = new BellmanFord();
        int size = 100;
        bf.V = size;
        bf.adj = new int[size][size];
        Random r = new Random();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                bf.adj[i][j] = r.nextBoolean() ? 1 : -1;
            }
        }
        String result = (bf.bellmanFord(1)) ? "No circle" : "Has circle" ;
        System.out.println(result);
        for (int i : bf.dist) {
            System.out.format("%d ", i);
        }
    }
}