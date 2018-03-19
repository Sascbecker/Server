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

import de.htwsaar.server.dao.interfaces.*;
import de.htwsaar.server.dataclass.*;

/**
 * Data Access Class for "User" objects in the database
 *
 */
public class UserDaoImpl implements UserDao{

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public UserDaoImpl(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public UserDaoImpl() {
		this.jdbc = new NamedParameterJdbcTemplate(SQLiteJDBC.getConnection());
	}
	
	/**
	 * Adds a new User to the Table "User"
	 * @param user
	 */
	public void newUser(User user)
	{
		String sqlStatement= "Insert into User (UserID, Passwort) Values (:UserID, :Passwort)";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("UserID", user.getUserID());
		paramSource.addValue("Passwort", user.getPassword());
		
		//Ausführen des SQL-Statements mit dem String sql-Statement und der paramSource
		jdbc.update(sqlStatement, paramSource);
	}
	
	/**
	 * returns the password of a user identified by a given userId (=username)
	 * in a secure context we'd store the hashed password instead of the plain text
	 */
	public String getPassword(String memberId) {
            
        String query = "SELECT Passwort FROM USER WHERE UserId = :UserID";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("UserID", memberId);

		return jdbc.queryForObject(query, paramSource, String.class);

	}
	
	/**
	 * returns the IP of a user with a given userId (=username)
	 */
	public String getIpAddress(String userId)
	{
		String query = "Select IP-Adresse from User where UserId = :UserID";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("UserID", userId);
		
		return jdbc.queryForObject(query, paramSource, String.class);
	}
	
	/**
	 * returns the User Object from the database for a user identified with a given userId (=username)
	 */
	public User getUserInformation(String userID)
	{
		String query = "Select * from User where UserID = :UserID";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("UserID", userID);

		try {
			return (User) jdbc.queryForObject(query, paramSource, new UserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * returns a list of users which are members of a group identified by a given groupId
	 */
	public List<User> selectGroupUser(int groupId)
	{
            //Insert SQL Statement
		String query="Select IstGruppe.UserID, User.IPAdresse from IstGruppe join User on IstGruppe.UserID = User.UserID where IstGruppe.GruppenID =:GroupID";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GroupID",groupId);
		
		return jdbc.query(query,paramSource, new UserRowMapper());
		
	}
	
	/**
	 * returns a list of users in a group without the sender of the message
	 * @param message contains information about the group and the sender
	 */
	public List<User> selectGroupUserWithoutSender(Message message)
	{
		String query="Select IstGruppe.UserID, User.Passwort, User.IPAdresse from IstGruppe join User on IstGruppe.UserID = User.UserID where IstGruppe.GruppenID =:GroupID and IstGruppe.UserID != :Sender";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GroupID",message.getGroupId());
		paramSource.addValue("Sender", message.getSender());
		
		return jdbc.query(query,paramSource, new UserRowMapper());
	}
	
	/**
	 * returns a list of users which the given contact has in their contact list
	 */
	public List<User> selectContacts(Kontakte contact)
	{
		String sqlStatement= "Select Kontakte.KontaktID as UserID, User.IPAdresse from Kontakte join User on Kontakte.UserID = User.UserID where Kontakte.UserID= :UserID";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("UserID", contact.getUserId());
		
		return jdbc.query(sqlStatement,paramSource, new UserRowMapper());
	}
	
	/**
	 * Updates the Table User with the IP-Address
	 * @param user
	 */
	public void updateIpAddress(User user)
	{
		String query = "Update User set IPAdresse = :IpAdresse where UserID = :UserID";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("IpAdresse", user.getIpAdress());
		
		paramSource.addValue("UserID", user.getUserID());
		
		jdbc.update(query,paramSource);
	}
	
	/**
	 * Return a List of all Users, which are online 	
	 */
    public List<User> getAllOnlineUser()
    {
		
		String query = "Select UserID, IPAdresse from User where length(IPAdresse)>5";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		return jdbc.query(query,paramSource, new UserRowMapper());
	}



	/**
	 * This Class creates Member-Objects from a result set which was the result
	 * of a database request
	 * 
	 * @return Member - object
	 */
	private class UserRowMapper implements RowMapper<User> {

		
		public User mapRow(ResultSet results, int rowNum) throws SQLException {

			User user = new User();

			try {
				user.setUserID(results.getString("UserID"));
				user.setIpAdress(results.getString("IPAdresse"));

			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return user;
		}
	}
}
