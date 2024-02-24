INSERT INTO genres (name, image)
SELECT 'Animación',
       'https://example.com/animation.jpg'
WHERE NOT EXISTS (SELECT name FROM genres WHERE name = 'Animación');

--

INSERT INTO genres (name, image)
SELECT 'Aventura',
       'https://example.com/aventura.jpg'
WHERE NOT EXISTS (SELECT name FROM genres WHERE name = 'Aventura');

--

INSERT INTO genres (name, image)
SELECT 'Comedia',
       'https://example.com/comedia.jpg'
WHERE NOT EXISTS (SELECT name FROM genres WHERE name = 'Comedia');

--

INSERT INTO genres (name, image)
SELECT 'Fantasia',
       'https://example.com/fantasia.jpg'
WHERE NOT EXISTS (SELECT name FROM genres WHERE name = 'Fantasia');

--

INSERT INTO genres (name, image)
SELECT 'Musical',
       'https://example.com/musical.jpg'
WHERE NOT EXISTS (SELECT name FROM genres WHERE name = 'Musical');
