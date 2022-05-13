package com.plantrip.controller;

import com.plantrip.service.JoinTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class TripListController {

    @Autowired
    private JoinTripService joinTripService;

    @RequestMapping("/planList")
    public String tripList(HttpServletRequest request/*, @SessionAttribute("userId") Long userId*/, Model model) {
        HttpSession httpSession = request.getSession();
        //Long userId = (Long) httpSession.getAttribute("userId");
        Long userId = 1L;

        /*Result<List<TripDto>> result = tripService.getTripListByUser(userId);
        if (result.isSuccess()) {
            model.addAttribute("tripList", result.getResultObject());
        }*/
        return "planList";
    }
}
