package com.braffa.sellem.webservcies.resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.braffa.sellem.model.xml.authentication.XmlRegisteredUserMsg;
import com.braffa.sellem.model.xml.product.XmlProductMsg;
import com.braffa.sellem.model.xml.product.XmlUserToProduct;
import com.braffa.sellem.model.xml.product.XmlUserToProductMsg;
import com.braffa.sellem.model.xml.product.XmlUsersProductMsg;
import com.braffa.sellem.webservcies.IUserToProductWebService;
import com.braffa.sellem.webservcies.services.UserProductServices;
import com.braffa.sellem.webservcies.services.UserToProductService;

@Path("userproduct")
public class userProductResource {
	@Context
	UriInfo uriInfo;
	private static final Logger logger = Logger
			.getLogger(userProductResource.class);

	@GET
	@Path("/finduserproducts/{userId}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_XML)
	public XmlProductMsg findUserProducts(@PathParam("userId") String userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("find");
		}
		try {
			return UserProductServices.getInstance().getUserCatalogue(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/findusersbyproductid/{productId}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_XML)
	public XmlUsersProductMsg findUsersByProductid(@PathParam("productId") String productId) {
		if (logger.isDebugEnabled()) {
			logger.debug("find");
		}
		try {
			return UserProductServices.getInstance().getUsersByProductId(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
