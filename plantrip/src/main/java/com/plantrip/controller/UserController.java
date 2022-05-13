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
import com.plantrip.service.UserSerivce;
import com.plantrip.dto.UserFormDto;

@RequestMapping
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserSerivce userSerivce;
    private final PasswordEncoder passwordEncoder;

    //회원 가입
    @GetMapping(value = "/register")
    public String userForm(Model model){
        model.addAttribute("userFormDto", new UserFormDto());
        return "user/userForm";
    }

    @PostMapping(value = "/register")
    public String newUser(@Validated UserFormDto userFormDto,
                          BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "user/userForm";
        }

        try {
           User user = User.createUser(userFormDto, passwordEncoder);
           userSerivce.saveUser(user);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "user/userForm";
        }

        return "redirect:/";
    }
}
