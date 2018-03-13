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
import de.htwsaar.server.service.MessageRowMapper;

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
		paramSource.addValue("UserID", user.getAbsenderId());
		paramSource.addValue("Passwort", user.getPasswort());
		
		//Ausführen des SQL-Statements mit dem String sql-Statement und der paramSource
		jdbc.update(sqlStatement, paramSource);
	}
	
	/**
	 * returns the password of a user identified by a given userId (=username)
	 * in a secure context we'd store the hashed password instead of the plain text
	 */
	public String getPasswort(String memberId) {
            
        String query = "SELECT Passwort FROM USER WHERE UserId = :UserID";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("UserID", memberId);

		return jdbc.queryForObject(query, paramSource, String.class);

	}
	
	/**
	 * returns the IP of a user with a given userId (=username)
	 */
	public String getIpAdresse(String userId)
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
	public List<User> selectGruppenUser(int gruppenId)
	{
            //Insert SQL Statement
		String query="Select IstGruppe.UserID, User.Passwort, User.IPAdresse from IstGruppe join User on IstGruppe.UserID = User.UserID where IstGruppe.GruppenID =:GroupID";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GroupID",gruppenId);
		
		return jdbc.query(query,paramSource, new UserRowMapper());
		
	}
	
	/**
	 * returns a list of users in a group without the sender of the message
	 * @param message contains information about the group and the sender
	 */
	public List<User> selectGruppenUserOhneSender(Message message)
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
	public List<User> selectKontakte(Kontakte kontakt)
	{
		String sqlStatement= "Select Kontakte.KontaktID as UserID, User.Passwort, User.IPAdresse from Kontakte join User on Kontakte.UserID = User.UserID where Kontakte.UserID= :UserID";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("UserID", kontakt.getUserId());
		
		return jdbc.query(sqlStatement,paramSource, new UserRowMapper());
	}
	
	/**
	 * Updates the Table User with the Ip-Adress
	 * @param user
	 */
	public void updateIpAdresse(User user)
	{
		String query = "Update User set IP-Adresse = :Ip-Adresse where UserID = :User_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("Ip-Adresse", user.getIpAdresse());
		
		paramSource.addValue("UserID", user.getAbsenderId());
		
		jdbc.update(query,paramSource);
	}
	
	/**
	 * datenbank nach allen nutzern die aktuell online sind abfragen 	
	 */
    public List<User> getAllOnlineUser()
    {
		
		String query = "Select * from User where length(IPAdresse)>5";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		return jdbc.query(query,paramSource, new MessageRowMapper());
	}



	/**
	 * Diese Klasse erstellt Member-Objekte aus einem Resultset welches das
	 * Ergebnis einer Datenbankanfrage war
	 * 
	 * @return Member - object
	 */
	private class UserRowMapper implements RowMapper<User> {

		
		public User mapRow(ResultSet results, int rowNum) throws SQLException {

			User user = new User();

			try {
				user.setAbsenderId(results.getString("UserID"));
				user.setPasswort(results.getString("Passwort"));
				user.setIpAdresse(results.getString("IPAdresse"));

			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return user;
		}
	}
}
