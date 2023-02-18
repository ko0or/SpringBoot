package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class BoardController {
    @GetMapping("/")
    @ResponseBody
    public String DefaultPage() {
        return "localhost:8080 접속시 첫화면 ..!";
    }

    @GetMapping("/main")
    public String PageMain() {
        return "page_main";
    }
}
