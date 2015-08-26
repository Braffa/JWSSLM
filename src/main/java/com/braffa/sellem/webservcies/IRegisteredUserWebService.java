package com.braffa.sellem.webservcies;

import com.braffa.sellem.model.xml.authentication.XmlRegisteredUserMsg;

public interface IRegisteredUserWebService {
	
	public String count();
	
	public XmlRegisteredUserMsg findAll();
	
	public XmlRegisteredUserMsg find(String userId);
	
	public XmlRegisteredUserMsg create(XmlRegisteredUserMsg xmlRegisteredUserMsg); 
	
	public XmlRegisteredUserMsg update(XmlRegisteredUserMsg xmlRegisteredUserMsg);
	
	public XmlRegisteredUserMsg delete(String userId);

}
