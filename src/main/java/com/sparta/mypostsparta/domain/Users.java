package com.sparta.mypostsparta.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Users extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;  // 사용자 아이디

    @Column(nullable = false, length= 100)
    private String password;  // 비밀번호

    @Builder
    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


}
