package de.htwsaar.server.dao;

import de.htwsaar.server.dao.interfaces.*;

public class DaoObjectBuilder {

	/*
	 * GroupDao
	 */
	public static GroupDao getGroupDao() {
		return new GroupDaoImpl();
	}
	
	/*
	 * KontaktDao
	 */
	public static KontaktDao getContactDao() {
		return new KontaktDaoImpl();
	}
	
	/*
	 * MessageDao
	 */
	public static MessageDao getMessageDao() {
		return new MessageDaoImpl();
	}
	
	/*
	 * UserDao
	 */
	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}
}
