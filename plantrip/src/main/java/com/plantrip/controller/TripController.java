package com.plantrip.controller;

import com.plantrip.common.Result;
import com.plantrip.dto.TripListDto;
import com.plantrip.service.TripListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TripController {

    @Autowired
    private TripListService tripListService;

    @RequestMapping("/tripList")
    public String tripList(HttpServletRequest request/*, @SessionAttribute("userId") Long userId*/, Model model) {
        HttpSession httpSession = request.getSession();
        //Long userId = (Long) httpSession.getAttribute("userId");
        Long userId = 1L;

        Result<List<TripListDto>> result = tripListService.getTripListByUser(userId);
        if (result.isSuccess()) {
            model.addAttribute("tripList", result.getResultObject());
        }
        return "TripList";
    }
}
