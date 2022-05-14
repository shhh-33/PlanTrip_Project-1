package com.plantrip.service;


import com.plantrip.common.Result;
import com.plantrip.common.ResultCode;
import com.plantrip.entity.User;
import com.plantrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * MemberService가 UserDetailsService를 구현한다.
 *    <UserDetailsService>
 *  - DB에서 회원정보를 가지고 오는 역할
 *  - loadUserByUsername()메소드가 존재하며, 회원 정보를 조회하여 사용자의 정보와 권한을 갖는 UserDetails 인터페이스(내장)를 반환
 *
 */
@Service
@Transactional //비즈니스 로직을 담당하는 서비스 계층에 작성, 로직처리 중 에러 발생시 변경된 데이터를 로직 수행 전으로 콜백
@RequiredArgsConstructor //bean 주입(@Setter,@Autowired) 이건 final이나 @NotNull이 붙은 필드에 생성자 생성
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user){
        validateDuplicateMember(user);
        return userRepository.save(user);
    }


    //이미 가입된 회원인 경우 IllegalStateException 예외 발생
    private void validateDuplicateMember(User user){
        User findMember = userRepository.findByEmail(user.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public Result<User> getUesr(Long id) {
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                return ResultCode.Success.result(user);
            }
            return ResultCode.NOT_EXISTS_USER.result();
        } catch (Exception e) {
            return ResultCode.DBError.result();
        }
    }

}