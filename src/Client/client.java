package Client;

import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);

        InputStream inputStream = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        String message = in.readLine();
        System.out.println(message);

        socket.close();
    }
}

