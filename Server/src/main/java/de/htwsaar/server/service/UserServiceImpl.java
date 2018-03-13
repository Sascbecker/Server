package de.htwsaar.server.service;

import de.htwsaar.server.dataclass.User;
import de.htwsaar.server.dataclass.UserActions;
import de.htwsaar.server.service.interfaces.UserService;
import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.*;

/**
 * service class for user account configuration calls from a client (login, register, logout)
 * the start(User user) method should be called to handle such a configuration call.
 * 
 */
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	private GroupDao groupDao;
	private Thread userServiceDaemon;
	
	/**
	 * constructor, also launches a daemon thread in the background
	 * that thread regularly pings all online users, so don't create multiple userServices, or you'll DDOS yourself
	 */
	public UserServiceImpl()
	{
		userDao = DaoObjectBuilder.getUserDao();
		groupDao = DaoObjectBuilder.getGroupDao();
		startUserServiceDaemon();
	}
	
	public void start(User user)
	{
		switch(user.getAktion()) {
		
		//User anlegen
		case UserActions.USER_LOGIN: userAnlegen(user);
			break;
		//User login
		case UserActions.USER_AUTHENTIFIZIERUNG: userAuthenfizierung(user);
			break;
		case UserActions.USER_LOGOUT: userAbmelden(user);
		default :
			break;
			
		}
	}
	
	private void userAnlegen(User user)
	{
		//TODO: pruefen ob user bereits existiert, fehler an client senden falls ja
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
	
	/**
	 * IPAdresse des Clients auf NUll setzen und in der Datenbank ändern.
	 * @param user
	 */
	private void userAbmelden(User user)
	{
		user.setIpAdresse(null);
		userDao.updateIpAdresse(user);
	}
	
	/**
	 * launches thread in the background to periodically check whether a user is still online
	 * updates the database accordingly
	 */
	private void startUserServiceDaemon() {
		userServiceDaemon= new Thread(new Runnable() {
			
			public void run() {
				//TODO: implement database und network team
				//datenbank nach allen nutzern die aktuell online sind abfragen
				//all diese nutzer anpingen
				//nutzer die nicht innerhalb der timeout zeit antworten ausloggen
				//am ende, schlafe eine weile (mindestens 1 minute)
				//damit das netzwerk interface nicht zu oft mit pings belastet wird
			}
		});
		userServiceDaemon.start();

	}
	

	
}
