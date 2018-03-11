package de.htwsaar.server.dao.interfaces;

import de.htwsaar.server.dataclass.Message;
import de.htwsaar.server.dataclass.User;
import java.util.List;

public interface UserDao {
	public void newUser(User user);
	public String getIpAdresse(String userID);
	public String getPasswort(String userID);
	public User getUserInformation(String userID);
	public void updateIpAdresse(User user);
	public List<User> selectGruppenUser(int gruppenId);
	public List<User> selectGruppenUserOhneSender(Message message);

}
