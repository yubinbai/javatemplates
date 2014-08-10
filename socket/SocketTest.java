package socket;

import java.util.*;
import java.util.concurrent.*;

public class SocketTest {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        exec.execute(new DataServer());

        DataClient client = new DataClient();
        client.run();

        exec.shutdown();

    }
}
