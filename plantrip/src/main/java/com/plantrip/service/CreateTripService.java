package com.plantrip.service;

import com.plantrip.common.DateUtil;
import com.plantrip.common.Result;
import com.plantrip.common.ResultCode;
import com.plantrip.dto.CreateTripDto;
import com.plantrip.entity.Trip;
import com.plantrip.entity.User;
import com.plantrip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 여행 만들기 페이지
 *  날짜 선택, 지역 선택 후 submit -> 여행 생성
 *  생성한 user는 attendeeType=LEADER로 저장
 */
@Service
public class CreateTripService {

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private AttendeeService attendeeService;
    @Autowired
    private UserService userService;

    /**
     * saveTrip : Trip DB에 입력된 여행 정보 저장하기
     * @param tripDTO : 날짜, 지역
     * @param userId : 현재 로그인한 user = 여행 생성자 = LEADER
     * @return 저장 로직 실행 후, 성공코드 전송
     */
    @Transactional
    public Result saveTrip(CreateTripDto tripDTO, Long userId) {

        try {
            // trip 만들기
            Trip trip = Trip.builder()
                    .startAt(DateUtil.str2LocalDateTime(tripDTO.getStartAt()))
                    .endAt(DateUtil.str2LocalDateTime(tripDTO.getEndAt()))
                    .region(tripDTO.getRegion())
                    .totalExpenses(0.0)
                    .build();

            Result<User> userResult = userService.getUesr(userId);
            if (userResult.isNotSuccess()) {
                return userResult;
            }
            // 유저 찾아오기
            User user = userResult.getResultObject();

            Trip savedTrip = tripRepository.save(trip); // trip 먼저 저장
            return attendeeService.saveAsLeader(user, savedTrip); // leader 저장

        } catch (Exception e) {
            ResultCode.DBError.result();
        }
        return ResultCode.Success.result();
    }
}
