package de.htwsaar.server.serverServer;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import de.htwsaar.service.serverConnector.*;

@Path( "/{parameter: login|getMessage|kontakte}" )
public class ServerService
{
	@Path("/login")
	@GET
   	@Produces( MediaType.APPLICATION_JSON )
   	public String loginService( @QueryParam("name") String name, @QueryParam("password") String password ){
   		JsonObjectBuilder builder = Json.createObjectBuilder();
   		
   		//; PLATZHALTER
   		
   		builder.add("ergebnis", true);
   		
   		//; PLATZHALTER
		
		return builder.build().toString();
   	}
	
	@Path("/getMessage")
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public boolean getMessage( @QueryParam("absender") String absender, @QueryParam( "empfaenger" ) String  empfaenger,
							@QueryParam( "absenderID" ) int absenderID, @QueryParam( "empfaengerID" ) int empfaengerID,
							@QueryParam( "gruppenID" ) int gruppenID, @QueryParam( "timestamp" ) int timestamp,
							@QueryParam( "message" ) String message ) {
		//JsonObjectBuilder builder = Json.createObjectBuilder();
   		//builder.add("Message", Singleton.getMessage());// VON WEM .... NAME REQUIRED !!!!!
		
		//EVENT HANDLER FUER MESSAGE EMPFANGEN
		
		return true;//Nachricht angekommen
	}
	
	@Path("/kontakte")
   	@GET
   	public void kontaktVerwaltung( @QueryParam("absender") String absender, @QueryParam("empfaenger") String empfaenger, @QueryParam("aktion") int aktion ){
   		switch(aktion) {
   		case 1: //kontakt hinzufuegen;
   				break;
   		case 2: //kontakt loeschen;
   				break;
   		case 3: //kontakt blockieren;
   				break;
   		}
   	}
}