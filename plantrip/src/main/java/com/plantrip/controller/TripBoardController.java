package com.plantrip.controller;

import com.plantrip.dto.TripBoardFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TripBoardController {

    @GetMapping(value = "/tripBoard")
    public String TripBoardForm(){   // 여행 참가 게시글로 접근
        return "tripBoardForm";
    }
}
