package de.htwsaar.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import de.htwsaar.server.dao.interfaces.*;



public class GroupDaoImpl  implements GroupDao{
	
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public GroupDaoImpl(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public GroupDaoImpl() {
		this.jdbc = new NamedParameterJdbcTemplate(SQLiteJDBC.getConnection());
	}
	
	/**
	 * Gruppen Anlegen 
	 */
	public void gruppeAnlegen()
	{
		
	}
	
	/**
	 * gruppen Löschen
	 */
	public void gruppeLöschen()
	{
		
	}
	
	/**
	 * Gruppe verlassen bzw. Benutzer wird gekickt
	 */
	public void gruppeVerlassen()
	{
		
	}
	
	
	/**
	 * Name der Gruppe ändern
	 */
	public void gruppeUmbennen()
	{
		
	}
	
	/**
	 * Nutzer zur Gruppe hinzufügen
	 */
	public void nutzerZurGruppeHinzufuegen()
	{
		
		
	}
	

	public void selectGroupMember() {
		// TODO Auto-generated method stub
		
	}
}
