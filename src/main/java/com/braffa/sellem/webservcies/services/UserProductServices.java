package com.braffa.sellem.webservcies.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.braffa.sellem.hbn.Dao;
import com.braffa.sellem.hbn.DaoFactory;
import com.braffa.sellem.hbn.DaoFactory.daoType;
import com.braffa.sellem.model.hbn.entity.Login;
import com.braffa.sellem.model.hbn.entity.Product;
import com.braffa.sellem.model.xml.authentication.XmlLogin;
import com.braffa.sellem.model.xml.authentication.XmlRegisteredUser;
import com.braffa.sellem.model.xml.authentication.XmlRegisteredUserMsg;
import com.braffa.sellem.model.xml.product.XmlProduct;
import com.braffa.sellem.model.xml.product.XmlProductMsg;
import com.braffa.sellem.model.xml.product.XmlUsersLinkedToProduct;
import com.braffa.sellem.model.xml.product.XmlUserToProduct;
import com.braffa.sellem.model.xml.product.XmlUserToProductMsg;
import com.braffa.sellem.model.xml.product.XmlUsersProductMsg;

public class UserProductServices {

	private static final Logger logger = Logger
			.getLogger(UserProductServices.class);

	private XmlUserToProductMsg xmlUserToProductMsg;

	public static UserProductServices userToProductService = new UserProductServices();

	public static UserProductServices getInstance() {
		return userToProductService;
	}

	private XmlUserToProductMsg getXmlUserToProductMsg(String userId,
			String productId, String productIndex) {
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

	public XmlProductMsg getUserCatalogue(String userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getUserCatalogue " + userId);
		}
		XmlUserToProduct xmlUserToProduct = new XmlUserToProduct();
		xmlUserToProduct.setUserId(userId);
		xmlUserToProductMsg = new XmlUserToProductMsg(xmlUserToProduct);
		xmlUserToProductMsg.setSearchField("USERID");
		Dao userToProductDao = DaoFactory.getDAO(daoType.USER_TO_PRODUCT_DAO,
				xmlUserToProductMsg);
		XmlUserToProductMsg userToProductMsg = (XmlUserToProductMsg) userToProductDao
				.search();
		List<XmlUserToProduct> lOfUserToProduct = userToProductMsg
				.getLOfXmlUserToProduct();

		if (lOfUserToProduct.size() > 0) {
			XmlProductMsg productMsg = new XmlProductMsg();
			ArrayList<XmlProduct> lOfProducts = new ArrayList<XmlProduct>();
			for (XmlUserToProduct userToProduct : lOfUserToProduct) {
				XmlProduct product = new XmlProduct();
				product.setProductid(userToProduct.getProductId());
				lOfProducts.add(product);
			}
			productMsg.setLOfProducts(lOfProducts);
			Dao productDao = DaoFactory.getDAO(daoType.PRODUCT_DAO, productMsg);
			XmlProductMsg xmlProductMsg = (XmlProductMsg) productDao
					.readListOfKeys();
			return xmlProductMsg;
		}
		return null;
	}

	public XmlUsersProductMsg getUsersByProductId(String productId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getUserByProduct " + productId);
		}
		XmlUsersProductMsg usersProductMsg = new XmlUsersProductMsg();
		XmlProduct product = new XmlProduct();
		product.setProductid(productId);
		product.setProductIndex("0");
		XmlProductMsg productMsg = new XmlProductMsg();
		productMsg.setProduct(product);
		Dao productDao = DaoFactory.getDAO(daoType.PRODUCT_DAO, productMsg);
		XmlProductMsg xmlProductMsg = (XmlProductMsg) productDao.read();
		usersProductMsg.setProduct(xmlProductMsg.getLOfProducts().get(0));

		XmlUserToProduct xmlUserToProduct = new XmlUserToProduct();
		xmlUserToProduct.setProductId(productId);
		xmlUserToProductMsg = new XmlUserToProductMsg(xmlUserToProduct);
		xmlUserToProductMsg.setSearchField("PRODUCTID");
		Dao userToProductDao = DaoFactory.getDAO(daoType.USER_TO_PRODUCT_DAO,
				xmlUserToProductMsg);
		XmlUserToProductMsg userToProductMsg = (XmlUserToProductMsg) userToProductDao
				.search();
		if (userToProductMsg.getSuccess().equals("false")) {
			usersProductMsg.setSuccess("false");
			return usersProductMsg;
		}
		List<XmlUserToProduct> lOfUserToProduct = userToProductMsg
				.getLOfXmlUserToProduct();
		if (lOfUserToProduct.size() > 0) {
			ArrayList<XmlRegisteredUser> lOfRegisteredUsers = new ArrayList<XmlRegisteredUser>();
			Map<String, XmlUsersLinkedToProduct> mOfUsersLinkedToProduct = new HashMap<String, XmlUsersLinkedToProduct>();
			for (XmlUserToProduct userToProduct : lOfUserToProduct) {
				XmlUsersLinkedToProduct usersLinkedToProduct = new XmlUsersLinkedToProduct();
				usersLinkedToProduct.setAddedDate(userToProduct.getCrDate());
				usersLinkedToProduct.setUserId(userToProduct.getUserId());
				mOfUsersLinkedToProduct.put(userToProduct.getUserId(),
						usersLinkedToProduct);
				XmlLogin login = new XmlLogin();
				login.setUserId(userToProduct.getUserId());
				XmlRegisteredUser registeredUser = new XmlRegisteredUser();
				registeredUser.setLogin(login);
				lOfRegisteredUsers.add(registeredUser);
			}

			XmlRegisteredUserMsg registeredUserMsg = new XmlRegisteredUserMsg();
			registeredUserMsg.setLOfRegisteredUsers(lOfRegisteredUsers);
			Dao registeredUserDao = DaoFactory.getDAO(
					daoType.REGISTERED_USER_DAO, registeredUserMsg);
			XmlRegisteredUserMsg xmlRegisteredUserMsg = (XmlRegisteredUserMsg) registeredUserDao
					.readListOfKeys();
			lOfRegisteredUsers = xmlRegisteredUserMsg.getLOfRegisteredUsers();
			for (XmlRegisteredUser registeredUser : lOfRegisteredUsers) {
				XmlUsersLinkedToProduct usersLinkedToProduct = mOfUsersLinkedToProduct
						.get(registeredUser.getLogin().getUserId());
				usersLinkedToProduct.setEmail(registeredUser.getEmail());
				usersLinkedToProduct
						.setFirstname(registeredUser.getFirstname());
				usersLinkedToProduct.setLastname(registeredUser.getLastname());
				usersLinkedToProduct
						.setTelephone(registeredUser.getTelephone());
				mOfUsersLinkedToProduct.put(registeredUser.getLogin()
						.getUserId(), usersLinkedToProduct);
			}
			ArrayList<XmlUsersLinkedToProduct> lOfXmlUsersLinkedToProduct = new ArrayList<XmlUsersLinkedToProduct>();
			for (String key : mOfUsersLinkedToProduct.keySet()) {
				XmlUsersLinkedToProduct usersLinkedToProduct = mOfUsersLinkedToProduct
						.get(key);
				lOfXmlUsersLinkedToProduct.add(usersLinkedToProduct);
			}
			usersProductMsg
					.setlOfXmlUsersLinkedToProduct(lOfXmlUsersLinkedToProduct);
			usersProductMsg.setSuccess("true");
		} else {
			usersProductMsg.setSuccess("false");
		}
		return usersProductMsg;
	}
}
