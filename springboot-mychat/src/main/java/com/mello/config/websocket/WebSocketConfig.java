package com.mello.config.websocket;

import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

/**
 * websocket配置
 * Created by MelloChan on 2017/10/16.
 */
@Configuration
@EnableWebSocketMessageBroker
@Log4j
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
            stompEndpointRegistry.addEndpoint("/mello-socket").withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry brokerRegistry){
        brokerRegistry.setApplicationDestinationPrefixes("/app");
        brokerRegistry.enableSimpleBroker("/topic","/queue");
    }
    @Override
    public void configureWebSocketTransport(final WebSocketTransportRegistration registration){
        registration.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
            @Override
            public WebSocketHandler decorate(final WebSocketHandler webSocketHandler) {
                return new WebSocketHandlerDecorator(webSocketHandler){
                    @Override
                    public void afterConnectionEstablished(final WebSocketSession session)throws Exception{
                        String username=session.getPrincipal().getName();
                        log.info("online:"+username);
                        super.afterConnectionEstablished(session);
                    }
                    @Override
                    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)throws  Exception{
                        String username=session.getPrincipal().getName();
                        log.info("offline:"+username);
                        super.afterConnectionClosed(session,closeStatus);
                    }
                };
            }
        });
        super.configureWebSocketTransport(registration);
    }
}
