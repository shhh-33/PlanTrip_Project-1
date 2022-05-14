package com.plantrip.entity;


import com.plantrip.constant.Role;
import com.plantrip.dto.UserFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;


@Entity
@Table(name="user_id")
@Getter@Setter
@ToString
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true) //회원은 이메일을 통해 유일하게 구분, 동일한 값 DB에 들어올 수 없게
    private String email;

    private String password;

    private String phone;

    @Enumerated(EnumType.STRING) //eum 순서가 바뀌지 않도록록
    private Role role;


    /* User 엔티티를 생성하는 메소드
      여기에서 관리를 한다면 코드가 변경되더라도 한군데만 수정하면 되는 이점
     */
    public static User createUser(UserFormDto userFormDto, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setName(userFormDto.getName());
        user.setEmail(userFormDto.getEmail());
        user.setPhone(userFormDto.getPhone());

        //스프링 시큐리티 설정 클래스에 등록한 BCrptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호 암호화
        String password = passwordEncoder.encode(userFormDto.getPassword());
        user.setPassword(password);
        user.setRole(Role.ADMIN);

        return user;

    }

}
