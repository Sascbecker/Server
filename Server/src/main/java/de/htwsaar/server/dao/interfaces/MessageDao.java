package de.htwsaar.server.dao.interfaces;

import java.util.List;
import de.htwsaar.server.dataclass.*;

public interface MessageDao {
	public void SaveMessage(Message message, User empfaenger);
	public List<Message> alleUngeleseneNachrichten(int timestamp);
	public Message readMessage();

}
