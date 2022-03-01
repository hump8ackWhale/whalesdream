package com.dev.whale.repository.post;

import com.dev.whale.domain.model.Post;

import java.util.List;

public interface PostRepository {

    // 나의 올해다짐 게시
    void save(Post post);

    // 나의 올해다짐 리스트 조회
    List<Post> selectMyPostList(String usernameParam);

    // 모두 올해다짐 리스트 조회
    List<Post> selectAllPostList();

    // 올해다짐 find
    Post findById(int postNo);

    // 나의 올해다짐 수정
    void update(Post post);

    // 나의 올해다짐 삭제
    void deleteById(int postNo);
}
