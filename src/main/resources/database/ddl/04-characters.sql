CREATE TABLE IF NOT EXISTS characters (
    id SERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL,
    weight INTEGER NOT NULL,
    history TEXT NOT NULL,
    CONSTRAINT pk_characters PRIMARY KEY (id)
)