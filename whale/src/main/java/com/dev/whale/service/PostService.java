package com.dev.whale.service;

import com.dev.whale.domain.model.Post;
import com.dev.whale.domain.model.User;
import com.dev.whale.repository.account.AccountRepository;
import com.dev.whale.repository.post.PostRepository;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class PostService {

    private final PostRepository postRepository;

    private final AccountRepository accountRepository;

    public PostService(PostRepository postRepository, AccountRepository accountRepository) {
        this.postRepository = postRepository;
        this.accountRepository = accountRepository;
    }

    // 나의 올해다짐 게시
    public void save(Post post) {
        postRepository.save(post);
    }

    // 나의 올해다짐 리스트 조회
    public List<Post> selectMyPostList(Long lastPostId, int size, Long userId) {
        PageRequest pageRequest = PageRequest.of(0, size);
        User user = accountRepository.findById(userId);
        List<Post> posts = postRepository.selectMyPostList(lastPostId, pageRequest, user);

        return posts;
    }

    // 모두 올해다짐 리스트 조회
    public List<Post> selectAllPostList(Long lastPostId, int size) {
        PageRequest pageRequest = PageRequest.of(0, size);
        List<Post> posts = postRepository.selectAllPostList(lastPostId, pageRequest);

        return posts;
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
