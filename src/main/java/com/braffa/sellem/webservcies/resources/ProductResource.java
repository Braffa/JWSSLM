package com.braffa.sellem.webservcies.resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.braffa.sellem.model.xml.authentication.XmlLoginMsg;
import com.braffa.sellem.model.xml.product.XmlProductMsg;
import com.braffa.sellem.webservcies.IProductWebService;
import com.braffa.sellem.webservcies.services.LoginService;
import com.braffa.sellem.webservcies.services.ProductService;

@Path("product")
public class ProductResource implements IProductWebService {

	@Context
	UriInfo uriInfo;

	private static final Logger logger = Logger.getLogger(ProductResource.class);
	
	
	@Override
	@POST
	@Path("/create")
	@Consumes("application/xml")
	@Produces("application/xml")
	public XmlProductMsg create(XmlProductMsg xmlProductMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("create");
		}
		URI uri = uriInfo.getAbsolutePathBuilder().path(xmlProductMsg.getProduct().getProductid()).build();
		Response res = Response.created(uri).build();
		try {
			return (XmlProductMsg)ProductService.getInstance().create(xmlProductMsg);
		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}
		return null;
	}
	
	//@Override
	@DELETE
	@Path("/remove/{productId}")
	@Consumes("application/xml")
	public void remove(@PathParam("productId") String productId) {
		if (logger.isDebugEnabled()) {
			logger.debug("remove " + productId);
		}
		try {
			ProductService.getInstance().remove(productId);
		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}
	}
	
	@Override
	@DELETE
	@Path("/delete/{productId}")
	@Consumes("application/xml")
	public void delete(@PathParam("productId") String productId) {
		if (logger.isDebugEnabled()) {
			logger.debug("delete " + productId);
		}
		try {
			ProductService.getInstance().delete(productId);
		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}
	}
	
	@Override
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getCount");
		}
		try {
			return ProductService.getInstance().getCount();
		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}
		return null;
	}
	
	@Override
	@GET
	@Path("/findall")
	@Produces(MediaType.TEXT_XML)
	public XmlProductMsg findAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("findAll");
		}
		try {
			return ProductService.getInstance().findAll();
		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}
		return null;
	}
	
	@Override
	@GET
	@Path("/findByProductId/{productId}")
	@Produces(MediaType.TEXT_XML)
	public XmlProductMsg findByProductId(@PathParam("productId") String productId) {
		if (logger.isDebugEnabled()) {
			logger.debug("findByProductId " + productId);
		}
		try {
			return ProductService.getInstance().findByProductId(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	@GET
	@Path("/searchProduct/{field}/{value}")
	@Produces(MediaType.TEXT_XML)
	public XmlProductMsg searchProduct(@PathParam("field")String field, @PathParam("value") String value) {
		if (logger.isDebugEnabled()) {
			logger.debug("searchProduct by "+ field + " for " + value);
		}
		try {
			return ProductService.getInstance().searchProduct(field, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	@POST
	@Path("/update")
	@Consumes("application/xml")
	@Produces("application/xml")
	public XmlProductMsg update(XmlProductMsg xmlProductMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("update");
		}
		URI uri = uriInfo.getAbsolutePathBuilder().path(xmlProductMsg.getProduct().getProductid()).build();
		Response res = Response.created(uri).build();
		try {
			return (XmlProductMsg)ProductService.getInstance().update(xmlProductMsg);
		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}
		return null;
	}

	@Override
	@POST
	@Path("/remove")
	@Consumes("application/xml")
	@Produces("application/xml")
	public XmlProductMsg remove(XmlProductMsg xmlProductMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("remove");
		}
		URI uri = uriInfo.getAbsolutePathBuilder().path(xmlProductMsg.getProduct().getProductid()).build();
		Response res = Response.created(uri).build();
		try {
			return (XmlProductMsg)ProductService.getInstance().remove(xmlProductMsg);
		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}
		return null;
	}

}
