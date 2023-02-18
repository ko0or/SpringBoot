package com.example.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FristApiController {

    @GetMapping("/api/test")
    public String hello() {
        return "hello world";
    }
}
