package org.websocket.chat.client.receiver;

import okhttp3.WebSocket;
import org.websocket.chat.common.dto.response.ChatResponse;

public class ChatReceiver implements WebsocketMessageReceiver<ChatResponse> {
    @Override
    public void onReceive(WebSocket webSocket, ChatResponse data) {
        System.out.println(String.format("%s: %s", data.getNickname(), data.getText()));
    }
}