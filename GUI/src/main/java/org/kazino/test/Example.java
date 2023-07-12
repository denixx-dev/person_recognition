package org.kazino;
import py4j.GatewayServer;
import java.io.*;

public class Example {

    public int member = 1;
    public String member() {
        return "Hello World";
    }

    public static void main(String[] args) {
        GatewayServer gatewayServer = new GatewayServer(new Example());
        gatewayServer.start();

    }

}

