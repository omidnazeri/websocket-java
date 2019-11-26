package org.websocket.chat.sever.receiver;

import com.google.gson.Gson;
import com.sun.javaws.exceptions.InvalidArgumentException;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.websocket.chat.common.dto.WebsocketResponseStatus;
import org.websocket.chat.common.dto.request.ChatRequest;
import org.websocket.chat.common.dto.request.LoginRequest;
import org.websocket.chat.common.dto.request.WebsocketRequest;
import org.websocket.chat.common.dto.response.WebsocketResponse;
import org.websocket.chat.common.dto.response.WebsocketResponseCommand;
import org.websocket.chat.sever.authentication.AuthenticationManager;
import org.websocket.chat.sever.authentication.exception.AuthenticationFailedException;
import org.yeauty.annotation.*;
import org.yeauty.pojo.ParameterMap;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;

@ServerEndpoint(prefix = "netty-websocket")
@Component
public class WebSocketServer {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * Holds the list of active WebSocket connections. "Active" means WebSocket
     * handshake is complete and socket can be written to, or read from.
     */
    private static Collection<Session> sessions = new ConcurrentLinkedDeque<>();

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    AuthenticationManager authenticationManager;

    public static Integer getNumberOfConnections() {
        return sessions.size();
    }

    public static void broadcastAll(WebsocketResponse response) {
        broadcastAll(new Gson().toJson(response));
    }

    public static void broadcastAll(String data) {
        sessions.parallelStream().forEach(session -> {
            try {
                if (session.isOpen() && session.isActive()) {
                    session.sendText(data);
                }
            } catch (Exception e) {
                if (session != null) {
                    logger.error(String.format("Lost connection to user{%s} form message %s", session.getAttribute("userId"), data));
                } else {
                    logger.error(e.getMessage(), e);
                }
            }
        });
    }

    public static void sendToOne(String userId, WebsocketResponse data) {
        sendToOne(userId, data.toJson());
    }

    public static void sendToOne(String userId, String data) {
        Optional<Session> session = sessions.parallelStream().filter(s -> s.getAttribute("userId") != null && s.getAttribute("userId").equals(userId)).findFirst();
        session.ifPresent(s -> sendToOne(s, data));
    }

    public static void sendToOne(Session session, WebsocketResponse data) {
        sendToOne(session, data.toJson());
    }

    public static void sendToOne(Session session, String data) {
        try {
            if (session.isOpen() && session.isActive()) {
                session.sendText(data);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /*
        open new websocket connection
     */
    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, ParameterMap parameterMap) throws IOException {
        String ipAddress = session.remoteAddress().toString();

        // adding some attribute to session
        session.setAttribute("ipAddress", ipAddress);
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error(throwable.getMessage(), throwable);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        WebsocketRequest data = (new Gson()).fromJson(message, WebsocketRequest.class);

        String userId = null;
        try {
            userId = authenticationManager.isAuthenticated(data.getToken());

            session.setAttribute("userId", userId);

            WebsocketMessageReceiver messageReceiver = (WebsocketMessageReceiver) applicationContext.getBean(data.getAction().toLowerCase() + "SocketReceiver");

            switch (data.getAction()) {
                case "CHAT":
                    data = (new Gson()).fromJson(message, ChatRequest.class);
                    break;
                case "LOGIN":
                    data = (new Gson()).fromJson(message, LoginRequest.class);
                    break;
            }

            data.setUserId(userId);

            if (messageReceiver != null) {
                messageReceiver.onReceive(session, data);
            }
            else {
                throw new InvalidArgumentException(new String[]{"invalid command"});
            }
        } catch (AuthenticationFailedException e) {
            logger.error(e.getMessage());
            sendToOne(session, new WebsocketResponse(WebsocketResponseCommand.AUTHENTICATION_FAILED, WebsocketResponseStatus.AUTHENTICATION_FAILED));
        } catch (BeansException | InvalidArgumentException e) {
            logger.error(e.getMessage(), e);
            sendToOne(session, new WebsocketResponse(WebsocketResponseCommand.INVALID_COMMAND, WebsocketResponseStatus.AUTHENTICATION_FAILED));
        }
    }

    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        // Do nothing
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    logger.info("read idle");
                    break;
                case WRITER_IDLE:
                    logger.info("write idle");
                    break;
                case ALL_IDLE:
                    logger.info("all idle");
                    break;
                default:
                    break;
            }
        }
    }

}