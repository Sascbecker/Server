package de.htwsaar.server.dao.interfaces;

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

}
