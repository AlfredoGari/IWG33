package com.example.demo.web;

public final class Constantes {
	public static final String URL_API_BASE = "/api";
	public static final String URL_API_VERSION = "/v1";
	public static final String URL_BASE = URL_API_BASE + URL_API_VERSION;

	public static final String URL_BASE_PRODUCTOS = URL_BASE + "/productos";
	public static final String URL_BASE_ARCHIVOS = URL_BASE + "/archivos";

	public static final String URL_DENY = "/deny";

	public static final String URL_LOGINOK = "/loginok";
	public static final String URL_AUTH_INFO =  "/authinfo";
	public static final String URL_VERSION = "/version";
	
	public static final String URL_TOKEN = URL_BASE + "/token";
	
	public static final String URL_WEBSOCKET_ENPOINT = URL_BASE + "/ws";
	
	public static final String TOPIC_SEND_WEBSOCKET_GRAPH="/iw3/data";
	
	public static final String URL_GRAPH =  URL_BASE  + "/graph";
	
	public static final String URL_AUTH =  URL_BASE  + "/auth";
}
