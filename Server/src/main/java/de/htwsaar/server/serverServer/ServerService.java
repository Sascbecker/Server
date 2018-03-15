package de.htwsaar.server.serverServer;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import de.htwsaar.server.dataclass.*;
import de.htwsaar.server.main.Start;
import de.htwsaar.server.service.*;
import de.htwsaar.server.main;

@Path( "/{parameter: login|getMessage|kontakte}" )
public class ServerService
{
	@Path("/login")
	@GET
   	@Produces( MediaType.APPLICATION_JSON )
   	public boolean loginService( @QueryParam("name") String name, @QueryParam("password") String password, 
   								@QueryParam("ip") String ip ){
		User user = new User( 2, name, password, ip );
		Start start = new Start();
		start.userStart(user);
		return user.getUserAuthentifizierung();
   	}
	
	@Path("/getMessage")
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public boolean getMessage( @QueryParam( "absender" ) String absender, @QueryParam( "empfaenger" ) String  empfaenger,
							@QueryParam( "gruppenID" ) int gruppenID, @QueryParam( "timestamp" ) int timestamp,
							@QueryParam( "message" ) String nachricht, @QueryParam( "aktion" ) int aktion ) {
		
		Message message = new Message( aktion, absender, gruppenID, empfaenger, nachricht, timestamp);
		Start start = new Start();
		start.messageStart(message);
		
		return true;//Nachricht angekommen
	}
	
	@Path("/getGroup")
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public boolean getMessage( @QueryParam( "gruppe" ) Group gruppe ) {
		
		Start start = new Start();
		start.groupStart(gruppe);
		
		return true;//Nachricht angekommen
	}
	
	@Path("/lastTimestamp")
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public boolean lastTimestamp( @QueryParam( "timestamp" ) long timestamp, @QueryParam( "sender" ) String sender ) {
		
		Start start = new Start();
		start.groupStart(gruppe);
		
		return true;//Nachricht angekommen
	}
	
	// gruppenamen, gruppenadmin, liste der nuzter, gruppenID
}