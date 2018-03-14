package de.htwsaar.server.service;

import java.util.List;

import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.GroupDao;
import de.htwsaar.server.dao.interfaces.UserDao;
import de.htwsaar.server.dataclass.Group;
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
	
	/**
	 * returns a List of Groups where the User is a Member
	 * @return
	 */
	public List<Group> getGroupListForUser(String userID)
	{
		List<Group> groups = groupDao.getGroupListForUser(userID);
		return groups;
	}
	/**
	 * returns a List of the Members for a given Group 
	 * @return
	 */
	public List<User> getMemberListOfGroup(int gruppenId)
	{ 
		List<User> members= userDao.selectGruppenUser(gruppenId);
		return members;
	}
	
	public void sendAllGroupInformation(List<User> user)
	{
   
	
	
	
	
	}
	
	
	

}
