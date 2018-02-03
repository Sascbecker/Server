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
		int sender = messageObj.getSender();
		int groupId = messageObj.getGroupId();
		String message = messageObj.getMessage();
		int timestamp = messageObj.getTimestamp();
		String insert = "Insert into ... () Values(:Sender, :GroupId, :Message, :Timestamp) ";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Sender", sender);
		paramSource.addValue("GroupId", groupId);
		paramSource.addValue("Message", message);
		paramSource.addValue("Timestamp", timestamp);

		jdbc.update(insert, paramSource);
		
	}
	public void SaveSingleMessage(Message messageObj)
	{
		
	}
	
	public void readMessage() {
		
	}

}
