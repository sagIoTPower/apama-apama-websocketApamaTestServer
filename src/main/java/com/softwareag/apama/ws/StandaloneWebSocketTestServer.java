package com.softwareag.apama.ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.glassfish.tyrus.server.Server;

public class StandaloneWebSocketTestServer {
	public static void main(String args[]) {
	    Server server = new Server("localhost", 9998, "/standaloneServer", null, EchoEndpoint.class);

	    try {
	        server.start();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	        System.out.println("Standalone server listening on ws://localhost:9998/standaloneServer/standaloneEcho");
	        System.out.println("Please press a key to stop the server.");
	        reader.readLine();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        server.stop();
	    }
	}
}
