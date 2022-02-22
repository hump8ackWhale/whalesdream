package com.dev.whale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    //@GetMapping("/")
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String getIndexPage() {
        return "index";
    }
}