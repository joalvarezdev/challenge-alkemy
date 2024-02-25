CREATE TABLE IF NOT EXISTS movies_characters (
    movie_id BIGINT NOT NULL,
    character_id BIGINT NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies(id),
    FOREIGN KEY (character_id) REFERENCES characters(character_id)
);