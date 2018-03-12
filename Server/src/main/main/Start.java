package de.htwsaar.server.main;

import de.htwsaar.server.service.ServiceObjektBuilder;

import de.htwsaar.server.service.interfaces.*;
import de.htwsaar.server.dataclass.*;

/*
 * Hauptprogramm des Servers, aufruf dieser Klasse bei ankommenen der Nachricht.
 * Verteilt die Logik auf andere Klassen
 */
public class Start {

	public static final int Nachricht=0; 
	public static final int Kontakt_Hinzufuegen=1;
	public static final int Kontakt_Loeschen=2;
	public static final int Kontakt_Blockieren=3; 
	public static final int Create_Group=4;
	public static final int Kick_From_Group=5; 
	public static final int Delete_Group=6; 
	public static final int Rename_Group=7;
	public static final int Add_To_Group=8;
	MessageService messageService;
	UserService userService;
	GroupService groupService;
	KontaktService kontaktService;
	
	
	public Start()
	{
		messageService = ServiceObjektBuilder.getMessageService();
		userService = ServiceObjektBuilder.getUserService();
		groupService = ServiceObjektBuilder.getGroupService();
		kontaktService = ServiceObjektBuilder.getKontaktService();
	}

	public void messageStart(int aktion, String absenderId, int groupId, String empfaengerId, String message, long timestamp)
	{
		Message nachricht = new Message(aktion, absenderId, groupId, empfaengerId, message, timestamp);
		switch(nachricht.getAktion())
		{
		case MessageActions.Nachricht: messageService.handleMessage(nachricht);
			break;
		case MessageActions.Kontakt_Hinzufuegen:
		case MessageActions.Kontakt_Loeschen:
		case MessageActions.Kontakt_Blockieren:
		case MessageActions.Kontakt_Liste:
				kontaktService.handleKontaktConfig(nachricht);
			break;
		}
		
		
		
	}
	
	public void userStart(int aktion, String absender,  String passwort)
	{
		User user = new User(aktion, absender, passwort);
		
		userService.start(user);
		
		System.out.println(user.getReturnCode());
	}
	
	/**
	 * 
	 * @param aktion bestimmt, welche aktion ausgeführt werden soll
	 * @param groupId Id der Gruppe
	 * @param groupName Name der Gruppe
	 * @param groupAdmin Admin der Gruppe / nicht unbedingt benötigt
	 * @param UserID Benutzer für den die Aktion in der Gruppe ausgeführt werden soll. Also welcher Benutzer hinzugefügt/ gelöscht werden soll
	 */
	public void groupStart(int aktion, String absenderID, String groupName, int groupID, String empfaengerID)
	{
		Group group;
		switch(aktion)
		{
		case GroupActions.Create_Group:
			group = new Group(aktion,absenderID,groupName, groupID,empfaengerID);
			groupService.handleGroupConfig(group);
			break;
		case GroupActions.Kick_From_Group:
			group = new Group(aktion,absenderID,groupName, groupID,empfaengerID);
			groupService.handleGroupConfig(group);
			break;
		case GroupActions.Delete_Group:
			group = new Group(aktion,absenderID,groupName, groupID,empfaengerID);
			groupService.handleGroupConfig(group);
			break;
		case GroupActions.Add_To_Group:
			group = new Group(aktion,absenderID,groupName, groupID,empfaengerID);
			groupService.handleGroupConfig(group);
			break;
		}
		
	}
	public static void main(String[] args)
	{
		
		Start start = new Start();
		
		//start.userStart(1, "Marco", "Test");
		//start.userStart(1, "Alex", "Test");
		//start.userStart(1, "Daniela", "Test");
		//start.userStart(1, "Robin", "Test");
		//start.userStart(1, "Sascha", "Test");
		//start.userStart(1, "Maurice", "Test");
		
		//user Anmeldung
		
		//start.userStart(2, "Marco", "Test");
		//start.userStart(2,"Marco","aaaa");
		
		//User zur kontaktListe hinzufügen
		//start.messageStart(1,"Marco",0 , "Alex", "", (System.currentTimeMillis() / 1000L));
		//start.messageStart(1,"Marco",0 , "Daniela", "", (System.currentTimeMillis() / 1000L));
		
		//User von kontaktliste löschen
		//start.messageStart(2, "Marco", 0,"Daniela" ,"", 0);
		
		//Gruppe gründen
		//start.groupStart(Create_Group, 0, "Versuch", "Marco", "");
		
		//User zur Gruppe hinzufügen
		//start.groupStart(Add_To_Group, 1, "Versuch", "Marco", "Marco");
		//start.groupStart(Add_To_Group, 1, "Versuch", "Marco", "Alex");
		//start.groupStart(Add_To_Group, 1, "Versuch", "Marco", "Daniela");
		//start.groupStart(Add_To_Group, 1, "Versuch", "Marco", "Robin");
		//start.groupStart(Add_To_Group, 1, "Versuch", "Alex", "Marco");
		
		//User von Gruppe kicken
		
		//start.groupStart(Kick_From_Group, 1, "Versuch", "Marco", "Daniela");
		//start.groupStart(Kick_From_Group, 1, "Versuch", "Alex", "Robin");
		//start.groupStart(Kick_From_Group, 1, "Versuch", "Alex", "Alex");
		//start.groupStart(Kick_From_Group, 1, "Versuch", "Marco", "Marco");
		//Gruppe löschen
		
		//start.groupStart(Delete_Group, 1, "Versuch", "Marco", "");
		
		//Gruppe umbennen
		
		
		start.messageStart(MessageActions.Kontakt_Liste, "Marco", 0, "", "",  (System.currentTimeMillis() / 1000L));
		//Gruppennachricht versenden
		//start.messageStart(0, "Marco", 2, "", "Dies ist eine neue Test nachricht", (System.currentTimeMillis() / 1000L));
		//Nachrichten versenden
		//start.messageStart(absenderId, groupId, empfaengerId, message, timestamp);
	}
	
}
