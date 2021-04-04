INSERT INTO categoria (nombre, descripcion) VALUES
	('Entrantes',' '),
	('Ensalada',' '),
	('Tandoori',' '),
	('Pollo',' '),
	('Cordero',' '),
	('Marisco',' '),
	('Vegetariano',' '),
	('Postre',' '),
	('Bebidas',' ');

INSERT INTO estado (estado) VALUES
	('Recibido'),
	('En preparacion'),
	('De camino'),
	('Entregado');
    
INSERT INTO temporada (nombre) VALUES
	('Primavera'),
	('Verano'),
	('Otoño'),
	('Invierno');

INSERT INTO rol (nombre) VALUES 
	('Gestor'),
	('Cocinero'),
	('Repartidores');

INSERT INTO plato (nombre,descripcion,precio,num_plato,id_categoria) VALUES
	('Tarkaari Raas','Mix-vegetable soup',5.2, 1, 1),
	('Samosa','Crispy fluty pastries with mixed vegetables',7.00,2,1),
	('Jhinge Machhako Raas','Shrimps with carrot-cream soup',6.10,3,1),
	('Vegetable MoMo','Vegetable dumplings steamed and mixed with Nepalese spices with Vegetable soup',15.5,4,2),
    ('Chicken MoMo','Minced chicken meat dumpling steamed and mixed spices with Chicken soup and Naan',16.9,5,2),
	('Kukhurako Salad','Marinated chicken fillet with cucumber, tomatoes, pineapple, green peas, corn and paprika',10.5,6,2),
	('Malekhuko Jhingemachha','Tandoori marinated king prawns, tomato, green chilies, onion, ginger and capsicum',21.90,7,3),
    ('Paneer Tikka','Marinated cottage cheese with capsicum and onion',16.9,8,3),
	('Machhako Sekuwa','Herbs and yoghurt marinated Tandoor grilled salmon',19.1,9,3),
    ('Kukhurako Masu','Stewed chicken fillet pieces in tomato onion curry sauce',14.7,10,4),
	('Kukhurako Korma Golden','Stewed chicken fillet pieces in tomato onion cream sauce and honey',15.5,11,4),
	('Karahi Kukhuro','Stewed chicken fillet pieces with onion capsicum fresh tomato ginger green chili garlic sauce',16.10,12,4),
    ('Bhedako masu','Stewed Lamb pieces in tomato, onion sauce',15.9,13,5),
	('Bhedako korma','Stewed lamb pieces in tomato, onion sauce',16.4,14,5),
	('Khursani bhenda','Stewed lamb pieces and capsicum fresh tomato in onion, ginger and soya sauce',16.9,15,5),
	('Makhani jhinge machha','Fried shrimps in tomato butter cream sauce',16.9,16,6),
	('Khursani Machha','Fried fish of pike-perch with soya green chili capsicum onion garlic ginger sauce',17.9,17,6),
	('Khursani jhinge machha','Shrimps with soya, green capsicum, onion, garlic, ginger and green chili sauce',18.7,18,6),
	('Alu Gobi','Fried potatoes, ginger cauliflower and fried cummin, coriander',13.9,19,7),
	('Dal Makhani','Stewed mixed lentils in tomato buttercream sauce',14.7,20,7),
    ('Karai Tarkari','Broccoli, Cauliflower, cottage cheese, mushroom, potato, capsicum, onion in ginger-garlic spicy sauce',15.10,21,7),
    ('Lal Mohan','Cardamom syrup cheeseballs',5.20,22,8),
    ('Golden Banana','Rum Flamed banana and ice cream with honey and almond sauce',6.7,23,8),
	('Ice-cream ball',' ',3.00,24,8),
    ('Irish coffee','Coffee and Tea',3.5,0,9),
	('Nepali beer','Beers, Ciders',5.5,0,9),
	('Mango Lassi','Soft drinks',3.5,0,9);

INSERT INTO menu (menu_actual) VALUES (TRUE);

INSERT INTO plato_menu 
	SELECT id_plato, 1
    FROM plato
    WHERE nombre in ('Samosa', 'Vegetable MoMo', 'Jhinge Machhako Raas', 'Malekhuko Jhingemachha', 'Karahi Kukhuro','Karai Tarkari', 'Lal Mohan', 'Ice-cream ball');

#PLATOS MENU
SELECT num_plato, nombre
FROM plato AS p
JOIN plato_menu AS pm ON p.id_plato=pm.id_plato
WHERE pm.id_menu=1;

INSERT INTO carta (carta_actual, id_temporada) VALUES (TRUE, 1);

INSERT INTO plato_carta
	SELECT 1, id_plato
    FROM plato;

#PLATOS MENU
SELECT p.num_plato AS num_plato, p.nombre AS nombre_plato
FROM plato AS p
JOIN plato_carta AS pc ON p.id_plato=pc.id_plato
JOIN carta AS c ON pc.id_carta=c.id_carta
JOIN temporada AS t ON c.id_temporada=t.id_temporada 
WHERE t.nombre="primavera";

UPDATE menu
SET menu_actual = 0
WHERE menu_actual=1;

SELECT * FROM menu;
