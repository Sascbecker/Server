package de.htwsaar.server.dao.interfaces;

import java.util.List;
import de.htwsaar.server.dataclass.*;

/**
 * Interface Klasse 
 * @author Marco
 *
 */
public interface GroupDao {
	
	
	public void gruppeAnlegen(Group group);
	public void gruppeLöschen(Group group);
	public void deleteMember(Group group);
	public void gruppeVerlassen(Group group);
	public void gruppeUmbennen(Group group);
	public void nutzerZurGruppeHinzufuegen(Group group);
	public String selectGroupAdmin(int groupID);
	public void selectGroupMember();

}
