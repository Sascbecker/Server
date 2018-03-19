package de.htwsaar.server.dataclass;

import de.htwsaar.server.dataclass.User;

import java.util.List;

/**
 * dataclass for Contacts
 * doesn't do anything, only contains information about a user
 *
 */
public class Kontakte {

	private String userId;
	private List<User> contactList;
	private List<Group> grouplist;
	
	/**
	 * empty constructor
	 */
	public Kontakte()
	{
	}
	
	
	public String getUserId() { return userId;}
	
	public List<User> getContactList () { return contactList;}
	public List<Group> getGroupListe() { return grouplist;}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	public void setContactList(List<User> contactList) { this.contactList = contactList;}
	public void setGroupList(List<Group> grouplist) { this.grouplist = grouplist; }
}
