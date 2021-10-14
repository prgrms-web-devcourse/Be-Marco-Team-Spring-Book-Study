package com.example.chap46.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.chap46.spring.*;

@Configuration
public class AppCtx {

	@Bean(initMethod = "initMethod")
	public Client client() {
		Client client = new Client();
		client.setHost("host");
//		client.afterPropertiesSet();
		return client;
	}
	
//	@Bean(initMethod = "connect", destroyMethod = "close")
//	public Client2 client2() {
//		Client2 client = new Client2();
//		client.setHost("host");
//		return client;
//	}
}
