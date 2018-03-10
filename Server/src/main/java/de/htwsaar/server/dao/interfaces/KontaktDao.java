package de.htwsaar.server.dao.interfaces;

import de.htwsaar.server.dataclass.*;
/**
 * Interface Klasse für Kontaktverwaltung
 * Kontakt anlegen
 * Kontakt löschen
 * Kontakte ausgeben
 * Kontakt blockieren
 * @author Marco
 *
 */
public interface KontaktDao {

	public void kontaktHinzufuegen(Message message);
	public void kontaktLoeschen(Message message);
	public void kontaktBlockieren(Message message);
	

}
