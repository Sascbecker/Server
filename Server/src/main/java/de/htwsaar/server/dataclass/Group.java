package de.htwsaar.server.dataclass;

public class Group {

	private int aktion;
	private String absenderId;
	private String GroupName;
	private int GroupID;
	private String empfaengerID;
	private String GroupAdmin;
	

	public Group() {}
	public Group(int aktion, String absenderId, String GroupName, int GroupID, String empfaengerID, String GroupAdmin) {
		this.aktion=aktion;
		this.absenderId=absenderId;
		this.GroupName=GroupName;
		this.GroupID=GroupID;
		this.empfaengerID=empfaengerID;
		this.GroupAdmin=GroupAdmin;
	}
	
	
	
	
	
	public int getAktion() { return aktion;}
	public String getSender() { return absenderId;}
	public String getGroupName() {return GroupName;}
	public int getGroupId(){return GroupID;}
	public String getEmpfaengerId() {return empfaengerID;}
	public String getGroupAdmin() {return GroupAdmin;}
	
	public void setAktion(int aktion) { this.aktion=aktion;}
	public void setSender(String id) { this.absenderId=id;}
	public void setGroupName(String name) {this.GroupName=name;}
	public void setGroupId(int id){this.GroupID=id;}
	public void setEmpfaengerId(String id) {this.empfaengerID=id;}
	public void setGroupAdmin(String GroupAdmin) {this.GroupAdmin=GroupAdmin;}

	
	
	
}
