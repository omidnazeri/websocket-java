package org.websocket.chat.client.receiver;

import okhttp3.WebSocket;
import org.websocket.chat.common.dto.response.WebsocketResponse;

public class AuthenticationFailedReceiver implements WebsocketMessageReceiver<WebsocketResponse> {
    @Override
    public void onReceive(WebSocket webSocket, WebsocketResponse data) {
        // do nothing

    }
}