package com.dev.whale.controller;

import com.dev.whale.domain.model.Post;
import com.dev.whale.service.AccountService;
import com.dev.whale.service.MainService;
import com.dev.whale.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/main")
public class MainController {

    private final MainService mainService;

    private final PostService postService;

    private final AccountService accountService;

    @Autowired
    public MainController(MainService mainService, PostService postService, AccountService accountService) {
        this.mainService = mainService;
        this.postService = postService;
        this.accountService = accountService;
    }

    @GetMapping("/main")
    public String loginSuccess(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails != null) {

            Long lastPostId = 41L;
            int size = 2;
            List<Post> myPostList = postService.selectMyPostList(lastPostId, size, 41L);

            if (myPostList.size() > 0) {
                model.addAttribute("myPostList", myPostList);
            }
//            model.addAttribute("username", userDetails.getUsername());
        }

        return "main/main";
    }
}