package com.gaebalje.restTemplate.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

public class SsocioAPIRestTemplateUtil extends RestOperationImpl implements IRestTemplate {

	private static Logger logger = LoggerFactory.getLogger(SsocioAPIRestTemplateUtil.class);
	
	private String accessMember;
	
	public SsocioAPIRestTemplateUtil(RestOperations restOperation, String host, String port,
			String accessMember) {
		this.restOperations = restOperation;
		this.host = host;
		this.port = port;
		this.accessMember = accessMember;
	}
	public String getAccessMember() {
		return accessMember;
	}
	public void setAccessMember(String accessMember) {
		this.accessMember = accessMember;
	}
	@Override
	public String getCallUrl(String restUrl) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.host);
		if(null != this.port && !"".equals(this.port)){
			sb.append(":").append(this.port);
		}
		sb.append(restUrl);
		if(null != this.accessMember && !"".equals(this.accessMember)){
			sb.append("/").append(this.accessMember);
		}
		return sb.toString();
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
