package com.evcharge.repository;

import com.evcharge.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByStationIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Long stationId, LocalDateTime endTime, LocalDateTime startTime);
}