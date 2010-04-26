package com.vancouvering.facebook;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/*
 * Copyright 2010 David Van Couvering
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this 
 *  file except in compliance with the License. You may obtain a copy of the License 
 *  at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law 
 *  or agreed to in writing, software distributed under the License is distributed o
 *  n an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express 
 *  or implied. See the License for the specific language governing permissions and 
 *  limitations under the License.  * A couple of example uses of TinyFBGraphClient.  
 */

/*
 * To use these examples, you need to first get your access code using OAuth.  
 * See the Facebook developer pages on authentication on how to do this for your environment
 * 
 * For processing the resulting JSON, I highly recommend the Svenson API at 
 * http://code.google.com/p/svenson/
 */
public class TinyFBClientExample {
	private static final String ACCESS_TOKEN = "put your access token string here";
	
	private TinyFBGraphClient client = new TinyFBGraphClient(ACCESS_TOKEN);
	
	@Test
	public void getMyFriendsExample()
	{
		String result = client.call("me/friends");
		System.out.println(result);
	}
	
	@Test
	public void getUserProfileExample()
	{
		Map<String, String> params = new HashMap<String, String>();
		params.put("limit", "10");
		String result = client.call("barackobama/feed", params);
		
		System.out.println(result);
	}
}
