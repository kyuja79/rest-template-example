package com.gaebalje.restTemplate.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

public class SearchAPIRestTemplateUtil extends RestOperationImpl implements IRestTemplate {
	
	private static Logger logger = LoggerFactory.getLogger(SearchAPIRestTemplateUtil.class);
	
	public SearchAPIRestTemplateUtil(RestOperations restOperation, String host, String port) {
		this.restOperations = restOperation;
		this.host = host;
		this.port = port;
	}

	@Override
	public <T> T get(String url, Object request, ParameterizedTypeReference<T> typeRef) {
		return send(getCallUrl(url) + request, null, typeRef, HttpMethod.GET);
	}

	@Override
	public <T> T post(String url, Object request, ParameterizedTypeReference<T> typeRef) {
		return send(getCallUrl(url), request, typeRef, HttpMethod.POST);
	}

	@Override
	public <T> T put(String url, Object request, ParameterizedTypeReference<T> typeRef) {
		return send(getCallUrl(url), request, typeRef, HttpMethod.PUT);
	}

	@Override
	public <T> T delete(String url, Object request, ParameterizedTypeReference<T> typeRef) {
		return null;
	}

	@Override
	public <T> T send(String url, Object request, ParameterizedTypeReference<T> typeRef, HttpMethod httpMethod) {
		return restOperation(logger, url, request, typeRef, httpMethod);
	}

}
