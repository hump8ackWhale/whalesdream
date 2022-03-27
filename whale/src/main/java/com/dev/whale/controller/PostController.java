package com.dev.whale.controller;

import com.dev.whale.domain.model.Post;
import com.dev.whale.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/postForm")
    public String postFormView(){
        return "post/postForm";
    }

    @GetMapping("/savePost")
    public String save(@ModelAttribute Post post) { // @RequestParam 사용 시 폼 파라미터 한 번에 받지 못함
        if (post.getLockYn() == null) {
            post.setLockYn("N");
        }
        postService.save(post);
        return "redirect:/post/myPostList";
    }

    @GetMapping("/myPostList")
    public String getPostList(@RequestParam Long lastPostId, @RequestParam int size, @RequestParam Long userId,
                              @RequestParam String flag, Model model) {
        List<Post> posts = postService.fetchPostPagesBy(lastPostId, size, userId, flag);

        model.addAttribute("posts", posts);

        return "post/myPostList";
    }

    @GetMapping("/editPost/{pno}")
    public String edit(@RequestParam int postNo, Model model) {

        Post editPost = postService.findById(postNo);

        model.addAttribute("editPost", editPost);

        return "post/editPost";
    }

    @GetMapping("/updatePost/{pno}")
    public String update(@RequestParam Post post) {
        postService.update(post);

        return "redirect:/post/postList";
    }

    @GetMapping("/deletePost/{pno}")
    public String delete(@RequestParam int postNo) {
        postService.deleteById(postNo);

        return "redirect:/post/postList";
    }
}