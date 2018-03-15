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
   	public String loginService( @QueryParam("name") String name, @QueryParam("password") String password, 
   								@QueryParam("ip") String ipAdresse ){
   		JsonObjectBuilder builder = Json.createObjectBuilder();
   		
   		//; PLATZHALTER
   		
   		builder.add("ergebnis", true);
   		
   		//; PLATZHALTER
		
		return builder.build().toString();
   	}
	
	@Path("/getMessage")
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public boolean getMessage( @QueryParam( "absender" ) String absender, @QueryParam( "empfaenger" ) String  empfaenger,
							@QueryParam( "gruppenID" ) int gruppenID, @QueryParam( "timestamp" ) int timestamp,
							@QueryParam( "message" ) String message, @QueryParam( "aktion" ) int aktion ) {
		
		//EVENT HANDLER FUER MESSAGE EMPFANGEN
		
		return true;//Nachricht angekommen
	}
	
	// gruppenamen, gruppenadmin, liste der nuzter, gruppenID
}