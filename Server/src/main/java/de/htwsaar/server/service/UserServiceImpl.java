package de.htwsaar.server.service;


import de.htwsaar.server.dataclass.User;
import de.htwsaar.server.dataclass.UserActions;
import de.htwsaar.server.service.interfaces.UserService;
import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.*;

import java.util.Iterator;
import java.util.List;
import de.htwsaar.server.service.interfaces.*;

/**
 * service class for user account configuration calls from a client (login, register, logout)
 * the start(User user) method should be called to handle such a configuration call.
 * 
 */
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	private GroupDao groupDao;
	private Thread userServiceDaemon;
	private UserServiceDaemon daemon;
	private static final int sleeptime = 60000;
	private MessageService messageService;
	private User nextUser;
	/**
	 * constructor, also launches a daemon thread in the background
	 * that thread regularly pings all online users, so don't create multiple userServices, or you'll DDOS yourself
	 */
	public UserServiceImpl()
	{
		userDao = DaoObjectBuilder.getUserDao();
		groupDao = DaoObjectBuilder.getGroupDao();
		messageService = ServiceObjektBuilder.getMessageService(); 
	   // daemon = new UserServiceDaemon();
		// startUserServiceDaemon();
		nextUser = new User();
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
	
	public void userAnlegen(User user)
	{
		//TODO: pruefen ob user bereits existiert, fehler an client senden falls ja
		try {
			userDao.newUser(user);
			System.out.println("User: "+ user.getAbsenderId() + " wurde mit dem Passwort: " + user.getPasswort() + " angelegt");
		}
		catch(Exception ex)
		{
			user.setReturnCode("User: "+ user.getAbsenderId() +" konnte nicht angelegt werden");
		}
	}
	
	public void userAuthenfizierung(User user)
	{
		
		String vergleichsUser = userDao.getPasswort(user.getAbsenderId());
		
		if(user.getPasswort().equals(vergleichsUser))
		{
			userDao.updateIpAdresse(user);
			user.setUserAuthentifizierung(true);
			System.out.println("User: "+ user.getAbsenderId()+ " wurde erfolgreich eingeloggt" );
			messageService.getAndSendAllMessages(user);
			
		}
		else
		{
			user.setUserAuthentifizierung(false);
			System.out.println("User: "+ user.getAbsenderId() + " konnte nicht eingeloggt werden. Passwort falsch");
		}
	}
	
	/**
	 * IPAdresse des Clients auf NUll setzen und in der Datenbank ändern.
	 * @param user
	 */
	public void userAbmelden(User user)
	{
		user.setIpAdresse(null);
		userDao.updateIpAdresse(user);
		System.out.println("User: "+ user.getAbsenderId() + " wurde erfolgreich ausgeloggt");
	}
	
	/**
	 * launches thread in the background to periodically check whether a user is still online
	 * updates the database accordingly
	 */
	private void startUserServiceDaemon() {
		List<User> user = daemon.getAllOnlineUser();
		Iterator<User> i = user.iterator();
		while(i.hasNext()==true)
		{
			nextUser = i.next();
		
		userServiceDaemon= new Thread(new Runnable() {
			//TODO: Seperaten Thread f�r jeden User starten
			// damit Daemon nicht zu lange warten muss
			
			public void run() {
				//TODO: implement database und network team
				//datenbank nach allen nutzern die aktuell online sind abfragen
				//all diese nutzer anpingen
				//nutzer die nicht innerhalb der timeout zeit antworten ausloggen
				//am ende, schlafe eine weile (mindestens 1 minute)
				//damit das netzwerk interface nicht zu oft mit pings belastet wird
				
				try {
					  for(int i=0; i<100; i++)
					  {
						boolean online = daemon.ping(nextUser);
						if (online == true)
						{
							
						}
						else
						{
							daemon.logout(nextUser);
						}
						userServiceDaemon.sleep(sleeptime);
					  }
				} catch (InterruptedException  e) 
				{
			         System.out.println("Thread " + " interrupted.");
				
				}
				 
		}
		
		});
		userServiceDaemon.start();
	}
}
	

	
}
