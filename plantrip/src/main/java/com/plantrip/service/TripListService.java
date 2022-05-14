package com.plantrip.service;

import com.plantrip.common.Result;
import com.plantrip.common.ResultCode;
import com.plantrip.entity.Attendee;
import com.plantrip.entity.Trip;
import com.plantrip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripListService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private AttendeeService attendeeService;

    @Autowired
    private UserService userService;

    /*
        Result 사용 코드 제외 -> 예외 처리 Controller 단에서 진행
     */

    // 모든 여행 리스트 가져오기
    public List<Trip> getTrips() {

        List<Trip> trips = tripRepository.findAll();
        return trips;
    }

    // trip_id에 해당하는 여행 정보 출력
    public Trip getTrip(Long tripId) {

        Optional<Trip> optionalTrip = tripRepository.findById(tripId);
        Trip trip = optionalTrip.get();
        return trip;
    }

    // user_id(일반 회원가입 유저)에 해당하는 참석 여행 리스트 출력
    public List<Trip> getTripsByUser(Long userId) {
        List<Attendee> result = attendeeService.getByUser_UserId(userId);
        if (result.isSuccess()) {
            // 간단한 람다식 사용 방법 알아두기
            List<Trip> trips = result.getResultObject().stream().map(x -> x.getTrip()).collect(Collectors.toList());
            return result.getResultCode().result(trips); //리턴 타입을 다르게 설정하는 이유? & 에러 코드가 날 일이 없기 때문인지?
        } else {
            return result.getResultCode().result();
        }
    }

}
