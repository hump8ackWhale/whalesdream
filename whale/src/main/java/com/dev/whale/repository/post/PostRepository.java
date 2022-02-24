package com.dev.whale.repository.post;

import com.dev.whale.domain.model.Post;

import java.util.List;

public interface PostRepository {
    void insertPost(Post post);
    List<Post> selectMyPostList(String usernameParam);
}
