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

    public void save(Post post) {
        postRepository.save(post);
    }

    public List<Post> selectMyPostList(String usernameParam) {
        return postRepository.selectMyPostList(usernameParam);
    }

    public List<Post> selectAllPostList() {
        return postRepository.selectAllPostList();
    }

    public void update(Post post) {
        postRepository.update(post);
    }

    public Post findById(int postNo) {
        return postRepository.findById(postNo);
    }

    public void deleteById(int postNo) { postRepository.deleteById(postNo); }
}
