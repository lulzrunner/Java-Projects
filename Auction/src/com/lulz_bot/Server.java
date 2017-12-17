package com.lulz_bot;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server  {

    String[] adviceList = {"advice1", "advice2" };

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(1488);
            boolean work = true;
            while (work) {
                Socket socket = serverSocket.accept();
                Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

                while (scanner.hasNextLine()) {
                    String str = scanner.nextLine();
                    printWriter.write("You're write: " + str);
                    System.out.println(str);
                    if (scanner.nextLine().equals("exit")) {
                        work = false;
                        break;
                    }
                }
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }
}
