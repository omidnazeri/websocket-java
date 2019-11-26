package org.websocket.chat.common.dto.request;

public class ChatRequest extends WebsocketRequest {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
