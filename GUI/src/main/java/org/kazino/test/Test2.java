package org.kazino.test;

import java.io.*;
import java.net.Socket;

public class Test2 extends Thread{
    private static Socket guiSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static String host = "127.0.0.1";
    private static int port = 8000;

    public static void main(String[] args) throws IOException, InterruptedException  {
        System.out.println("main");

        try {
            guiSocket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(guiSocket.getInputStream()));
            // threadedConsoleOut = new ThreadedConsoleOut();
            // threadedConsoleOut.start();

            System.out.println(in.readLine());
        } finally {
            System.out.println("Клиент был закрыт...");
            guiSocket.close();
            in.close();
        }
        System.out.flush();
    }
}
