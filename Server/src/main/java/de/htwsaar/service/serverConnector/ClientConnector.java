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
	
	public static boolean sendMessage( String absender, String empfaenger, int gruppenID, long timestamp, String message, int aktion ) {
		String webContextPath = "/getMessage";
		boolean angekommen = false;

		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		
		angekommen = target.path( webContextPath ).queryParam( "absender", absender ).queryParam( "empfaenger", empfaenger )
									.queryParam( "gruppenID", gruppenID ).queryParam( "timestamp", timestamp )
									.queryParam( "message", message ).queryParam( "aktion", absender )
									.request(MediaType.APPLICATION_JSON).get(boolean.class);
		return angekommen;
	}
	
	public static boolean sendContacts( Kontakte kontakte ) {
		String webContextPath = "/kontakte";
		boolean angekommen = false;

		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		angekommen = target.path( webContextPath ).queryParam( "kontakte", kontakte )
								.request( MediaType.APPLICATION_JSON ).get( boolean.class );
		
		return angekommen;
	}
}
