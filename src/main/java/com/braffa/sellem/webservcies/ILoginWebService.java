package com.braffa.sellem.webservcies;

import com.braffa.sellem.model.xml.authentication.XmlLoginMsg;

public interface ILoginWebService {

	public String count();
	
	public XmlLoginMsg findAll();
	
	public XmlLoginMsg find(String userId);
	
	public XmlLoginMsg create(XmlLoginMsg xmlLoginMsg);
	
	public XmlLoginMsg update(XmlLoginMsg xmlLoginMsg);
	
	public XmlLoginMsg delete(String userId);
}
