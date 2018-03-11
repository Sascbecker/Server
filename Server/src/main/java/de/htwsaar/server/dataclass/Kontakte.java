package de.htwsaar.server.dataclass;

import de.htwsaar.server.dataclass.User;

import java.util.List;

/**
 * dataclass for Users
 * doesn't do anything, only contains information about a user
 *
 */
public class Kontakte {

	private String userId;
	private List<User> kontaktliste;
	
	/**
	 * empty constructor
	 */
	public Kontakte()
	{
	}
	
	
	public String getUserId() { return userId;}
	
	public List<User> getKontaktListe () { return kontaktliste;}
	
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	public void setKontaktListe(List<User> kontaktListe) { this.kontaktliste = kontaktListe;}
}
