package com.example.demo.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.demo.events.SendWebSocketEvent;
import com.example.demo.model.dto.ChangeStateMessage;
import com.example.demo.model.dto.LabelValue;
import com.example.demo.web.Constantes;

@Service
public class NotificacionesBusiness implements INotificacionesBusiness {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void randomNoti() {
		LabelValue v = new LabelValue("Mensaje a las " + new Date(), (int) (Math.random() * 3));
		SendWebSocketEvent event = new SendWebSocketEvent(
				new ChangeStateMessage<LabelValue>(ChangeStateMessage.TYPE_NOTIFICA, v),
				Constantes.TOPIC_SEND_WEBSOCKET_GRAPH);

		applicationEventPublisher.publishEvent(event);

		//wSock.convertAndSend(Constantes.TOPIC_SEND_WEBSOCKET_GRAPH,
		//		new ChangeStateMessage<LabelValue>(ChangeStateMessage.TYPE_NOTIFICA, v));
	}

}
