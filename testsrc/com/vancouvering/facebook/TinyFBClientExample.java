package com.vancouvering.facebook;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * A couple of example uses of TinyFBGraphClient.  You need to first get your
 * access code using OAuth.  See the Facebook developer pages on authentication
 * on how to do this for your environment
 * 
 * For processing JSON, I highly recommend the Svenson API at http://code.google.com/p/svenson/
 * 
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
