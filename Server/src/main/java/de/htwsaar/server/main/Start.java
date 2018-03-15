package de.htwsaar.server.main;

import de.htwsaar.server.service.ServiceObjektBuilder;

import de.htwsaar.server.service.interfaces.*;
import de.htwsaar.server.dataclass.*;
import de.htwsaar.server.service.*;

/*
 * Hauptprogramm des Servers, aufruf dieser Klasse bei ankommenen der Nachricht.
 * Verteilt die Logik auf andere Klassen
 */
public class Start {

	MessageService messageService;
	UserService userService;
	GroupService groupService;
	KontaktService kontaktService;
	//GroupServiceDaemon groupServiceDeamon;
	//MessageServiceDaemon messageServiceDeamon;
	//UserServiceDaemon userServiceDeamon;
	
	
	public Start()
	{
		messageService = ServiceObjektBuilder.getMessageService();
		userService = ServiceObjektBuilder.getUserService();
		groupService = ServiceObjektBuilder.getGroupService();
		kontaktService = ServiceObjektBuilder.getKontaktService();
		//groupServiceDeamon = new GroupServiceDaemon();
		//messageServiceDeamon = new MessageServiceDaemon();
		//userServiceDeamon = new UserServiceDaemon();
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
		start.messageStart(MessageActions.Kontakt_Liste, "Marco", 0, "", "", 0);
		
	}
	
}
