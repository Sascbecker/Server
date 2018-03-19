package de.htwsaar.server.dao.interfaces;

import java.util.List;
import de.htwsaar.server.dataclass.*;

public interface MessageDao {
	public void SaveMessage(Message message, User empfaenger);
	public List<Message> allUnreadMessages(String userID);
	public List<Message> allMessagesTimestamp(String userID, int timestamp);
	public int readMessageID();
	public Message readMessage();
	public void updateDeliveredState(Message message);

}
