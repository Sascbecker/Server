package de.htwsaar.server.dao.interfaces;

import de.htwsaar.server.dataclass.*;
/**
 * Interface Class for contact management
 * create Contact
 * delete Contact
 * Kontakte ausgeben
 * block Contact
 * @author Marco
 *
 */
public interface KontaktDao {

	public void addContact(Message message);
	public void deleteContact(Message message);
	public void blockContact(Message message);
	

}
