package com.braffa.sellem.webservcies.client;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;

import com.braffa.sellem.model.xml.authentication.XmlLoginMsg;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class LoginClient extends BaseClient {

	private static final Logger logger = Logger.getLogger(LoginClient.class);

	static final String COUNT = "/login/count";
	static final String FIND_ALL_URI = "/login/findall";
	static final String FIND_URI = "/login/find/";
	static final String CREATE_URI = "/login/create";
	static final String UPDATE_URI = "/login/update";
	static final String DELETE_URI = "/login/delete/";
	
	public String getCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getCount");
		}
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(REST_URI);
		WebResource countService = service.path("rest").path(COUNT);
		return (getResponse(countService));
	}

	public String findall() {
		if (logger.isDebugEnabled()) {
			logger.debug("findall");
		}
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(REST_URI);
		WebResource getAllService = service.path("rest").path(FIND_ALL_URI);
		return getResponseAsXML(getAllService);
	}

	public String find(String userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("find");
		}
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(REST_URI);
		WebResource getService = service.path("rest").path(FIND_URI + userId);
		return getResponseAsXML(getService);
	}

	public void create(XmlLoginMsg xmlLoginMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("create");
		}
		Client client = Client.create();
		WebResource r = client.resource(REST_URI + "rest" + CREATE_URI);
		ClientResponse response = r.accept(MediaType.APPLICATION_XML).post(
				ClientResponse.class, xmlLoginMsg);
		// response.
	}

	public void update(XmlLoginMsg xmlLoginMsg) {
		if (logger.isDebugEnabled()) {
			logger.debug("update");
		}
		try {
			Client client = Client.create();
			WebResource r = client.resource(REST_URI + "rest" + UPDATE_URI);
			ClientResponse response = r.accept(MediaType.APPLICATION_XML).post(
					ClientResponse.class, xmlLoginMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(String userId) {
		if (logger.isDebugEnabled()) {
		logger.debug("delete");
		}
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);

		WebResource service = client.resource(getBaseURI());
		service.path("rest").path(DELETE_URI + userId).delete();
	}

}
