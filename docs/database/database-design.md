# Ride Booking Platform - Database Design

## 1. Purpose

This document describes the database design for the Ride Booking Platform.

The database supports:

- User management
- Driver management
- Vehicle management
- Ride booking
- Ride lifecycle
- Fare calculation
- Payment
- Ratings

---

## 2. Phase 1 Entities

1. User
2. Role
3. Driver
4. Vehicle
5. Ride
6. RideStatusHistory
7. RideFare
8. Payment
9. Rating

---

## 3. Entity Relationships

User
 ├── Roles
 └── Driver

Driver
 └── Vehicle

User
 └── Ride

Ride
 ├── RideStatusHistory
 ├── RideFare
 ├── Payment
 └── Rating