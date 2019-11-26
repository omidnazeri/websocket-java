package org.websocket.chat.sever.authentication.exception;

public class AuthenticationFailedException extends Exception {
    private String token;

    public AuthenticationFailedException(String token) {
        this.token = token;
    }

    @Override
    public String getMessage() {
        return String.format("invalid token : %s", token);
    }
}
