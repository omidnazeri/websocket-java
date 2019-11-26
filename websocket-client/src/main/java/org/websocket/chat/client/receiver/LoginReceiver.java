package org.websocket.chat.client.receiver;

import okhttp3.WebSocket;
import org.websocket.chat.common.dto.response.LoginResponse;

public class LoginReceiver implements WebsocketMessageReceiver<LoginResponse> {
    @Override
    public void onReceive(WebSocket webSocket, LoginResponse data) {
        // do nothing
    }
}