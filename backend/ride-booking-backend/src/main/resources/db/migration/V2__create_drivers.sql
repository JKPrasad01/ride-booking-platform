CREATE TABLE drivers (
    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT NOT NULL UNIQUE,

    license_number VARCHAR(50) NOT NULL UNIQUE,

    status VARCHAR(30) NOT NULL,

    verification_status VARCHAR(30) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_drivers_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);