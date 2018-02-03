package de.htwsaar.server.dataclass;

public class Member {
	
	private boolean newMember; //Gibt an, ob ein Benutzer angelegt werden soll oder nicht;
	private int id;
	private String password;
	
	public Member(boolean benutzerAnlegen, int id, String password)
	{
		this.newMember= benutzerAnlegen;
		this.id = id;
		this.password = password;
	}
	
	public Member(boolean benutzerAnlegen, String password)
	{
		this.newMember = benutzerAnlegen;
		this.password = password;
	}
	
	public boolean getBenutzerAnlegen()
	{
		return newMember;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setBenutzerAnlegen(boolean newMember)
	{
		this.newMember = newMember;
	}
	
	public void setId (int id)
	{
		this.id = id;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}

}
