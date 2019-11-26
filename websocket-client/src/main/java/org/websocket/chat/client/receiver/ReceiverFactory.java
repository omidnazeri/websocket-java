package org.websocket.chat.client.receiver;

import com.google.gson.Gson;
import com.sun.javaws.exceptions.InvalidArgumentException;
import okhttp3.WebSocket;
import org.websocket.chat.common.dto.response.ChatResponse;
import org.websocket.chat.common.dto.response.LoginResponse;
import org.websocket.chat.common.dto.response.WebsocketResponse;
import org.websocket.chat.common.dto.response.WebsocketResponseCommand;

public class ReceiverFactory {
    public static void doReceive(WebSocket webSocket, WebsocketResponse data, String message) throws InvalidArgumentException {
        WebsocketMessageReceiver receiver = null;

        if (WebsocketResponseCommand.LOGIN.name().equals(data.getCommand())) {
            data = (new Gson()).fromJson(message, LoginResponse.class);
            receiver = new LoginReceiver();
        } else if (WebsocketResponseCommand.CHAT.name().equals(data.getCommand())) {
            data = (new Gson()).fromJson(message, ChatResponse.class);
            receiver = new ChatReceiver();
        } else if (WebsocketResponseCommand.AUTHENTICATION_FAILED.name().equals(data.getCommand())) {
            data = (new Gson()).fromJson(message, WebsocketResponse.class);
            receiver = new AuthenticationFailedReceiver();
        } else if (WebsocketResponseCommand.INVALID_COMMAND.name().equals(data.getCommand())) {
            data = (new Gson()).fromJson(message, WebsocketResponse.class);
            receiver = new InvalidCommandReceiver();
        }

        if (receiver != null) {
            receiver.onReceive(webSocket, data);
        } else {
            throw new InvalidArgumentException(new String[]{"invalid command"});
        }
    }
}
