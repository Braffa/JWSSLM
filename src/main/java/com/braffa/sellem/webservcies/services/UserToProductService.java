package com.braffa.sellem.webservcies.services;

import org.apache.log4j.Logger;

import com.braffa.sellem.hbn.Dao;
import com.braffa.sellem.hbn.DaoFactory;
import com.braffa.sellem.hbn.DaoFactory.daoType;
import com.braffa.sellem.model.hbn.entity.Login;
import com.braffa.sellem.model.xml.authentication.XmlRegisteredUser;
import com.braffa.sellem.model.xml.authentication.XmlRegisteredUserMsg;
import com.braffa.sellem.model.xml.product.XmlUserToProduct;
import com.braffa.sellem.model.xml.product.XmlUserToProductMsg;
import com.braffa.sellem.webservcies.IUserToProductWebService;

public class UserToProductService implements IUserToProductWebService{

	private static final Logger logger = Logger
			.getLogger(UserToProductService.class);
	
	private XmlUserToProductMsg xmlUserToProductMsg;

	public static UserToProductService userToProductService = new UserToProductService();

	public static UserToProductService getInstance() {
		return userToProductService;
	}
	
	private XmlUserToProductMsg getXmlUserToProductMsg (String userId, String productId, String productIndex) {
		if (logger.isDebugEnabled()) {
			logger.debug("getXmlUserToProductMsg");
		}
		XmlUserToProduct xmlUserToProduct = new XmlUserToProduct();
		if (userId != null) {
			xmlUserToProduct.setUserId(userId);
		}
		if (productId != null) {
			xmlUserToProduct.setProductId(productId);
		}
		if (productIndex != null) {
			xmlUserToProduct.setProductIndex(Integer.parseInt(productIndex));
		}
		xmlUserToProductMsg = new XmlUserToProductMsg(xmlUserToProduct);
		return xmlUserToProductMsg;
	}

	public String count() {
		if (logger.isDebugEnabled()) {
			logger.debug("countUserToProduct");
		}
		Dao userToProductDao = DaoFactory.getDAO(daoType.USER_TO_PRODUCT_DAO,
				new XmlUserToProductMsg());
		return "" + userToProductDao.getCount();
	}

	public XmlUserToProductMsg create(XmlUserToProductMsg anXMLUserToProductMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("createUserToProduct");
		}
		Dao userToProductDao = DaoFactory.getDAO(daoType.USER_TO_PRODUCT_DAO,
				anXMLUserToProductMsg);
		userToProductDao.create();
		return anXMLUserToProductMsg;
	}

	public XmlUserToProductMsg delete(String userId, String productId, String productIndex) {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteUserToProduct");
		}
		Dao userToProductDao = DaoFactory.getDAO(daoType.USER_TO_PRODUCT_DAO,
				getXmlUserToProductMsg(userId, productId, productIndex));
		userToProductDao.delete();
		return xmlUserToProductMsg;
	}
	
	public XmlUserToProductMsg remove(String userId, String productId, String productIndex) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeUserToProduct");
		}
		Dao userToProductDao = DaoFactory.getDAO(daoType.USER_TO_PRODUCT_DAO,
				getXmlUserToProductMsg(userId, productId, productIndex));
		userToProductDao.delete();
		return xmlUserToProductMsg;
	}
	
	public XmlUserToProductMsg findAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllUserToProduct");
		}
		Dao userToProductDao = DaoFactory.getDAO(daoType.USER_TO_PRODUCT_DAO,
				new XmlUserToProductMsg());
		return (XmlUserToProductMsg) userToProductDao.readAll();
	}

	public XmlUserToProductMsg find(String userId, String productId, String productIndex) {
		if (logger.isDebugEnabled()) {
			logger.debug("getRegisteredUser");
		}
		Dao userToProductDao = DaoFactory.getDAO(daoType.USER_TO_PRODUCT_DAO,
				getXmlUserToProductMsg(userId, productId, productIndex));
		return  (XmlUserToProductMsg) userToProductDao.read();
	}
	
	public XmlUserToProductMsg search(String searchField, String value) {
		if (logger.isDebugEnabled()) {
			logger.debug("search");
		}
		XmlUserToProduct xmlUserToProduct = new XmlUserToProduct ();
		if (searchField.equals("PRODUCTID")) {
			xmlUserToProduct.setProductId(value);
		}
		if (searchField.equals("USERID")) {
			xmlUserToProduct.setUserId(value);
		}
		XmlUserToProductMsg xmlUserToProductMsg = new XmlUserToProductMsg (xmlUserToProduct);
		xmlUserToProductMsg.setSearchField(searchField);
		Dao userToProductDao = DaoFactory.getDAO(daoType.USER_TO_PRODUCT_DAO,
				xmlUserToProductMsg);
		return (XmlUserToProductMsg) userToProductDao.search();

	}
}
