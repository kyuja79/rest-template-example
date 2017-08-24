package com.gaebalje.restTemplate.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringRunner;

import com.gaebalje.restTemplate.RestTemplateApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestTemplateApplication.class)
public class DeliveryAPIRestTemplateUtilTest {
	
	@Autowired
	private IRestTemplate deliveryAPIRestTemplateUtil; 
	
	@Test
	public void healthCheck(){
		ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {};
		String health = deliveryAPIRestTemplateUtil.get("/health", "", typeRef);
		System.out.println(">>> " + health);
	}
	
}
