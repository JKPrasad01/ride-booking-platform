package com.ridebooking.ride_booking_backend.ride;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ride_fares")
@Getter
@Setter
@NoArgsConstructor
public class RideFare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ride_id", nullable = false, unique = true)
    private Ride ride;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal baseFare;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal distanceFare;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal timeFare;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal surgeAmount;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal discount;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal tax;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false)
    private LocalDateTime calculatedAt;

    @PrePersist
    protected void onCreate() {
        if (calculatedAt == null) {
            calculatedAt = LocalDateTime.now();
        }
    }
}