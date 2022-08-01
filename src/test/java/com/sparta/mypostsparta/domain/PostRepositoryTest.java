package com.sparta.mypostsparta.domain;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// junit 아니라 assertj에서 core api로 들어가자
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @After("")
    public void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("게시판 저장 테스트")
    public void createPostTest() {
        // given
        Users user1 = new Users("테스트아이디","1234");
        Post post = new Post("테스트 글제목", "테스트 본문", user1);

        // 댓글, 작성자 제외 테스트 -> comment table 없어서 실패
        postRepository.save(Post.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .user(user1)  // post에 user로 씀
                .build());

        //when
        List<Post> postList = postRepository.findAll();

        // then
        Post testPost = postList.get(0);
        assertThat(testPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(testPost.getContent()).isEqualTo(post.getContent());
//        assertThat(testPost.getUser()).isEqualTo(user1);
    }


}