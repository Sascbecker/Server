package de.htwsaar.server.service;

import de.htwsaar.server.dataclass.Message;


public class MessageServiceImpl {

	public void start(Message message)
	{
		if(message.getGroupId() == 0)
		{
			gruppenNachrichten(message);
		}
		else
			einzelNachricht(message);
	}
	
	private void gruppenNachrichten(Message message)
	{
		
	}
	
	private void einzelNachricht(Message message)
	{
		
	}
}
