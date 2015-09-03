package com.braffa.sellem.webservcies.client;

import org.apache.log4j.Logger;

public class UserProductServicesClient extends BaseClient {
	
	private static final Logger logger = Logger.getLogger(UserProductServicesClient.class);
	
	static final String FIND_USER_PRODUCTS_URL = "/userproduct/finduserproducts/";
	static final String FIND_USER_BY_PRODUCT_ID_URL = "/userproduct/finduserbyproductid/";
	
	
	public String getUserCatalogue (String userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getUserCatalogue " + userId);
		}
		try {
			return (getResponseAsXML(getWebService (FIND_USER_PRODUCTS_URL + userId)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String findUsersByProductId (String productId) {
		if (logger.isDebugEnabled()) {
			logger.debug("findUsersByProductId " + productId);
		}
		try {
			return (getResponseAsXML(getWebService (FIND_USER_BY_PRODUCT_ID_URL + productId)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
