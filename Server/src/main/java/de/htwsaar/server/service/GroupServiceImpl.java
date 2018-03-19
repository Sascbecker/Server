package de.htwsaar.server.service;

import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.GroupDao;
import de.htwsaar.server.dao.interfaces.MessageDao;
import de.htwsaar.server.dao.interfaces.UserDao;
import de.htwsaar.server.dataclass.GroupActions;
import de.htwsaar.server.dataclass.User;
import de.htwsaar.server.dataclass.Group;
import de.htwsaar.server.service.interfaces.GroupService;

import java.util.Iterator;
import java.util.List;

/**
 * this class handles requests for group management.
 * handleGroupConfig(Message message) should be called when such a message is received from a client.
 */
public class GroupServiceImpl implements GroupService{
	UserDao userDao;
	MessageDao messageDao;
	GroupDao groupDao;
	private Thread groupServiceDaemon;
	GroupServiceDaemon daemon;
	private User nextUser;
	private Group nextGroup;

	
	/**
	 * default constructor.
	 * automatically starts a daemon thread in the background, so make sure not to create 
	 * too many instances of this class, ideally just one.
	 */
	public GroupServiceImpl() {
		userDao = DaoObjectBuilder.getUserDao();
		messageDao = DaoObjectBuilder.getMessageDao();
		groupDao = DaoObjectBuilder.getGroupDao();
		//daemon = new GroupServiceDaemon();
		//startGroupServiceDaemon();
		nextUser = new User();
		nextGroup = new Group();
	}
	/**
	 * handles incoming configuration parameters for group conversations
	 * @param message contains the necessary information
	 */
	public void handleGroupConfig(Group group)
	{
		switch (group.getAktion()) {
		case GroupActions.Create_Group:
						
			create(group);
			break;
			
		case GroupActions.Kick_From_Group:
			kick(group);
			break;
			
		case GroupActions.Delete_Group:
			delete(group);
			break;
			
		case GroupActions.Rename_Group:
			rename(group);
			break;
			
		case GroupActions.Add_To_Group:
			add(group);
			break;

		}
		
		
	}
	
	private void startGroupServiceDaemon(){
		groupServiceDaemon= new Thread(new Runnable() {
			
			public void run() {
				//TODO: implement
				//Datenbank abfragen nach nutzern die grade online sind
				//für jeden von denen:
				//sende ihnen die gesamten informationen aller gruppen zu in denen sie sind
				//schlafe für ein paar sekunden, damit das alles nicht zu oft gesendet wird
				List<User> user = daemon.getAllOnlineUser();
				Iterator<User> i = user.iterator();
				while(i.hasNext()==true)
				{
					nextUser = i.next();
					String userID = nextUser.getUserID();
					 List<Group> groupList = daemon.getGroupListForUser(userID);
					 Iterator<Group> j = groupList.iterator();
					 while(i.hasNext()==true)
					 {
						 nextGroup = j.next();
						 int groupID = nextGroup.getGroupId();
						 List<User> members = daemon.getMemberListOfGroup(groupID);
					 }
		         }
			}
		});
		groupServiceDaemon.start();
	}

	
	/**
	 * creates a group in the database
	 * @param group contains the information for the group config to take place
	 * TODO überprüfen, ob es die Gruppe schon gibt, nach anlegen der Gruppe muss sofort die GruppenID ausgelesen werden.
	 */
	private void create(Group group){
		groupDao.createGroup(group);
		group.setGroupId(groupDao.getGroupID());
		group.setRecipientId(group.getSender());
		group.setGroupAdmin(group.getSender());
		System.out.println("Gruppe wurde erfolgreich angelegt mit der\nID: "+ group.getGroupId()
		+" mit dem Namen "+ group.getGroupName()
		+" Gruppenadmin: "+ group.getGroupAdmin());
		add(group);
		
	}
	
	/**
	 * kicks a user from the group in the database
	 * is also used when a user leaves a group
	 * @param group contains the information for the group config to take place
	 */
	private void kick(Group group){
		group.setGroupAdmin(groupDao.selectGroupAdmin(group.getGroupId()));
		
		//Schaut ob der Benutzer der die Anfrage geschickt hat auch der Admin der Gruppe ist, wird gebraucht auch um Leute zu kicken
		if(group.getSender().equals(group.getGroupAdmin()))
		{
			//Wenn Admin und sender überrein stimmen, wird die Gruppe gelöscht
			if(group.getSender().equals(group.getRecipientId()))
			{
				delete(group);
				System.out.println("Gruppe:" + group.getGroupName()+ " wurde gelöscht da Admin die Gruppe verlassen hat");
			}
			//Ansonsten wird der Benutzer gekickt
			else
			{
				groupDao.leaveGroup(group);
				System.out.println("User: "+group.getRecipientId() + " wurder aus der Gruppe gekickt");
			}
		}
		//Schaut, ob der Sender auch der ist, der aus der Gruppe raus gehen möchte
		else if(group.getSender().equals(group.getRecipientId()))
		{
			groupDao.leaveGroup(group);
			System.out.println("User :" + group.getSender()+ " hat erfolgreich die Gruppe verlassen");
		}
		//Nicht möglich wenn man vesucht andere zu kicken
		else
		{
			//TODO Fehlermeldung
			System.out.println("Keine Berechtigung um leute aus der Gruppe zu kicken");
		}
	}
	/**
	 * deletes a group from the database
	 * @param group contains the information for the group config to take place
	 */
	private void delete(Group group){
		
		group.setGroupAdmin(groupDao.selectGroupAdmin(group.getGroupId()));
		
		if(group.getSender().equals(group.getGroupAdmin())) {
			groupDao.deleteGroup(group);
			groupDao.deleteMember(group);
		}
		else
		{
			System.out.println("Keine Berechtigung zum löschen der Gruppe");
		}
		
	}
	/**
	 * renames a group in the database
	 * @param group contains the information for the group config to take place
	 */
	private void rename(Group group){
		
		group.setGroupAdmin(groupDao.selectGroupAdmin(group.getGroupId()));
		if(group.getSender().equals(group.getGroupAdmin())) {
			groupDao.renameGroup(group);
			System.out.println("Gruppe wurde in " + group.getGroupName()+ " umbennant");
		}
		else
		{
			System.out.println("Keine Berechtigung zum ändern des Namens der Gruppe");
		}
	}
	/**
	 * adds a user to a group in the database
	 * @param group contains the information for the group config to take place
	 * TODO: überprüfen, ob der Benutzer schon in der Gruppe vorhanden ist
	 */
	private void add(Group group){
		group.setGroupAdmin(groupDao.selectGroupAdmin(group.getGroupId()));
		
		if(group.getSender().equals(group.getGroupAdmin()) == true)
		{
			groupDao.addUserToGroup(group);
			System.out.println("User: "+group.getRecipientId()+ " wurde erfolgreich zur gruppe hinzugefügt");
		}
		else
		{
			System.out.println("Kein GruppenAdmin");
		}
			
	}

}
