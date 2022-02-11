package com.dev.whale.service.post;

import com.dev.whale.repository.post.PostRepository;
import com.dev.whale.repository.qna.QnaRepository;
import com.dev.whale.service.PostService;
import com.dev.whale.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class PostServiceIntegrationTest {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

}
