CREATE TABLE ride_fares (
    id BIGSERIAL PRIMARY KEY,

    ride_id BIGINT NOT NULL UNIQUE,

    base_fare NUMERIC(12,2) NOT NULL,

    distance_fare NUMERIC(12,2) NOT NULL,

    time_fare NUMERIC(12,2) NOT NULL,

    surge_amount NUMERIC(12,2) NOT NULL,

    discount NUMERIC(12,2) NOT NULL,

    tax NUMERIC(12,2) NOT NULL,

    total_amount NUMERIC(12,2) NOT NULL,

    currency VARCHAR(3) NOT NULL,

    calculated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_ride_fares_ride
        FOREIGN KEY (ride_id)
        REFERENCES rides(id)
);