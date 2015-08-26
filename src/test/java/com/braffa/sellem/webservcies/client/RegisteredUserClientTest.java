package com.braffa.sellem.webservcies.client;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.braffa.sellem.dao.utility.mysql.MySQLSetUp;
import com.braffa.sellem.hbn.dao.authentication.RegisteredUserDao;
import com.braffa.sellem.model.hbn.entity.Login;
import com.braffa.sellem.model.xml.authentication.XmlRegisteredUser;
import com.braffa.sellem.model.xml.authentication.XmlRegisteredUserMsg;
import com.braffa.sellem.model.xml.product.XmlProduct;
import com.braffa.sellem.model.xml.product.XmlProductMsg;
import com.sun.jersey.api.client.ClientResponse;

public class RegisteredUserClientTest {
	
	private RegisteredUserClient registeredUserClient;
	
	@BeforeClass
	public static void setUpDatabase() {
		try {
			MySQLSetUp mySQLSetUp = new MySQLSetUp();
			mySQLSetUp.setUpRegisteredUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setProductClient() {
		registeredUserClient = new RegisteredUserClient();
	}
	
	private XmlRegisteredUserMsg convertStringToObject(String xmlStr) {
		try {
			StringReader reader = new StringReader(xmlStr);
			JAXBContext jaxbContext = JAXBContext
					.newInstance(XmlRegisteredUserMsg.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (XmlRegisteredUserMsg) jaxbUnmarshaller.unmarshal(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<XmlRegisteredUser> getLOfRegisteredUser(String xmlStr) {
		XmlRegisteredUserMsg XmlRegisteredUserMsg = convertStringToObject(xmlStr);
		return XmlRegisteredUserMsg.getLOfRegisteredUsers();
	}
	
	@Test
	public void countTest() {
		String count = registeredUserClient.count();
		System.out.println(count);
		//assertEquals("count failed Incorrect number of rows ", "5", count);
	}
	
	@Test
	public void createTest() {
		XmlRegisteredUser registeredUser = new XmlRegisteredUser();
		registeredUser.setCrDate(new Date());
		registeredUser.setEmail("dave_allen476@hotmail.com");
		registeredUser.setFirstname("Alan");
		registeredUser.setLastname("Mills");
		registeredUser.setTelephone("01388 445561");
		registeredUser.setUpdDate(new Date());
		registeredUser.setLogin(new Login());
		registeredUser.getLogin().setUserId("gordon");
		XmlRegisteredUserMsg xmlRegisteredUserMsg = new XmlRegisteredUserMsg(registeredUser);
		
		ClientResponse response = registeredUserClient.create(xmlRegisteredUserMsg);
		System.out.println(response);
		assertEquals("create failed ", "POST http://localhost:8080/sellemws/rest/registeredusers/create returned a response status of 200 OK", response.toString());
	}
	
	@Test
	public void deleteTest() {
		String response = registeredUserClient.delete("gordon");
		System.out.println(response);
		assertEquals("remove failed ", "DELETE http://localhost:8080/sellemws/rest/registeredusers/delete/gordon returned a response status of 200 OK", response);
	}
	
	@Test
	public void removeTest() {
		String response = registeredUserClient.remove("SUE123");
		System.out.println(response);
		assertEquals("remove failed ", "DELETE http://localhost:8080/sellemws/rest/registeredusers/remove/SUE123 returned a response status of 204 No Content", response);
	}
	
	@Test
	public void findallTest() {
		String xmlStr = registeredUserClient.findAll();
		List lOfRegisteredUser = getLOfRegisteredUser(xmlStr);
		assertEquals("userId should be  ", 4, lOfRegisteredUser.size());
	}
	
	@Test
	public void findByUserIdTest() {
		String xmlStr = registeredUserClient.find("admin");
		List lOfRegisteredUser = getLOfRegisteredUser(xmlStr);
		assertEquals("userId should be  ", 1, lOfRegisteredUser.size());
	}
	
	@Test
	public void updateEmailTest () {
		String xmlStr = registeredUserClient.find("Braffa");
		List lOfRegisteredUser = getLOfRegisteredUser(xmlStr);
		XmlRegisteredUser registeredUser = (XmlRegisteredUser)lOfRegisteredUser.get(0);
		registeredUser.setEmail("dave_allen476@yahoo.co.uk");
		XmlRegisteredUserMsg xmlRegisteredUserMsg = new XmlRegisteredUserMsg(registeredUser);
		
		ClientResponse response = registeredUserClient.update(xmlRegisteredUserMsg);
		System.out.println(response);
		assertEquals("create failed ", "POST http://localhost:8080/sellemws/rest/registeredusers/update returned a response status of 200 OK", response.toString());

	}

}
