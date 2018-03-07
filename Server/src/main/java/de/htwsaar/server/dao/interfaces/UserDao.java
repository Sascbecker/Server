package de.htwsaar.server.dao.interfaces;

import de.htwsaar.server.dataclass.User;
import java.util.List;

public interface UserDao {
	public void insertUser(User user);
	public User getUser(String empfaengerId);
	public User getPasswort(String memberId);
	public List<User> selectGruppenUser(int GruppenId);

}
