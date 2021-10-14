package main;

import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.Client;
import spring.Client2;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        Client client = ctx.getBean(Client.class);
        Client client2 = ctx.getBean(Client.class);
        System.out.println(client == client2);
        System.out.println("client = " + client);
        System.out.println("client2 = " + client2);
        ctx.close();
    }
}
