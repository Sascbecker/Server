package de.htwsaar.server.dataclass;

/**
 * Klasse für alle aktionen mit Benutzer.
 * Speichern der IP-Adresse eines Benutzers in dieser Klasse
 * 
 * 
 * Deklaration für Aktion
 * 0 = nix
 * 1 = BenutzerAnlegen
 * 2 = LogIn
 */
public class User {
	
	private int aktion;
	private String absenderId;
	private String passwort;
	private String ipAdresse;
	private String returnCode;
	private boolean userAuthentication;
	

	/**
	 * empty constructor
	 */
	public User() {}
	
	/**
	 * Constructor for user logout. Doesn't require passwort or IP-Adress
	 * @param aktion
	 * @param absenderId
	 */
	public User(int aktion, String absenderId)
	{
		this.aktion = aktion;
		this.absenderId = absenderId;
	}
	/**
	 * constructor without returnCode and ipAdresse
	 */
	public User(int aktion, String absenderId, String passwort)
	{
		this.aktion = aktion;
		this.absenderId = absenderId;
		this.passwort = passwort;
	}
	/**
	 * constructor without returnCode and ipAdresse, empfaengerId is unused
	 */

	public User(int aktion, String absenderId, String passwort, String ipAdresse)
	{
		this.aktion = aktion;
		this.absenderId = absenderId;
		this.passwort = passwort;
		this.ipAdresse = ipAdresse;
	}
	
	
	public int getAktion() { return aktion;}
	public String getUserID() { return absenderId;}
	public String getPassword() { return passwort;}
	public String getIpAdress() { return ipAdresse;}
	public String getReturnCode() { return returnCode;}
	public boolean getUserAuthentifizierung() { return userAuthentication;}
	
	public void setAktion(int aktion) { this.aktion = aktion;}
	public void setUserID(String absenderId) {this.absenderId = absenderId;}
	public void setPasswort(String passwort) {this.passwort = passwort;}
	public void setIpAdress(String ipAdresse) { this.ipAdresse = ipAdresse;}
	public void setReturnCode(String returnCode) {this.returnCode = returnCode;}
	public void setUserAuthentication(boolean userAuthentication) { this.userAuthentication = userAuthentication;}
	
}
