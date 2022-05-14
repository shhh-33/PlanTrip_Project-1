package com.plantrip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Main 페이지 이동
 *  - 사이드바 페이지 이동
 */
@Controller
@RequiredArgsConstructor
public class MainController {

    @RequestMapping("/")
    public String main(){
        return "main";
    }

    @RequestMapping("/createTrip")
    public String createPlan() {
        return "createPlan";
    }

    @RequestMapping("/tripList")
    public String tripList() {
        return "TripList";
    }

    @RequestMapping("/tripBoardForm")
    public String tripBoardForm() {
        return "tripBoardForm";
    }

    @RequestMapping("/tripBoardDetail")
    public String tripBoardDetail() {
        return "tripBoardDetail";
    }

    @RequestMapping("/tripBoardList")
    public String tripBoardList() {
        return "tripBoardList";
    }



}
