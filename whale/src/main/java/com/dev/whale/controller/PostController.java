package com.dev.whale.controller;

import com.dev.whale.domain.model.User;
import com.dev.whale.service.MainService;
import com.dev.whale.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/allThisYearPromise")
    public String allThisYearPromise() {
        return "post/allThisYearPromise";
    }


}