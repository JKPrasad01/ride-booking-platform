CREATE TABLE ratings (
    id BIGSERIAL PRIMARY KEY,

    ride_id BIGINT NOT NULL,

    from_user_id BIGINT NOT NULL,

    to_user_id BIGINT NOT NULL,

    score INTEGER NOT NULL,

    comment VARCHAR(500),

    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_ratings_ride
        FOREIGN KEY (ride_id)
        REFERENCES rides(id),

    CONSTRAINT fk_ratings_from_user
        FOREIGN KEY (from_user_id)
        REFERENCES users(id),

    CONSTRAINT fk_ratings_to_user
        FOREIGN KEY (to_user_id)
        REFERENCES users(id)
);