package de.htwsaar.server.service.interfaces;

import de.htwsaar.server.dataclass.*;
public interface UserService {
	
	public void userAnlegen(User user);
	public boolean userAuthenfizierung(User user);	
	public void kontaktHinzufuege(User uesr);	
	public void kontaktLoeschen(User user);	
	public void kontaktBlockieren(User user);
	

}
