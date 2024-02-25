INSERT INTO characters (name, image, age, weight, history)
SELECT 'Mickey Mouse',
       'https://example.com/mickey-mouse.jpg',
       93,
       23,
       'Mickey Mouse es un personaje animado creado por Walt Disney y Ub Iwerks en 1928. Es uno de los personajes más icónicos de Disney y es conocido por su personalidad alegre y amigable.'
WHERE NOT EXISTS (SELECT name FROM characters WHERE name = 'Mickey Mouse');

--

INSERT INTO characters (name, image, age, weight, history)
SELECT 'Mulan',
       'https://example.com/mulan.jpg',
       20,
       50,
       'Mulan es la protagonista de la película de animación de Disney ''Mulan'' de 1998. Es una joven valiente que se disfraza de hombre para tomar el lugar de su padre en el ejército chino y luchar contra los invasores hunos.'
WHERE NOT EXISTS (SELECT name FROM characters WHERE name = 'Mulan');

--

INSERT INTO characters (name, image, age, weight, history)
SELECT 'Simba',
       'https://example.com/simba.jpg',
       3,
       35,
       'Simba es el protagonista de la película de Disney El Rey León de 1994. Es un joven león que debe enfrentar su destino como rey de la sabana africana después de la muerte de su padre, Mufasa.'
WHERE NOT EXISTS (SELECT name FROM characters WHERE name = 'Simba');

--

INSERT INTO characters (name, image, age, weight, history)
SELECT 'Ariel',
       'https://example.com/ariel.jpg',
       16,
       45,
       'Ariel es la protagonista de la película de animación de Disney La Sirenita de 1989. Es una sirena curiosa y aventurera que sueña con vivir en la superficie y enamorarse de un humano.'
WHERE NOT EXISTS (SELECT name FROM characters WHERE name = 'Ariel');

--

INSERT INTO characters (name, image, age, weight, history)
SELECT 'Woody',
       'https://example.com/woody.jpg',
       65,
       20,
       'Woody es el protagonista de la serie de películas de Pixar y Disney Toy Story. Es un vaquero de juguete leal y valiente que lidera a los demás juguetes en diversas aventuras para proteger a su dueño, Andy.'
WHERE NOT EXISTS (SELECT name FROM characters WHERE name = 'Woody');

--
