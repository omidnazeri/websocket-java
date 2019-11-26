package org.websocket.chat.sever.receiver;

import org.springframework.stereotype.Service;
import org.websocket.chat.common.dto.request.ChatRequest;
import org.websocket.chat.common.dto.response.ChatResponse;
import org.yeauty.pojo.Session;

@Service("chatSocketReceiver")
public class ChatReceiver implements WebsocketMessageReceiver<ChatRequest> {
    @Override
    public void onReceive(Session session, ChatRequest data) {
        ChatResponse response = new ChatResponse();
        response.setUserId(data.getUserId());
        response.setNickname(data.getUserId());
        response.setText(data.getMessage());

        WebSocketServer.broadcastAll(response);
    }
}