package de.htwsaar.server.service;

import java.util.Iterator;
import java.util.List;

import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.GroupDao;
import de.htwsaar.server.dao.interfaces.UserDao;
import de.htwsaar.server.dataclass.Group;
import de.htwsaar.server.dataclass.User;

/**
 * Creats the GroupServiceDaemon
 * GroupServiceDaeom is for
 * @author Marco
 *
 */
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
		List<User> members= userDao.selectGroupUser(gruppenId);
		return members;
	}
	
	public void sendAllGroupInformation(List<User> user)
	{
  
	}
	
	
	
	private void startGroupServiceDaemon(){
		Thread groupServiceDaemon= new Thread(new Runnable() {
			
			public void run() {
				//TODO: implement
				//Datenbank abfragen nach nutzern die grade online sind
				//für jeden von denen:
				//sende ihnen die gesamten informationen aller gruppen zu in denen sie sind
				//schlafe für ein paar sekunden, damit das alles nicht zu oft gesendet wird
				while (true)
				{
				List<User> user = getAllOnlineUser();
				Iterator<User> i = user.iterator();
				
				while(i.hasNext()==true)
				{
					final User nextUser;
					nextUser = i.next();
					String userID = nextUser.getUserID();
					 List<Group> groupList = getGroupListForUser(userID);
					 Iterator<Group> j = groupList.iterator();
					 while(i.hasNext()==true)
					 {
						 final Group nextGroup;
						 nextGroup = j.next();
						 int groupID = nextGroup.getGroupId();
						 List<User> members = getMemberListOfGroup(groupID);
					 }
		         }
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					
				}
				
			   }
		}
		});
		groupServiceDaemon.start();
	}
	
	
	

}
