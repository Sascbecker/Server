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
		kontaktDao = DaoObjectBuilder.getKontaktDao();
		userDao = DaoObjectBuilder.getUserDao();
		groupDao = DaoObjectBuilder.getGroupDao();
		
	}
	
	/**
	 * Processes a configuration prompt for the contact list of the message's sender
	 * @param message contains the relevant information for the contact configuration
	 */
	public void handleKontaktConfig(Message message)
	{
		switch (message.getAktion()) {
		case MessageActions.Kontakt_Hinzufuegen:
						
			kontaktHinzufuegen(message);
			break;
			
		case MessageActions.Kontakt_Loeschen:
			kontaktLoeschen(message);
			break;
			
		case MessageActions.Kontakt_Blockieren:
			kontaktBlockieren(message);
			break;
		case MessageActions.Kontakt_Liste:
			sendeKontaktListe(message);
			
		

		}
	}
	
	/**
	 * Service method for adding the recipient of the message to the sender's contact list
	 * @param message contains the relevant information for the contact configuration
	 */
	private void kontaktHinzufuegen(Message message)
	{
		//TODO: Überprüfen ob Benutzer schon in Liste ist
		kontaktDao.kontaktHinzufuegen(message);
		System.out.println("kontakt: "+ message.getRecipient()+ " wurde zu der KontaktListe von "+message.getSender()+" hinzugefügt");
	}
	
	/**
	 * Service method for deleting the recipient of the message from the sender's contact list
	 * @param message contains the relevant information for the contact configuration
	 */
	private void kontaktLoeschen(Message message)
	{
		//TODO: Überprüfen, ob der Benutzer noch in der Liste ist
		kontaktDao.kontaktLoeschen(message);
		System.out.println("kontakt: "+ message.getRecipient()+ " wurde aus der KontaktListe von "+message.getSender()+" gelöscht");
	}
	
	/**
	 * Erst im späteren Verlauf zu implementieren
	 * @param message contains the relevant information for the contact configuration
	 */
	private void kontaktBlockieren(Message message)
	{
		
	}
	
	private Kontakte kontaktListe(Kontakte kontakt)
	{
		
		List<User> kontaktListe = userDao.selectKontakte(kontakt);
		kontakt.setKontaktListe(kontaktListe);
		kontakt.setGroupListe(groupDao.selectGroupInformation(kontakt.getUserId()));
		for(Group group : kontakt.getGroupListe())
		{
			group.setGroupMember(userDao.selectGruppenUser(group.getGroupId()));
		}
		return kontakt;
		
	}
	
	private void sendeKontaktListe(Message message)
	{
		
		Kontakte kontakt = new Kontakte();
		kontakt.setUserId(message.getSender());
		kontakt = kontaktListe(kontakt);
		
		System.out.println("Übertragen der Kontaktliste\nAlle Kontakte:");
		for(User user : kontakt.getKontaktListe())
		{
			System.out.println("User: "+ user.getAbsenderId() + " IPAdresse: " +user.getIpAdresse());
		}
		System.out.println("Alle zugehörigen Gruppen: ");
		for(Group group: kontakt.getGroupListe())
		{
			System.out.println("Gruppenname: "+ group.getGroupName());
			for(User user: group.getGroupMember())
			{
				System.out.println("\t Zugehörigen Gruppenmitglieder: "+user.getAbsenderId());
			}
		}
	}
}
