import java.util.*;

/**
 * Created by ybai on 12/3/14.
 */
public class EdmondsKarpMaxFlow {
    int[][] graph;
    int n;
    int s, t;
    int currFlow = 0;
    int maxFlow = 0;
    int[] p; // parent map to reconstruct path
    public static int INF = (int) 1e20;
    public EdmondsKarpMaxFlow(int[][] graph, int s, int t) {
        this.graph = graph;
        this.n = graph.length;
        this.p = new int[n];
        this.s = s;
        this.t = t;
        this.calculate();
    }

    public int getMaxFlow() {
        return maxFlow;
    }

    private void calculate() {

        currFlow = maxFlow = 0;
        while (true) {
            // this loop will be run max O(VE) times

            // init bfs
            ArrayDeque<Integer> q = new ArrayDeque<Integer>();
            int[] dist = new int[n];
            Arrays.fill(dist, INF);
            dist[s] = 0;
            q.offer(s);

            // bfs
            while (!q.isEmpty()) {
                int u = q.pollFirst();  // queue: layer by layer!
                if (u == t) {
                    break;
                }
                for (int v = 0; v < n; v++) {
                    if (graph[u][v] > 0 && dist[v] == INF) {
                        dist[v] = dist[u] + 1;
                        q.offer(v);
                        p[v] = u;
                    }
                }
            }

            // add path to flow
            augmentPath(t, INF);
            if (currFlow == 0) {
                break;
            }
            maxFlow += currFlow;
        }
    }

    private void augmentPath(int v, int minEdge) {
        if (v == s) {
            currFlow = minEdge;
        } else if (p[v] != -1) {
            augmentPath(p[v], Math.min(minEdge, graph[p[v]][v]));
            graph[p[v]][v] -= currFlow;  // forward edges -> decrease
            graph[v][p[v]] += currFlow; // backward edges -> increase
        }
    }

    public static void main(String[] args) {
        // combination of adj list and adj matrix
        int n = 10;
        int[][] graph = new int[n][n];
        graph[1][3] = 70;
        graph[1][4] = 30;
        graph[3][2] = 25;
        graph[3][4] = 5;
        graph[4][2] = 70;
        EdmondsKarpMaxFlow flow = new EdmondsKarpMaxFlow(graph, 1, 2);
        System.out.format("Max flow = %d\n", flow.getMaxFlow());

    }
}
