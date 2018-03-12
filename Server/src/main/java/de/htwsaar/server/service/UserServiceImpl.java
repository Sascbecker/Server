package de.htwsaar.server.service;

import de.htwsaar.server.dataclass.User;
import de.htwsaar.server.dataclass.UserActions;
import de.htwsaar.server.service.interfaces.UserService;
import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.*;

/**
 * service class for user account configuration
 * 
 */
public class UserServiceImpl implements UserService {
	
	UserDao userDao;
	GroupDao groupDao;
	
	public UserServiceImpl()
	{
		userDao = DaoObjectBuilder.getUserDao();
		groupDao = DaoObjectBuilder.getGroupDao();
	}
	
	public void start(User user)
	{
		switch(user.getAktion()) {
		
		//User anlegen
		case UserActions.USER_ANLEGEN: userAnlegen(user);
			break;
		//User login
		case UserActions.USER_AUTHENTIFIZIERUNG: userAuthenfizierung(user);
			break;
		case UserActions.USER_ABMELDEN: userAbmelden(user);
		default :
			break;
			
		}
	}
	
	private void userAnlegen(User user)
	{
		try {
			userDao.newUser(user);
		}
		catch(Exception ex)
		{
			user.setReturnCode("User konnte nicht angelegt werden");
		}
	}
	
	private void userAuthenfizierung(User user)
	{
		
		String vergleichsUser = userDao.getPasswort(user.getAbsenderId());
		
		if(user.getPasswort().equals(vergleichsUser))
		{
			userDao.updateIpAdresse(user);
			user.setReturnCode("Authentifizierung erfolgreich");
			
		}
		else
			user.setReturnCode("Authentifizierung fehlgeschlagen, bitte erneut versuchen");
	}
	
	private void userAbmelden(User user)
	{
		user.setIpAdresse("");
		userDao.updateIpAdresse(user);
	}
	

	
}
