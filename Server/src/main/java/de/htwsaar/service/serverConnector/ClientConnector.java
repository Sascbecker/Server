package de.htwsaar.service.serverConnector;


//import java.io.StringReader;
//import javax.json.*;
//import javax.json.JsonObject;
//import javax.ws.rs.QueryParam;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import de.htwsaar.server.dataclass.*;
public class ClientConnector
{
	private static String baseUrl = ":4434";
	
	public static boolean sendMessage( Message nachricht , String ip ) {
		String webContextPath = "/getMessage";
		boolean angekommen = false;

		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( ip + baseUrl );
		
		
		angekommen = target.path( webContextPath ).queryParam( "nachricht", nachricht )
									//.queryParam( "absender", absender ).queryParam( "empfaenger", empfaenger )
									//.queryParam( "gruppenID", gruppenID ).queryParam( "timestamp", timestamp )
									//.queryParam( "message", message ).queryParam( "aktion", absender )
									.request(MediaType.APPLICATION_JSON).get(boolean.class);
		return angekommen;
	}
	
	public static boolean sendContacts( Kontakte kontakte, String ip ) {
		String webContextPath = "/kontakte";
		boolean angekommen = false;

		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( ip + baseUrl );
		
		angekommen = target.path( webContextPath ).queryParam( "kontakte", kontakte )
								.request( MediaType.APPLICATION_JSON ).get( boolean.class );
		
		return angekommen;
	}
	
	
	public static boolean ping( String ip ) {
		String webContextPath = "/ping";
		boolean angekommen = false;

		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( ip + baseUrl );
		
		angekommen = target.path( webContextPath ).request( MediaType.APPLICATION_JSON ).get( boolean.class );
		
		return angekommen;
	}
	
	public static void lastMessage( String ip ) {
		String webContextPath = "/lastMessage";

		Client c = ClientBuilder.newClient();
		WebTarget target = c.target( ip + baseUrl );
		
		target.path( webContextPath );
	}
}
