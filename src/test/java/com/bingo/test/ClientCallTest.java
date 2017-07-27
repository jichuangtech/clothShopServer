package com.bingo.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import junit.framework.TestCase;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;


public class ClientCallTest extends TestCase{
	
    public void testJsonResponse() throws IOException, URISyntaxException {  
        String url = "http://localhost:8080/clothshopserver/api/order/list?userId=16777215";  
//    	String url = "http://localhost:8080/clothshopserver/api/goodsCategory/list";  
        ClientHttpRequest request = new SimpleClientHttpRequestFactory().createRequest(new URI(url), HttpMethod.GET); 
        request.getHeaders().set("Accept", "application/json");  
        ClientHttpResponse response = request.execute();  
        InputStream is = response.getBody();  
        byte bytes[] = new byte[(int) response.getHeaders().getContentLength()];  
        is.read(bytes);  
  
        String jsonData = new String(bytes);  
        System.out.println(jsonData);  
    }  
}
