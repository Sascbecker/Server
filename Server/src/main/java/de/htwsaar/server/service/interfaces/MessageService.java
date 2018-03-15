package de.htwsaar.server.service.interfaces;

import de.htwsaar.server.dataclass.Message;
import de.htwsaar.server.dataclass.User;

public interface MessageService {
	
	public void handleMessage(Message message);
	public void gruppenNachrichten(Message message);
	public void einzelNachricht (Message message);
	public void sendeNachricht (Message message, User user);
	public void getAndSendAllMessages(User user);
}
