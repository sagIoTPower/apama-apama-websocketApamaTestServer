package com.softwareag.apama.ws;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/standaloneEcho")
public class EchoEndpoint {
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    @OnOpen
    public void onOpen(Session peer) throws IOException {
    	//peer.getBasicRemote().sendText("onOpen");
    	System.out.println("Session connected"+ peer.getId());
        peers.add(peer);
    }

    @OnMessage
    public void echo(String message) throws Exception {
    	broadcastToAll(message);
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @OnClose
    public void onClose(Session peer) {
    	System.out.println("Session disconnected"+ peer.getId());
    }
    
    public void broadcastToAll (String msg) throws Exception{
    	for (Session peer : peers) {
        	System.out.println("Sending msg: "+ msg + "client:" + peer.getId());
        	peer.getBasicRemote().sendText(msg);
		}
    }
}
