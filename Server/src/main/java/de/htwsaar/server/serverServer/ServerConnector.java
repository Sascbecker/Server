package de.htwsaar.server.serverServer;


import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Creats the RestServer
 * @author Marco
 *
 */
public class ServerConnector
{
	public static void main( String[] args ) throws IOException, InterruptedException 
	{
		
		String baseUrl = "localhost:4434";
		
		InetAddress ip = null;
		
		try {
			ip = InetAddress.getLocalHost();
			System.out.println(ip);
			System.out.println(ip.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
				URI.create( baseUrl ), new ResourceConfig( ServerService.class ));
		Runtime.getRuntime().addShutdownHook( new Thread( new Runnable() {
			
			public void run() {
				server.shutdownNow();
			}
		} ) );
		server.start();
      
		System.out.println( String.format( "\nGrizzly-HTTP-Server gestartet mit der URL: %s\n"
										+ "IP: %s\n"
                                         + "Stoppen des Grizzly-HTTP-Servers mit:      Strg+C\n",
                                         baseUrl, ip ) );

		Thread.currentThread().join();
	}
}