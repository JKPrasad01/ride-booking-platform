package com.ridebooking.ride_booking_backend.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface PaymentRepository extends JpaRepository<Payment,Long>{
    
}
