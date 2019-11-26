package org.websocket.chat.sever.authentication;

import org.apache.logging.log4j.util.Strings;
import org.websocket.chat.sever.authentication.exception.AuthenticationFailedException;

public class SimpleAuthenticationManager implements AuthenticationManager {
    @Override
    public String isAuthenticated(String token) throws AuthenticationFailedException {
        if (Strings.isEmpty(token)) {
            throw new AuthenticationFailedException(token);
        }

        return token;
    }
}
