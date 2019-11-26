package org.websocket.chat.common.dto.request;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class WebsocketRequest {
    @SerializedName(value = "token")
    private String token;

    @SerializedName(value = "action")
    private String action;

    private String userId;

    public WebsocketRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
