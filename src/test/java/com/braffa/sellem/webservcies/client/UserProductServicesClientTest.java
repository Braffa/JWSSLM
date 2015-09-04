package com.braffa.sellem.webservcies.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.braffa.sellem.dao.utility.mysql.MySQLSetUp;
import com.braffa.sellem.hbn.dao.product.ProductDao;
import com.braffa.sellem.model.xml.authentication.XmlRegisteredUser;
import com.braffa.sellem.model.xml.authentication.XmlRegisteredUserMsg;
import com.braffa.sellem.model.xml.product.XmlProduct;
import com.braffa.sellem.model.xml.product.XmlProductMsg;
import com.braffa.sellem.model.xml.product.XmlUsersProductMsg;
import com.sun.jersey.api.client.ClientResponse;

public class UserProductServicesClientTest {

	private UserProductServicesClient userProductServicesClient;

	@BeforeClass
	public static void setUpDatabase() {
		try {
			MySQLSetUp mySQLSetUp = new MySQLSetUp();
			mySQLSetUp.setUpProduct();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUserProductServicesClient() {
		userProductServicesClient = new UserProductServicesClient();
	}

	private XmlProductMsg convertStringToObject(String xmlStr) {
		try {
			StringReader reader = new StringReader(xmlStr);
			JAXBContext jaxbContext = JAXBContext
					.newInstance(XmlProductMsg.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (XmlProductMsg) jaxbUnmarshaller.unmarshal(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<XmlProduct> getLOfProducts(String xmlStr) {
		XmlProductMsg xmlProductMsg = convertStringToObject(xmlStr);
		return xmlProductMsg.getLOfProducts();
	}

	private List<XmlRegisteredUser> getLOfRegisteredUsers (String xmlStr) {
		try {
			StringReader reader = new StringReader(xmlStr);
			JAXBContext jaxbContext = JAXBContext
					.newInstance(XmlRegisteredUserMsg.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			XmlRegisteredUserMsg xmlRegisteredUserMsg = (XmlRegisteredUserMsg) jaxbUnmarshaller.unmarshal(reader);
			return xmlRegisteredUserMsg.getLOfRegisteredUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private XmlUsersProductMsg getXmlUsersProductMsg (String xmlStr) {
		try {
			StringReader reader = new StringReader(xmlStr);
			JAXBContext jaxbContext = JAXBContext
					.newInstance(XmlUsersProductMsg.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			XmlUsersProductMsg xmlUsersProductMsg = (XmlUsersProductMsg) jaxbUnmarshaller.unmarshal(reader);
			return xmlUsersProductMsg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public void findUsersByProductIdTest() {
		String xmlStr = userProductServicesClient.findUsersByProductId("9780789724410");
		System.out.println(xmlStr);
		XmlUsersProductMsg xmlUsersProductMsg = getXmlUsersProductMsg(xmlStr);

		System.out.println(xmlUsersProductMsg.getProduct().toString());
		
		List lOfXmlUsersLinkedToProduct = xmlUsersProductMsg.getlOfXmlUsersLinkedToProduct();

		assertEquals("Expect 2 UsersLinkedToProduct ", 2, lOfXmlUsersLinkedToProduct.size());
	}


}
