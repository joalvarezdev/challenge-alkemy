CREATE TABLE IF NOT EXISTS movies_genres (
    movie_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies(id),
    FOREIGN KEY (genre_id) REFERENCES genres(id)
);