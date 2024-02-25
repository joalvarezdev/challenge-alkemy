CREATE TABLE IF NOT EXISTS genres (
    genre_id SERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    CONSTRAINT pk_genres PRIMARY KEY (genre_id)
)