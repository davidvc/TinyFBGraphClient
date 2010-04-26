/*
 * Copyright 2010 David Van Couvering
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this 
 *  file except in compliance with the License. You may obtain a copy of the License 
 *  at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law 
 *  or agreed to in writing, software distributed under the License is distributed o
 *  n an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express 
 *  or implied. See the License for the specific language governing permissions and 
 *  limitations under the License.  
 */

/**
 * Tiny Rest Client for Facebook Open Graph API
 * 
 * @author David Van Couvering
 * http://davidvancouvering.blogspot.com
 * 
 * This is a modified and refined version of TinyFBClient
 * by Carmen Delessio
 * 
 * carmendelessio AT gmail DOT com
 * http://www.socialjava.com
 * created March 30, 2009
 **/

package com.vancouvering.facebook;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TinyFBGraphClient {
	private Map<String, String> standardParms = new TreeMap<String, String>();
	private ClientResponse restResponse;
	private Client restClient;
	private String facebookGraphServer = "http://graph.facebook.com";

	public TinyFBGraphClient() {
		restClient = Client.create();
	}

	public TinyFBGraphClient(String accessToken)
	{
		this();
		standardParms.put("access_token", accessToken);
	}
	
	public String call(String node) {
		return call(node, Collections.<String, String>emptyMap());
	}
	
	public String call(String node, Map<String, String> parms) {
		ClientResponse thisResponse;

		thisResponse = this.getResponse(node, parms);
		return (thisResponse.getEntity(String.class));
	}

	public void setRequestParms(Map<String, String> parms) {
		TreeMap<String, String> requestParms = new TreeMap<String, String>();
		requestParms.putAll(standardParms);
		requestParms.putAll(parms);
	}

	private ClientResponse getResponse(String node, Map<String, String> parms) {
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

	private void buildURIParams(UriBuilder ub, Map<String, String> restParms) {
		for (Entry<String, String> entry: restParms.entrySet()) {
			ub.queryParam(entry.getKey(), entry.getValue());
		}
	}


}
