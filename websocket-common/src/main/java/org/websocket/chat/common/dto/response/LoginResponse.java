package org.websocket.chat.common.dto.response;

import org.websocket.chat.common.dto.WebsocketResponseStatus;

public class LoginResponse extends WebsocketResponse {
    public LoginResponse() {
        super(WebsocketResponseCommand.LOGIN, WebsocketResponseStatus.SUCCESS);
    }
}
