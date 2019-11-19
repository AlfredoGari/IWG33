package com.example.demo.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.business.BusinessException;
import com.example.demo.business.IEmailBusiness;
import com.example.demo.model.dto.ChangeStateMessage;
import com.example.demo.model.dto.LabelValue;

@Component
public class SendWebSocketListener implements ApplicationListener<SendWebSocketEvent> {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SimpMessagingTemplate wsClient;

	@Autowired
	private IEmailBusiness emailService;

	@Value("${mail.alarmas:-}")
	private String mailAlarmas;

	private static int enviado = 3;

	@SuppressWarnings("rawtypes")
	@Override
	public void onApplicationEvent(SendWebSocketEvent event) {
		wsClient.convertAndSend(event.getTopic(), event.getSource());
		if (event.getSource() instanceof ChangeStateMessage) {
			ChangeStateMessage cem = (ChangeStateMessage<?>) event.getSource();
			if (cem.getPayload() instanceof LabelValue) {
				LabelValue lv = (LabelValue) cem.getPayload();
				if (!mailAlarmas.equals("-") && lv.getValue() == 2 && enviado > 0) {
					log.debug("Enviando mail nro {} a {}...", enviado, mailAlarmas);
					try {
						emailService.sendSimpleMessage(mailAlarmas, "Alarma!", lv.getLabel());
					} catch (BusinessException e) {
						log.error(e.getMessage(), e);
					}
					enviado--;
				}

			}

		}

	}

}
