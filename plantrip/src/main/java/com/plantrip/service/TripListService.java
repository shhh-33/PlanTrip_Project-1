package com.plantrip.service;

import com.plantrip.common.DateUtil;
import com.plantrip.common.Result;
import com.plantrip.common.ResultCode;
import com.plantrip.dto.TripListDto;
import com.plantrip.entity.Attendee;
import com.plantrip.entity.Trip;
import com.plantrip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TripListService {

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private AttendeeService attendeeService;

    /**
     * 모든 여행 리스트 찾기
    * @return Result 상태 코드 리턴
     */
    public Result<List<Trip>> getTrips() {
        try {
            List<Trip> trips = tripRepository.findAll();

            return ResultCode.Success.result(trips);
        } catch (Exception e) {
            return ResultCode.DBError.result();
        }
    }

    /**
     * trip_id에 해당하는 여행 정보 찾기
     * @param tripId
     * @return Result 상태 코드 리턴
     */
    public Result<Trip> getTrip(Long tripId) {
        try {
            Optional<Trip> optionalTrip = tripRepository.findById(tripId);
            if (optionalTrip.isPresent()) {
                Trip trip = optionalTrip.get();
                return ResultCode.Success.result(trip);
            } else {
                return ResultCode.NOT_EXISTS_TRIP.result();
            }
        } catch (Exception e) {
            return ResultCode.DBError.result();
        }
    }

    /**
     * Attendee 테이블을 이용하여 해당 여행 정보 리턴
     * @param userId
     * @return
     */
    public Result<List<Trip>> getTripsByUser(Long userId) {
        //user_id를 이용하여 현재 참석 or 리드 하고있는 Attendee 테이블 정보 가져오기
        Result<List<Attendee>> result = attendeeService.getByUser_UserId(userId);

        if (result.isSuccess()) { //참석하고 있는 여행을 잘 가져온 경우
            // getResultObject = 검색한 Attendee 테이블 정보 존재
            //     .stream().map() = stream 함수에서 map은 요소들을 특정조건에 해당하는 값으로 변환
            //          .collect(Collectors.toList()) = 결과물의 컬럼(trip_id)를 이용하여 알맞는 trip정보를 검색해 가져오기(stream 객체를 list로 변환)
            List<Trip> trips = result.getResultObject().stream().map(x -> x.getTrip()).collect(Collectors.toList());

            return result.getResultCode().result(trips);
        } else {
            return result.getResultCode().result();
        }
    }

    /**
     * user_id를 이용하여 참석하고 있는 trip 정보 출력
     * @param userId
     * @return
     */
    public Result<List<TripListDto>> getTripListByUser(Long userId) {
        try {
            Result<List<Attendee>> result = attendeeService.getByUser_UserId(userId);

            if (result.isSuccess()) {
                List<Trip> trips = result.getResultObject().stream().map(x -> x.getTrip()).collect(Collectors.toList());
                List<TripListDto> tripListDtos = new ArrayList<>();

                trips.stream().forEach(
                        trip -> {
                            Result<Long> resultCount = attendeeService.getCountByTrip_TripId(trip.getTripId());
                            Long count = resultCount.getResultObject();
                            if (resultCount.isNotSuccess()) {
                                count = 0L;
                            }

                            tripListDtos.add(
                                    TripListDto.builder()
                                            .tripId(trip.getTripId())
                                            .startAt(DateUtil.localDateTime2str(trip.getStartAt(), "yyyy-MM-dd"))
                                            .endAt(DateUtil.localDateTime2str(trip.getEndAt(), "yyyy-MM-dd"))
                                            .region(trip.getRegion())
                                            .totalExpenses(trip.getTotalExpenses())
                                            .dayCount(ChronoUnit.DAYS.between(trip.getStartAt(), trip.getEndAt()) + 1)
                                            .attendeeCount(count)
                                            .build()
                            );
                        }
                );
                return result.getResultCode().result(tripListDtos);
            } else {
                return result.getResultCode().result();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCode.ETCError.result();
        }
    }
    
}
