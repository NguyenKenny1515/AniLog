DROP TABLE IF EXISTS anime_entry;

CREATE TABLE anime_entry (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    mal_id INT NOT NULL,
    entry_status TEXT NOT NULL,
    user_score INT,
    image_url TEXT NOT NULL,
    title TEXT NOT NULL,
    type TEXT,
    status TEXT,
    aired TEXT,
    score NUMERIC(3,2),
    episodes INT,
    episodes_watched INT,
    genres TEXT,
    studios TEXT,
    UNIQUE(username, mal_id),
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
);
