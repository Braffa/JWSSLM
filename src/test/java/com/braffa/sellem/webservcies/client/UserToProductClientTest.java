package com.braffa.sellem.webservcies.client;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.braffa.sellem.dao.utility.mysql.MySQLSetUp;
import com.braffa.sellem.model.xml.authentication.XmlRegisteredUser;
import com.braffa.sellem.model.xml.authentication.XmlRegisteredUserMsg;
import com.braffa.sellem.model.xml.product.XmlUserToProduct;
import com.braffa.sellem.model.xml.product.XmlUserToProductMsg;
import com.sun.jersey.api.client.ClientResponse;

public class UserToProductClientTest {
	
	private UserToProductClient userToProductClient;
	
	@BeforeClass
	public static void setUpDatabase() {
		try {
			MySQLSetUp mySQLSetUp = new MySQLSetUp();
			mySQLSetUp.setUpUserToProductTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setProductClient() {
		userToProductClient = new UserToProductClient();
	}
	
	private XmlUserToProductMsg convertStringToObject(String xmlStr) {
		try {
			StringReader reader = new StringReader(xmlStr);
			JAXBContext jaxbContext = JAXBContext
					.newInstance(XmlUserToProductMsg.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (XmlUserToProductMsg) jaxbUnmarshaller.unmarshal(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<XmlUserToProduct> getLOfUserToProduct(String xmlStr) {
		XmlUserToProductMsg xmlUserToProductMsg = convertStringToObject(xmlStr);
		return xmlUserToProductMsg.getLOfXmlUserToProduct();
	}
	
	@Test
	public void countTest() {
		String count = userToProductClient.count();
		System.out.println(count);
		//assertEquals("count failed Incorrect number of rows ", "5", count);
	}
	
	@Test
	public void createTest () {
		XmlUserToProduct userToProduct = new XmlUserToProduct ();
		userToProduct.setUserId("Deepa123");
		userToProduct.setProductId("12346789123");
		userToProduct.setProductIndex(0);
		XmlUserToProductMsg userToProductMsg = new XmlUserToProductMsg(userToProduct);
		ClientResponse resp = userToProductClient.create(userToProductMsg);
		System.out.println(resp);
		assertEquals("create failed", "POST http://localhost:8080/sellemws/rest/usertoproduct/create returned a response status of 200 OK", resp.toString());
	}
	
	@Test
	public void deleteTest() {
		String response = userToProductClient.delete("amanda33", "9999999999000", "0");
		System.out.println(response);
		assertEquals("create failed", "DELETE http://localhost:8080/sellemws/rest/usertoproduct/delete/amanda33/9999999999000/0 returned a response status of 200 OK", response.toString());
	}
	
	@Test
	public void findAllTest() {
		String xmlStr = userToProductClient.findAll();
		List lOfUserToProduct = getLOfUserToProduct(xmlStr);
		//assertEquals("count failed Incorrect number of rows ", "5", lOfUserToProduct.size());
	}

	
	@Test
	public void findTest() {
		String xmlStr = userToProductClient.find("Braffa", "978098056856", "0");
		List lOfUserToProduct = getLOfUserToProduct(xmlStr);
		assertEquals("count failed Incorrect number of rows ", 1, lOfUserToProduct.size());
		XmlUserToProduct result = (XmlUserToProduct)lOfUserToProduct.get(0);
		assertEquals("userId failed Incorrect number of rows ", "Braffa", result.getUserId());
		assertEquals("productId failed Incorrect number of rows ", "978098056856", result.getProductId());
		assertEquals("productId failed Incorrect number of rows ", 0, result.getProductIndex());
	}
	
	@Test
	public void removeTest () {
		ClientResponse resp = userToProductClient.remove("grimbo" , "9999999999001", "0");
		System.out.println(resp);
		assertEquals("delete failed", "DELETE http://localhost:8080/sellemws/rest/usertoproduct/remove/grimbo/9999999999001/0 returned a response status of 204 No Content", resp.toString());
	}
	
	@Test 
	public void searchByUserIdTest () {
		String xmlStr = userToProductClient.search("USERID", "Braffa");
		System.out.println(xmlStr);
		List lOfUserToProduct = getLOfUserToProduct(xmlStr);
		assertEquals("count failed Incorrect number of rows ", 5, lOfUserToProduct.size());
	}
	
	@Test 
	public void searchByProductIdTest () {
		String xmlStr = userToProductClient.search("PRODUCTID", "9780789724410");
		System.out.println(xmlStr);
		List lOfUserToProduct = getLOfUserToProduct(xmlStr);
		assertEquals("count failed Incorrect number of rows ", 2, lOfUserToProduct.size());
	}

}
