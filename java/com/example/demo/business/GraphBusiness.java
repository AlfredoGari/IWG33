package com.example.demo.business;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.ChangeStateMessage;
import com.example.demo.model.dto.LabelValue;
import com.example.demo.web.Constantes;

@Service
public class GraphBusiness implements IGraphBusiness {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SimpMessagingTemplate wSock;

	@Override
	public void pushGraphData() {
		try {
			String[] meses = "Enero,Febrero,Marzo,Abril,Mayo,Junio,Julio,Agosto,Sptiembre,Octubre,Noviembre,Diciembre"
					.split(",");
			List<LabelValue> valores = Arrays.stream(meses).map(mes -> {
				return new LabelValue(mes, ((int) (Math.random() * 100)));
			}).collect(Collectors.toList());
			wSock.convertAndSend(Constantes.TOPIC_SEND_WEBSOCKET_GRAPH,
					new ChangeStateMessage<List<LabelValue>>(ChangeStateMessage.TYPE_GRAPH_DATA, valores));
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}

}
