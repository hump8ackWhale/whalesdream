package com.dev.whale.service.post;

import com.dev.whale.domain.model.Post;
import com.dev.whale.repository.post.PostRepository;
import com.dev.whale.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class PostServiceIntegrationTest {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @Test
//    @Commit
    public void save() {

        // Given
        Post post = new Post();

        post.setUsername("mj-yeo");
        post.setTitle("2022년 가장 최근 작성");
        post.setContent("작성 기준 한달 동안 조회 가능");

/*        post.setUsername("mj-yeo");
        post.setTitle("2022년 몇 달 전 작성");
        post.setContent("작성 기준 한달이 지나서 조회 불가능");*/

/*        post.setUsername("mj-yeo");
        post.setTitle("2020 작성1");
        post.setContent("당해년도 이전 글 모두 조회 가능1");*/

/*        post.setUsername("mj-yeo");
        post.setTitle("2021 작성2");
        post.setContent("당해년도 이전 글 모두 조회 가능2");*/

        // When
        postService.save(post);

    }

    @Test
    public void selectMyPost() {

        List<Post> myPost = postService.selectMyPostList("mjyeo");

        System.out.println("나의 올해다짐 리스트 :: " + myPost);
        System.out.println("나의 올해다짐 리스트 개수 :: " + myPost.size());
    }

    @Test
    public void selectAllPost() {

        List<Post> allPost = postService.selectAllPostList();

        System.out.println("올해다짐 리스트 :: " + allPost);
    }

    @Test
    public void update() {

        // Given
        Post post = new Post();

        // when
        postService.update(post);
    }

}
