package com.braffa.sellem.webservcies.services;

import org.apache.log4j.Logger;

import com.braffa.sellem.hbn.Dao;
import com.braffa.sellem.hbn.DaoFactory;
import com.braffa.sellem.hbn.DaoFactory.daoType;
import com.braffa.sellem.model.hbn.entity.Login;
import com.braffa.sellem.model.xml.authentication.XmlRegisteredUser;
import com.braffa.sellem.model.xml.authentication.XmlRegisteredUserMsg;

public class RegisteredUserService {

	private static final Logger logger = Logger
			.getLogger(RegisteredUserService.class);

	public static RegisteredUserService registeredUserService = new RegisteredUserService();

	public static RegisteredUserService getInstance() {
		return registeredUserService;
	}

	public String count() {
		if (logger.isDebugEnabled()) {
			logger.debug("getCount");
		}
		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO,
				new XmlRegisteredUserMsg());
		return "" + registeredUserDao.getCount();
	}

	public XmlRegisteredUserMsg create(XmlRegisteredUserMsg xmlRegisteredUserMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("createRegisteredUser");
		}
		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO,
				xmlRegisteredUserMsg);
		registeredUserDao.create();
		return xmlRegisteredUserMsg;
	}

	public XmlRegisteredUserMsg delete(String userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLogin");
		}
		XmlRegisteredUser registeredUser = new XmlRegisteredUser();
		Login login = new Login();
		registeredUser.setLogin(login);
		registeredUser.getLogin().setUserId(userId);
		XmlRegisteredUserMsg xmlRegisteredUserMsg = new XmlRegisteredUserMsg(
				registeredUser);
		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO,
				xmlRegisteredUserMsg);
		registeredUserDao.delete();
		return xmlRegisteredUserMsg;
	}
	
	public XmlRegisteredUserMsg remove(String userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("remove");
		}
		XmlRegisteredUser registeredUser = new XmlRegisteredUser();
		Login login = new Login();
		registeredUser.setLogin(login);
		registeredUser.getLogin().setUserId(userId);
		XmlRegisteredUserMsg xmlRegisteredUserMsg = new XmlRegisteredUserMsg(
				registeredUser);
		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO,
				xmlRegisteredUserMsg);
		registeredUserDao.delete();
		return xmlRegisteredUserMsg;
	}
	
	public XmlRegisteredUserMsg findAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllRegisteredUsers");
		}
		XmlRegisteredUser registeredUser = new XmlRegisteredUser();
		Login login = new Login();
		XmlRegisteredUserMsg xmlRegisteredUserMsg = new XmlRegisteredUserMsg(
				registeredUser);
		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO,
				xmlRegisteredUserMsg);
		xmlRegisteredUserMsg = (XmlRegisteredUserMsg) registeredUserDao
				.readAll();
		return xmlRegisteredUserMsg;
	}

	public XmlRegisteredUserMsg find(String userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getRegisteredUser");
		}
		XmlRegisteredUser registeredUser = new XmlRegisteredUser();
		Login login = new Login();
		registeredUser.setLogin(login);
		registeredUser.getLogin().setUserId(userId);
		XmlRegisteredUserMsg xmlRegisteredUserMsg = new XmlRegisteredUserMsg(
				registeredUser);
		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO,
				xmlRegisteredUserMsg);
		xmlRegisteredUserMsg = (XmlRegisteredUserMsg) registeredUserDao.read();
		return xmlRegisteredUserMsg;
	}
	
	public XmlRegisteredUserMsg update(XmlRegisteredUserMsg xmlRegisteredUserMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("createLogin");
		}
		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO,
				xmlRegisteredUserMsg);
		registeredUserDao.update();
		return xmlRegisteredUserMsg;
	}
}
