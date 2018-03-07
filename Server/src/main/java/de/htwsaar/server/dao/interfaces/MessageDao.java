package de.htwsaar.server.dao.interfaces;

import java.util.List;
import de.htwsaar.server.dataclass.*;

public interface MessageDao {
	public void SaveMessage(Message message, User empfaenger);
	public void kontaktHinzufügen(Message message);
	public void kontaktLöschen(Message message);
	public void kontaktBlockieren(Message message);
	public List<Message> alleUngeleseneNachrichten(int timestamp);
	public Message readMessage();

}
