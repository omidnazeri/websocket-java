package org.websocket.chat.client.config;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.websocket.chat.client.listener.ChatWebSocketListener;
import org.websocket.chat.common.dto.request.LoginRequest;

@Configuration
public class WebsocketClientConfig {
    private final String WEBSOCKET_URI = "ws://localhost:3000/";

    @Bean
    public WebSocket websocket() {
        WebSocket ws = new OkHttpClient().newWebSocket(new Request.Builder().url(WEBSOCKET_URI).build(), new ChatWebSocketListener());

        LoginRequest request = new LoginRequest();
        request.setAction("LOGIN");
        request.setToken("user1");
        ws.send(request.toJson());

        return ws;
    }
}
