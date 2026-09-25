package com.ridebooking.ride_booking_backend.ride;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface RideFareRepository extends JpaRepository<RideFare,Long>{
    
}
