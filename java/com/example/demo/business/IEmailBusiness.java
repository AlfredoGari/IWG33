package com.example.demo.business;

public interface IEmailBusiness {

	public void sendSimpleMessage(String to, String subject, String text) throws BusinessException;

}
