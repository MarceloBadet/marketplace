package com.badet.marketplace.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.http2client.Http2Client;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@Configuration
public class JsonClientConfiguration {
    
	@Bean
	public Contract useFeignAnnotationsJson() {
		return new Contract.Default();
	}   
	
    @Bean
    public Logger.Level feignLoggerLevelJson() {
        return Logger.Level.FULL;
    }
    
    @Bean
    public Client feignClientJson() {
    	return new Http2Client();
    }
	
	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilderJson() {
		var mapper = new ObjectMapper()
		        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
		        .configure(SerializationFeature.INDENT_OUTPUT, true)
		        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		return Feign.builder()
				.encoder(new JacksonEncoder(mapper))
				.decoder(new JacksonDecoder(mapper));
	}	

}
