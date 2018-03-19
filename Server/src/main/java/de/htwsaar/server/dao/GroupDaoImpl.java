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
import de.htwsaar.server.dataclass.*;

/**
 * Class for the SQL-Statements for Group-Operations.
 * Implements the Class GroupDao
 * @author Marco
 *
 */

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
	 * Create a Group 
	 */
	public void createGroup(Group group)
	{
		String sqlStatement = "Insert Into Gruppen (GruppenName, GruppenAdmin) Values (:GroupName, :GroupAdmin)";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GroupName", group.getGroupName());
		paramSource.addValue("GroupAdmin", group.getSender());
		
		jdbc.update(sqlStatement, paramSource);
		
	}
	
	/**
	 * Delete a Group
	 */
	public void deleteGroup(Group group)
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
	 * Leave the Group or User is kicked
	 */
	public void leaveGroup(Group group)
	{
		String sqlStatement = "Delete from IstGruppe where GruppenID = :GruppenID and UserID = :UserID";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GruppenID", group.getGroupId());
		paramSource.addValue("UserID", group.getRecipientId());
		
		jdbc.update(sqlStatement, paramSource);
	}
	
	
	/**
	 * Change the Name of the Group
	 */
	public void renameGroup(Group group)
	{

		String sqlStatement = "Update Gruppen set GruppenName = :GruppenName where GruppenID = :GruppenID";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GruppenName", group.getGroupName());
		paramSource.addValue("UserID", group.getGroupId());
		
		jdbc.update(sqlStatement, paramSource);
	}
	
	/**
	 * Adds a User to the Group
	 */
	public void addUserToGroup(Group group)
	{
		String query = "Insert into IstGruppe Values(:GroupID, :UserID)";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GroupID", group.getGroupId());
		paramSource.addValue("UserID", group.getRecipientId());
		
		jdbc.update(query, paramSource);

		
	}
	public List<Group> selectGroupInformation(String userID)
	{
		String query = "Select Gruppen.GruppenID, Gruppen.GruppenName, Gruppen.GruppenAdmin "
				+ "from Gruppen join IstGruppe on Gruppen.GruppenID = IstGruppe.GruppenID "
				+ "where IstGruppe.UserID =:UserID";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("UserID", userID);
		return jdbc.query(query,paramSource, new GroupRowMapper());
	}
	/**
	 * return the GroupID
	 */
	public int getGroupID()
	{
		String query = "Select GruppenID from Gruppen order by GruppenID DESC Limit 1";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		return jdbc.queryForObject(query, paramSource, Integer.class);
	}
	/**
	 * return the Admin of a Group by a given GroupID
	 */
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
	    String query="Select IstGruppe.GruppenID, Gruppen.GruppenName, Gruppen.GruppenAdmin"
	    		+ "from istGruppe join Gruppen on IstGruppe.GruppenID = Gruppen.GruppenID" 
	    		+ "where IstGruppe.UserID =:UserID";
	
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("UserID", userID);
		return jdbc.query(query,paramSource, new GroupRowMapper());
	}
	
	
	private class GroupRowMapper implements RowMapper<Group> {

		
		public Group mapRow(ResultSet results, int rowNum) throws SQLException {

			Group group = new Group();
			try {
				group.setGroupId(results.getInt("GruppenID"));
				group.setGroupName(results.getString("GruppenName"));
				group.setGroupAdmin(results.getString("GruppenAdmin"));
				

			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return group;
		}
	}
}

