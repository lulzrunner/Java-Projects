package com.lulz_bot;

import java.io.*;

public class Server {

    public static void main(String[] args) throws IOException {
        Runnable threadJob = new ConnectedClient();
        Thread thread = new Thread(threadJob);
        thread.start();

        System.out.println("exiting");
    }
}
