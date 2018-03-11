package de.htwsaar.server.service;

import de.htwsaar.server.dao.interfaces.UserDao;
import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.GroupDao;
import de.htwsaar.server.dao.interfaces.MessageDao;

import java.util.Iterator;
import java.util.List;
import de.htwsaar.server.dataclass.*;
import de.htwsaar.server.service.interfaces.MessageService;


public class MessageServiceImpl implements MessageService{
	
	UserDao userDao;
	MessageDao messageDao;
	GroupDao groupDao;

	public MessageServiceImpl()
	{
		userDao = DaoObjectBuilder.getUserDao();
		messageDao = DaoObjectBuilder.getMessageDao();
		groupDao = DaoObjectBuilder.getGroupDao();
		
	}

	private Thread messageServiceDaemon;
	/**
	 * Starts a Thread in the background.
	 * This thread will periodically check for unsent messages in the database and send them if possible.
	 */
	private void startMessageServiceDaemon(){
		messageServiceDaemon= new Thread(new Runnable() {
			
			public void run() {
				//TODO: implement
				//datenbank nach allen nutzern die aktuell online sind abfragen
				//datenbank abfragen nach ungesendeten nachrichten für diese nutzer
				//für jede der nachrichten, versuche sie zu senden 
				//wenn erfolgreich, trage dies in der datenbank ein
				//am ende, schlafe für 100ms, damit die datenbank nicht zu oft gepollt wird
			}
		});
		messageServiceDaemon.start();
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
			//Kontakt hinzufügen
		case MessageActions.Kontakt_Hinzufuegen:
			break;
			//Kontakt löschen
		case MessageActions.Kontakt_Loeschen:
			break;
			//Kontakt blockieren
		case MessageActions.Kontakt_Blockieren:
			break;
		}
		
		
	}
	

	/**
	 * Handles the processing of a group message
	 * @param message
	 */
	private void gruppenNachrichten(Message message)
	{
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
	private void einzelNachricht(Message message)
	{
		//empfangen:
		//entnehme der nachricht den Empfänger
		
		//senden:
		//sende die nachricht wie empfangen an den empfänger
		
		//Liest empfaengerDaten aus Datenbank aus.
		User empfaenger = new User();
		empfaenger = userDao.getUserInformation(message.getRecipient());
				
		sendeNachricht(message, empfaenger);
	}
	
	/**
	 * sends a message as it was received to its recipient
	 * @param message
	 * @param User
	 */
	private void sendeNachricht(Message message, User user){
		//Schreibt die Nachricht in die Datenbank, mit dem Flag, dass die nachricht noch nicht zugestellt wurde.
		messageDao.SaveMessage(message, user);
		message.setMessageID(messageDao.readMessageID());
		
		System.out.println("Absender: "+ message.getSender()+ " Empfäenger: "+ user.getAbsenderId()+ " IpAdresse: "+ user.getIpAdresse() + " MessageID: "+ message.getMessageID());
		
		
		//TODO: bei erfolgreichem Zustellen, muss das FLAG Zugestellt auf 1 gesetzt werden.
	}
}
