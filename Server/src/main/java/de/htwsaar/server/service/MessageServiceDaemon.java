package de.htwsaar.server.service;

import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;

import de.htwsaar.server.dao.DaoObjectBuilder;
import de.htwsaar.server.dao.interfaces.MessageDao;
import de.htwsaar.server.dataclass.Message;
import de.htwsaar.server.dataclass.User;
import de.htwsaar.server.dao.interfaces.UserDao;
import de.htwsaar.server.service.interfaces.*;
import de.htwsaar.service.serverConnector.ClientConnector;
/**
 * Class for sending all Messages
 * @author Marco
 *
 */
public class MessageServiceDaemon implements Runnable 
{
	UserDao userDao;
	MessageDao messageDao;
	MessageService messageService;
	User user;
	
	public MessageServiceDaemon(User user)
	{
		this.user = user;
		userDao = DaoObjectBuilder.getUserDao();
		messageDao = DaoObjectBuilder.getMessageDao();
		messageService = ServiceObjektBuilder.getMessageService();
	}

	/**
	 * Return a list of Users, which are Online
	 * @return List
	 */
	
	public void run()
	{
		//Sende alle Nachrichten
		messageService.getAndSendAllMessages(user);
		
	}
}