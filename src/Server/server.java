package Server;

import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;
        int clientNumber = 0;

        try {
            serverSocket = new ServerSocket(1234); 
            System.out.println("Server started on port 1234");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 1234");
            System.exit(-1);
        }

        while (listening) {
            new ServerThread(serverSocket.accept(), clientNumber++).start();
        }

        serverSocket.close();
    }
}

class ServerThread extends Thread {
    private Socket socket = null;
    private int clientNumber;

    public ServerThread(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        System.out.println("New connection with client: " + clientNumber + " at " + socket.getRemoteSocketAddress());
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("You are client :" + clientNumber);

            while (true) {
                String inputLine = in.readLine();
                if (inputLine == null || inputLine.equals("exit")) break; // Exit on 'exit' command
                System.out.println("Client " + clientNumber + " says: " + inputLine);
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}