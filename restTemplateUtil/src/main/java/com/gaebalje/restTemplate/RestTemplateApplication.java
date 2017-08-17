package com.gaebalje.restTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.gaebalje.restTemplate.common.DeliveryAPIRestTemplateUtil;
import com.gaebalje.restTemplate.common.IRestTemplate;
import com.gaebalje.restTemplate.common.SearchAPIRestTemplateUtil;
import com.gaebalje.restTemplate.common.SsocioAPIRestTemplateUtil;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class RestTemplateApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RestTemplateApplication.class, args);
	}
	
}

@Configuration
@PropertySource(value = { "classpath:application.properties" })
class apiAccessConfiguration{
	
	@Autowired
	private Environment env;

	private final int defaultTimeout = 15000;
	
	private RestOperations getRestTemplate(HttpComponentsClientHttpRequestFactory factory) {
		RestTemplate restTemplate = new RestTemplate(factory);
		
		/*
		 * restTemplate message converter
		 * 
		ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
		StringHttpMessageConverter stringMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		ResourceHttpMessageConverter resourceHttpMessageConverter = new ResourceHttpMessageConverter();
		SourceHttpMessageConverter<Source> sourceHttpMessageConverter = new SourceHttpMessageConverter<Source>();
		
		MappingJackson2HttpMessageConverter jackson2Converter = new MappingJackson2HttpMessageConverter();
		FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
		formHttpMessageConverter.setCharset(Charset.forName("UTF-8"));
		
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(byteArrayHttpMessageConverter);
		converters.add(stringMessageConverter);
		converters.add(resourceHttpMessageConverter);
		converters.add(sourceHttpMessageConverter);
		converters.add(jackson2Converter);
		converters.add(formHttpMessageConverter);
		
		restTemplate.setMessageConverters(converters);
		*/
		
		/*
		 * restTemplate responseHandler 
		 * 
		ResponseErrorHandler responseHandler = new ResponseErrorHandler() {
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				if (response.getStatusCode() != HttpStatus.OK) {
					System.out.println(response.getStatusText());
				}
				return response.getStatusCode() == HttpStatus.OK ? false : true;
			}
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				// TODO Auto-generated method stub
			}
		};
		restTemplate.setErrorHandler(responseHandler);
		*/
		return restTemplate;
	}
	
	private RestOperations getRestOperation(int readTimeout){
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setConnectTimeout(1000);
		factory.setReadTimeout(readTimeout);
        return getRestTemplate(factory);
	}
	
	/**
	 * ssocioAPIRestTemplate
	 * @return
	 */
	@Bean(name="ssocioAPIRestTemplateUtil", autowire = Autowire.BY_NAME)
	public IRestTemplate ssocioAPIRestTemplateUtil(){
		return new SsocioAPIRestTemplateUtil(
				getRestOperation(env.getProperty("ssocioAPI.timeout", int.class, defaultTimeout))
				, env.getProperty("ssocioAPI.host")
				, env.getProperty("ssocioAPI.port")
				, env.getProperty("ssocioAPI.accessPathVariable", "-1"));
	}
	
	/**
	 * searchAPIRestTemplate
	 * @return
	 */
	@Bean(name="searchAPIRestTemplateUtil", autowire = Autowire.BY_NAME)
	public IRestTemplate searchAPIRestTemplateUtil(){
		return new SearchAPIRestTemplateUtil(
				getRestOperation(env.getProperty("searchAPI.timeout", int.class, defaultTimeout))
				, env.getProperty("searchAPI.host")
				, env.getProperty("searchAPI.port"));
	}
	
	/**
	 * deliveryAPIRestTemplate
	 * CJ, sweetTracker
	 * @return
	 */
	@Bean(name="deliveryAPIRestTemplateUtil", autowire = Autowire.BY_NAME)
	public IRestTemplate deliveryAPIRestTemplateUtil(){
		return new DeliveryAPIRestTemplateUtil(
				getRestOperation(env.getProperty("deliveryAPI.timeout", int.class, defaultTimeout))
				, env.getProperty("deliveryAPI.host")
				, env.getProperty("deliveryAPI.port"));
	}
	
}