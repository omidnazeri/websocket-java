package org.websocket.chat.common.dto.response;

import org.websocket.chat.common.dto.WebsocketResponseStatus;

public class PongResponse extends WebsocketResponse {
    public PongResponse() {
        super(WebsocketResponseCommand.PONG, WebsocketResponseStatus.SUCCESS);
    }
}
