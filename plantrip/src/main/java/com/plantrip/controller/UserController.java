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
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;



    //회원 가입 페이지로 연결
    @GetMapping(value = "/register")
    public String userForm(Model model) {
        model.addAttribute("userFormDto", new UserFormDto());
        return "user/userRegisterForm";
    }

    @PostMapping(value = "/register")
    public String userForm(UserFormDto userFormDto){

        User user = User.createUser(userFormDto,passwordEncoder);
        userService.saveUser(user);

        return "redirect:/";
    }
}

