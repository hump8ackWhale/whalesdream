package com.dev.whale.repository.post;

import com.dev.whale.domain.model.Post;
import com.dev.whale.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PostRepository {

    // 나의 올해다짐 게시
    void save(Post post);

    // 나의 올해다짐 리스트 조회
    List<Post> selectMyPostList(Long lastPostId, User user, PageRequest pageRequest);

    // 모두 올해다짐 리스트 조회
    List<Post> selectAllPostList(Long lastPostId, PageRequest pageRequest);

    // 올해다짐 find
    Post findById(int postNo);

    // 나의 올해다짐 수정
    void update(Post post);

    // 나의 올해다짐 삭제
    void deleteById(int postNo);

}
