CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,

    ride_id BIGINT NOT NULL UNIQUE,

    amount NUMERIC(12,2) NOT NULL,

    payment_method VARCHAR(20) NOT NULL,

    status VARCHAR(20) NOT NULL,

    transaction_reference VARCHAR(100) UNIQUE,

    paid_at TIMESTAMP,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_payments_ride
        FOREIGN KEY (ride_id)
        REFERENCES rides(id)
);