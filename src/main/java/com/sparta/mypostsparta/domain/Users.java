package com.sparta.mypostsparta.domain;


import com.sparta.mypostsparta.domain.enums.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Users extends Timestamped {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;  // key

    @Column(nullable = false, unique = true)  // 중복 불허
    private String userName;  // 사용자 아이디

    @Column(nullable = false, length= 100)
    private String password;  // 비밀번호

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();  // ORM, 게시글과의 연관관계

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;  // 사용자, 관리자 구분 필드
    
    @Builder
    public Users(String userName, String password, UserRole role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

}
