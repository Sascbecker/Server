package de.htwsaar.server.dataclass;

import java.util.List;

import de.htwsaar.server.dataclass.*;

/**
 * dataclass for groups
 * doesn't do anything, only stores information for a group
 *
 */
public class Group {

	private int aktion;
	private String senderID;
	private String groupName;
	private int groupID;
	private String recipientId;
	private String groupAdmin;
	private List<User> groupMember;
	
	

	/**
	 * empty constructor
	 */
	public Group() {}
	
	/**
	 * constructor for new Group
	 */
	public Group(int aktion, String senderID, String groupName)
	{
		this.aktion = aktion;
		this.senderID = senderID;
		this.groupName = groupName;
	}
	
	/**
	 * constructor without admin parameter
	 */
	public Group(int aktion, String senderID, String groupName, int groupID, String recipientID )
	{
		this.aktion = aktion;
		this.senderID = senderID;
		this.groupName = groupName;
		this.groupID = groupID;
		this.recipientId = recipientID;
	}
	
	/**
	 * full constructor, not much else
	 */
	public Group(int aktion, String senderID, String groupName, int groupID, String recipientId, String GroupAdmin) {
		this.aktion=aktion;
		this.senderID = senderID;
		this.groupName=groupName;
		this.groupID=groupID;
		this.recipientId=recipientId;
		this.groupAdmin=groupAdmin;
	}
	
	
	
	
	
	public int getAktion() { return aktion;}
	public String getSender() { return senderID;}
	public String getGroupName() {return groupName;}
	public int getGroupId(){return groupID;}
	public String getRecipientId() {return recipientId;}
	public String getGroupAdmin() {return groupAdmin;}
	public List<User> getGroupMember(){ return groupMember;}
	
	public void setAktion(int aktion) { this.aktion=aktion;}
	public void setSender(String senderID) { this.senderID = senderID;}
	public void setGroupName(String name) {this.groupName=name;}
	public void setGroupId(int id){this.groupID=id;}
	public void setRecipientId(String id) {this.recipientId=id;}
	public void setGroupAdmin(String groupAdmin) {this.groupAdmin=groupAdmin;}
	public void setGroupMember(List <User> groupMember) {this.groupMember = groupMember;}

	
	
	
}
