package de.htwsaar.server.dataclass;

public class Message {

	private String sender;
	private int groupId;
	private String recipient;
	private String message;
	private int timestamp;

	public Message(String sender, int groupId, String recipient, String message, int timestamp)
	{
		this.sender = sender;
		this.groupId = groupId;
		this.recipient = recipient;
		this.message = message;
		this.timestamp = timestamp;
	}
	public Message() {}
	
	public String getSender()
	{
		return sender;
	}
	
	public int getGroupId()
	{
		return groupId;
	}
	
	public String getRecipient()
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
	
	public void setSender(String sender)
	{
		this.sender = sender;
	}
	
	public void setGroupId(int groupId)
	{
		this.groupId = groupId;
	}
	
	public void setRecipient(String recipient)
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
