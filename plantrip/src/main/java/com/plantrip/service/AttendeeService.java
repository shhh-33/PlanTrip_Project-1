package com.plantrip.service;

import com.plantrip.common.Result;
import com.plantrip.common.ResultCode;
import com.plantrip.entity.Attendee;
import com.plantrip.entity.Trip;
import com.plantrip.entity.User;
import com.plantrip.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    public Result save(User user, Trip trip, Attendee.AttendeeType attendeeType) {
        Attendee attendee = Attendee.builder()
                .user(user)
                .trip(trip)
                .attendeeType(attendeeType)
                .build();
        try {
            attendeeRepository.save(attendee);
            return ResultCode.Success.result();
        } catch (Exception e) {
            return ResultCode.DBError.result();
        }
    }

    public Result saveAsLeader(User user, Trip trip) {
        return save(user, trip, Attendee.AttendeeType.LEADER);
    }
}
