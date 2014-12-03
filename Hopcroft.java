/**
 * Java Program to Implement Hopcroft Algorithm
 * 
 * For bipartite matching, O(E * sqrt(V))
 *
 **/

import java.util.*;

public class Hopcroft {
    private final int NIL = 0;
    private final int INF = Integer.MAX_VALUE;
    private ArrayList<Integer>[] adjList;
    private int[] pair;
    private int[] dist;
    private int cx, cy;

    /** Function BFS **/
    public boolean BFS() {
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int v = 1; v <= cx; ++v) {
            if (pair[v] == NIL) {
                dist[v] = 0;
                queue.add(v);
            } else {
                dist[v] = INF;
            }
        }

        dist[NIL] = INF;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            if (dist[v] < dist[NIL])
                for (int u : adjList[v])
                    if (dist[pair[u]] == INF) {
                        dist[pair[u]] = dist[v] + 1;
                        queue.add(pair[u]);
                    }
        }
        return dist[NIL] != INF;
    }
    /** Function DFS **/
    public boolean DFS(int v) {
        if (v != NIL) {
            for (int u : adjList[v])
                if (dist[pair[u]] == dist[v] + 1)
                    if (DFS(pair[u])) {
                        pair[u] = v;
                        pair[v] = u;
                        return true;
                    }

            dist[v] = INF;
            return false;
        }
        return true;
    }
    /** Function to get maximum matching **/
    public int HopcroftKarp() {
        pair = new int[cx + cy + 1];
        dist = new int[cx + cy + 1];
        int matching = 0;
        while (BFS())
            for (int v = 1; v <= cx; ++v)
                if (pair[v] == NIL && DFS(v)) matching++;
        return matching;
    }
    /** Function to make graph with vertices x , y **/
    public void makeGraph(int[] x, int[] y, int E) {
        adjList = new ArrayList[cx + cy + 1];
        for (int i = 0; i < adjList.length; ++i)
            adjList[i] = new ArrayList<Integer>();
        /** adding edges **/
        for (int i = 0; i < E; ++i)
            addEdge(x[i] + 1, y[i] + 1);
    }
    /** Function to add a edge **/
    public void addEdge(int u, int v) {
        adjList[u].add(cx + v);
        adjList[cx + v].add(u);
    }
    /** Main Method **/
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hopcroft Algorithm Test\n");
        /** Make an object of Hopcroft class **/
        Hopcroft hc = new Hopcroft();

        /** Accept number of edges **/
        System.out.println("Enter number of edges\n");
        int E = scan.nextInt();
        int[] x = new int[E];
        int[] y = new int[E];
        hc.cx = 0;
        hc.cy = 0;

        System.out.println("Enter " + E + " x, y coordinates ");
        for (int i = 0; i < E; i++) {
            x[i] = scan.nextInt();
            y[i] = scan.nextInt();
            hc.cx = Math.max(hc.cx, x[i]);
            hc.cy = Math.max(hc.cy, y[i]);
        }
        hc.cx += 1;
        hc.cy += 1;

        /** make graph with vertices **/
        hc.makeGraph(x, y, E);

        System.out.println("\nMatches : " + hc.HopcroftKarp());
    }
}