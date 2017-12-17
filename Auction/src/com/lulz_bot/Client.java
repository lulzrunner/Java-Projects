package com.lulz_bot;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void start() {
	    try {
	        Socket socket = new Socket();
	        socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 1488), 2000);

            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("lol");

            String advice = reader.readLine();
            System.out.println("Today you must: " + advice);

            reader.close();
        } catch (IOException ex) {
	        ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Client.start();
    }
}
