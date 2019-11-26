package org.websocket.chat.sever.receiver;

import org.springframework.stereotype.Service;
import org.websocket.chat.common.dto.request.PingRequest;
import org.websocket.chat.common.dto.response.PongResponse;
import org.yeauty.pojo.Session;

@Service("pingSocketReceiver")
public class PingReceiver implements WebsocketMessageReceiver<PingRequest> {
    @Override
    public void onReceive(Session session, PingRequest data) {
        PongResponse response = new PongResponse();

        WebSocketServer.broadcastAll(response);
    }
}