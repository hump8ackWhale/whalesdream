package com.dev.whale.service.post;

import com.dev.whale.domain.model.Post;
import com.dev.whale.repository.post.PostRepository;
import com.dev.whale.repository.qna.QnaRepository;
import com.dev.whale.service.PostService;
import com.dev.whale.service.QnaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class PostServiceIntegrationTest {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @Test
//    @Commit
    public void insertPost() {

        Post post = new Post();

        post.setUsername("mj-yeo");
        post.setTitle("2021 작성");
        post.setContent("작년도 글 조회 가능");
        postService.insertPost(post);

        System.out.print("생성날짜 : " + post.getCreatedDate());
        System.out.print("생성날짜 : " + post.getModifiedDate());
    }

    @Test
    public void selectMyPost() {

        List<Post> myPost = postService.selectMyPost("mj-yeo");

        System.out.print("나의 올해다짐 리스트 :: " + myPost);
    }

}
