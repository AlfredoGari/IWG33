package com.example.demo.events;

import org.springframework.context.ApplicationEvent;

public class SendWebSocketEvent extends ApplicationEvent {
private String topic; 
	public SendWebSocketEvent(Object source, String topic) {
		super(source);
		this.topic=topic;
	}

	private static final long serialVersionUID = 1L;
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
