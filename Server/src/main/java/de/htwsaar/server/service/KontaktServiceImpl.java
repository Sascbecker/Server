package de.htwsaar.server.service;

import java.util.Iterator;
import java.util.List;
import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.GroupDao;
import de.htwsaar.server.dao.interfaces.KontaktDao;
import de.htwsaar.server.dao.interfaces.UserDao;
import de.htwsaar.server.dataclass.*;
import de.htwsaar.server.service.interfaces.KontaktService;

/**
 * this class handles requests for contact list changes sent from the clients.
 * handleKontaktConfig(Message message) should be called when such a message is received from a client.
 *
 */
public class KontaktServiceImpl implements KontaktService{

	KontaktDao kontaktDao;
	UserDao userDao;
	GroupDao groupDao;
	
	/**
	 * default constructor
	 */
	public KontaktServiceImpl()
	{
		kontaktDao = DaoObjectBuilder.getContactDao();
		userDao = DaoObjectBuilder.getUserDao();
		groupDao = DaoObjectBuilder.getGroupDao();
		
	}
	
	/**
	 * Processes a configuration prompt for the contact list of the message's sender
	 * @param message contains the relevant information for the contact configuration
	 */
	public void handleContactConfig(Message message)
	{
		switch (message.getAktion()) {
		case MessageActions.Add_Contact:
						
			addContact(message);
			break;
			
		case MessageActions.Delete_Contact:
			deleteContact(message);
			break;
			
		case MessageActions.Block_Contact:
			blockContact(message);
			break;
		case MessageActions.Kontakt_List:
			sendContactList(message);
			
		

		}
	}
	
	/**
	 * Service method for adding the recipient of the message to the sender's contact list
	 * @param message contains the relevant information for the contact configuration
	 */
	private void addContact(Message message)
	{
		//TODO: Überprüfen ob Benutzer schon in Liste ist
		kontaktDao.addContact(message);
		System.out.println("kontakt: "+ message.getRecipient()+ " wurde zu der KontaktListe von "+message.getSender()+" hinzugefügt");
	}
	
	/**
	 * Service method for deleting the recipient of the message from the sender's contact list
	 * @param message contains the relevant information for the contact configuration
	 */
	private void deleteContact(Message message)
	{
		//TODO: Überprüfen, ob der Benutzer noch in der Liste ist
		kontaktDao.deleteContact(message);
		System.out.println("kontakt: "+ message.getRecipient()+ " wurde aus der KontaktListe von "+message.getSender()+" gelöscht");
	}
	
	/**
	 * Erst im späteren Verlauf zu implementieren
	 * @param message contains the relevant information for the contact configuration
	 */
	private void blockContact(Message message)
	{
		
	}
	
	private Kontakte contactList(Kontakte contact)
	{
		
		List<User> kontaktListe = userDao.selectContacts(contact);
		contact.setContactList(kontaktListe);
		contact.setGroupList(groupDao.selectGroupInformation(contact.getUserId()));
		for(Group group : contact.getGroupListe())
		{
			group.setGroupMember(userDao.selectGroupUser(group.getGroupId()));
		}
		return contact;
		
	}
	
	private void sendContactList(Message message)
	{
		
		Kontakte kontakt = new Kontakte();
		kontakt.setUserId(message.getSender());
		kontakt = contactList(kontakt);
		
		System.out.println("Übertragen der Kontaktliste\nAlle Kontakte:");
		for(User user : kontakt.getContactList())
		{
			System.out.println("User: "+ user.getUserID() + " IPAdresse: " +user.getIpAdress());
		}
		System.out.println("Alle zugehörigen Gruppen: ");
		for(Group group: kontakt.getGroupListe())
		{
			System.out.println("Gruppenname: "+ group.getGroupName());
			for(User user: group.getGroupMember())
			{
				System.out.println("\t Zugehörigen Gruppenmitglieder: "+user.getUserID());
			}
		}
	}
}
