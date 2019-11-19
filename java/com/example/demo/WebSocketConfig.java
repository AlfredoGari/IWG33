package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.example.demo.web.Constantes;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/iw3"); // subscribe('/backend/msg'), el cd sale del @SendTo en el controller server
		registry.setApplicationDestinationPrefixes("/wsin"); // send('/wsin/toServer'), toServer sale del
																// @MessageMapping en el controller
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(Constantes.URL_WEBSOCKET_ENPOINT).withSockJS(); // es el nombre del endpoint para hacer new
																				// SockJs( endpoint )
	}

}
