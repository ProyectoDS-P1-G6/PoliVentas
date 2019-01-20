USE POLIVENTAS;


INSERT INTO 
Usuario(id, nombres , apellidos, email, telefono, usa_whatsapp, direccion, matricula, saldo, tipo)
VALUES
(1299999999, 'Miguelx','ax', 'mapatino@espol.edu.ec', 985858585, true, 'enrique segoviano', 201608148, 300.00, 'A'),
(1200000000, 'Neythanx','ax', 'xxx@espol.edu.ec', 985858585, true, 'enrique segoviano', 201600000, 300.00, 'C'),
(1211111111, 'Carlax','ax', 'xxx@espol.edu.ec', 985858585, true, 'enrique segoviano', 201611111, 300.00, 'V');

INSERT INTO 
Login(username, contrasena, id_usuario) 
VALUES
('miguel', 'pass', 1299999999),
('neythan', 'pass', 1200000000),
('carla', 'pass', 1211111111);

INSERT INTO 
Pedidos(cantidad, fecha, estado) 
VALUES
(3, now(), 'E');