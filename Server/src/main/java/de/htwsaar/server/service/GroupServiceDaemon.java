package de.htwsaar.server.service;

import java.util.List;

import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.GroupDao;
import de.htwsaar.server.dao.interfaces.UserDao;
import de.htwsaar.server.dataclass.User;

public class GroupServiceDaemon {
	
	UserDao userDao;
	GroupDao groupDao;
	
	public GroupServiceDaemon()
	{
		userDao = DaoObjectBuilder.getUserDao();
		groupDao = DaoObjectBuilder.getGroupDao();
		
	}
	
	public List<User> getAllOnlineUser()
	{
		List<User> user = userDao.getAllOnlineUser();
		
		return user;
	}
	
	public void sendAllGroupInformation(List<User> user)
	{
   
	
	
	
	
	}
	
	
	

}
