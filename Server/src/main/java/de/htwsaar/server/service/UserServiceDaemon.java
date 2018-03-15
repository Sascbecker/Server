package de.htwsaar.server.service;

import java.util.List;

import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.UserDao;
import de.htwsaar.server.dataclass.User;
import de.htwsaar.server.service.interfaces.UserService;

public class UserServiceDaemon {

	UserDao userDao;
	UserService userService;


	public UserServiceDaemon()
	{
		userDao = DaoObjectBuilder.getUserDao();
		userService = ServiceObjektBuilder.getUserService();
	}
	
	
	/**
	 * Return a list of Users, which are Online
	 * @return List
	 */
	
	public List<User> getAllOnlineUser()
	{
		List<User> user = userDao.getAllOnlineUser();
		
		return user;
	}
	
	/**
	 * sends a Request to each User in the List
	 * if the Client isn�t sending a Reply before timeout, the user gets logged out
	 */
	
	public boolean ping(User user)
	{
		boolean online;
		String ip = user.getIpAdresse();
		online = de.htwsaar.service.serverConnector.ClientConnector.ping(ip);
		return online;
	}
	/**
	 * logs the User out
	 */
	public void logout(User user)
	{
		userService.userAbmelden(user);
		
	}
}
