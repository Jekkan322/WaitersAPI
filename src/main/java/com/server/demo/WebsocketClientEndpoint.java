/*
package com.server.demo;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.net.URI;
import java.nio.ByteBuffer;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WebsocketClientEndpoint {

    Session userSession = null;

    public WebsocketClientEndpoint(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    */
/**
     * Callback hook for Connection open events.
     *
     * @param userSession the userSession which is opened.
     *//*

    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("opening websocket");
        this.userSession = userSession;
    }

    */
/**
     * Callback hook for Connection close events.
     *
     * @param userSession the userSession which is getting closed.
     * @param reason the reason for connection close
     *//*

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("closing websocket");
        this.userSession = null;
    }

    */
/**
     * Callback hook for Message Events. This method will be invoked when a client send a message.
     *
     * @param message The text message
     *//*

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Get message: " + message);
    }

    @OnMessage
    public void onMessage(ByteBuffer bytes) {
        System.out.println("Handle byte buffer");
    }

    */
/**
     * register message handler
     *
     * @param msgHandler
     *//*


    */
/**
     * Send a message.
     *
     * @param message
     *//*

    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }
}
*/
/*    public static interface MessageHandler {

        public void handleMessage(String message);
}*//*



*/
/**
 * Message handler.
 *
 * @author Jiji_Sasidharan
 *//*


*/
