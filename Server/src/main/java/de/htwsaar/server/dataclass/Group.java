package de.htwsaar.server.dataclass;

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
	private String empfaengerID;
	private String GroupAdmin;
	

	/**
	 * empty constructor
	 */
	public Group() {}
	
	/**
	 * constructor without admin parameter
	 */
	public Group(int aktion, String senderID, String groupName, int groupID, String empfaengerID )
	{
		this.aktion = aktion;
		this.senderID = senderID;
		this.groupName = groupName;
		this.groupID = groupID;
		this.empfaengerID = empfaengerID;
	}
	
	/**
	 * full constructor, not much else
	 */
	public Group(int aktion, String senderID, String groupName, int groupID, String empfaengerID, String GroupAdmin) {
		this.aktion=aktion;
		this.senderID = senderID;
		this.groupName=groupName;
		this.groupID=groupID;
		this.empfaengerID=empfaengerID;
		this.GroupAdmin=GroupAdmin;
	}
	
	
	
	
	
	public int getAktion() { return aktion;}
	public String getSender() { return senderID;}
	public String getGroupName() {return groupName;}
	public int getGroupId(){return groupID;}
	public String getEmpfaengerId() {return empfaengerID;}
	public String getGroupAdmin() {return GroupAdmin;}
	
	public void setAktion(int aktion) { this.aktion=aktion;}
	public void setSender(String senderID) { this.senderID = senderID;}
	public void setGroupName(String name) {this.groupName=name;}
	public void setGroupId(int id){this.groupID=id;}
	public void setEmpfaengerId(String id) {this.empfaengerID=id;}
	public void setGroupAdmin(String GroupAdmin) {this.GroupAdmin=GroupAdmin;}

	
	
	
}
