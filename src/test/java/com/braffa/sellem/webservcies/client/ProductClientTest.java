package com.braffa.sellem.webservcies.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.braffa.sellem.dao.utility.mysql.MySQLSetUp;
import com.braffa.sellem.hbn.dao.product.ProductDao;
import com.braffa.sellem.model.xml.product.XmlProduct;
import com.braffa.sellem.model.xml.product.XmlProductMsg;
import com.sun.jersey.api.client.ClientResponse;

public class ProductClientTest {

	private ProductClient productClient;

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
	public void setProductClient() {
		productClient = new ProductClient();
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

	@Test
	public void createTest() {
		productClient = new ProductClient();
		XmlProduct xmlProduct = new XmlProduct();
		xmlProduct = new XmlProduct();
		xmlProduct.setAuthor("Arthur C Clark");
		xmlProduct
				.setImageLargeURL("http://ecx.images-amazon.com/images/I/51YKR0ZVKRL._SL75_.jpg");
		xmlProduct
				.setImageURL("ttp://ecx.images-amazon.com/images/I/51YKR0ZVKRL._SL75_.jpg");
		xmlProduct.setManufacturer("Google");
		xmlProduct.setProductgroup("Book");
		xmlProduct.setProductid("987654328");
		xmlProduct.setProductidtype("ISBN");
		xmlProduct.setSource("Daelington");
		xmlProduct.setTitle("New from webserviceclient");

		XmlProductMsg xmlProductMsg = new XmlProductMsg(xmlProduct);
		ClientResponse response = productClient.create(xmlProductMsg);
		assertEquals("create failed ", "POST http://localhost:8080/sellemws/rest/product/create returned a response status of 200 OK", response.toString());

	}

	@Test
	public void deleteTest() {
		productClient = new ProductClient();
		String response = productClient.delete("987654328");
		System.out.println(response);
		assertEquals("remove failed ", "DELETE http://localhost:8080/sellemws/rest/product/delete/987654328 returned a response status of 204 No Content", response);
	}
	
	@Test
	public void removeTest() {
		productClient = new ProductClient();
		String response = productClient.remove("5050582388237");
		System.out.println(response);
		assertEquals("remove failed ", "DELETE http://localhost:8080/sellemws/rest/product/remove/5050582388237 returned a response status of 204 No Content", response);
	}

	@Test
	public void getCountTest() {
		productClient = new ProductClient();
		String count = productClient.getCount();
		//assertEquals("getCount failed Incorrect number of rows ", "5", count);
	}

	@Test
	public void findByProductIdTest() {
		productClient = new ProductClient();
		String xmlStr = productClient.findByProductId("9781861005618");
		List lOfProducts = getLOfProducts(xmlStr);
		assertEquals("products returned should be ", 1, lOfProducts.size());
		XmlProduct product = (XmlProduct) lOfProducts.get(0);
		assertEquals("author should be  ", "Subrahmanyam Allamaraju",
				product.getAuthor());
		assertEquals("imageURL should be  ",
				"http://ecx.images-amazon.com/images/I/51YKR0ZVKRL._SL75_.jpg",
				product.getImageURL());
		assertEquals("ImageLargeURL should be  ", "",
				product.getImageLargeURL());
		assertEquals("productgroup should be  ", "Book",
				product.getProductgroup());
		assertEquals("productid should be  ", "9781861005618",
				product.getProductid());
		assertEquals("productidtype should be  ", "EAN",
				product.getProductidtype());
		assertEquals("source should be  ", "Amazon", product.getSource());
		assertEquals("sourceid should be  ", "186100561X",
				product.getSourceid());
		assertEquals("title should be  ",
				"Professional Java Servlets 2.3 (Programmer to Programmer)",
				product.getTitle());
		assertEquals("userId should be  ", null, product.getUserId());
		assertEquals("action should be  ", null, product.getAction());
		assertFalse("crDate should be  ", null == product.getCrDate());
		assertFalse("updDate should be  ", null == product.getUpdDate());
	}

	@Test
	public void findallTest() {
		productClient = new ProductClient();
		String xmlStr = productClient.findall();
		List lOfProducts = getLOfProducts(xmlStr);
		//assertEquals("userId should be  ", 5, lOfProducts.size());
	}

	@Test
	public void updateTest () {
		productClient = new ProductClient();
		XmlProductMsg xmlProductMsg = new XmlProductMsg();
		XmlProduct xmlProduct = new XmlProduct();
		xmlProduct.setProductid("9780789724410");
		xmlProduct.setAuthor("David the Great Updater");
		xmlProductMsg.setProduct(xmlProduct);
		
		ClientResponse response =  productClient.update(xmlProductMsg);
		System.out.println(response);
		assertEquals("create failed ", "POST http://localhost:8080/sellemws/rest/product/update returned a response status of 200 OK", response.toString());
	}

	@Test
	public void getProductByAuthorTest() {
		productClient = new ProductClient();
		String xmlStr = productClient.searchProduct("author",
				"Sandercoe Justin");
		List lOfProducts = getLOfProducts(xmlStr);
		XmlProduct product = (XmlProduct) lOfProducts.get(0);
		assertEquals("author should be  ", "Sandercoe Justin",
				product.getAuthor());
		assertEquals("imageURL should be  ",
				"http://ecx.images-amazon.com/images/I/41hdqEVaWML._SL75_.jpg",
				product.getImageURL());
		assertEquals("ImageLargeURL should be  ", "",
				product.getImageLargeURL());
		assertEquals("productgroup should be  ", "Book",
				product.getProductgroup());
		assertEquals("productid should be  ", "9781849386685",
				product.getProductid());
		assertEquals("productidtype should be  ", "EAN",
				product.getProductidtype());
		assertEquals("source should be  ", "Amazon", product.getSource());
		assertEquals("sourceid should be  ", "1849386684",
				product.getSourceid());
		assertEquals(
				"title should be  ",
				"Justinguitar.Com Beginners Songbook J. Sandercoe (Easy Guitar With Notes and Tab)",
				product.getTitle());
		assertEquals("userId should be  ", null, product.getUserId());
		assertEquals("action should be  ", null, product.getAction());
		assertFalse("crDate should be  ", null == product.getCrDate());
		assertFalse("updDate should be  ", null == product.getUpdDate());
	}

	@Test
	public void getProductByTitleTest() {
		productClient = new ProductClient();
		String xmlStr = productClient.searchProduct("title",
				"jQuery: Novice to Ninja");
		List lOfProducts = getLOfProducts(xmlStr);
		XmlProduct product = (XmlProduct) lOfProducts.get(0);
		assertEquals("author should be  ", "Craig Sharkie",
				product.getAuthor());
		assertEquals("imageURL should be  ",
				"http://ecx.images-amazon.com/images/I/412Ddd%2Bq1ZL._SL75_.jpg",
				product.getImageURL());
		assertEquals("ImageLargeURL should be  ",
				"",
				product.getImageLargeURL());
		assertEquals("Manufacturer should be  ",
				"SITEPOINT (ACADEMIC)",
				product.getManufacturer());
		assertEquals("productgroup should be  ", "Book",
				product.getProductgroup());
		assertEquals("productid should be  ", "978098056856",
				product.getProductid());
		assertEquals("productidtype should be  ", "EAN",
				product.getProductidtype());
		assertEquals("source should be  ", "Amazon", product.getSource());
		assertEquals("sourceid should be  ", "980576857", product.getSourceid());
		assertEquals("title should be  ", "jQuery: Novice to Ninja",
				product.getTitle());
		assertEquals("userId should be  ", null, product.getUserId());
		assertEquals("action should be  ", null, product.getAction());
		assertFalse("crDate should be  ", null == product.getCrDate());
		assertFalse("updDate should be  ", null == product.getUpdDate());
	}

	@Test
	public void getProductByProductIdTest() {
		productClient = new ProductClient();
		System.out.println(productClient.searchProduct("productid",
				"9781861005618"));
	}

	@Test
	public void getProductBymanufacturerTest() {
		productClient = new ProductClient();
		System.out.println(productClient.searchProduct("manufacturer",
				"WROX Press Ltd"));
	}
	
	@Test
	public void removeTest2() {
		productClient = new ProductClient();
		XmlProductMsg xmlProductMsg = new XmlProductMsg();
		XmlProduct xmlProduct = new XmlProduct();
		xmlProduct.setProductid("9780789799999");
		//xmlProduct.setProductid("9780789799999");
		xmlProduct.setAuthor("David the Great Updater");
		xmlProductMsg.setProduct(xmlProduct);
		
		ClientResponse response = productClient.remove(xmlProductMsg);
		System.out.println(response);
		assertEquals("create failed ", "POST http://localhost:8080/sellemws/rest/product/remove/ returned a response status of 200 OK", response.toString());
	}

}
