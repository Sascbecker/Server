package de.htwsaar.server.service;

import java.util.List;

import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.GroupDao;
import de.htwsaar.server.dao.interfaces.KontaktDao;
import de.htwsaar.server.dao.interfaces.UserDao;
import de.htwsaar.server.dataclass.*;
import de.htwsaar.server.service.interfaces.KontaktService;
public class KontaktServiceImpl implements KontaktService{

	KontaktDao kontaktDao;
	UserDao userDao;
	GroupDao groupDao;
	
	public KontaktServiceImpl()
	{
		kontaktDao = DaoObjectBuilder.getKontaktDao();
		userDao = DaoObjectBuilder.getUserDao();
		groupDao = DaoObjectBuilder.getGroupDao();
		
	}
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
	 * Service methode zum hinzufügen eines Benutzers
	 * 
	 * @param message
	 */
	private void kontaktHinzufuegen(Message message)
	{
		//TODO: Überprüfen ob Benutzer schon in Liste ist
		kontaktDao.kontaktHinzufuegen(message);
	}
	
	private void kontaktLoeschen(Message message)
	{
		//TODO: Überprüfen, ob der Benutzer noch in der Liste ist
		kontaktDao.kontaktLoeschen(message);
	}
	
	/**
	 * Erst im späteren Verlauf zu implementieren
	 * @param message
	 */
	private void kontaktBlockieren(Message message)
	{
		
	}
	
	private void kontaktListe(Message message)
	{
		Kontakte kontakt = new Kontakte();
		kontakt.setUserId(message.getSender());
		List<User> kontaktListe = userDao.selectKontakte(kontakt);
		kontakt.setKontaktListe(kontaktListe);
		
	}
}
