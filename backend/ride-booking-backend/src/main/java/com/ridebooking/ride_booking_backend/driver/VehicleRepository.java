package com.ridebooking.ride_booking_backend.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    
}
