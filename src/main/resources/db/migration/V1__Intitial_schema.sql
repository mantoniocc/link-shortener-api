CREATE TABLE link (
    id BIGSERIAL PRIMARY KEY,
    long_url VARCHAR(2048) NOT NULL,
    short_code VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX idx_short_code ON link (short_code);