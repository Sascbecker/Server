package de.htwsaar.service.serverConnector;


import java.io.StringReader;

import javax.json.*;
import javax.json.JsonObject;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

public class ClientConnector
{
	private static String baseUrl = "http://localhost:4434";
	
	public static boolean sendMessage( String absender, String empfaenger, int gruppenID, int timestamp, String message, int aktion ) {
		String webContextPath = "/getMessage";

		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		return target.path( webContextPath ).queryParam( "absender", absender ).queryParam( "empfaenger", empfaenger )
									.queryParam( "gruppenID", gruppenID ).queryParam( "timestamp", timestamp )
									.queryParam( "message", message ).queryParam( "aktion", absender )
									.request(MediaType.APPLICATION_JSON).get(boolean.class);
	}
}
