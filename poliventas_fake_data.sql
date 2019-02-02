
USE POLIVENTAS;

INSERT INTO 
Usuario(id, nombres , apellidos, email, telefono, usa_whatsapp, direccion, matricula, tipo)
VALUES
(1299999999, 'Miguelx','ax', 'mapatino@espol.edu.ec', 985858585, true, 'enrique segoviano', 201608148, 'A'),
(1200000000, 'Neythanx','ax', 'xxx@espol.edu.ec', 985858585, true, 'enrique segoviano', 201600000, 'C'),
(1211111111, 'Carlax','ax', 'xxx@espol.edu.ec', 985858585, true, 'enrique segoviano y la h', 201611111, 'V'),
(1211111112, 'MaxSteel','Reloaded', 'xxxmsr@espol.edu.ec', 985858585, true, 'enrique segoviano 2', 201611112, 'V'),
(1211111113, 'Jhon','Travolta', 'xxxt@espol.edu.ec', 985858585, true, 'enrique segoviano y la k', 201611113, 'V');

INSERT INTO 
Login(username, contrasena, id_usuario) 
VALUES
('admin', 'pass', 1299999999),
('comprador', 'pass', 1200000000),
('vendedor', 'pass', 1211111111),
('vendedor2', 'pass', 1211111112),
('vendedor3', 'pass', 1211111113);

INSERT INTO
Categorias(id, nombre_categoria, detalles )
VALUES
(1, 'transporte', 'articulos que sirven para transportarse. (captain obvious mode On)'),
(2, 'prendas', 'articulos para vestir. (captain obvious mode On)'),
(3, 'accesorios', 'articulos que sirven para decorar el alma.'),
(4, 'adultos', 'articulos para hacer cosas sucias.'),
(5, 'gadget', 'aparatos para faclitarte la vida.');


INSERT INTO 
Articulos(nombre, id_categoria, descripcion, precio, tiempo_max_entrega, image_path, id_vendedor)
VALUES
('CrazyNavy',1 , 'nave que vuela muy pero muy alto, cómprala', 900.99, 100, '/nave_espacial.png', 1211111111 ),
('Luck T-shirt',2 , 'Camiseta de la suerte, cómprala guapo', 100.99, 1, '/luck_t-shirt.png', 1211111111),
('FutureBrasalette',3 , 'Brasalete que revela que puede revelar la fecha de WW3', 8919.99, 50, '/brasalete.png', 1211111111),
('HitGuardian',3 , 'Protege a tu celular de accidentes', 10.99, 10, '/HitGuardian.png', 1211111111),
('MouseX',5 , 'Mause digital con detector de huella', 50.99, 10, '/MouseX.png', 1211111112),
('Relogx',3 , 'Relog que muestra tu horaio de exámenes', 40.99, 10, '/Relogx.png', 1211111112),
('BOLÍGRAFO LIVESCRIBE',5 , 'Bolígrafo inteligente Livescribe permite grabar las clases y recuperar el audio cuando queramos', 60.99, 10, '/LIVESCRIBE.png', 1211111111),
('IBACKPACK',5 , 'La mochila tiene cuatro puertos USB y dispone de conexión a través de WIFI', 200.99, 10, '/iBackPack.png', 1211111112),
('Celluon Magic Cube',5 , 'gadget que proyecta un teclado virtual sobre nuestra mesa de trabajo ', 50.99, 10, '/celluon.png', 1211111113),
('INFOSCAN TS',5 , 'Escáner en forma de bolígrafo, que captura la información que queremos remarcar y lo pasa a cualquier dispositivo multimedia', 50.99, 10, '/infoscan-ts.png', 1211111111),
('LÁMPARA USB',5 , 'Lamparitas USB, totalmente flexibles y adaptables a cualquier soporte tecnológico', 30.99, 10, '/lampara-usb.png', 1211111112),
('Mini Cámara De Video Gafas',5 , '...', 19.99, 10, '/gafasvideos.png', 1211111113);


INSERT INTO 
Pedidos(cantidad, fecha,id_comprador,id_articulo) 

VALUES
(2, now(),1200000000, 1),
(1, now(),1200000000, 2),
(3, now(),1200000000, 3),
(4, now(),1200000000, 4),
(1, now(),1200000000, 1),
(2, now(),1200000000, 2),
(5, now(),1299999999, 2),
(2, now(),1299999999, 2),
(3, now(),1299999999, 3),
(1, now(),1299999999, 4),
(2, now(),1299999999, 1),
(5, now(),1299999999, 2),
(2, now(),1299999999, 2),
(3, now(),1211111111, 3),
(1, now(),1211111111, 4),
(2, now(),1211111111, 1),
(2, now(),1211111111, 1);

INSERT INTO 
Pedidos(cantidad, fecha, estado, id_comprador,id_articulo) 
VALUES
(3, now(), 'E', 1200000000, 2),
(2, now(), 'E', 1299999999, 2),
(4, now(), 'E', 1299999999, 2),
(1, now(), 'E', 1299999999, 2);
