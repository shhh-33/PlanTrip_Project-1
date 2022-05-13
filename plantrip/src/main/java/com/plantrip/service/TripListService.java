package com.plantrip.service;

import com.plantrip.entity.Attendee;
import com.plantrip.entity.Trip;
import com.plantrip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripListService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private JoinTripService joinTripService;

    @Autowired
    private UserService userService;

    // 모든 여행 리스트 가져오기
    public List<Trip> getTrips() {

        List<Trip> trips = tripRepository.findAll();
        return trips;
    }


}
