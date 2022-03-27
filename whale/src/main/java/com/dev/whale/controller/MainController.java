package com.dev.whale.controller;

import com.dev.whale.domain.model.Post;
import com.dev.whale.service.AccountService;
import com.dev.whale.service.MainService;
import com.dev.whale.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/main")
public class MainController {

    private final PostService postService;

    @Autowired
    public MainController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/goMainPage")
    public String loginSuccess(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            return "main/main";
        }
        return "index";
    }

    @GetMapping("/mainPostList")
    public ResponseEntity<List<Post>> getPostList(@RequestParam Long lastPostId, @RequestParam int size, @RequestParam Long userId, @RequestParam String flag) {

        List<Post> posts = postService.fetchPostPagesBy(lastPostId, size, userId, flag);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}