package com.dev.whale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestPageController {

    @RequestMapping("testPage")
    public String TestPageController() {
        //testPage 호출 시 mappingTest로 연결된다.
        return "mappingTest";
    }

}
