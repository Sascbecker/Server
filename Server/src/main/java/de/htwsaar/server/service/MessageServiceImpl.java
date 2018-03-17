package de.htwsaar.server.service;

import de.htwsaar.server.dao.interfaces.UserDao;
import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.GroupDao;
import de.htwsaar.server.dao.interfaces.MessageDao;

import java.util.Iterator;
import java.util.List;
import de.htwsaar.server.dataclass.*;
import de.htwsaar.server.service.interfaces.MessageService;
import de.htwsaar.service.serverConnector.ClientConnector;

import java.util.ArrayList;

/**
 * this class handles direct and group messages.
 * handleMessage(Message message) should be called when a message is received from a client.
 */
public class MessageServiceImpl implements MessageService{
	
	UserDao userDao;
	MessageDao messageDao;
	GroupDao groupDao;
	MessageServiceDaemon daemon;
	ClientConnector clientconnector;
	/**
	 * default constructor
	 * automatically starts a daemon thread in the background that interacts with the database and the network
	 * do not create multiple instances of this class
	 */
	public MessageServiceImpl()
	{
		userDao = DaoObjectBuilder.getUserDao();
		messageDao = DaoObjectBuilder.getMessageDao();
		groupDao = DaoObjectBuilder.getGroupDao();
	}
	
	/**
	 * Handles the Processing of a message that was just received and sends it
	 * 	to its recipient(s)
	 * @param message the message to handle
	 */
	public void handleMessage(Message message)
	{
		switch (message.getAktion())
		{
		case MessageActions.Nachricht: 
			if(message.getGroupId()== 0)
			{
				einzelNachricht(message);
			}
			else
			{
				gruppenNachrichten(message);
			}
			break;
		}	
	}
	/**
	 * Handles the processing of a group message
	 * @param message the message to send to a group
	 */
	public void gruppenNachrichten(Message message)
	{
		System.out.println("\n Gruppennachricht:");
		List<User> gruppenUser;
		User nextUser = new User();
		gruppenUser = userDao.selectGruppenUserOhneSender(message);
		
		Iterator<User> i = gruppenUser.iterator();
		
		//Durchlaufe die Liste solange es einen next eintrag gibt und sendet die Nachricht
		while(i.hasNext() == true)
		{
			nextUser = i.next();
			sendeNachricht(message, nextUser);
		}
	}
	
	
	/**
	 * handles the processing of a direct message
	 * @param message
	 */
	public void einzelNachricht(Message message)
	{
		System.out.println("\nEinzelnachricht:");
		User empfaenger = new User();
		empfaenger = userDao.getUserInformation(message.getRecipient());
				
		sendeNachricht(message, empfaenger);
	}
	
	public void getAndSendAllMessages(User user)
	{
		List<Message> allMessages = messageDao.alleNachrichtenTimestamp(user.getAbsenderId(), 0);
		
		for(Message message : allMessages)
		{
			if(message.getGroupId() == 0)
			System.out.println("Nachricht von: " + message.getSender() + " für: " + message.getRecipient() + " mit dem Inhalt  "+ message.getMessage() );
			else
				System.out.println("Nachricht in der Gruppe " + message.getGroupId() + " von " + message.getSender()+ " mit dem Inhalt" + message.getMessage());
			
			message.setDelivered(1);
			messageDao.updateDeliveredState(message);
		}
	/*	
		while(iterator.hasNext() == true)
		{
			boolean angekommen = false;
			Message message = iterator.next();
			angekommen = ClientConnector.sendMessage(message, user.getIpAdresse());
			if(angekommen == true && message.getRecipient() == user.getAbsenderId())
			{
				message.setDelivered(1);
				messageDao.updateDeliveredState(message);
			}
		}*/
	}
	
	/**
	 * sends a message as it was received to its recipient
	 * @param message the message to send
	 * @param empfaenger the recipient of the message
	 */
	public void sendeNachricht(Message message, User empfaenger){
		boolean angekommen;
		//Schreibt die Nachricht in die Datenbank, mit dem Flag, dass die nachricht noch nicht zugestellt wurde.
		messageDao.SaveMessage(message, empfaenger);
		message.setMessageID(messageDao.readMessageID());
		//angekommen = ClientConnector.sendMessage(message, empfaenger.getIpAdresse());
		
//		if (angekommen == true)
//		{
//			message.setDelivered(1);
//			messageDao.updateDeliveredState(message);
//		}
		
		if(empfaenger.getIpAdresse() == null)
		{
			System.out.println("User: "+ empfaenger.getAbsenderId() + " aktuell offline.");
		}
		else
		{
			System.out.println("Versenden der Nachricht von "+message.getSender() +" zu "+empfaenger.getAbsenderId() + " mit der Nachricht: " +message.getMessage());
			message.setDelivered(1);
			messageDao.updateDeliveredState(message);
		}
		//ClientConnector.sendMessage()
		//TODO: bei erfolgreichem Zustellen, muss das FLAG Zugestellt auf 1 gesetzt werden.
	}
}
