package org.websocket.chat.sever.receiver;

import org.springframework.stereotype.Service;
import org.websocket.chat.common.dto.request.LoginRequest;
import org.websocket.chat.common.dto.response.LoginResponse;
import org.yeauty.pojo.Session;

@Service("loginSocketReceiver")
public class LoginReceiver implements WebsocketMessageReceiver<LoginRequest> {
    @Override
    public void onReceive(Session session, LoginRequest data) {
        LoginResponse response = new LoginResponse();

        WebSocketServer.sendToOne(session, response);
    }
}
