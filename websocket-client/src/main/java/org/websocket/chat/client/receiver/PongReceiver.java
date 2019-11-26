package org.websocket.chat.client.receiver;

import okhttp3.WebSocket;
import org.springframework.stereotype.Service;
import org.websocket.chat.common.dto.response.PongResponse;

public class PongReceiver implements WebsocketMessageReceiver<PongResponse> {
    @Override
    public void onReceive(WebSocket webSocket, PongResponse data) {
        // do nothing
    }
}