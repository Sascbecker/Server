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
			kontaktListe(message);
			
		

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
	}
	
	/**
	 * Service method for deleting the recipient of the message from the sender's contact list
	 * @param message contains the relevant information for the contact configuration
	 */
	private void kontaktLoeschen(Message message)
	{
		//TODO: Überprüfen, ob der Benutzer noch in der Liste ist
		kontaktDao.kontaktLoeschen(message);
	}
	
	/**
	 * Erst im späteren Verlauf zu implementieren
	 * @param message contains the relevant information for the contact configuration
	 */
	private void kontaktBlockieren(Message message)
	{
		
	}
	
	private Kontakte kontaktListe(Message message)
	{
		Kontakte kontakt = new Kontakte();
		kontakt.setUserId(message.getSender());
		List<User> kontaktListe = userDao.selectKontakte(kontakt);
		kontakt.setKontaktListe(kontaktListe);
		kontakt.setGroupListe(groupDao.selectGroupInformation(kontakt.getUserId()));
		
		Iterator<Group> i = kontakt.getGroupListe().iterator();
		int j = 0;
		while(i.hasNext() == true)
		{
			kontakt.getGroupListe().get(j).setGroupMember(userDao.selectGruppenUser(kontakt.getGroupListe().get(j).getGroupId()));
			j++;
		}
		return kontakt;
		
	}
}
