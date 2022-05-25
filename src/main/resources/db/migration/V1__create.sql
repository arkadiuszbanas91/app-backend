CREATE TABLE users
(
    username   VARCHAR(50) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    enabled    INT DEFAULT 1,
    PRIMARY KEY (username)
);

INSERT INTO users (username, password, enabled) VALUES
    ('admin', '$2a$12$4g/wZmQM7APK70106ASLTOIrJLr9gcP0gu8yzI84oaJ3p/HUy5cda', 1);