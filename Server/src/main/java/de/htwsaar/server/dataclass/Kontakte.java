package de.htwsaar.server.dataclass;

import de.htwsaar.server.dataclass.*;

import java.util.List;

public class Kontakte {

	private String userId;
	private List<String> kontaktliste;
	
	public Kontakte()
	{
	}
	
	public String getUserId() { return userId;}
	
	public List<String> getKontaktListe () { return kontaktliste;}
	
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	public void setKontaktListe(List<String> kontaktListe) { this.kontaktliste = kontaktListe;}
}
