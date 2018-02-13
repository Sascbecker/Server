package de.htwsaar.server.service;

import de.htwsaar.server.dataclass.User;
import de.htwsaar.server.dao.interfaces.UserDao;

public class UserServiceImpl {
	
	UserDao userDao;
	
	public void start(User user)
	{
		switch(user.getAktion()) {
		
		//User anlegen
		case 1: userAnlegen(user);
			break;
		//User login
		case 2: userAuthenfizierung(user);
			break;
		//Kontakt hinzufügen
		case 3: kontaktHinzufuege(user);
			break;
		//Kontakt löschen
		case 4: kontaktLoeschen(user);
			break;
		//Kontakt blockieren
		case 5: kontaktBlockieren(user);
			break;
		default :
			break;
			
		}
	}
	
	private void userAnlegen(User user)
	{
		try {
			userDao.userAnlegen(user);
		}
		catch(Exception ex)
		{
			user.setReturnCode("User konnte nicht angelegt werden");
		}
	}
	
	private void userAuthenfizierung(User user)
	{
		User vergleichsUser = new User();
		vergleichsUser = userDao.getPasswort(user.getAbsenderId());
		
		if(vergleichsUser.getPasswort() == user.getPasswort())
		{
			user.setReturnCode("Authentifizierung erfolgreich");
		}
		else
			user.setReturnCode("Authentifizierung fehlgeschlagen, bitte erneut versuchen");
	}
	
	private void kontaktHinzufuege(User uesr)
	{
		
	}
	
	private void kontaktLoeschen(User user)
	{
		
	}
	
	private void kontaktBlockieren(User user)
	{
		
	}
	
	
}
