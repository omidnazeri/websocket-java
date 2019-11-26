package org.websocket.chat.client.listener;

import com.google.gson.Gson;
import com.sun.javaws.exceptions.InvalidArgumentException;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.websocket.chat.client.receiver.ReceiverFactory;
import org.websocket.chat.common.dto.response.WebsocketResponse;

public class ChatWebSocketListener extends WebSocketListener {
    private static final Logger logger = LoggerFactory.getLogger(ChatWebSocketListener.class);

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        logger.info(message);
        WebsocketResponse data = (new Gson()).fromJson(message, WebsocketResponse.class);

        try {
            ReceiverFactory.doReceive(webSocket, data, message);
        } catch (InvalidArgumentException e) {
            logger.error(e.getMessage(), e);
        }
    }
}