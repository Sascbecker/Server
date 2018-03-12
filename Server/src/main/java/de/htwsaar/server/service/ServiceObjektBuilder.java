package de.htwsaar.server.service;

import de.htwsaar.server.service.interfaces.*;

public class ServiceObjektBuilder {

	/*
	 * GroupService
	 */
	public static GroupService getGroupService() {
		return new GroupServiceImpl();
	}
	
	/*
	 * KontaktService
	 */
	public static KontaktService getKontaktService() {
		return new KontaktServiceImpl();
	}
	
	/*
	 * MessageService
	 */
	public static MessageService getMessageService() {
		return new MessageServiceImpl();
	}
	
	/*
	 * UserDao
	 */
	public static UserService getUserService() {
		return new UserServiceImpl();
	}
}

