package de.htwsaar.server.service.interfaces;

import de.htwsaar.server.dataclass.*;
public interface UserService {
	
	public void start(User user);
	public void createUser(User user);
	public void userAuthentication(User user);
	public void logOutUser(User user);

}
