package de.htwsaar.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import de.htwsaar.server.dao.UserDaoImpl.UserRowMapper;
import de.htwsaar.server.dao.interfaces.*;
import de.htwsaar.server.dataclass.Group;
import de.htwsaar.server.dataclass.*;



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
		paramSource.addValue("GruppenID", group.getGroupId());
		
		jdbc.update(sqlStatement, paramSource);
		
	}
	
	/**
	 * Delets all Member from the Table IstGruppe
	 */
	public void deleteMember(Group group)
	{
		String sqlStatement = "Delete from IstGruppe where GruppenID = :GruppenID";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GruppenID", group.getGroupId());
		
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
		paramSource.addValue("UserID", group.getEmpfaengerId());
		
		jdbc.update(sqlStatement, paramSource);
	}
	
	
	/**
	 * Name der Gruppe ändern
	 */
	public void gruppeUmbennen(Group group)
	{

		String sqlStatement = "Update Gruppen set GruppenName = :GruppenName where GruppenID = :GruppenID";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GruppenName", group.getGroupName());
		paramSource.addValue("UserID", group.getGroupId());
		
		jdbc.update(sqlStatement, paramSource);
	}
	
	/**
	 * Nutzer zur Gruppe hinzufügen
	 */
	public void nutzerZurGruppeHinzufuegen(Group group)
	{
		String query = "Insert into IstGruppe Values(:GroupID, :UserID)";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GroupID", group.getGroupId());
		paramSource.addValue("UserID", group.getEmpfaengerId());
		
		jdbc.update(query, paramSource);

		
	}
	
	public int getGroupID()
	{
		String query = "Select GruppenID from Gruppen order by GruppenID DESC Limit 1";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		return jdbc.queryForObject(query, paramSource, Integer.class);
	}
	
	public String selectGroupAdmin(int groupID)
	{
		 String query = "SELECT GruppenAdmin FROM Gruppen WHERE GruppenID = :GroupID";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GroupID", groupID);

		return jdbc.queryForObject(query, paramSource, String.class);
	}
	
	/**
	 * return a List of Groups, where the User is a Member
	 * @param userID
	 * @return
	 */
	public List<Group> getGroupListForUser(String userID)
	{
	  String query="Select IstGruppe.GruppenID, Gruppen.GruppenName, Gruppen.GruppenAdmin from istGruppe join Gruppen on IstGruppe.GruppenID = Gruppen.GruppenID where IstGruppe.UserID =:UserID";
	
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("UserID", userID);
		return jdbc.query(query,paramSource, new UserRowMapper());
	}
	
}
