package org.websocket.chat.common.dto;

public enum WebsocketResponseStatus {
    SUCCESS(200, "Success"),
    AUTHENTICATION_FAILED(403, "Authentication failed!"),
    INVALID_COMMAND(422, "Invalid command!"),
    ;

    int status;
    String message;

    WebsocketResponseStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
