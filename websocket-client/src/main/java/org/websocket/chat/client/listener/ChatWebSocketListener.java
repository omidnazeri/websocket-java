package org.websocket.chat.client.listener;

import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatWebSocketListener extends WebSocketListener {
    private static final Logger logger = LoggerFactory.getLogger(ChatWebSocketListener.class);

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        logger.info(message);
    }
}