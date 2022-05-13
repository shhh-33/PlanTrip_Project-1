package com.plantrip.controller;

import com.plantrip.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.plantrip.service.UserService;
import com.plantrip.dto.UserFormDto;

@RequestMapping
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    //회원 가입
    @GetMapping(value = "/register")
    public String userForm(Model model){
        model.addAttribute("userFormDto", new UserFormDto());
        return "user/userForm";
    }

    /**
     * 회원가입 성공 : main으로 리다이렉트
     * 회원정보 검증 및 중복회원 가입 조건에 의해 실패 : 다시 회원가입 페이지로 돌아가 실패이유 출력
     */

    @PostMapping(value = "/register")
    public String newUser(@Validated UserFormDto userFormDto,
                          BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "user/userForm";
        }

        try {
           User user = User.createUser(userFormDto, passwordEncoder);
           userService.saveUser(user); //저장
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "user/userForm";
        }

        return "redirect:/"; //성공
    }





}
