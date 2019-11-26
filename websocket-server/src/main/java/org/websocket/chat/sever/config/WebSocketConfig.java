package org.websocket.chat.sever.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.websocket.chat.sever.authentication.AuthenticationManager;
import org.websocket.chat.sever.authentication.SimpleAuthenticationManager;
import org.yeauty.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new SimpleAuthenticationManager();
    }
}