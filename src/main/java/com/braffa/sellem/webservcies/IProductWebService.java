package com.braffa.sellem.webservcies;

import com.braffa.sellem.model.xml.product.XmlProductMsg;

public interface IProductWebService {
	
	public XmlProductMsg create(XmlProductMsg xmlProductMsg);
	
	public void delete (String productId); 

	public String getCount(); 
	
	public XmlProductMsg findAll();
	
	public XmlProductMsg findByProductId(String productId);
	
	public XmlProductMsg searchProduct (String field, String value);
	
	public XmlProductMsg update(XmlProductMsg xmlProductMsg);
	
	public XmlProductMsg remove(XmlProductMsg xmlProductMsg);
	
}
