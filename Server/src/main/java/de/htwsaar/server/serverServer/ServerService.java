package de.htwsaar.server.serverServer;

//import javax.json.Json;
//import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import de.htwsaar.server.dataclass.*;
import de.htwsaar.server.main.Start;
//import de.htwsaar.server.service.*;
//import de.htwsaar.server.main.*;

@Path( "/{parameter: login|getMessage|kontakte}" )
public class ServerService
{
	@Path("/login")
	@GET
   	@Produces( MediaType.APPLICATION_JSON )
   	public boolean loginService( @QueryParam("user") User user, @QueryParam("ip") String ip ){
		//User user = new User( 2, name, password, ip );
		Start start = new Start();
		start.userStart(user);
		return user.getUserAuthentifizierung();
   	}
	
	@Path("/getMessage")
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public boolean getMessage( @QueryParam( "nachricht" ) Message nachricht ){
							//@QueryParam( "absender" ) String absender, @QueryParam( "empfaenger" ) String  empfaenger,
							//@QueryParam( "gruppenID" ) int gruppenID, @QueryParam( "timestamp" ) int timestamp,
							//@QueryParam( "message" ) String nachricht, @QueryParam( "aktion" ) int aktion ) {
		
		//Message message = new Message( aktion, absender, gruppenID, empfaenger, nachricht, timestamp);
		Start start = new Start();
		start.messageStart(nachricht);
		
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
}