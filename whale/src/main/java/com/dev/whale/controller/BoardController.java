package com.dev.whale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/board/**")
public class BoardController {

    @GetMapping("/allThisYearPromise")
    public String allThisYearPromise() {
        return "/board/allThisYearPromise";
    }


}
