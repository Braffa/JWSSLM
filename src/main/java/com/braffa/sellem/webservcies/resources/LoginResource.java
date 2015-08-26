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

import com.braffa.sellem.model.xml.authentication.XmlLoginMsg;
import com.braffa.sellem.webservcies.ILoginWebService;
import com.braffa.sellem.webservcies.services.LoginService;

@Path("login")
public class LoginResource implements ILoginWebService {
	@Context
	UriInfo uriInfo;

	private static final Logger logger = Logger.getLogger(LoginResource.class);

	@Override
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public String count() {
		if (logger.isDebugEnabled()) {
			logger.debug("getCount");
		}
		try {
			return LoginService.getInstance().count();
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	@GET
	@Path("/findall")
	@Produces(MediaType.TEXT_XML)
	public XmlLoginMsg findAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLogins");
		}
		try {
			return LoginService.getInstance().findAll();
		} catch (Exception e) {

		}
		return null;
	}
	
	@Override
	@GET
	@Path("/find/{userId}")
	@Produces(MediaType.TEXT_XML)
	public XmlLoginMsg find(@PathParam("userId") String userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getLogins + " + userId);
		}
		try {
			return LoginService.getInstance().find(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	@POST
	@Path("/create")
	@Consumes("application/xml")
	@Produces("application/xml")
	public XmlLoginMsg create(XmlLoginMsg xmlLoginMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("createLogin");
		}
		URI uri = uriInfo.getAbsolutePathBuilder().path(xmlLoginMsg.getXmllogin().getUserId()).build();
		Response res = Response.created(uri).build();
		try {
			LoginService.getInstance().create(xmlLoginMsg);
		} catch (Exception e){
			
		}
		return xmlLoginMsg;
	}
	
	@Override
	@POST
	@Path("/update")
	@Consumes("application/xml")
	@Produces("application/xml")
	public XmlLoginMsg update(XmlLoginMsg xmlLoginMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("createLogin");
		}
		URI uri = uriInfo.getAbsolutePathBuilder().path(xmlLoginMsg.getXmllogin().getUserId()).build();
		Response res = Response.created(uri).build();
		try {
			LoginService.getInstance().update(xmlLoginMsg);
		} catch (Exception e){
			
		}
		return xmlLoginMsg;
	}
	
	@Override
	@DELETE
	@Path("/delete/{userId}")
	@Consumes("application/xml")
	public XmlLoginMsg delete(@PathParam("userId")String userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLogin " + userId);
		}
		try {
			XmlLoginMsg xmlLoginMsg  = LoginService.getInstance().delete(userId);
			return xmlLoginMsg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
