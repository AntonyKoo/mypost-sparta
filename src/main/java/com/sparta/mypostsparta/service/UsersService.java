package com.sparta.mypostsparta.service;

import com.sparta.mypostsparta.controller.dto.SignupRequestDto;
import com.sparta.mypostsparta.domain.Users;
import com.sparta.mypostsparta.domain.UsersRepository;
import com.sparta.mypostsparta.domain.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsersService {

//    private final PasswordEncoder passwordEncoder;  // 스프링시큐리티 의존성에 있음
    private final BCryptPasswordEncoder encodePassword;
    private final UsersRepository usersRepository;

    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UsersService(UsersRepository usersRepository, BCryptPasswordEncoder encodePassword) {
        this.usersRepository = usersRepository;
        this.encodePassword = encodePassword;
    }

    public void saveUserInfo(SignupRequestDto signupRequestDto) {
        // 회원 ID 중복 확인
        String userName = signupRequestDto.getUserName();  // 사용자 아이디는 유니크
        Optional<Users> found = usersRepository.findByUserName(userName);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 패스워드 암호화
        String password = encodePassword.encode(signupRequestDto.getPassword());

        // 사용자 ROLE 확인
        UserRole role = UserRole.USER;  // ROLE_USER 라는 값
        if (signupRequestDto.isAdmin()) {  // false가 default
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRole.ADMIN;
        }

        Users user = new Users(userName, password, role);
        usersRepository.save(user);
    }

}
