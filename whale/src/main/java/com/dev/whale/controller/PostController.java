package com.dev.whale.controller;

import com.dev.whale.domain.model.Post;
import com.dev.whale.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String save(@RequestParam Post post) {
        postService.save(post);

        return "redirect:/post/myPostList";
    }

    @GetMapping("/myPostList")
    public String myPostPagingList(Model model, @RequestParam int lastPostId, @RequestParam int size, @RequestParam Long userId) {

        List<Post> myPostList = postService.selectMyPostList(lastPostId, size, userId);

        if (myPostList.size() > 0) {
            model.addAttribute("myPostList", myPostList);
        }

        return "post/myPostList";
    }

    @GetMapping("/allPostList")
    public String allPostList(Model model, @RequestParam int lastPostId, @RequestParam int size) {

        List<Post> allPostList = postService.selectAllPostList(lastPostId, size);

        if (allPostList.size() > 0) {
            model.addAttribute("allPostList", allPostList);
        }

        return "post/allPostList";
    }

    @GetMapping("/editPost/{pno}")
    public String edit(@PathVariable("pno") int postNo, Model model) {

        Post editPost = postService.findById(postNo);

        model.addAttribute("editPost", editPost);

        return "post/editPost";
    }

    @GetMapping("/updatePost/{pno}")
    public String update(Post post) {
        postService.update(post);

        return "redirect:/post/myPostList";
    }

    @GetMapping("/deletePost/{pno}")
    public String delete(@PathVariable("pno") int postNo) {
        postService.deleteById(postNo);

        return "redirect:/post/myPostList";
    }
}