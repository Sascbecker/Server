package de.htwsaar.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import de.htwsaar.server.dataclass.Member;

public class MemberDaoImpl {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public MemberDaoImpl(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public MemberDaoImpl() {
		this.jdbc = new NamedParameterJdbcTemplate(SQLiteJDBC.getConnection());
	}
	
	public void updatePassword(Member member)
	{
		String update = "Update User set Passwort = :Passwort where Id = :Id ";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Passwort", member.getPassword());
		paramSource.addValue("Id", member.getId());
		
		jdbc.update(update, paramSource);
	}
	
	
	public Member getPassword(int memberId) {

		String query = "SELECT Passwort FROM  WHERE RegisterNr = :Id";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Id", memberId);

		try {
			return (Member) jdbc.queryForObject(query, paramSource, new MemberRowMapper());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void memberAnlegen(Member member)
	{
		String anlegen = "Insert into User (Passwort, Timestamp) Values(:Password, :Timestamp) ";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Password", member.getPassword());
		
		jdbc.update(anlegen, paramSource);
		
	}
	

	/**
	 * Diese Klasse erstellt Member-Objekte aus einem Resultset welches das
	 * Ergebnis einer Datenbankanfrage war
	 * 
	 * @return Member - object
	 */
	private class MemberRowMapper implements RowMapper<Member> {

		
		public Member mapRow(ResultSet results, int rowNum) throws SQLException {

			Member member = new Member();

			try {
				member.setPassword(results.getString("Password"));
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return member;
		}
	}
}
