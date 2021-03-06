package de.htwsaar.server.dao;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteConfig.Pragma;

/**
 * Class for the SQLite connection to the Databnk.
 * @author Marco Becker
 * 
 */

public class SQLiteJDBC {
	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String URL = "jdbc:sqlite:server.db";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	// Singleton
	private static DriverManagerDataSource dataSource;
	
	/**
	 * Initialised the JDBC Connection
	 */
	private static void initDataSource() {
		try {
			// Damit das Datum von SQLite <-> Java richtig uebersetzt wird:
			SQLiteConfig sqLiteConfig = new SQLiteConfig();
			Properties properties = sqLiteConfig.toProperties();
			properties.setProperty(Pragma.DATE_STRING_FORMAT.pragmaName, "yyyy-MM-dd HH:mm:ss");
			
			dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(DRIVER);
			dataSource.setUrl(URL);
			dataSource.setUsername(USER);
			dataSource.setPassword(PASSWORD);
			dataSource.setConnectionProperties(properties);
		} catch (Exception e) {
			System.err.println("Es konnte keine Verbindung zur Datenbank hergestellt werden");
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode for the singelton object. So that there are not more than one class
	 * @return
	 */
	public static synchronized DataSource getConnection() {
		if (dataSource == null)
			initDataSource();

		return dataSource;
	}
}