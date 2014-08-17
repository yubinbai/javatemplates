import java.util.*;
import java.io.*;
public class RandomLine {
    public static Scanner genrateFile(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(i);
            sb.append("\n");
        }
        return new Scanner(sb.toString());
    }

    public static String[] randomLine(Scanner sc, int n) {
        String[] ret = new String[n];
        int lineCount = 0;
        Random rand = new Random();
        while (sc.hasNextLine()) {
            lineCount++;
            String line = sc.nextLine();
            for (int i = 0; i < n; i++) {
                if (rand.nextDouble() <= 1.0 / lineCount) ret[i] = line;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        String[] ret = randomLine(genrateFile(10000), 5);
        for (String s : ret) {
            System.out.format("%s ", s);
        }
    }
}