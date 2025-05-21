package com.evcharge.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Station station;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double amount;
    private String status; // e.g., pending, confirmed, cancelled
}
