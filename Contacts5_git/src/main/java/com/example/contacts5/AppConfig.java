package com.example.contacts5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan("com.example.contacts5")
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("application.properties"));
        return configurer;
    }

    @Bean
    @Profile("defaultContacts")
    public ContactService defaultContactService() {
        return new ContactServiceImpl();
    }

    @Bean
    @Profile("!defaultContacts")
    public ContactService emptyContactService() {
        return new EmptyContactServiceImpl();
    }

}
