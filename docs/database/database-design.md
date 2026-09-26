# Ride Booking Platform - Database Design

## 1. Purpose

This document describes the database design for the Ride Booking Platform.

The database supports:

- User management
- Role management
- Driver management
- Vehicle management
- Ride booking
- Ride lifecycle tracking
- Fare calculation
- Payment processing
- Ride ratings

The database uses PostgreSQL.

Flyway is used to manage database schema migrations, while Hibernate/JPA is used for ORM and schema validation.

---

## 2. Phase 1 Entities

The initial version of the platform contains the following entities:

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

## 3. Entity Responsibilities

### 3.1 User

Represents a person using the platform.

A user can:

- Book rides as a passenger
- Have one or more roles
- Participate in ratings

A passenger is represented using the `User` entity rather than having a separate `Passenger` entity.

---

### 3.2 Role

Represents an application role assigned to a user.

Examples:

- PASSENGER
- DRIVER
- ADMIN

Users and roles have a many-to-many relationship.

---

### 3.3 Driver

Represents driver-specific information associated with a user.

A driver contains:

- License number
- Driver status
- Verification status

A driver is associated with exactly one user.

A driver can have multiple vehicles.

---

### 3.4 Vehicle

Represents a vehicle operated by a driver.

A vehicle contains:

- Vehicle number
- Vehicle type
- Vehicle model
- Vehicle color
- Vehicle status

Each vehicle belongs to one driver.

---

### 3.5 Ride

Represents a ride requested by a passenger.

A ride contains:

- Passenger
- Driver
- Vehicle
- Pickup location
- Drop location
- Ride status
- Ride lifecycle timestamps

The driver and vehicle are initially optional because a ride can be created before a driver is assigned.

---

### 3.6 RideStatusHistory

Stores the historical status changes of a ride.

Example ride lifecycle:

```text
REQUESTED
    ↓
DRIVER_ASSIGNED
    ↓
DRIVER_ARRIVED
    ↓
STARTED
    ↓
COMPLETED


---

## 4. Authentication & Authorization

### 4.1 Design Decision

Authentication does not introduce a new entity. It reuses the existing `User`
and `Role` entities from Section 2 — registration and login operate directly
on these tables. No separate `Credential` or `Account` entity was introduced,
since `User.password` and `User.email` already model everything authentication
needs.

### 4.2 Role Seeding

`Role` rows are not created dynamically by the application. They are seeded
once via Flyway migration `V9__seed_roles.sql`:

```sql
INSERT INTO roles (name)
VALUES ('PASSENGER'), ('DRIVER'), ('ADMIN')
ON CONFLICT (name) DO NOTHING;
```

Public registration may only assign `PASSENGER` or `DRIVER` — `ADMIN` is
restricted at the DTO validation layer (`RegisterRequestDTO`) and is never
selectable by a registering client.

### 4.3 Password Storage

`User.password` stores a BCrypt hash, never plaintext. Hashing happens in
`AuthService` at registration time via Spring Security's `PasswordEncoder`
bean — the column itself remains a plain `VARCHAR`, since hashing is an
application-layer concern, not a schema concern.

### 4.4 Session Model

The platform uses stateless authentication. No `sessions` or `refresh_tokens`
table exists yet. Each request is authenticated independently via a signed
JWT carrying `userId`, `email`, and `roles` as claims — Spring Security's
session creation policy is set to `STATELESS`, so no server-side session
state is persisted in the database.

### 4.5 Future Considerations

If token revocation (e.g. logout, forced session invalidation) is required
later, a `refresh_tokens` table or a Redis-backed token blocklist will be
introduced at that time — not preemptively, per the project's
do-not-overengineer principle (see project context, Section 28).