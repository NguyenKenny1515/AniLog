DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS authorities;

CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOL NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    UNIQUE(username, authority),
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
);