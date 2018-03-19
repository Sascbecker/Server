package de.htwsaar.server.service;

import java.util.Iterator;
import java.util.List;

import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.UserDao;
import de.htwsaar.server.dataclass.User;
import de.htwsaar.server.service.interfaces.UserService;

public class UserServiceDaemon extends Thread {

	UserDao userDao;
	UserService userService;
	private Thread t;

	public UserServiceDaemon() {
		userDao = DaoObjectBuilder.getUserDao();
		userService = ServiceObjektBuilder.getUserService();
		this.startUserServiceDaemon();
		
	}

	/**
	 * Return a list of Users, which are Online
	 * 
	 * @return List
	 */

	public List<User> getAllOnlineUser() {
		List<User> user = userDao.getAllOnlineUser();

		return user;
	}

	/**
	 * sends a Request to each User in the List if the Client isn�t sending a
	 * Reply before timeout, the user gets logged out
	 */

	public boolean ping(User user) {
		boolean online;
		String ip = user.getIpAdress();
		online = de.htwsaar.service.serverConnector.ClientConnector.ping(ip);
		return online;
	}

	/**
	 * logs the User out
	 */
	public void logout(User user) {
		userService.logOutUser(user);

	}
	
	public void run()
	{
		while (true)
		{
			List<User> user = getAllOnlineUser();
			
			for(User userOnline : user)
			{
				System.out.println("Ping User: "+ userOnline.getUserID() + " an die IPAdresse = "+ userOnline.getIpAdress());
				
			}
			
			try {
				t.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void startUserServiceDaemon() {
		
//		/*	
//						while (i.hasNext() == true) 
//						{
//							final User nextUser;
//							nextUser = i.next();
//							boolean online = ping(nextUser);
//							if (online == true) {
//
//							} else {
//								logout(nextUser);
//							}
//*/						//}
//						try {
//							Thread.sleep(60000);
//						} catch (InterruptedException e) {
//							
//						}
//					}
//					
//
//				}
//
//			});
//			//userServiceDaemon.start();
//		}
	}
}
