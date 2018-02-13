package de.htwsaar.server.main;

import de.htwsaar.server.service.interfaces.*;
import de.htwsaar.server.dataclass.*;

/*
 * Hauptprogramm des Servers, aufruf dieser Klasse bei ankommenen der Nachricht.
 * Verteilt die Logik auf andere Klassen
 */
public class start {

	MessageService messageService;
	UserService userService;

	public void messageStart(String absenderId, int groupId, String empfaengerId, String message, int timestamp)
	{
		Message nachricht = new Message(absenderId, groupId, empfaengerId, message, timestamp);
		messageService.start(nachricht);
		
	}
	
	public void userStart(int aktion, String absender, String empfaenger, String passwort)
	{
		User user = new User(aktion, absender, empfaenger, passwort);
		
		userService.start(user);
		
		//message an Client mit returnCode
	}
	
}
