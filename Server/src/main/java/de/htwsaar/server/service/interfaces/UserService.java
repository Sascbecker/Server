package de.htwsaar.server.service.interfaces;

import de.htwsaar.server.dataclass.*;
public interface UserService {
	
	public void start(User user);
	public void userAnlegen(User user);
	public void userAuthenfizierung(User user);
	public void userAbmelden(User user);

}
