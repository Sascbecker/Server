package de.htwsaar.server.service;

import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.GroupDao;
import de.htwsaar.server.dao.interfaces.MessageDao;
import de.htwsaar.server.dao.interfaces.UserDao;
import de.htwsaar.server.dataclass.Actions;
import de.htwsaar.server.dataclass.Group;
import de.htwsaar.server.service.interfaces.GroupService;

public class GroupServiceImpl implements GroupService{
	UserDao userDao;
	MessageDao messageDao;
	GroupDao groupDao;
	private Thread groupServiceDaemon;

	
	
	public GroupServiceImpl() {
		userDao = DaoObjectBuilder.getUserDao();
		messageDao = DaoObjectBuilder.getMessageDao();
		groupDao = DaoObjectBuilder.getGroupDao();
	}
	/**
	 * handles incoming configuration parameters for group conversations
	 * @param message contains the necessary information
	 */
	public void handleGroupConfig(Group group)
	{
		switch (group.getAktion()) {
		case Actions.Create_Group:
						
			create(group);
			break;
			
		case Actions.Kick_From_Group:
			kick(group);
			break;
			
		case Actions.Delete_Group:
			delete(group);
			break;
			
		case Actions.Rename_Group:
			rename(group);
			break;
			
		case Actions.Add_To_Group:
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
				
			}
		});
		groupServiceDaemon.start();
	}

	
	/**
	 * creates a group in the database
	 * TODO überprüfen, ob es die Gruppe schon gibt
	 */
	private void create(Group group){
		groupDao.gruppeAnlegen(group);
	}
	
	/**
	 * kicks a user from the group in the database
	 * is also used when a user leaves a group
	 */
	private void kick(Group group){
		if(group.getSender() == group.getGroupUser()) {//case leave
			
		}else {//case "real" kick
			if(/*message.getSender() == gruppe.getAdmin */true ) {
				//kick the user
			}   //else do nothing
		}
	}
	/**
	 * deletes a group from the database
	 */
	private void delete(Group group){
		if(/*Message.getSender == gruppe.getAdmin */true) {
			//delete the group
		}	//else do nothing
		
	}
	/**
	 * renames a group in the database
	 */
	private void rename(Group group){
		if(/*Message.getSender == gruppe.getAdmin */true) {
			//rename the group
		}	//else do nothing
	}
	/**
	 * adds a user to a group in the database
	 * TODO: überprüfen, ob der Benutzer schon in der Gruppe vorhanden ist
	 */
	private void add(Group group){
		String admin = groupDao.selectGroupAdmin(group.getGroupId());
		
		if(group.getSender().equals(admin) == true)
		{
			groupDao.nutzerZurGruppeHinzufuegen(group);
		}
		else
		{
			System.out.println("Kein GruppenAdmin");
		}
			
	}

}
