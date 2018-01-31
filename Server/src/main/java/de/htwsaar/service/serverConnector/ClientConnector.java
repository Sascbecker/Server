package de.htwsaar.service.serverConnector;


import java.io.StringReader;

import javax.json.*;
import javax.json.JsonObject;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

import de.htwsaar.service.serverConnector.Singleton;
//import java.util.*;

public class ClientConnector
{
	public static void getMessage( String absender )//muss boolean sein?
	{
		//String baseUrl        = ( args.length > 0 ) ? args[0] : "http://localhost:4434";
		
		String baseUrl = "http://localhost:4434";
		String webContextPath = "/requestMessage";
		
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		String jsonObjectString = target.path( webContextPath )
				.request( MediaType.APPLICATION_JSON ).get( String.class );
		
		JsonObject jsonObject = jsonFromString(jsonObjectString);
		String message = jsonObject.getString("Message");
		
		System.out.println(message);
		//return message;
		
		//EVENT HANLDER (EVENT WERFEN FÃœR DATENBANK) ALEX FRAGEN
	}
	public static void sendMessage( String absender, String message ) {
		String baseUrl = "http://localhost:4434";
		String webContextPath = "/getMessage";
		
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( baseUrl );
		
		Singleton.setInstanceAndMessage(message);
		
		target.path( webContextPath ).queryParam( "absender", absender );
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
