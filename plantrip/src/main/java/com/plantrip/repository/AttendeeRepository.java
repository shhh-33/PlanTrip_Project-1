package com.plantrip.repository;

import com.plantrip.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    /**
     *  find + 엔티티 이름(생략가능) + By + 변수이름
     */
    List<Attendee> findByUser_UserId(Long userId); // userId로 해당하는 Attendee 테이블의 컬럼 조회
    long countByTrip_TripId(Long tripId);
    long countByUser_UserIdAndTrip_TripId(Long userId, Long tripId);
}