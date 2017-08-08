package com.gaebalje.restTemplate.common;

import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestOperationImpl {
	
	protected RestOperations restOperations;
	protected String host = "";
	protected String port = "";

	public String getCallUrl(String restUrl) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.host);
		if(null != this.port && !"".equals(this.port)){
			sb.append(":").append(this.port);
		}
		sb.append(restUrl);
		return sb.toString();
	}
	
	protected <T> T restOperation(Logger logger, String url, Object request, ParameterizedTypeReference<T> typeRef,
			HttpMethod httpMethod) {
		HttpEntity<Object> entity = getEntity(request);
		ResponseEntity<T> responseEntity = null;
		try {
			responseEntity = restOperations.exchange(url, httpMethod, entity, typeRef);
		} catch (ResourceAccessException raex) {
			Throwable t = raex.getCause();
			if (t != null && !(t instanceof SocketTimeoutException)) {
				logger.error("ResourceAccessException - {}", raex);
			}
		}catch (HttpClientErrorException hcex){            
			logger.error("HttpClientErrorException - {}", hcex);        
        } catch (RestClientException rcex) {
        	logger.error("RestClientException - {}", rcex);
        }
        catch (Exception ex) {
            logger.error("url", ex);
        }

		if(null != responseEntity && HttpStatus.OK.equals(responseEntity.getStatusCode())){
//			System.out.println("==================================================================");
//			System.out.println(logger.getName());
//			System.out.println("==================================================================");
            return responseEntity.getBody();
        } else {
            if(null != responseEntity){
            	try {
            		logger.error("StatusCode : " + responseEntity.getStatusCode());
					logger.error(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(responseEntity));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
					logger.error("StatusCode : " + responseEntity.getStatusCode());
				}
            }
        }
        return null;
	}

	private HttpEntity<Object> getEntity(Object request) {
		return new HttpEntity<Object>(request, getHeaders());
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
//		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return headers;
	}
	
}
