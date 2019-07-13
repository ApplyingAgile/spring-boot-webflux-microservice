package com.aal.be.examplems;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@RefreshScope
public class ConsulConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoDBURI;

    @PostConstruct
    public void postConstruct(){
        System.out.println("**  mongoDBURI ** " + mongoDBURI);
    }

}
