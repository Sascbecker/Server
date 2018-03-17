package de.htwsaar.server.main;

import de.htwsaar.server.service.ServiceObjektBuilder;

import java.io.IOException;
import java.lang.Thread;
import java.net.InetAddress;
import java.net.UnknownHostException;

import de.htwsaar.server.service.interfaces.*;
import de.htwsaar.server.dataclass.*;
import de.htwsaar.server.service.*;
import de.htwsaar.server.serverServer.*;

/*
 * Hauptprogramm des Servers, aufruf dieser Klasse bei ankommenen der Nachricht.
 * Verteilt die Logik auf andere Klassen
 */
public class Start{

	private Start instance;
	MessageService messageService;
	UserService userService;
	GroupService groupService;
	KontaktService kontaktService;
	GroupServiceDaemon groupServiceDeamon;
	MessageServiceDaemon messageServiceDeamon;
	UserServiceDaemon userServiceDeamon;
	Thread messageStart;
	Thread userStart;
	Thread groupStart;
	
	public Start()
	{
		messageService = ServiceObjektBuilder.getMessageService();
		userService = ServiceObjektBuilder.getUserService();
		groupService = ServiceObjektBuilder.getGroupService();
		kontaktService = ServiceObjektBuilder.getKontaktService();
		//groupServiceDeamon = new GroupServiceDaemon();
		userServiceDeamon = new UserServiceDaemon();
		userServiceDeamon.start();
	}
	
	public synchronized Start getInstance()
	{
		if(instance == null)
		{
			instance = new Start();
		}
		return instance;
	}

	public void messageStart(final Message message)
	{
				//System.out.println("messageStart");
				switch(message.getAktion())
				{
				case MessageActions.Nachricht: messageService.handleMessage(message);
					break;
				case MessageActions.Kontakt_Hinzufuegen:
				case MessageActions.Kontakt_Loeschen:
				case MessageActions.Kontakt_Blockieren:
				case MessageActions.Kontakt_Liste:
						kontaktService.handleKontaktConfig(message);
					break;
				}
			
		
	}
	
	public void userStart(final User user)
	{
				//System.out.println("userStart");
				userService.start(user);
		
	}
	
	/**
	 * 
	 * @param aktion bestimmt, welche aktion ausgeführt werden soll
	 * @param groupId Id der Gruppe
	 * @param groupName Name der Gruppe
	 * @param groupAdmin Admin der Gruppe / nicht unbedingt benötigt
	 * @param UserID Benutzer für den die Aktion in der Gruppe ausgeführt werden soll. Also welcher Benutzer hinzugefügt/ gelöscht werden soll
	 */
	public void groupStart(final Group group)
	{
				//System.out.println("Groupstart");
				switch(group.getAktion())
				{
				case GroupActions.Create_Group:
					groupService.handleGroupConfig(group);
					break;
				case GroupActions.Kick_From_Group:
					groupService.handleGroupConfig(group);
					break;
				case GroupActions.Delete_Group:
					groupService.handleGroupConfig(group);
					break;
				case GroupActions.Add_To_Group:
					groupService.handleGroupConfig(group);
					break;
				}
		
		
	}
	public static void main(String[] args) throws Throwable
	{
		Start start = new Start();
		//Neuen Benutzer anlegen
		start.userStart(new User(UserActions.USER_LOGIN, "Marco", "Test"));
		start.userStart(new User(UserActions.USER_LOGIN,"Alex","Test"));
		start.userStart(new User(UserActions.USER_LOGIN,"Daniela","Test"));
		start.userStart(new User(UserActions.USER_LOGIN,"Robin","Test"));
		start.userStart(new User(UserActions.USER_LOGIN,"Maurice","Test"));
		start.userStart(new User(UserActions.USER_LOGIN,"Sascha","Test"));
		//Benutzer einloggen
		start.userStart(new User(UserActions.USER_AUTHENTIFIZIERUNG, "Marco", "Test",InetAddress.getLocalHost().getHostAddress()));
		start.userStart(new User(UserActions.USER_AUTHENTIFIZIERUNG, "Alex", "Test",InetAddress.getLocalHost().getHostAddress()));
		start.userStart(new User(UserActions.USER_AUTHENTIFIZIERUNG, "Daniela", "dasdas",InetAddress.getLocalHost().getHostAddress()));
		start.userStart(new User(UserActions.USER_AUTHENTIFIZIERUNG, "Maurice", "Test",InetAddress.getLocalHost().getHostAddress()));
		start.userStart(new User(UserActions.USER_AUTHENTIFIZIERUNG, "Sascha", "Test",InetAddress.getLocalHost().getHostAddress()));
		start.userStart(new User(UserActions.USER_AUTHENTIFIZIERUNG, "Robin", "Test", InetAddress.getLocalHost().getHostAddress()));
		//Benutzer ausloggen
		start.userStart(new User(UserActions.USER_LOGOUT, "Maurice"));
		start.userStart(new User(UserActions.USER_LOGOUT,"Sascha"));
		
		//Gruppe erstellen
		start.groupStart(new Group(GroupActions.Create_Group, "Marco", "Test"));
		//Benutzer zur Gruppen hinzufügen
		start.groupStart(new Group(GroupActions.Add_To_Group,"Marco","Test",1,"Alex"));
		start.groupStart(new Group(GroupActions.Add_To_Group,"Marco","Test",1,"Daniela"));
		start.groupStart(new Group(GroupActions.Add_To_Group,"Marco","Test",1,"Maurice"));
		start.groupStart(new Group(GroupActions.Add_To_Group,"Marco","Test",1,"Sascha"));
		start.groupStart(new Group(GroupActions.Add_To_Group,"Marco","Test",1,"Robin"));
		//Benutzer von der Gruppe löschen
		
		//Benutzer versuchen aus der Gruppe zu kicken
		
		//Benutzer aus der Gruppe kicken
		start.groupStart(new Group(GroupActions.Kick_From_Group,"Sascha","Test",1,"Sascha"));
		
		//Benutzer zur Kontaktliste hinzufügen
		start.messageStart(new Message(MessageActions.Kontakt_Liste,"Marco"));
		start.messageStart(new Message(MessageActions.Kontakt_Hinzufuegen,"Marco",0,"Alex","",System.currentTimeMillis()));
		start.messageStart(new Message(MessageActions.Kontakt_Hinzufuegen,"Marco",0,"Daniela","",System.currentTimeMillis()));
		start.messageStart(new Message(MessageActions.Kontakt_Hinzufuegen,"Marco",0,"Maurice","",System.currentTimeMillis()));
		start.messageStart(new Message(MessageActions.Kontakt_Hinzufuegen,"Marco",0,"Sascha","",System.currentTimeMillis()));
		start.messageStart(new Message(MessageActions.Kontakt_Hinzufuegen,"Marco",0,"Robin","",System.currentTimeMillis()));
		start.messageStart(new Message(MessageActions.Kontakt_Liste,"Marco"));
		
		start.messageStart(new Message(MessageActions.Kontakt_Loeschen,"Marco",0,"Alex","",System.currentTimeMillis()));
		start.messageStart(new Message(MessageActions.Kontakt_Liste,"Marco"));
		
		//Nachricht an einzel Person
		start.messageStart(new Message("Marco",0,"Alex", "Dies ist eine Test nachricht",System.currentTimeMillis()));
		start.messageStart(new Message("Marco",0,"Sascha", "Dies ist eine 2Test nachricht",System.currentTimeMillis()));
		start.messageStart(new Message("Marco",0,"Robin", "Dies ist eine Test nachricht",System.currentTimeMillis()));
		//Nachricht an einzel Person, die aktuell nicht online ist
		start.messageStart(new Message("Marco",1,"", "Dies ist eine Test nachricht für Personen die offline sind",System.currentTimeMillis()));
		start.messageStart(new Message("Marco",0,"Sascha", "Dies ist eine Test nachricht für Personen die Offline sind",System.currentTimeMillis()));
		
		//Nachricht an Gruppe
		
		start.messageStart(new Message("Marco",1,"","Dies ist eine test nachricht", System.currentTimeMillis()));
		
		//Kontaktliste ausgeben
	
		
		//Kontakt die eben offline waren neu anmelden für die anderen Nachrichten
		start.userStart(new User(UserActions.USER_AUTHENTIFIZIERUNG, "Maurice", "Test",InetAddress.getLocalHost().getHostAddress()));
		start.userStart(new User(UserActions.USER_AUTHENTIFIZIERUNG, "Sascha", "Test",InetAddress.getLocalHost().getHostAddress()));
		//start.messageStart(MessageActions.Kontakt_Liste, "Marco", 0, "", "", 0);
	}
	
}
