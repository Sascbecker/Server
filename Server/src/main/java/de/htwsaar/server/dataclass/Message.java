package de.htwsaar.server.dataclass;

public class Message {

	private int sender;
	private int groupId;
	private int recipient;
	private String message;
	private int timestamp;

	public Message(int sender, int groupId, int recipient, String message, int timestamp)
	{
		this.sender = sender;
		this.groupId = groupId;
		this.recipient = recipient;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public int getSender()
	{
		return sender;
	}
	
	public int getGroupId()
	{
		return groupId;
	}
	
	public int getRecipient()
	{
		return recipient;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public int getTimestamp()
	{
		return timestamp;
	}
	
	public void setSender(int sender)
	{
		this.sender = sender;
	}
	
	public void setGroupId(int groupId)
	{
		this.groupId = groupId;
	}
	
	public void setRecipient(int recipient)
	{
		this.recipient = recipient;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public void setTimestamp(int timestamp)
	{
		this.timestamp = timestamp;
	}
	
}
