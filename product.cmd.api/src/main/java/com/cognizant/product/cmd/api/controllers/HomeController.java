package com.cognizant.product.cmd.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping(path = "/")
    String home(){
        return "<h1>Welcome to Cognizant  auction</h1>";
    }

    @GetMapping(path = "/hi")
    String hello(){
        return "<h1>Hi there</h1>";
    }
}
