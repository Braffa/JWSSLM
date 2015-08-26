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
import com.braffa.sellem.webservcies.IRegisteredUserWebService;
import com.braffa.sellem.webservcies.services.ProductService;
import com.braffa.sellem.webservcies.services.RegisteredUserService;

@Path("registeredusers")
public class RegisteredUserResource implements IRegisteredUserWebService {
	@Context
	UriInfo uriInfo;
	private static final Logger logger = Logger
			.getLogger(RegisteredUserResource.class);

	@Override
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public String count() {
		if (logger.isDebugEnabled()) {
			logger.debug("count");
		}
		try {
			return RegisteredUserService.getInstance().count();
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	@POST
	@Path("/create")
	@Consumes("application/xml")
	@Produces("application/xml")
	public XmlRegisteredUserMsg create(XmlRegisteredUserMsg xmlRegisteredUserMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("create");
		}
		URI uri = uriInfo
				.getAbsolutePathBuilder()
				.path(xmlRegisteredUserMsg.getRegisteredUser().getLogin()
						.getUserId()).build();
		Response res = Response.created(uri).build();
		try {
			RegisteredUserService.getInstance().create(xmlRegisteredUserMsg);
		} catch (Exception e) {

		}
		return xmlRegisteredUserMsg;
	}

	@Override
	@DELETE
	@Path("/delete/{userId}")
	@Consumes("application/xml")
	public XmlRegisteredUserMsg delete(String userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("delete");
		}
		try {
			XmlRegisteredUserMsg xmlRegisteredUserMsg = RegisteredUserService
					.getInstance().delete(userId);
			return xmlRegisteredUserMsg;
		} catch (Exception e) {

		}
		return null;
	}
	
	//@Override
	@DELETE
	@Path("/remove/{userId}")
	@Consumes("application/xml")
	public void remove(@PathParam("userId") String userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("remove " + userId);
		}
		try {
			RegisteredUserService.getInstance().remove(userId);
		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}
	}
	
	@Override
	@GET
	@Path("/findall")
	@Produces(MediaType.TEXT_XML)
	public XmlRegisteredUserMsg findAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("findAll");
		}
		try {
			return RegisteredUserService.getInstance().findAll();
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	@GET
	@Path("/findbyuserid/{userId}")
	@Produces(MediaType.TEXT_XML)
	public XmlRegisteredUserMsg find(@PathParam("userId") String userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("find");
		}
		try {
			return RegisteredUserService.getInstance().find(userId);
		} catch (Exception e) {

		}
		return null;
	}
	
	@Override
	@POST
	@Path("/update")
	@Consumes("application/xml")
	@Produces("application/xml")
	public XmlRegisteredUserMsg update(XmlRegisteredUserMsg xmlRegisteredUserMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("update");
		}
		URI uri = uriInfo
				.getAbsolutePathBuilder()
				.path(xmlRegisteredUserMsg.getRegisteredUser().getLogin()
						.getUserId()).build();
		Response res = Response.created(uri).build();
		try {
			RegisteredUserService.getInstance().update(xmlRegisteredUserMsg);
		} catch (Exception e) {

		}
		return xmlRegisteredUserMsg;
	}
}
