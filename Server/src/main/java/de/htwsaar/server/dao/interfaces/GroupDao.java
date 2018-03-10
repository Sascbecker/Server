package de.htwsaar.server.dao.interfaces;

import java.util.List;

/**
 * Interface Klasse 
 * @author Marco
 *
 */
public interface GroupDao {
	
	
	public void gruppeAnlegen();
	public void gruppeLöschen();
	public void gruppeVerlassen();
	public void gruppeUmbennen();
	public void nutzerZurGruppeHinzufuegen();
	public void selectGroupMember();

}
