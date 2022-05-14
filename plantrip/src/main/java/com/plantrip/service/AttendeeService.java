package com.plantrip.service;

import com.plantrip.common.Result;
import com.plantrip.common.ResultCode;
import com.plantrip.entity.Attendee;
import com.plantrip.entity.Trip;
import com.plantrip.entity.User;
import com.plantrip.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    /**
     * Attendee table에 삽입
     * @param user
     * @param trip
     * @param attendeeType
     * @return
     */
    public Result save(User user, Trip trip, Attendee.AttendeeRole attendeeType) {
        Attendee attendee = Attendee.builder()
                .user(user)
                .trip(trip)
                .attendeeRole(attendeeType)
                .build();
        try {
            attendeeRepository.save(attendee);
            return ResultCode.Success.result();
        } catch (Exception e) {
            return ResultCode.DBError.result();
        }
    }

    /**
     * 여행 만들기 유저는 AttendeeType = LEADER 설정
     * @param user 여행 만들기에 로그인한 유저
     * @param trip 새로 만들 여행
     * @return
     */
    public Result saveAsLeader(User user, Trip trip) {
        return save(user, trip, Attendee.AttendeeRole.LEADER);
    }

    /**
     * user_id를 이용하여 현재 참석 or 리드 하고있는 Attendee 테이블 정보 가져오기
     * @param userId
     * @return
     */
    public Result<List<Attendee>> getByUser_UserId(Long userId) {
        try {
            List<Attendee> attendees = attendeeRepository.findByUserId(userId);
            return ResultCode.Success.result(attendees);
        } catch (Exception e) {
            return ResultCode.DBError.result();
        }
    }

    /**
     * 현재 trip_id에 참석하고 있는 attendee 수 리턴
     * @param tripId
     * @return
     */
    public Result<Long> getCountByTrip_TripId(Long tripId) {
        try {
            Long count = attendeeRepository.countByTrip_TripId(tripId);
            return ResultCode.Success.result(count);
        } catch (Exception e) {
            return ResultCode.DBError.result();
        }
    }
}
