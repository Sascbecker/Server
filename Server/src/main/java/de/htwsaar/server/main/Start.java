package de.htwsaar.server.main;

import de.htwsaar.server.service.ServiceObjektBuilder;

import java.lang.Thread;
import de.htwsaar.server.service.interfaces.*;
import de.htwsaar.server.dataclass.*;
import de.htwsaar.server.service.*;

/*
 * Hauptprogramm des Servers, aufruf dieser Klasse bei ankommenen der Nachricht.
 * Verteilt die Logik auf andere Klassen
 */
public class Start implements Runnable{

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
		groupServiceDeamon = new GroupServiceDaemon();
		userServiceDeamon = new UserServiceDaemon();
	}
	
	public synchronized Start getInstance()
	{
		if(instance == null)
		{
			instance = new Start();
		}
		return instance;
	}
	public void run()
	{
		
	}

	public void messageStart(final Message message)
	{
		messageStart = new Thread(new Runnable() {
			
			public void run() {
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
		});
		
	}
	
	public void userStart(final User user)
	{
		userStart = new Thread(new Runnable() {
			
			public void run() {
				userService.start(user);
			}
		});
		
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
		groupStart = new Thread(new Runnable() {
			
			public void run() {
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
		});
		
		
		
	}
	public static void main(String[] args)
	{
		
		Start start = new Start();
		//start.messageStart(MessageActions.Kontakt_Liste, "Marco", 0, "", "", 0);
	}
	
}
