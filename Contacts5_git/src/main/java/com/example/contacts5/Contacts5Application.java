package com.example.contacts5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Contacts5Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Contacts5Application.class, args);
        ContactController contactController = context.getBean(ContactController.class);
        contactController.handleUserInput();
    }
}
