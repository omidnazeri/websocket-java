package org.websocket.chat.sever.receiver;

import org.websocket.chat.common.dto.request.WebsocketRequest;
import org.yeauty.pojo.Session;

public interface WebsocketMessageReceiver<T extends WebsocketRequest> {
    void onReceive(Session session, T data);
}
