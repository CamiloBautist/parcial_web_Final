-- Insert Towns (Pueblo)
INSERT INTO pueblo (nombre) VALUES ('Pueblo Paleta');
INSERT INTO pueblo (nombre) VALUES ('Ciudad Celeste');

-- Insert Pokemon Types (TipoPokemon)
INSERT INTO tipo_pokemon (nombre, descripcion, uuid) VALUES ('Eléctrico', 'Pokémon de tipo eléctrico', 'a1b2c3d4-e5f6-7890-abcd-ef1234567801');
INSERT INTO tipo_pokemon (nombre, descripcion, uuid) VALUES ('Fuego', 'Pokémon de tipo fuego', 'a1b2c3d4-e5f6-7890-abcd-ef1234567802');
INSERT INTO tipo_pokemon (nombre, descripcion, uuid) VALUES ('Agua', 'Pokémon de tipo agua', 'a1b2c3d4-e5f6-7890-abcd-ef1234567803');
INSERT INTO tipo_pokemon (nombre, descripcion, uuid) VALUES ('Planta', 'Pokémon de tipo planta', 'a1b2c3d4-e5f6-7890-abcd-ef1234567804');

-- Insert Pokémon
-- Pikachu: Eléctrico (tipo_pokemon = 1)
INSERT INTO pokemon (nombre, descripcion, tipo_pokemon, fecha_descubrimiento, generacion, uuid) 
VALUES ('Pikachu', 'Pokémon tipo ratón eléctrico muy amigable.', 1, '1996-02-27', 1, 'e379435b-cb8e-4a6c-9c71-3310702d73f1');

-- Charizard: Fuego (tipo_pokemon = 2)
INSERT INTO pokemon (nombre, descripcion, tipo_pokemon, fecha_descubrimiento, generacion, uuid) 
VALUES ('Charizard', 'Escupe fuego que es capaz de derretir rocas.', 2, '1996-02-27', 1, 'b21bfa98-2da7-4e60-93fb-e0871926615b');

-- Blastoise: Agua (tipo_pokemon = 3)
INSERT INTO pokemon (nombre, descripcion, tipo_pokemon, fecha_descubrimiento, generacion, uuid) 
VALUES ('Blastoise', 'Un Pokémon brutal con cañones de agua en su caparazón.', 3, '1996-02-27', 1, 'cd8849a6-cf03-4f9e-9907-88ab91024345');

-- Bulbasaur: Planta (tipo_pokemon = 4)
INSERT INTO pokemon (nombre, descripcion, tipo_pokemon, fecha_descubrimiento, generacion, uuid) 
VALUES ('Bulbasaur', 'Lleva una semilla en su lomo que crece con él.', 4, '1996-02-27', 1, '09f8742e-13b0-40e1-bb96-189f7cd59d1a');

-- Insert Trainers (Entrenador)
-- Ash Ketchum: Pueblo Paleta (pueblo_id = 1), uuid matching example: f3262c24-473d-437d-a5cf-e87673637954
INSERT INTO entrenador (nombre, apellido, fecha_nacimiento, fecha_vinculacion, pueblo_id, uuid) 
VALUES ('Ash', 'Ketchum', '1987-05-22', '1997-04-01', 1, 'f3262c24-473d-437d-a5cf-e87673637954');

-- Gary Oak: Pueblo Paleta (pueblo_id = 1), different uuid
INSERT INTO entrenador (nombre, apellido, fecha_nacimiento, fecha_vinculacion, pueblo_id, uuid) 
VALUES ('Gary', 'Oak', '1987-05-23', '1997-04-01', 1, '8d821361-9c87-43cf-be79-df54c728e51b');

-- Let's associate Pikachu to Ash by default
INSERT INTO entrenador_pokemon (entrenador_id, pokemon_id) VALUES (1, 1);
