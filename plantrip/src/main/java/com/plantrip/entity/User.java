package com.plantrip.entity;


import com.plantrip.dto.UserFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="user")
@Getter
@Setter
@ToString
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id; //사용자 기본키 숫자

    private String name; //사용자 이름

    @Column(unique = true)
    private String email; //사용자 이메일

    private String password; //사용자 비밀번호

    private String phone; //사용자 전화번호

    @Enumerated(EnumType.STRING)
    private UserType role; //사용자와 관리자 구분

    private enum UserType {
        ADMIN, USER
    }

    public static User createUser (UserFormDto userFormDto,
                                   PasswordEncoder passwordEncoder){
        //사용자 회원가입, user 엔티티 생성하는 메소드
        User user = new User();
        user.setName(userFormDto.getName());
        user.setEmail(userFormDto.getEmail());
        String password = passwordEncoder.encode(userFormDto.getPassword());
        //스프링 시큐리티 설정 클래스에 등록한 BCryptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호를 암호화한다
        user.setPassword(password);
        user.setRole(UserType.ADMIN);

        return user;
    }
}