package de.htwsaar.server.service;

import java.util.ArrayList;
import java.util.List;
import de.htwsaar.server.dao.interfaces.MessageDao;
import de.htwsaar.server.dao.MapSqlParameterSource;
import de.htwsaar.server.dao.MessageDaoImpl.MessageRowMapper;
import de.htwsaar.server.dataclass.Message;
import de.htwsaar.server.dataclass.User;


public class MessageServiceDaemon 
{

	/**
	 * Return a list of Users, which are Online
	 * @return List
	 */
	
	public List<User> getAllOnlineUser()
	{
		
		String query = "Select * from User where length(IPAdresse)>5";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		return jdbc.query(query,paramSource, new MessageRowMapper());
	}
	/**
	 * creates a List of unread Messages for each User, who is online
	 * return a List, which contains all created lists
	 * @param user contains a list of Users, which are online
	 * @return
	 */
	public ArrayList<List> getUnreadMessages(List<User> user)
	{   MessageDao messagedao;
		User currentUser = new User();
		ArrayList<List> unreadMessages = new ArrayList();
		
		for (int i =0; i< user.size(); i++)
		{
		  currentUser=user.get(i); 
		  String userId = currentUser.getAbsenderId();
		  List<Message> message = messagedao.alleUngeleseneNachrichten(userId);
			unreadMessages.add(i, message);
			
		}
		return unreadMessages;
		
		
	}
	
	public void sendUnreadMessages(ArrayList<List> unreadMessages)
	{
		Message message = new Message();
		
		for(int i =0; i<unreadMessages.size(); i++)
		{
			List<Message>messageList= unreadMessages.get(i);
			for(int j=0; j<messageList.size(); i++)
			{
				message = messageList.get(j);
				message.getMessage();
			}
			
		}
	}
}