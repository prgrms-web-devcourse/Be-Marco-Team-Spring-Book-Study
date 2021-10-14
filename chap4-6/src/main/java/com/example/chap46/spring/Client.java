package com.example.chap46.spring;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean, DisposableBean {

	private String host;

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public void afterPropertiesSet() {
		System.out.println("Client.afterPropertiesSet() 실행");
	}

	public void initMethod() {
		System.out.println("Bean initMethod");
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("Bean PostConstruct()");
	}

	public void send() {
		System.out.println("Client.send() to " + host);
	}

	@Override
	public void destroy()  {
		System.out.println("Client.destroy() 실행");
	}

}
