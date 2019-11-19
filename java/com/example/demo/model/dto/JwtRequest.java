package com.example.demo.model.dto;

public class JwtRequest{
	
	private String name;
	private String password;
	
	//need default constructor for JSON Parsing
	public JwtRequest()
	{
		
	}
	public JwtRequest(String username, String password) {
		this.setName(username);
		this.setPassword(password);
	}
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
