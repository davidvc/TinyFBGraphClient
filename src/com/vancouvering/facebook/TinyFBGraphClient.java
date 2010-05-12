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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.vancouvering.net.URLInvoker;

public class TinyFBGraphClient {
	private final Map<String, String> standardParms = new TreeMap<String, String>();
	private final URLInvoker invoker;
	static final String FACEBOOK_GRAPH_SERVER = "https://graph.facebook.com";

	public TinyFBGraphClient(String accessToken) {
		this(new URLInvoker(), accessToken);
	}
	
	TinyFBGraphClient(URLInvoker invoker, String accessToken)
	{
		this.invoker = invoker;
		standardParms.put("access_token", accessToken);
	}

	public String callNode(String node) {
		return callNode(node, Collections.<String, String>emptyMap());
	}
	
	public String callNode(String node, Map<String, String> parms) {
		String url = FACEBOOK_GRAPH_SERVER + "/" + node;

		Map<String, String> requestParms = new HashMap<String, String>();
		requestParms.putAll(standardParms);
		requestParms.putAll(parms);
		
		return invoker.call(url, requestParms);
	}
}
