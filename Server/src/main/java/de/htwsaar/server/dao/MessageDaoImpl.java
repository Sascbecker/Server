package de.htwsaar.server.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import de.htwsaar.server.dao.SQLiteJDBC;
import de.htwsaar.server.dao.interfaces.MessageDao;
import de.htwsaar.server.dataclass.*;

public class MessageDaoImpl implements MessageDao{
	
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public MessageDaoImpl(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public MessageDaoImpl() {
		this.jdbc = new NamedParameterJdbcTemplate(SQLiteJDBC.getConnection());
	}

	
	/**
	 * Speichert eine Nachricht auf Server
	 * bekommt Message objekt für Absender, Nachricht, Timestamp,
	 * bekommt User objekt für Empfänger
	 * Speichert die Nachricht mit dem Flag, dass die Nachricht noch nicht zugestellt wurde
	 */
	public void SaveMessage(Message message, User empfaenger)
	{
		
	}
	
	
	/**
	 * Eintrag auf Datenbank, dass Benutzer zur Kontaktliste hinzugefügt werden soll
	 */
	public void kontaktHinzufügen(Message message)
	{
		
	}
	
	/**
	 * Eintrag auf Datenbank, dass Benutzer aus Kontaktliste gelöscht werden soll
	 */
	public void kontaktLöschen(Message message)
	{
		
	}
	
	/**
	 * Eintrag auf Datenbank, dass Benutzer blockiert werden soll
	 */
	public void kontaktBlockieren(Message message)
	{
		
	}
	

	
	/**
	 * Reads a message from DB
	 * @return MessageObject
	 */
	public Message readMessage() {
		Message message = new Message();
		
		return message;
	}

	/**
	 * Diese Klasse erstellt Member-Objekte aus einem Resultset welches das
	 * Ergebnis einer Datenbankanfrage war
	 * 
	 * @return Member - object
	 */
	private class MessageRowMapper implements RowMapper<Message> {

		
		public Message mapRow(ResultSet results, int rowNum) throws SQLException {

			Message message = new Message();

			try {
				message.setMessage(results.getString("Message"));
				
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return message;
		}
	}
}
