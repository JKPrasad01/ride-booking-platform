package com.ridebooking.ride_booking_backend.ride;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ride_status_history")
@Getter
@Setter
@NoArgsConstructor
public class RideStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private RideStatus status;

    @Column(nullable = false)
    private Long changedBy;

    @Column(nullable = false)
    private LocalDateTime changedAt;

    @Column(length = 500)
    private String remarks;
}