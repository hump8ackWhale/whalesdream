package com.dev.whale.service;

import com.dev.whale.repository.post.PostRepository;
import com.dev.whale.repository.qna.QnaRepository;

import javax.transaction.Transactional;

@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


}
