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
import de.htwsaar.server.dataclass.Group;



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
	public void gruppeAnlegen(Group group)
	{
		String sqlStatement = "Insert Into Gruppen (GruppenName, GruppenAdmin) Values (:GroupName, :GroupAdmin)";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GroupName", group.getGroupName());
		paramSource.addValue("GroupAdmin", group.getSender());
		
		jdbc.update(sqlStatement, paramSource);
		
	}
	
	/**
	 * gruppen Löschen
	 */
	public void gruppeLöschen(Group group)
	{
		String sqlStatement = "Delete from Gruppen where GruppenID = :GruppenID";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GruppenId", group.getGroupId());
		
		jdbc.update(sqlStatement, paramSource);
		
	}
	
	/**
	 * Gruppe verlassen bzw. Benutzer wird gekickt
	 */
	public void gruppeVerlassen(Group group)
	{
		String sqlStatement = "Delete from IstGruppe where GruppenID = :GruppenID and UserID = :UserID";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GruppenID", group.getGroupId());
		paramSource.addValue("UserID", group.getGroupUser());
		
		jdbc.update(sqlStatement, paramSource);
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
	
	public String selectGroupAdmin(int groupID)
	{
		 String query = "SELECT GruppenAdmin FROM Gruppen WHERE GruppenID = :GroupID";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GroupID", groupID);

		return jdbc.queryForObject(query, paramSource, String.class);
	}
	

	public void selectGroupMember() {
		// TODO Auto-generated method stub
		
	}
}
