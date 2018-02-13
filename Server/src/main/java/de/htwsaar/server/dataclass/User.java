package de.htwsaar.server.dataclass;

/*
 * Dekleration für Aktion
 * 1 = BenutzerAnlegen
 * 2 = LogIn
 * 3 = Kontakt hinzufügen
 * 4 = Kontakt löschen
 * 5 = Kontakt blockieren
 */
public class User {
	
	private int aktion;
	private String absenderId;
	private String empfaengerId;
	private String passwort;
	
	public User() {}
	
	public User(int aktion, String absenderId, String passwort)
	{
		this.aktion = aktion;
		this.absenderId = absenderId;
		this.passwort = passwort;
	}
	
	public User(int aktion, String absenderId, String empfaengerId, String passwort)
	{
		this.aktion = aktion;
		this.absenderId = absenderId;
		this.empfaengerId = empfaengerId;
		this.passwort = passwort;
	}
	
	public int getAktion() { return aktion;}
	public String getAbsenderId() { return absenderId;}
	public String getEmpfaengerId() { return empfaengerId;}
	public String getPasswort() { return passwort;}
	
	public void setAktion(int aktion) { this.aktion = aktion;}
	public void setAbsenderId(String absenderId) {this.absenderId = absenderId;}
	public void setEmpfaengerId(String empfaengerId) {this.empfaengerId = empfaengerId;}
	public void setPasswort(String passwort) {this.passwort = passwort;}
	
}
