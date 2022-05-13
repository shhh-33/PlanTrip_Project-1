package com.plantrip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 여행 만들기
 */
@Controller
@RequiredArgsConstructor
public class CreateTripController {

    @RequestMapping("/createTrip")
    public String createPlan() {
        return "createPlan";
    }
}
