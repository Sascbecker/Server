package de.htwsaar.server.service.interfaces;

import de.htwsaar.server.dataclass.Message;

public interface MessageService {
	
	public void handleMessage(Message message);

}
