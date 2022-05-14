package com.plantrip.repository;

import com.plantrip.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

}
