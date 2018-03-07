package de.htwsaar.server.service;

import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.GroupDao;
import de.htwsaar.server.dao.interfaces.MessageDao;
import de.htwsaar.server.dao.interfaces.UserDao;
import de.htwsaar.server.dataclass.Message;

public class GroupServiceImpl {
	UserDao userDao;
	MessageDao messageDao;
	GroupDao groupDao;
	
	public static final int CREATE=5;	//TODO: Das sind alles placeholder werte
	public static final int KICK=6;	//maurice sollte wissen was die korrekten werte sind
	public static final int DELETE=7;
	public static final int RENAME=8;
	public static final int ADD=9;
	
	public GroupServiceImpl() {
		userDao = DaoObjectBuilder.getUserDao();
		messageDao = DaoObjectBuilder.getMessageDao();
		groupDao = DaoObjectBuilder.getGroupDao();
	}
	/**
	 * handles incoming configuration parameters for group conversations
	 * @param message contains the necessary information
	 */
	public void handleGroupConfig(Message message)
	{
		switch (message.getAktion()) {
		case CREATE:
			create(message);
			break;
			
		case KICK:
			kick(message);
			break;
			
		case DELETE:
			delete(message);
			break;
			
		case RENAME:
			rename(message);
			break;
			
		case ADD:
			add(message);
			break;

		}
		
		
	}
	
	/**
	 * creates a group in the database
	 */
	private void create(Message message){
		//creates a group
	}
	
	/**
	 * kicks a user from the group in the database
	 * is also used when a user leaves a group
	 */
	private void kick(Message message){
		if(message.getSender()==message.getRecipient()) {//case leave
			
		}else {//case "real" kick
			if(/*message.getSender() == gruppe.getAdmin */true ) {
				//kick the user
			}   //else do nothing
		}
	}
	/**
	 * deletes a group from the database
	 */
	private void delete(Message message){
		if(/*Message.getSender == gruppe.getAdmin */true) {
			//delete the group
		}	//else do nothing
		
	}
	/**
	 * renames a group in the database
	 */
	private void rename(Message message){
		if(/*Message.getSender == gruppe.getAdmin */true) {
			//rename the group
		}	//else do nothing
	}
	/**
	 * adds a user to a group in the database
	 */
	private void add(Message message){
		//add a user to the group
	}

}
