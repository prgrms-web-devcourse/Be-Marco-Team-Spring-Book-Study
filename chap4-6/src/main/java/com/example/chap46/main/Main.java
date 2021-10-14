package com.example.chap46.main;

import java.io.IOException;
import com.example.chap46.config.*;
import com.example.chap46.spring.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {

	public static void main(String[] args) throws IOException {
		AbstractApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtx.class);
		Client client = ctx.getBean(Client.class);
		client.send();

		ctx.close();
	}

}