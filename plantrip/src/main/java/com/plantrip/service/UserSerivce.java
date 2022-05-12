package com.plantrip.service;


import com.plantrip.entity.User;
import com.plantrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional //비즈니스 로직을 담당하는 서비스 계층 클래스에 선언
@RequiredArgsConstructor //final이나 Nonnull이 붙은 필드에 생성자 생성
public class UserSerivce {

    private final UserRepository userRepository; //AUtowired 어노테이션 없이 의존성 주입 가능

    public User saveUser(User user){
        validateDuplicateUser(user); //이미 가입된 회원인지 확인
        return userRepository.save(user); //회원 가입 후 회원 저장
    }

    private void validateDuplicateUser(User user) {
        User findUser = userRepository.findByEmail(user.getEmail());
        if(findUser != null){ //회원 검색했을 때 null이 아니면
            throw new IllegalStateException("이미 가입된 회원입니다.");
            //illegalStateException 예외 발생
        }
    }

}
