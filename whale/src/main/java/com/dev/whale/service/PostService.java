package com.dev.whale.service;

import com.dev.whale.domain.model.Post;
import com.dev.whale.repository.post.PostRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void insertPost(Post post) {
        postRepository.insertPost(post);
    }

    public List<Post> selectMyPostList(String usernameParam) {
        return postRepository.selectMyPostList(usernameParam);
    }

}
