package de.htwsaar.server.dao.interfaces;

import de.htwsaar.server.dataclass.User;

public interface UserDao {
	public void userAnlegen(User user);
	public User getPasswort(String memberId);

}
