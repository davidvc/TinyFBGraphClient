package com.vancouvering.net;

import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class URLInvoker {
	private final Client client;

	public URLInvoker()
	{
		this(Client.create());
	}
	
	URLInvoker(Client client)
	{
		this.client = client;
	}
	
	public String call(String URL, Map<String, String> parms)
	{
		ClientResponse response = getResponse(URL, parms);
		return response.getEntity(String.class);
	}
	
	public String call(String URL)
	{
		ClientResponse response = getResponse(URL);
		return response.getEntity(String.class);
	}
	
	private ClientResponse getResponse(String URI)
	{
		UriBuilder ub = UriBuilder.fromUri(URI);
		WebResource resource = buildResource(ub);
		return resource.get(ClientResponse.class);
	}
	
	private ClientResponse getResponse(String path, Map<String, String> parms) {
		UriBuilder ub = UriBuilder.fromPath(path);
		buildURIParams(ub, parms);
		
		WebResource resource = buildResource(ub);

		return resource.get(ClientResponse.class);
	}

	private WebResource buildResource(UriBuilder ub) {
		return client.resource(ub.build());
	}

	private void buildURIParams(UriBuilder ub, Map<String, String> restParms) {
		for (Entry<String, String> entry: restParms.entrySet()) {
			ub.queryParam(entry.getKey(), entry.getValue());
		}
	}

}
