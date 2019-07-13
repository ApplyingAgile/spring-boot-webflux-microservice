package com.aal.be.examplems.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @RequestMapping("/health")
    public String home() {
        return "Hello world";
    }
}
