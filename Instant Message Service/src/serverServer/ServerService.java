package serverServer;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import clientConnector.Singleton;
import serverConnector.ClientConnector;

@Path( "/{parameter: sendMessage|login|getMessage|kontakte}" )
public class ServerService
{
	@Path("/sendMessage")//evtl ohne slash? weil es oben vor der klammer steht
   	@GET
   	public void requestMessage( @QueryParam("absender") String absender ){
   		ClientConnector.getMessage(absender);
   	}
	
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
	public String getMessage() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
   		
   		builder.add("Message", Singleton.getMessage());// VON WEM .... NAME REQUIRED !!!!!
		
		return builder.build().toString();
	}
	
	/*
	@Path("/getMessage")
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public String getMessage( @QueryParam("absender"), String absender ) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
   		
   		builder.add("Message", Singleton.getMessage());// VON WEM .... NAME REQUIRED !!!!!
		
		return builder.build().toString();
	}
	*/
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