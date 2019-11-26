package org.websocket.chat.sever.authentication;

import org.websocket.chat.sever.authentication.exception.AuthenticationFailedException;

public interface AuthenticationManager {
    String isAuthenticated(String token) throws AuthenticationFailedException;
}
