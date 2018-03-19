package de.htwsaar.server.service.interfaces;

import de.htwsaar.server.dataclass.Message;
import de.htwsaar.server.dataclass.User;

public interface MessageService {
	
	public void handleMessage(Message message);
	public void groupMessage(Message message);
	public void singleMessage (Message message);
	public void sendMessage (Message message, User user);
	public void getAndSendAllMessages(User user);
}
