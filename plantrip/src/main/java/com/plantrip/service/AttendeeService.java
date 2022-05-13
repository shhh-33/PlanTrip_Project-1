package com.plantrip.service;

import com.plantrip.common.Result;
import com.plantrip.common.ResultCode;
import com.plantrip.entity.Attendee;
import com.plantrip.entity.Trip;
import com.plantrip.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private UserService userService;

    public Result<List<Attendee>> getAttendees() {
        try {
            List<Attendee> attendees = attendeeRepository.findAll();
            return ResultCode.Success.result(attendees);
        } catch (Exception e) {
            return ResultCode.DBError.result();
        }
    }

    /*public Result<List<Attendee>> getByUser_UserId(Long userId) {
        try {
            List<Attendee> attendees = attendeeRepository.findByUser_UserId(userId);
            return ResultCode.Success.result(attendees);
        } catch (Exception e) {
            return ResultCode.DBError.result();
        }
    }

    public Result<Long> getCountByTrip_TripId(Long tripId) {
        try {
            Long count = attendeeRepository.countByTrip_TripId(tripId);
            return ResultCode.Success.result(count);
        } catch (Exception e) {
            return ResultCode.DBError.result();
        }
    }

    public Result<Boolean> isExistsByUserAndTrip(User user, Trip trip) {
        return isExistsByUserAndTrip(user.getUserId(), trip.getTripId());
    }

    public Result<Boolean> isExistsByUserAndTrip(Long userId, Long tripId) {
        try {
            Long count = attendeeRepository.countByUser_UserIdAndTrip_TripId(userId, tripId);
            return ResultCode.Success.result(count > 0);
        } catch (Exception e) {
            return ResultCode.DBError.result();
        }
    }

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

    public Result saveAsAttendee(User user, Trip trip) {
        return save(user, trip, Attendee.AttendeeType.ATTENDEE);
    }*/

}
