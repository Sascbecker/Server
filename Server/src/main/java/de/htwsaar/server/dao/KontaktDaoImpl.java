package de.htwsaar.server.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import de.htwsaar.server.dao.interfaces.*;
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
	 * Methode zum hinzufügen eines Kontaktes
	 */
	public void kontaktHinzufuegen()
	{
		
	}
	
	public void kontaktLöschen()
	{
		
	}

}
