package com.gaebalje.restTemplate.common;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

public class SearchAPIRestTemplateUtil extends RestOperationImpl implements IRestTemplate {

	public SearchAPIRestTemplateUtil(RestOperations restOperation, String property, String property2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> T get(String url, Object request, ParameterizedTypeReference<T> typeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T post(String url, Object request, ParameterizedTypeReference<T> typeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T put(String url, Object request, ParameterizedTypeReference<T> typeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T delete(String url, Object request, ParameterizedTypeReference<T> typeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T send(String url, Object request, ParameterizedTypeReference<T> typeRef, HttpMethod httpMethod) {
		// TODO Auto-generated method stub
		return null;
	}

}
