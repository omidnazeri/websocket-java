package org.websocket.chat.common.dto.response;

import org.websocket.chat.common.dto.WebsocketResponseStatus;

public class ChatResponse extends WebsocketResponse {
    private String userId;
    private String nickname;
    private String text;

    public ChatResponse() {
        super(WebsocketResponseStatus.SUCCESS);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
