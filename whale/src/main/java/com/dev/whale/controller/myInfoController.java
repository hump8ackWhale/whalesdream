package com.dev.whale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myInfo")
public class myInfoController {

    @GetMapping("/myForm")
    public String myInfo() {
        return "myInfo/myForm";
    }
}
