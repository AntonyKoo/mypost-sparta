package com.sparta.mypostsparta.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor  // 기본 생성자를 생성
@Entity
public class Post extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto와 차이?
    @Id
    private Long id;  // pk

    @Column(nullable = false)
    private String title;  // 게시글 제목

    @Column(nullable = false)
    private String content;  // 게시글 본문


    // Eager => Post 조회 시, User 객체 같이 조회 (Lazy는 실제 사용 시 조회)
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name="user_idx", nullable = false)
    private Users user;  // 게시글 작성자

    @Builder  // user 객체 통째로
    public Post(String title, String content, Users user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
