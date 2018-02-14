package de.htwsaar.server.service;

import de.htwsaar.server.dataclass.Message;


public class MessageServiceImpl {

	private Thread messageServiceDaemon;
	/**
	 * Starts a Thread in the background.
	 * This thread will periodically check for unsent messages in the database and send them if possible.
	 */
	private void startMessageServiceDaemon(){
		messageServiceDaemon= new Thread(new Runnable() {
			
			public void run() {
				//TODO: implement
				//datenbank nach allen nutzern die aktuell online sind abfragen
				//datenbank abfragen nach ungesendeten nachrichten für diese nutzer
				//für jede der nachrichten, versuche sie zu senden 
				//wenn erfolgreich, trage dies in der datenbank ein
				//am ende, schlafe für 100ms, damit die datenbank nicht zu oft gepollt wird
			}
		});
		messageServiceDaemon.start();
	}
	
	/**
	 * Handles the Processing of a message that was just received and sends it
	 * 	to its recipient(s)
	 * @param message the message to handle
	 */
	public void handleMessage(Message message)
	{
		if(message.getGroupId() == 0)
		{
			gruppenNachrichten(message);
		}
		else
		{
			einzelNachricht(message);
		}
	}
	
	
	
	/**
	 * Handles the processing of a group message
	 * @param message
	 */
	private void gruppenNachrichten(Message message)
	{
		//empfangen:
		//entnehme der message die gruppenid
		//frage die teilnehmer der gruppe aus der datenbank ab
		//für jeden teilnehmer:
			//senden:
			//sende die nachricht wie empfangen an jeden user der gruppe
			//(verwende dazu die selbe funktion wie für einzelnachricht
	}
	
	/**
	 * handles the processing of a direct message
	 * @param message
	 */
	private void einzelNachricht(Message message)
	{
		//empfangen:
		//entnehme der nachricht den Empfänger
		
		//senden:
		//sende die nachricht wie empfangen an den empfänger
	}
	
	/**
	 * sends a message as it was received to its recipient
	 * @param message
	 * @param recipient
	 */
	private void sendeNachricht(Message message, String recipient){
		//schreibe nachricht-empfänger tupel in die datenbank
		//setze den erfolgreich-gesendet wert der nachricht in der datenbank auf false
		//der Thread in startMessageServiceDaemon hier in der Klasse erledigt dann den rest.
	}
}
