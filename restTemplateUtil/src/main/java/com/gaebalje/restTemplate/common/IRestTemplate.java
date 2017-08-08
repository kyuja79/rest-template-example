package com.gaebalje.restTemplate.common;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

public interface IRestTemplate {
	
	public String getCallUrl(String restUrl);
	
	public <T> T get(String url, Object request, ParameterizedTypeReference<T> typeRef);
	
	public <T> T post(String url, Object request, ParameterizedTypeReference<T> typeRef);
	
	public <T> T put(String url, Object request, ParameterizedTypeReference<T> typeRef);
	
	public <T> T delete(String url, Object request, ParameterizedTypeReference<T> typeRef);
	
	public <T> T send(String url, Object request, ParameterizedTypeReference<T> typeRef, HttpMethod httpMethod);
	
}
