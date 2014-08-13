import java.util.*;
public class RemoveDuplicate {

    public static int process(Integer[] data) {
        // remove adjacent duplicates in an array
        // use the sliding windows data[0..i] is processed region

        int i = 0, j = 0, size = data.length;
        for (j = 0; j < size; j++)
            if (data[i] != data[j]) {
                i++;
                data[i] = data[j];
            }
        return i + 1;
    }

    public static void main(String[] args) {
        int size = 100;
        Integer[] data = new Integer[size];

        Random rand = new Random();
        for (int i = 0; i < size; i++)
            data[i] = rand.nextInt(size / 2);

        Arrays.sort(data);
        int newSize = RemoveDuplicate.process(data);
        // for (int i = 0; i < size; i++)
        // System.out.println(data[i]);

        for (int i = 0; i < newSize - 1; i++)
            for (int j = i + 1; j < newSize; j++)
                if (data[i] == data[j]) System.out.println("Errors");
    }
}
