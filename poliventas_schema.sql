
#drop schema poliventas;
DROP DATABASE IF EXISTS POLIVENTAS ;
CREATE DATABASE POLIVENTAS;
USE POLIVENTAS ;

DROP TABLE IF EXISTS Login;
CREATE TABLE Login(
	username 	VARCHAR(10) NOT NULL,
    contrasena 	VARCHAR(12) NOT NULL,
    id_usuario 	INTEGER NOT NULL
);
CREATE INDEX loginIndex ON Login (username);



DROP TABLE IF EXISTS Usuario ;
CREATE TABLE Usuario (
	id 			INTEGER PRIMARY KEY,  ## cédula
    nombres 	VARCHAR(50) NOT NULL,
    apellidos 	VARCHAR(50) NOT NULL,
    email 		VARCHAR(40) NOT NULL,
    telefono 	VARCHAR(10) NOT NULL,
    usa_whatsapp BOOLEAN DEFAULT FALSE,
    direccion 	VARCHAR(60),
    matricula 	INTEGER UNIQUE NOT NULL,
    tipo		CHAR(1),     #A administrador; C comprador; V vendedor
    eliminado   BOOLEAN DEFAULT FALSE
);
  

DROP TABLE IF EXISTS Categorias;
CREATE TABLE Categorias(
	id 				INTEGER PRIMARY KEY,
    nombre_categoria	VARCHAR(10),
    detalles 		VARCHAR(70)
);



DROP TABLE IF EXISTS Articulos ;
CREATE TABLE Articulos (
	id 				INTEGER PRIMARY KEY AUTO_INCREMENT,
	nombre 			VARCHAR(30) NOT NULL,
    id_categoria	INTEGER,
	descripcion 	CHAR(150),
	precio 			FLOAT4 NOT NULL,
	tiempo_max_entrega INTEGER NOT NULL, # Horas
    image_path 		VARCHAR(30),
	id_vendedor 		INTEGER,
    numero_busquedas	INTEGER DEFAULT 0,
    eliminado   BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_categoria) REFERENCES Categorias(id),
	FOREIGN KEY (id_vendedor) REFERENCES Usuario(id)
);




DROP TABLE IF EXISTS Pedidos;
CREATE TABLE Pedidos(
	id 			INTEGER PRIMARY KEY AUTO_INCREMENT,
    cantidad 	INTEGER,
	fecha 		DATE NOT NULL,
	estado 		CHAR(1) DEFAULT 'P', # E entregado; P pendiente; A anulado 
	id_comprador INTEGER,
    id_articulo  INTEGER,
    
    FOREIGN KEY (id_articulo) REFERENCES Articulos(id),
	FOREIGN KEY (id_comprador) REFERENCES Usuario(id)
);



DROP VIEW IF EXISTS Ventas;
CREATE VIEW Ventas AS
    SELECT *
    FROM Pedidos p WHERE p.estado = "E" ;

DROP VIEW IF EXISTS PedidosPendientes;
CREATE VIEW PedidosPendientes AS
    SELECT *
    FROM Pedidos p WHERE p.estado = "P" ;
      


DROP TABLE IF EXISTS Calificacion_vendedor ;
CREATE TABLE Calificacion_Vendedor (
	id 			INTEGER PRIMARY KEY AUTO_INCREMENT,
	no_estrellas INTEGER,
	id_vendedor 	INTEGER,
	id_usuario 	INTEGER,
    
	FOREIGN KEY (id_vendedor) REFERENCES Usuario (id),
    FOREIGN KEY (id_usuario) REFERENCES Usuario (id)
);



DROP TABLE IF EXISTS Calificacion_producto ;
CREATE TABLE Calificacion_producto (

	id 			INT PRIMARY KEY AUTO_INCREMENT,
	no_estrellas INTEGER,
	id_producto 	INTEGER,
	id_comprador INTEGER,
	FOREIGN KEY (id_producto) REFERENCES Articulos(id),
	FOREIGN KEY (id_comprador) REFERENCES Usuario(id)
);



DROP TABLE IF EXISTS Credito_cuenta;
CREATE TABLE Credito_cuenta(
	id_usuario	INTEGER,
    saldo 		DOUBLE NOT NULL,
    detalles	VARCHAR(100),
    
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);



DROP TABLE IF EXISTS Metodo_pago;
CREATE TABLE Metodo_pago(
	id	INTEGER PRIMARY KEY,
    id_usuario INTEGER,
    nombre_metodo VARCHAR(20),
    
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);



DROP TABLE IF EXISTS Pagos ;
CREATE TABLE Pagos (
	id			INTEGER PRIMARY KEY AUTO_INCREMENT,
	id_metodoPago INTEGER,
    estado		CHAR(1),  # P = pendiente; R = realizado
    monto		DOUBLE,
    fecha		DATE,
	id_pedido	INTEGER,
    
	FOREIGN KEY (id_pedido) REFERENCES Pedidos(id),
    FOREIGN KEY (id_metodoPago) REFERENCES Metodo_pago(id)
);
 