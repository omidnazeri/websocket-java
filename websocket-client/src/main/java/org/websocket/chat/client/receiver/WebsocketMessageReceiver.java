package org.websocket.chat.client.receiver;

import okhttp3.WebSocket;
import org.websocket.chat.common.dto.response.WebsocketResponse;

public interface WebsocketMessageReceiver<T extends WebsocketResponse> {
    void onReceive(WebSocket webSocket, T data);
}
