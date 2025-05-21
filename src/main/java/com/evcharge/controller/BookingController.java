package com.evcharge.controller;

import com.evcharge.model.Booking;
import com.evcharge.repository.BookingRepository;
import com.evcharge.repository.UserRepository;
import com.evcharge.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StationRepository stationRepository;

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        // Check for conflicts
        List<Booking> conflicts = bookingRepository.findByStationIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                booking.getStation().getId(),
                booking.getEndTime(),
                booking.getStartTime()
        );

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("Time slot conflict with existing booking.");
        }

        booking.setStatus("confirmed");
        return bookingRepository.save(booking);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getUserBookings(@PathVariable Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}
