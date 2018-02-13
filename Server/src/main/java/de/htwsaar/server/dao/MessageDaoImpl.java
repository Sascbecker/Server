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

	
	public void saveGroupMessage(Message messageObj) {
		
		String insert = "Insert into ... () Values(:Sender, :GroupId, :Message, :Timestamp) ";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		//paramSource.addValue("GroupId", groupId);
		//paramSource.addValue("Message", message);
		//paramSource.addValue("Timestamp", timestamp);

		jdbc.update(insert, paramSource);
		
	}
	public void SaveSingleMessage(Message messageObj)
	{
		
	}
	
	public void readMessage() {
		
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
