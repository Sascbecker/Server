package de.htwsaar.server.main;

import de.htwsaar.server.service.interfaces.*;
import de.htwsaar.server.dataclass.*;

/*
 * Hauptprogramm des Servers, aufruf dieser Klasse bei ankommenen der Nachricht.
 * Verteilt die Logik auf andere Klassen
 */
public class start {

	MessageService mesService;
	UserService userService;

	public void messageStart()
	{
		
	}
	
	public void userStart(int aktion, String absender, String empfaenger, String passwort)
	{
		boolean returnValue = true;
		User user = new User(aktion, absender, empfaenger, passwort);
		
		switch(user.getAktion()) {
		
		//User anlegen
		case 1: userService.userAnlegen(user);
			break;
		//User login
		case 2: returnValue = userService.userAuthenfizierung(user);
			break;
		//Kontakt hinzufügen
		case 3: userService.kontaktHinzufuege(user);
			break;
		//Kontakt löschen
		case 4: userService.kontaktLoeschen(user);
			break;
		//Kontakt blockieren
		case 5: userService.kontaktBlockieren(user);
			break;
		default :
			break;
			
		}
		
		if(returnValue == false)
		{
			//Message an Client keine Berechtigung für login
		}
	}
	
}
