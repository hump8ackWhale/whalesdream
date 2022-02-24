package com.dev.whale.controller;

import com.dev.whale.domain.model.Post;
import com.dev.whale.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/myPost")
    public @ResponseBody Map<String, Object> myPostList(String usernameParam) {
        Map<String, Object> map = new HashMap<>();

        List<Post> postList = postService.selectMyPostList(usernameParam);

        map.put("post", postList);

        return map;
    }

}