package com.braffa.sellem.webservcies.services;

import org.apache.log4j.Logger;

import com.braffa.sellem.hbn.Dao;
import com.braffa.sellem.hbn.DaoFactory;
import com.braffa.sellem.hbn.DaoFactory.daoType;
import com.braffa.sellem.model.xml.product.XmlProduct;
import com.braffa.sellem.model.xml.product.XmlProductMsg;
import com.braffa.sellem.webservcies.IProductWebService;

public class ProductService implements IProductWebService {

	private static final Logger logger = Logger.getLogger(ProductService.class);

	public static ProductService productService = new ProductService();

	public static ProductService getInstance() {
		return productService;
	}

	@Override
	public XmlProductMsg create(XmlProductMsg xmlProductMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("create");
		}
		try {
			Dao productDao = DaoFactory.getDAO(daoType.PRODUCT_DAO, xmlProductMsg);
			return (XmlProductMsg)productDao.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void remove(String productId) {
		if (logger.isDebugEnabled()) {
			logger.debug("remove " + productId);
		}
		XmlProduct xmlProduct = new XmlProduct();
		xmlProduct.setProductid(productId);
		XmlProductMsg xmlProductMsg = new XmlProductMsg(xmlProduct);
		Dao productDao = DaoFactory.getDAO(daoType.PRODUCT_DAO, xmlProductMsg);
		productDao.delete();
	}
	
	@Override
	public void delete(String productId) {
		if (logger.isDebugEnabled()) {
			logger.debug("delete " + productId);
		}
		XmlProduct xmlProduct = new XmlProduct();
		xmlProduct.setProductid(productId);
		XmlProductMsg xmlProductMsg = new XmlProductMsg(xmlProduct);
		Dao productDao = DaoFactory.getDAO(daoType.PRODUCT_DAO, xmlProductMsg);
		productDao.delete();
	}

	@Override
	public String getCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getCount");
		}
		try {
			Dao productDao = DaoFactory.getDAO(daoType.PRODUCT_DAO,
					new XmlProductMsg());
			int count = productDao.getCount();
			return "" + count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public XmlProductMsg findAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("findAll");
		}
		XmlProduct xmlProduct = new XmlProduct();
		XmlProductMsg xmlProductMsg = new XmlProductMsg(xmlProduct);
		Dao productDao = DaoFactory.getDAO(daoType.PRODUCT_DAO,
				new XmlProductMsg());
		xmlProductMsg = (XmlProductMsg) productDao.readAll();
		return xmlProductMsg;
	}

	@Override
	public XmlProductMsg findByProductId(String productId) {
		if (logger.isDebugEnabled()) {
			logger.debug("findByProductId - " + productId);
		}
		XmlProduct xmlProduct = new XmlProduct();
		xmlProduct.setProductid(productId);
		XmlProductMsg xmlProductMsg = new XmlProductMsg(xmlProduct);
		Dao productDao = DaoFactory.getDAO(daoType.PRODUCT_DAO, xmlProductMsg);
		xmlProductMsg = (XmlProductMsg) productDao.read();
		return xmlProductMsg;
	}

	@Override
	public XmlProductMsg searchProduct(String field, String value) {
		if (logger.isDebugEnabled()) {
			logger.debug("searchProduct by " + field + " for " + value);
		}
		XmlProduct xmlProduct = new XmlProduct();
		switch (field) {
		case "author":
			xmlProduct.setAuthor(value);
			break;
		case "title":
			xmlProduct.setTitle(value);
			break;
		case "productid":
			xmlProduct.setProductid(value);
			break;
		case "manufacturer":
			xmlProduct.setManufacturer(value);
			break;
		default:
			xmlProduct.setProductid("*");
			break;
		}
		XmlProductMsg xmlProductMsg = new XmlProductMsg(xmlProduct);
		xmlProductMsg.setSearchField(field);
		Dao productDao = DaoFactory.getDAO(daoType.PRODUCT_DAO, xmlProductMsg);
		xmlProductMsg = (XmlProductMsg) productDao.search();
		return xmlProductMsg;
	}
	
	@Override
	public XmlProductMsg update(XmlProductMsg xmlProductMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("update");
		}
		try {
			Dao productDao = DaoFactory.getDAO(daoType.PRODUCT_DAO, xmlProductMsg);
			return (XmlProductMsg)productDao.update();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public XmlProductMsg remove(XmlProductMsg xmlProductMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("remove");
		}
		try {
			Dao productDao = DaoFactory.getDAO(daoType.PRODUCT_DAO, xmlProductMsg);
			return (XmlProductMsg)productDao.remove();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
