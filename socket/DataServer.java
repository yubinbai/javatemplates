package socket;

import java.io.*;
import java.net.*;

public class DataServer implements Runnable {

    public void run() {
        try {

            ServerSocket sock = new ServerSocket(6013);
            // now listen for connections
            while (true) {

                Socket client = sock.accept();
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                // write the Date to the socket
                pout.println(new java.util.Date().toString());
                // close the socket and resume
                // listening for connections

                pout.println("This is from the data server.");
                client.close();
            }
        }

        catch (IOException ioe) {
            System.err.println(ioe);
        }

    }
}
