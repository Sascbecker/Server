package de.htwsaar.server.dao.interfaces;

import de.htwsaar.server.dataclass.User;
import java.util.List;

public interface UserDao {
	public void newUser(User user);
	public String getIpAdresse(String userID);
	public String getPasswort(String userID);
	public void updateIpAdresse(User user);

}
