package com.plantrip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Main
 */
@Controller
@RequiredArgsConstructor
public class MainController {

    @RequestMapping("/")
    public String main(){
        return "main";
    }
}
