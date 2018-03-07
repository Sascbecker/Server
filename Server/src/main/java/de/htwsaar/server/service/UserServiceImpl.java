package de.htwsaar.server.service;

import de.htwsaar.server.dataclass.User;
import de.htwsaar.server.dao.interfaces.*;


public class UserServiceImpl {
	
	UserDao userDao;
	GroupDao groupDao;
	
	public void start(User user)
	{
		switch(user.getAktion()) {
		
		//User anlegen
		case 1: userAnlegen(user);
			break;
		//User login
		case 2: userAuthenfizierung(user);
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
	
	

	
}
