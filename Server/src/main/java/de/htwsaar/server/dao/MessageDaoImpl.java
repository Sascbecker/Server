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
		String sqlStatement = "INSERT INTO Nachrichten (Zeit, Inhalt, SenderID, EmpfaengerID, Zugestellt) VALUES( :Time, :Content, :IdSender, :IdEmpf, 0 )";
                
                MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("Time", message.getTimestamp());
                paramSource.addValue("Content", message.getMessage());
                paramSource.addValue("IdSender", message.getSender());
                paramSource.addValue("IdEmpf", empfaenger.getAbsenderId());
                
                jdbc.update(sqlStatement, paramSource);
	}
	
	/**
	 * Liste mit allen Nachrichten, die noch nicht gesendet wurden,  
	 * @param timestamp
	 * @return Liste mit allen Nachrichten ab einem gewissen Zeitpunk
	 */
	public List<Message> alleUngeleseneNachrichten(String userID)
	{
		int delivered = 0;
		String query = "Select * from Nachrichten where EmpfaengerID = :UserID and Zugestellt = :delivered order by Zeit";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("UserID", userID);
		paramSource.addValue("delivered", delivered);
		
		return jdbc.query(query,paramSource, new MessageRowMapper());
	}
	
	public List<Message> alleNachrichtenTimestamp(String userID, int timestamp)
	{
		String query="Select * from Nachrichten";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		//paramSource.addValue();
		return jdbc.query(query,paramSource, new MessageRowMapper());
	}
	
	public int readMessageID()
	{
		String query = "Select MessageID from Nachrichten order by MessageID DESC limit 1";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		//paramSource.addValue("UserID", memberId);

		return jdbc.queryForObject(query, paramSource, Integer.class);

	}
	/**
	 * Reads a message from DB
	 * @return MessageObject
	 */
	public Message readMessage() {
		Message message = new Message();
		
		return message;
	}
	
	public void updateDeliveredState(int messageID)
	{
		int delivered = 1;
		String query = "Update Nachrichten set Zugestellt = :delivered where MessageID = :messageID";
        
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("messageID", messageID);
		paramSource.addValue("delivered", delivered);
		
		
		
		jdbc.update(query,paramSource);
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
				message.setSender(results.getString("SenderID"));
				message.setRecipient(results.getString("EmpfaengerID"));
				message.setMessage(results.getString("Inhalt"));
				message.setTimestamp(results.getInt("Zeit"));
				message.setMessageID(results.getInt("MessageID"));
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return message;
		}
	}
}
