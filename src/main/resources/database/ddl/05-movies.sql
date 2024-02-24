CREATE TABLE IF NOT EXISTS movies (
    id SERIAL NOT NULL,
    title VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    creation_date DATE NOT NULL,
    rating INTEGER NOT NULL,
    CONSTRAINT pk_movies PRIMARY KEY (id)
)