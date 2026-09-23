CREATE TABLE vehicles (
    id BIGSERIAL PRIMARY KEY,

    driver_id BIGINT NOT NULL,

    vehicle_number VARCHAR(20) NOT NULL UNIQUE,

    vehicle_type VARCHAR(30) NOT NULL,

    vehicle_model VARCHAR(100) NOT NULL,

    vehicle_color VARCHAR(30),

    status VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_vehicles_driver
        FOREIGN KEY (driver_id)
        REFERENCES drivers(id)
);