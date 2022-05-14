package com.plantrip.repository;

import com.plantrip.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

    List<Attendee> findByUser_UserId(Long userId);

   // long countByTrip_TripId(Long tripId);

   // long countByUser_UserIdAndTrip_TripId(Long userId, Long tripId);
}
