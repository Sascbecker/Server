package de.htwsaar.server.dataclass;


/**
 * Klasse für alle aktionen von Nachrichten
 * 
 * 
 * Deklaration Nachrichten
 * Aktion = 0 Message
 * Aktion = 1 add Contact
 * Aktion = 2 delete Contact
 * Aktion = 3 block Contact
 * 
 * @author mabecker
 *
 */
public class Message {

	private int aktion;
	private int messageID;
	private String sender;
	private int groupId;
	private String recipient;
	private String message;
	private long timestamp;
	private int delivered;
	
	/**
	 * empty constructor
	 */
	public Message() {}
	
	public Message(int aktion, String sender)
	{
		this.aktion = aktion;
		this.sender = sender;
	}
	
	/**
	 * constructor without messageId and aktion
	 */
	public Message(String sender, int groupId, String recipient, String message, long timestamp)
	{
		aktion = 0;
		this.sender = sender;
		this.groupId = groupId;
		this.recipient = recipient;
		this.message = message;
		this.timestamp = timestamp;
	}
	/**
	 * constructor without messageID
	 */
	public Message(int aktion, String sender, int groupId, String recipient, String message, long timestamp)
	{
		this.aktion = aktion;
		this.sender = sender;
		this.groupId = groupId;
		this.recipient = recipient;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public int getAktion() {return aktion;}	
	public int getMessageID() { return messageID;}
	public String getSender() {return sender;}
	public int getGroupId() {return groupId;}	
	public String getRecipient() {return recipient;}	
	public String getMessage() {return message;}
	public long getTimestamp() {return timestamp;}
	public int getDelivered() {return delivered;}
	
	public void setAktion(int aktion) {this.aktion = aktion;}
	public void setMessageID(int messageID) { this.messageID = messageID;}
	public void setSender(String sender) {this.sender = sender;}
	public void setGroupId(int groupId) {this.groupId = groupId;}	
	public void setRecipient(String recipient) {this.recipient = recipient;}
	public void setMessage(String message) {this.message = message;}
	public void setTimestamp(int timestamp) {this.timestamp = timestamp;}
	public void setDelivered(int delivered) { this.delivered = delivered;}
	
}
