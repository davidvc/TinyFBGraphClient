/**
 * Tiny Rest Client for Facebook Open Graph API
 * 
 * @author David Van Couvering
 * http://davidvancouvering.blogspot.com
 * 
 * Inspired by TinyFBClient by
 * @author Carmen Delessio
 * carmendelessio AT gmail DOT com
 * http://www.socialjava.com
 * created March 30, 2009
 *
 **/

package com.vancouvering.facebook;

import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TinyFBGraphClient {
	TreeMap<String, String> standardParms = new TreeMap<String, String>();
	ClientResponse restResponse;
	Client restClient;
	String facebookGraphServer = "http://graph.facebook.com";

	public TinyFBGraphClient() {
		restClient = Client.create();
	}

	public TinyFBGraphClient(String accessToken)
	{
		this();
		standardParms.put("access_token", accessToken);
	}
	
	public String call(String node, TreeMap<String, String> parms) {
		ClientResponse thisResponse;

		thisResponse = this.getResponse(node, parms);
		return (thisResponse.getEntity(String.class));
	}

	public void setRequestParms(TreeMap<String, String> parms) {
		TreeMap<String, String> requestParms = new TreeMap<String, String>();
		requestParms.putAll(standardParms);
		requestParms.putAll(parms);
	}

	private ClientResponse getResponse(String node, TreeMap<String, String> parms) {
		TreeMap<String, String> restParms = new TreeMap<String, String>();
		restParms.putAll(standardParms);
		restParms.putAll(parms);

		UriBuilder ub = UriBuilder.fromPath(facebookGraphServer + "/" + node);
		buildURIParams(ub, restParms);
		
		WebResource resource = buildResource(ub);

		restResponse = resource.get(ClientResponse.class);

		return (restResponse);
	}

	private WebResource buildResource(UriBuilder ub) {
		WebResource resource;
		URI uri;
		uri = ub.build();
		resource = restClient.resource(uri);
		return resource;
	}

	private void buildURIParams(UriBuilder ub, TreeMap<String, String> restParms) {
		Collection<String> c = restParms.keySet();
		Iterator<String> itr = c.iterator();

		while (itr.hasNext()) {
			String currentKey = (String) itr.next();
			String currentValue = restParms.get(currentKey);
			ub.queryParam(currentKey, currentValue);
		}
	}

}
