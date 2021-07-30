package com.web.curation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //WebSocket 서버를 가능하게 하는 어노테이션
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{ //서버 활성화 interface
	//클라이언트에서 클라이언트로 메시지 라우팅하기 위한 메시지 브로커 구성
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic", "/queue");
    }
    //서버 연결을 위한 엔드포인트 구성
	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { 
		//STOMP: 단순 메세지 프로토콜이다. WebSocket은 그냥 통신일 뿐이라 특정 주제나 사용자에게 메시지를 보내는 방법을 정의하기 위함
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS(); //withSockJs: 웹소켓 지원하지 않는 브라우저에 폴백 옵션 제공
    }

	
}