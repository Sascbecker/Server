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
	 * Add a new User to the Table "User"
	 * @param user
	 */
	public void newUser(User user)
	{
		String sqlStatement= "Insert into User (User_id,Passwort) Values(:BenutzerId, :Passwort)";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("BenutzerId", user.getAbsenderId());
		paramSource.addValue("Passwort", user.getPasswort());
		
		//Ausführen des SQL-Statements mit dem String sql-Statement und der paramSource
		jdbc.update(sqlStatement, paramSource);
	}
	
	/**
	 * Auslesen eines UserObjektes aus der Datenbank anhand der empfaengerId
	 * @param empfaengerId
	 * @return UserObjekt 
	 */
	public User getUser(String empfaengerId)
	{
            //SQL Statement
		String query = "SELECT * FROM User WHERE User_id = :IdEmpf";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("IdEmpf", empfaengerId);
		
		try {
			return (User) jdbc.queryForObject(query, paramSource, new UserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public User getPasswort(String memberId) {
            
            String query = "SELECT Passwort FROM User WHERE RegisterNr = :Id";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Id", memberId);

		try {
			return (User) jdbc.queryForObject(query, paramSource, new UserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	public List<User> selectGruppenUser(int gruppenId)
	{
            //Insert SQL Statement
		String query="SELECT User_id FROM Gruppen WHERE Gruppen_id = :IdGroup";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("IdGroup",gruppenId);
		
		return jdbc.query(query,paramSource, new UserRowMapper());
		
	}
	
	/**
	 * Updates the Table User with the Ip-Adress
	 * @param user
	 */
	public void updateIpAdresse(User user)
	{
		String query = "Update User set IP-Adresse = :Ip-Adresse where User_id = :User_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("Ip-Adresse", user.getIpAdresse());
		
		paramSource.addValue("User_id", user.getAbsenderId());
		
		jdbc.update(query,paramSource);
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
				user.setAbsenderId(results.getString("AbsenderId"));
				user.setPasswort(results.getString("Passwort"));

			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return user;
		}
	}
}
