package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.business.BusinessException;
import com.example.demo.business.IAuthTokenService;
import com.example.demo.business.IGraphBusiness;
import com.example.demo.business.INotificacionesBusiness;

@Configuration
@EnableScheduling
public class ScheduledEvents {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private IAuthTokenService authTokenService;
	
	@Scheduled(fixedDelay = 24*60*60*1000)
	public void purgeAuthTokens() {
		try {
			authTokenService.purgeTokens();
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
		}
	}

	
	
	@Scheduled(fixedDelay = 5*1000, initialDelay = 3000)
	public void dummy() {
		//log.info("Ejecutando tarea");
	}
	
	
	@Autowired
	private IGraphBusiness graphService;
	
	@Scheduled(fixedDelay = 5000, initialDelay = 10000)
	// @Scheduled(cron = " 0 0/1 * 1/1 * ? *")
	public void estados() {
		graphService.pushGraphData();
		
	}
	
	
	@Autowired
	private INotificacionesBusiness notiService;
	
	@Scheduled(fixedDelay = 3000, initialDelay = 1000)
	public void notificaiones() {
		notiService.randomNoti();
		
	}
}
