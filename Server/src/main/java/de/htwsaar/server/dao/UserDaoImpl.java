package de.htwsaar.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import de.htwsaar.server.dataclass.User;

public class UserDaoImpl {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public UserDaoImpl(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public UserDaoImpl() {
		this.jdbc = new NamedParameterJdbcTemplate(SQLiteJDBC.getConnection());
	}
	
	public User getPasswort(String memberId) {

		String query = "SELECT Passwort FROM  WHERE RegisterNr = :Id";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Id", memberId);

		try {
			return (User) jdbc.queryForObject(query, paramSource, new UserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
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
