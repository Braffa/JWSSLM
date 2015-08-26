package com.braffa.sellem.webservcies;

import com.braffa.sellem.model.xml.product.XmlUserToProductMsg;

public interface IUserToProductWebService {
	
	public String count();
	
	public XmlUserToProductMsg findAll();
	
	public XmlUserToProductMsg find(String userId, String productId, String productIndex);
	
	public XmlUserToProductMsg create(XmlUserToProductMsg xmlUserToProductMsg); 
	
	public XmlUserToProductMsg search(String searchField, String value);
	
	public XmlUserToProductMsg delete(String userId, String productId, String productIndex);

}
