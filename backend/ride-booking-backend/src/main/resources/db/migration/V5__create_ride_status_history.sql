CREATE TABLE ride_status_history (
    id BIGSERIAL PRIMARY KEY,

    ride_id BIGINT NOT NULL,

    status VARCHAR(30) NOT NULL,

    changed_by BIGINT NOT NULL,

    changed_at TIMESTAMP NOT NULL,

    remarks VARCHAR(500),

    CONSTRAINT fk_ride_status_history_ride
        FOREIGN KEY (ride_id)
        REFERENCES rides(id)
);