package de.htwsaar.server.dao.interfaces;

import java.util.List;
import de.htwsaar.server.dataclass.*;

/**
 * Interface Klasse 
 * @author Marco
 *
 */
public interface GroupDao {
	
	
	public void createGroup(Group group);
	public void deleteGroup(Group group);
	public void deleteMember(Group group);
	public void leaveGroup(Group group);
	public void renameGroup(Group group);
	public void addUserToGroup(Group group);
	public int getGroupID();
	public String selectGroupAdmin(int groupID);
	public List<Group> getGroupListForUser(String userID);
	public List<Group> selectGroupInformation(String UserID);
}
