CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       created_by UUID,
                       created_date BIGINT,
                       modified_date BIGINT,
                       modified_by UUID,
                       CONSTRAINT uk_username UNIQUE (username),
                       CONSTRAINT uk_email UNIQUE (email)
);

CREATE INDEX idx_users_created_date ON users(created_date);