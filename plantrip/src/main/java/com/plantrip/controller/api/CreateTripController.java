package com.plantrip.controller.api;

import com.plantrip.common.Result;
import com.plantrip.dto.CreateTripDto;
import com.plantrip.service.CreateTripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 여행 만들기 api
 */
@RestController
@RequestMapping("/api")
public class CreateTripController {

    @Autowired
    private CreateTripService createTripService;

    @PostMapping("/trip/save")
    public Result saveTrip(@RequestBody CreateTripDto tripDTO, HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        //Long userId = (Long) httpSession.getAttribute("userId");
        Long userId = 1L;

        return createTripService.saveTrip(tripDTO, userId);
    }
}
