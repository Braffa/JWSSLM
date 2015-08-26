package com.braffa.sellem.webservcies.client;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.braffa.sellem.dao.utility.mysql.MySQLSetUp;
import com.braffa.sellem.model.xml.authentication.XmlLogin;
import com.braffa.sellem.model.xml.authentication.XmlLoginMsg;

public class LoginClientTest {

	private LoginClient loginClient;
	
	@Before
	public void setLoginClient() {
		loginClient = new LoginClient();
	}

	@Test
	public void getCountTest() {
		loginClient = new LoginClient();
		String count = loginClient.getCount();
		System.out.println(count);
		//assertEquals("getCount failed Incorrect number of rows ", "6", count);
	}

	@Test
	public void findallTest() {
		loginClient = new LoginClient();
		System.out.println(loginClient.findall());
	}

	@Test
	public void findTest() {
		loginClient = new LoginClient();
		System.out.println(loginClient.find("Braffa"));
	}

	@Test
	public void createTest() {
		XmlLogin xmlLogin = setUpXmlLogin("77777", "scott", "itWorked");
		XmlLoginMsg xmlLoginMsg = new XmlLoginMsg(xmlLogin);

		loginClient = new LoginClient();
		loginClient.create(xmlLoginMsg);
		System.out.println(loginClient.find("itWorked"));
	}
	
	@Test
	public void updateTest() {
		XmlLogin xmlLogin = setUpXmlLogin("33333", null, "georgie");
		XmlLoginMsg xmlLoginMsg = new XmlLoginMsg(xmlLogin);

		loginClient = new LoginClient();
		loginClient.update(xmlLoginMsg);
		System.out.println(loginClient.find("georgie"));
	}
	
	@Test
	public void deleteTest() {
		loginClient = new LoginClient();
		loginClient.delete("itWorked");
	}
 
	private static XmlLogin setUpXmlLogin(String authorityLevel,
			String password, String userId) {
		XmlLogin xmlLogin = new XmlLogin(authorityLevel, password, userId);
		return xmlLogin;
	}

}
