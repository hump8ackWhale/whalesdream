package com.dev.whale.repository.post;

import com.dev.whale.domain.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    void insertPost(Post post);
    List<Post> selectMyPost(String usernameParam);
}
