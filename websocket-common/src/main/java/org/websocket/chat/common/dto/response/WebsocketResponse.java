package org.websocket.chat.common.dto.response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.websocket.chat.common.dto.WebsocketResponseStatus;

import java.util.Objects;

public class WebsocketResponse {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("command")
    private String command;

    public WebsocketResponse() {
    }

    public WebsocketResponse(WebsocketResponseCommand command, WebsocketResponseStatus status) {
        this.code = status.getStatus();
        this.message = status.getMessage();
        this.command = command.name();
    }

    public WebsocketResponse(String command, int code, String message) {
        this.code = code;
        this.message = message;
        this.command = command;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebsocketResponse resultDto = (WebsocketResponse) o;
        return getCode() == resultDto.getCode() &&
                Objects.equals(getMessage(), resultDto.getMessage()) &&
                Objects.equals(getCommand(), resultDto.getCommand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getMessage(), getCommand());
    }
}
