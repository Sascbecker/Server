package de.htwsaar.server.service;

import de.htwsaar.server.dataclass.User;
import de.htwsaar.server.dao.interfaces.UserDao;

public class UserServiceImpl {
	
	UserDao userDao;
	
	public void userAnlegen(User user)
	{
		userDao.userAnlegen(user);
	}
	
	public boolean UserAuthenfizierung(User user)
	{
		User vergleichsUser = new User();
		vergleichsUser = userDao.getPasswort(user.getAbsenderId());
		
		if(vergleichsUser.getPasswort() == user.getPasswort())
		{
			return true;
		}
		else
			return false;
	}
	
	public void kontaktHinzufuege(User uesr)
	{
		
	}
	
	public void kontaktLoeschen(User user)
	{
		
	}
	
	public void kontaktBlockieren(User user)
	{
		
	}
	
	
}
