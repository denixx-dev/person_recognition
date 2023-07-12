package org.kazino.test;
import py4j.GatewayServer;
public class StackEntryPoint {
    private Stack2 stack;

    public StackEntryPoint() {
        stack = new Stack2();
        stack.push("Initial Item");
    }

    public Stack2 getStack() {
        return stack;
    }

    public static void main(String[] args) {
        GatewayServer gatewayServer = new GatewayServer(new StackEntryPoint());
        gatewayServer.start();
        System.out.println("Gateway Server Started");
    }
}
