package de.htwsaar.service.serverConnector;


import java.io.StringReader;

import javax.json.*;
import javax.json.JsonObject;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

import de.htwsaar.service.serverConnector.Singleton;
//import java.util.*;

public class ClientConnector
{
	private static String baseUrl = "http://localhost:4434";
	
	public static boolean sendMessage( String absender, String empfaenger, int gruppenID, int timestamp, String message, int aktion ) {
		String webContextPath = "/getMessage";
		
		boolean angekommen = false;
		
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		angekommen = target.path( webContextPath ).queryParam( "absender", absender ).queryParam( "empfaenger", empfaenger )
									.queryParam( "gruppenID", gruppenID ).queryParam( "timestamp", timestamp )
									.queryParam( "message", message ).queryParam( "aktion", absender )
									.request(MediaType.APPLICATION_JSON).get(boolean.class);
	}
	
	private static JsonObject jsonFromString(String jsonObjectStr) {
	    JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectStr));
	    JsonObject object = jsonReader.readObject();
	    jsonReader.close();
	    return object;
	}
	/*
	private static int[] convertJsonToIntArray (int anzahl, JsonObject job) {
		int[] array = new int[anzahl];
		for(int i = 0; i < anzahl; i++) {
			array[i] = job.getInt(String.valueOf(i));
		}
		return array;
	}
	
	private static List<String> convertJsonToObject (int anzahl, JsonObject job) {
		List<String> primzahlen = new ArrayList<String>();
		for(int i = 0; i < anzahl; i++) {
			primzahlen.add(job.getString(String.valueOf(i)));
		}
		return primzahlen;
	}
	*/
}
