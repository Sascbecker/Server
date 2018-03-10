package de.htwsaar.server.service;

import de.htwsaar.server.dataclass.*;
import de.htwsaar.server.service.interfaces.KontaktService;
public class KontaktServiceImpl implements KontaktService{

	public void handleKontaktConfig(Message message)
	{
		switch (message.getAktion()) {
		case Actions.Kontakt_Hinzufuegen:
						
			kontaktHinzufuegen(message);
			break;
			
		case Actions.Kontakt_Loeschen:
			kontaktLoeschen(message);
			break;
			
		case Actions.Kontakt_Blockieren:
			kontaktBlockieren(message);
			break;
			
		

		}
	}
	
	private void kontaktHinzufuegen(Message message)
	{
		
	}
	
	private void kontaktLoeschen(Message message)
	{
		
	}
	
	private void kontaktBlockieren(Message message)
	{
		
	}
}
