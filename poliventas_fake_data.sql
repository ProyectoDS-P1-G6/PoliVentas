USE POLIVENTAS;


INSERT INTO 
Usuario(id, nombres , apellidos, email, telefono, usa_whatsapp, direccion, matricula, tipo)
VALUES
(1299999999, 'Miguelx','ax', 'mapatino@espol.edu.ec', 985858585, true, 'enrique segoviano', 201608148, 'A'),
(1200000000, 'Neythanx','ax', 'xxx@espol.edu.ec', 985858585, true, 'enrique segoviano', 201600000, 'C'),
(1211111111, 'Carlax','ax', 'xxx@espol.edu.ec', 985858585, true, 'enrique segoviano', 201611111, 'V');

INSERT INTO 
Login(username, contrasena, id_usuario) 
VALUES
('admin', 'pass', 1299999999),
('comprador', 'pass', 1200000000),
('vendedor', 'pass', 1211111111);

INSERT INTO
Categorias(id, nombre_categoria, detalles )
VALUES
(1, 'transporte', 'articulos que sirven para transportarse. (captain obvious mode On)'),
(2, 'prendas', 'articulos para vestir. (captain obvious mode On)'),
(3, 'accesorios', 'articulos que sirven para decorar el alma.'),
(4, 'adultos', 'articulos para hacer cosas sucias.');


INSERT INTO 
Articulos(nombre, id_categoria, descripcion, precio, tiempo_max_entrega, image_path, id_vendedor, numero_busquedas)
VALUES
('nave espacial',1 , 'nave que vuela muy pero muy alto, cómprala', 900000.99, 100, '/nave_espacial.png', 1211111111, 2 ),
('Luck T-shirt',2 , 'Camiseta de la suerte, cómprala guapo', 1000.99, 1, '/luck_t-shirt.png', 1211111111, 5 ),
('Brasalete del futuro',3 , 'Brasalete que revela que puede revelar la fecha de WW3', 8912899.99, 50, '/brasalete.png', 1211111111, 10 ),
('pistola de lodo',4 , 'para ensuciar a todos tus amigos', 50.99, 100, '/adult.png', 1211111111, 7 );


INSERT INTO
Pedidos(cantidad ,fecha,id_comprador,id_articulo)
VALUES
(2, now(),1200000000, 1),
(1, now(),1200000000, 2),
(3, now(),1200000000, 3),
(4, now(),1200000000, 4),
(1, now(),1200000000, 1),
(2, now(),1200000000, 2);



INSERT INTO 
Pedidos(cantidad, fecha, estado, id_comprador,id_articulo) 
VALUES
(3, now(), 'E', 1200000000, 2);