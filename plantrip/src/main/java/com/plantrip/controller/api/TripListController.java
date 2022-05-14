package com.plantrip.controller.api;

import com.plantrip.common.Result;
import com.plantrip.dto.TripListDto;
import com.plantrip.entity.Trip;
import com.plantrip.service.AttendeeService;
import com.plantrip.service.TripListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TripListController {

    @Autowired
    private TripListService tripListService;

    @RequestMapping("/trips")
    public Result<List<Trip>> getTrips() {
        return tripListService.getTrips();
    }

    @RequestMapping("/tripsByUser/{userId}")
    public Result<List<Trip>> getTripsByUser(@PathVariable Long userId) {
        return tripListService.getTripsByUser(userId);
    }

    @RequestMapping("/tripVOsByUser/{userId}")
    public Result<List<TripListDto>> getTripVOsByUser(@PathVariable Long userId) {
        return tripListService.getTripListByUser(userId);
    }

}
