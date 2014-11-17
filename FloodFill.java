import java.util.Arrays;
import java.util.Random;

/**
 * Created by Yubin on 11/16/2014.
 */
public class FloodFill {
    int[][] graph;
    int size;
    int[] color;
    int nComponents;
    public FloodFill(int[][] graph) {
        this.size = graph.length;
        this.graph = graph;
        this.color = new int[size];
        this.fill();
    }
    
    private void fill() {
        for (int i = 0; i < size; i++) {
            if (color[i] == 0) {
                nComponents++;
                flood_fill(i, nComponents);
            }
        }
    }

    private void flood_fill(int i, int nComponents) {
        color[i] = nComponents;
        for (int j = 0; j < size; j++) {
            if (graph[i][j] > 0 && color[j] == 0) {
                flood_fill(j, nComponents);
            }
        }
    }

    private void printComponents() {
        for (int i = 0; i < nComponents; i++) {
            System.out.format("Color: %d\n", i);
            for (int j = 0; j < size; j++) {
                if (color[j] == i + 1) {
                    System.out.format("%d ", j);
                }
            }
            System.out.format("\n");
        }
    }

    private int getNumberOfComponents() {
        return nComponents;
    }

    public static void main(String[] args) {
        int size = 20;
        int[][] graph = new int[size][size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                graph[i][j] = r.nextDouble() > 0.9 ? 1 : 0;
            }
        }
        FloodFill f = new FloodFill(graph);
        int nCompoments = f.getNumberOfComponents();
        f.printComponents();
    }
}
