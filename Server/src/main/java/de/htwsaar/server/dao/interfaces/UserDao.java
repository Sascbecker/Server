package de.htwsaar.server.dao.interfaces;

import de.htwsaar.server.dataclass.Kontakte;
import de.htwsaar.server.dataclass.Message;
import de.htwsaar.server.dataclass.User;
import java.util.List;

public interface UserDao {
	public void newUser(User user);
	public String getIpAddress(String userID);
	public String getPassword(String userID);
	public User getUserInformation(String userID);
	public void updateIpAddress(User user);
	public List<User> selectGroupUser(int gruppenId);
	public List<User> selectGroupUserWithoutSender(Message message);
	public List<User> selectContacts(Kontakte kontakt);
	public List<User> getAllOnlineUser();

}
