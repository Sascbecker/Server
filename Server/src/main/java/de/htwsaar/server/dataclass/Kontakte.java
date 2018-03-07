package de.htwsaar.server.dataclass;

import de.htwsaar.server.dataclass.*;

import java.util.List;

public class Kontakte {

	private String userId;
	private List<User> kontaktliste;
	
	public Kontakte()
	{
	}
	
	public String getUserId() { return userId;}
	
	public List<User> getKontaktListe () { return kontaktliste;}
	
}