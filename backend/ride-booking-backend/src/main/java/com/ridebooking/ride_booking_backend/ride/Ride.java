package com.ridebooking.ride_booking_backend.ride;

import com.ridebooking.ride_booking_backend.driver.Driver;
import com.ridebooking.ride_booking_backend.driver.Vehicle;
import com.ridebooking.ride_booking_backend.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rides")
@Getter
@Setter
@NoArgsConstructor
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Passenger
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;

    // Driver - assigned after booking
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    // Vehicle - assigned along with driver
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    // Pickup
    @Column(nullable = false)
    private Double pickupLatitude;

    @Column(nullable = false)
    private Double pickupLongitude;

    @Column(length = 500)
    private String pickupAddress;

    // Drop
    @Column(nullable = false)
    private Double dropLatitude;

    @Column(nullable = false)
    private Double dropLongitude;

    @Column(length = 500)
    private String dropAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private RideStatus status;

    private LocalDateTime requestedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

    private LocalDateTime cancelledAt;

    @OneToMany(
            mappedBy = "ride",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RideStatusHistory> statusHistory = new ArrayList<>();

    @OneToOne(
            mappedBy = "ride",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private RideFare fare;

    @PrePersist
    protected void onCreate() {
        requestedAt = LocalDateTime.now();

        if (status == null) {
            status = RideStatus.REQUESTED;
        }
    }
}