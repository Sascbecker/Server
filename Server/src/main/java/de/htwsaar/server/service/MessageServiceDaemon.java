package de.htwsaar.server.service;

import java.util.ArrayList;
import java.util.List;

import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.MessageDao;
//import de.htwsaar.server.dao.MapSqlParameterSource;
//import de.htwsaar.server.dao.MessageDaoImpl.MessageRowMapper;
import de.htwsaar.server.dataclass.Message;
import de.htwsaar.server.dataclass.User;
import de.htwsaar.server.dao.interfaces.UserDao;

import de.htwsaar.service.serverConnector.ClientConnector;


public class MessageServiceDaemon 
{
	UserDao userDao;
	MessageDao messageDao;
	boolean delivered;
	public MessageServiceDaemon()
	{
		userDao = DaoObjectBuilder.getUserDao();
		messageDao = DaoObjectBuilder.getMessageDao();
		delivered = false;
	}

	/**
	 * Return a list of Users, which are Online
	 * @return List
	 */
	
	public List<User> getAllOnlineUser()
	{
		//UserDao userdao;
		List<User> user = userDao.getAllOnlineUser();
		
		return user;
	}
	/**
	 * creates a List of unread Messages for each User, who is online
	 * return a List, which contains all created lists
	 * @param user contains a list of Users, which are online
	 * @return
	 */
	public ArrayList<List> getUnreadMessages(List<User> user)
	{
		User currentUser = new User();
		ArrayList<List> unreadMessages = new ArrayList();
		
		for (int i =0; i< user.size(); i++)
		{
		  currentUser=user.get(i); 
		  String userId = currentUser.getAbsenderId();
		  List<Message> message = messageDao.alleUngeleseneNachrichten(userId);
			unreadMessages.add(i, message);
			
		}
		return unreadMessages;
		
		
	}
	
	public void sendUnreadMessages(ArrayList<List> unreadMessages)
	{ ClientConnector connector = new ClientConnector();
		Message message = new Message();
		
		for(int i =0; i<unreadMessages.size(); i++)
		{
			List<Message>messageList= unreadMessages.get(i);
			for(int j=0; j<messageList.size(); i++)
			{
				message = messageList.get(j);
				String sender = message.getSender();
				String empfaenger = message.getRecipient();
				int groupID = message.getGroupId();
				long timestamp = message.getTimestamp();
				String inhalt = message.getMessage();
				int aktion = message.getAktion();
				int messageID = message.getMessageID();
				//TODO 
				//Message Objekt an die Funktion zum senden der Message uebergeben
				//moegliche Implementierung
				delivered = connector.sendMessage(sender, empfaenger, groupID, timestamp, inhalt, aktion);
				
				if (delivered = true)
				{ 
					messageDao.updateDeliveredState(messageID);
				}
				
				//TODO
				//sendMessage auf true pruefen und Datenbankeintrag f�r diese Nachricht aktualisieren
			}
			
		}
	}
}