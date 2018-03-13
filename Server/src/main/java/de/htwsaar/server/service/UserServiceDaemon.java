package de.htwsaar.server.service;

import java.util.List;

import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.UserDao;
import de.htwsaar.server.dataclass.User;

public class UserServiceDaemon {

	UserDao userDao;
	


	public UserServiceDaemon()
	{
		userDao = DaoObjectBuilder.getUserDao();
		
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
	
	public void ping(List<User> user)
	{
		
	}
	
	public void logout()
	{
		
		
	}
}
