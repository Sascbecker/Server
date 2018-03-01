package de.htwsaar.server.dataclass;


/**
 * Klasse für alle aktionen von Nachrichten
 * 
 * 
 * Deklaration Nachrichten
 * Aktion = 0 Nachricht
 * Aktion = 1 Kontakt hinzufügen
 * Aktion = 2 Kontakt löschen
 * Aktion = 3 Kontakt blockieren
 * 
 * @author mabecker
 *
 */
public class Message {

	private int aktion;
	private String sender;
	private int groupId;
	private String recipient;
	private String message;
	private int timestamp;

	public Message() {}
	
	public Message(String sender, int groupId, String recipient, String message, int timestamp)
	{
		aktion = 0;
		this.sender = sender;
		this.groupId = groupId;
		this.recipient = recipient;
		this.message = message;
		this.timestamp = timestamp;
	}
	public Message(int aktion, String sender, int groupId, String recipient, String message, int timestamp)
	{
		this.aktion = aktion;
		this.sender = sender;
		this.groupId = groupId;
		this.recipient = recipient;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public int getAktion() {return aktion;}	
	public String getSender() {return sender;}
	public int getGroupId() {return groupId;}	
	public String getRecipient() {return recipient;}	
	public String getMessage() {return message;}
	public int getTimestamp() {return timestamp;}
	
	public void setAktion(int aktion) {this.aktion = aktion;}
	public void setSender(String sender) {this.sender = sender;}
	public void setGroupId(int groupId) {this.groupId = groupId;}	
	public void setRecipient(String recipient) {this.recipient = recipient;}
	public void setMessage(String message) {this.message = message;}
	public void setTimestamp(int timestamp) {this.timestamp = timestamp;}
	
}
