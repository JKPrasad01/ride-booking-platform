CREATE TABLE rides (
    id BIGSERIAL PRIMARY KEY,

    passenger_id BIGINT NOT NULL,

    driver_id BIGINT,

    vehicle_id BIGINT,

    pickup_latitude DOUBLE PRECISION NOT NULL,

    pickup_longitude DOUBLE PRECISION NOT NULL,

    pickup_address VARCHAR(500),

    drop_latitude DOUBLE PRECISION NOT NULL,

    drop_longitude DOUBLE PRECISION NOT NULL,

    drop_address VARCHAR(500),

    status VARCHAR(30) NOT NULL,

    requested_at TIMESTAMP,

    accepted_at TIMESTAMP,

    started_at TIMESTAMP,

    completed_at TIMESTAMP,

    cancelled_at TIMESTAMP,

    CONSTRAINT fk_rides_passenger
        FOREIGN KEY (passenger_id)
        REFERENCES users(id),

    CONSTRAINT fk_rides_driver
        FOREIGN KEY (driver_id)
        REFERENCES drivers(id),

    CONSTRAINT fk_rides_vehicle
        FOREIGN KEY (vehicle_id)
        REFERENCES vehicles(id)
);