package com.vancouvering.facebook;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.vancouvering.net.URLInvoker;


public class TinyFBGraphClientTest {

	private static final String ACCESS_TOKEN = "access token";
	
	private URLInvoker mockInvoker = mock(URLInvoker.class);
	private TinyFBGraphClient underTest;
	
	@Before
	public void setup()
	{
		underTest = new TinyFBGraphClient(mockInvoker, ACCESS_TOKEN);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void callNodeDelegatesToInvoker()
	{
		String key = "key";
		String value = "value";
		Map<String, String> params = new HashMap<String, String>();
		params.put(key, value);
		String node = "node";

		underTest.callNode(node, params);
		
		String expectedURL = TinyFBGraphClient.FACEBOOK_GRAPH_SERVER + "/" + node;
		ArgumentCaptor<Map> paramsCaptor = ArgumentCaptor.forClass(Map.class);
		verify(mockInvoker).call(eq(expectedURL), paramsCaptor.capture());
		
		Map<String, String> actualParams = paramsCaptor.getValue();
		assertEquals(ACCESS_TOKEN, actualParams.get("access_token"));
		assertEquals(actualParams.get(key), value);	
	}
	
	@SuppressWarnings("unchecked")
	public void callNodeWithNoParamsPassesAccessCode()
	{
		String node = "node";
		
		underTest.callNode(node);
		
		ArgumentCaptor<Map> paramsCaptor = ArgumentCaptor.forClass(Map.class);
		verify(mockInvoker).call(anyString(), paramsCaptor.capture());
		
		Map<String, String> actualParams = paramsCaptor.getValue();
		assertEquals(ACCESS_TOKEN, actualParams.get("access_token"));
		assertEquals(1, actualParams.size());
	}
}
