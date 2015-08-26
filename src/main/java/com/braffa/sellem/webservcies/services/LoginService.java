package com.braffa.sellem.webservcies.services;

import org.apache.log4j.Logger;

import com.braffa.sellem.hbn.Dao;
import com.braffa.sellem.hbn.DaoFactory;
import com.braffa.sellem.hbn.DaoFactory.daoType;
import com.braffa.sellem.model.xml.authentication.XmlLogin;
import com.braffa.sellem.model.xml.authentication.XmlLoginMsg;
import com.braffa.sellem.webservcies.ILoginWebService;

public class LoginService implements ILoginWebService {

	private static final Logger logger = Logger.getLogger(LoginService.class);

	public static LoginService loginService = new LoginService();

	public static LoginService getInstance() {
		return loginService;
	}

	@Override
	public String count() {
		if (logger.isDebugEnabled()) {
			logger.debug("getCount");
		}
		Dao loginDao = DaoFactory.getDAO(daoType.LOGIN_DAO, new XmlLoginMsg());
		return "" + loginDao.getCount();
	}

	@Override
	public XmlLoginMsg findAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLogins");
		}
		XmlLogin xmlLogin = new XmlLogin();
		XmlLoginMsg xmlLoginMsg = new XmlLoginMsg(xmlLogin);
		Dao loginDao = DaoFactory.getDAO(daoType.LOGIN_DAO, new XmlLoginMsg());
		xmlLoginMsg = (XmlLoginMsg) loginDao.readAll();
		return xmlLoginMsg;
	}

	@Override
	public XmlLoginMsg find(String userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getLogin");
		}
		XmlLogin xmlLogin = new XmlLogin();
		xmlLogin.setUserId(userId);
		XmlLoginMsg xmlLoginMsg = new XmlLoginMsg(xmlLogin);
		Dao loginDao = DaoFactory.getDAO(daoType.LOGIN_DAO, xmlLoginMsg);
		xmlLoginMsg = (XmlLoginMsg) loginDao.read();
		return xmlLoginMsg;
	}

	@Override
	public XmlLoginMsg create(XmlLoginMsg xmlLoginMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("createLogin");
		}
		Dao loginDao = DaoFactory.getDAO(daoType.LOGIN_DAO, xmlLoginMsg);
		loginDao.create();
		return xmlLoginMsg;
	}

	@Override
	public XmlLoginMsg update(XmlLoginMsg xmlLoginMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("createLogin");
		}
		Dao loginDao = DaoFactory.getDAO(daoType.LOGIN_DAO, xmlLoginMsg);
		loginDao.update();
		return xmlLoginMsg;
	}

	@Override
	public XmlLoginMsg delete(String userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLogin");
		}
		XmlLogin xmlLogin = new XmlLogin();
		xmlLogin.setUserId(userId);
		XmlLoginMsg xmlLoginMsg = new XmlLoginMsg(xmlLogin);
		Dao loginDao = DaoFactory.getDAO(daoType.LOGIN_DAO, xmlLoginMsg);
		loginDao.delete();
		return xmlLoginMsg;
	}
}
