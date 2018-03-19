package de.htwsaar.server.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import de.htwsaar.server.dao.interfaces.*;
import de.htwsaar.server.dataclass.Message;
/**
 * Klasse zur Implementierung aller Kontakt eigenschaften
 * @author Marco
 *
 */
public class KontaktDaoImpl implements KontaktDao{
	
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public KontaktDaoImpl(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public KontaktDaoImpl() {
		this.jdbc = new NamedParameterJdbcTemplate(SQLiteJDBC.getConnection());
	}
	/**
	 * Method to add a Contact
	 */
	public void addContact(Message message)
	{
		String sqlStatement = "Insert into Kontakte (UserID, KontaktID) Values(:UserID, :KontaktID)";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("UserID", message.getSender());
		paramSource.addValue("KontaktID", message.getRecipient());
		
		jdbc.update(sqlStatement, paramSource);
		
	}
	/**
	 * Delete a Contact
	 */
	
	public void deleteContact(Message message)
	{
		String sqlStatement = "Delete from Kontakte where UserID = :UserID and KontaktID = :KontaktID";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("UserID", message.getSender());
		paramSource.addValue("KontaktID", message.getRecipient());
		
		jdbc.update(sqlStatement, paramSource);
	}
	
	public void blockContact(Message message)
	{
		
	}

}
