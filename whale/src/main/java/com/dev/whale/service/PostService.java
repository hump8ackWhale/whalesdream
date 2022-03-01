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

    // 나의 올해다짐 게시
    public void save(Post post) {
        postRepository.save(post);
    }

    // 나의 올해다짐 리스트 조회
    public List<Post> selectMyPostList(String usernameParam) {
        return postRepository.selectMyPostList(usernameParam);
    }

    // 모두 올해다짐 리스트 조회
    public List<Post> selectAllPostList() {
        return postRepository.selectAllPostList();
    }

    // 나의 올해다짐 수정
    public void update(Post post) {
        postRepository.update(post);
    }

    // 올해다짐 find
    public Post findById(int postNo) {
        return postRepository.findById(postNo);
    }

    // 나의 올해다짐 삭제
    public void deleteById(int postNo) { postRepository.deleteById(postNo); }
}
