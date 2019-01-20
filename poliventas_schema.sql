
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
    saldo 		DOUBLE NOT NULL,
    tipo		CHAR(1)     #A administrador; C comprador; V vendedor
);
  


DROP TABLE IF EXISTS Articulos ;
CREATE TABLE Articulos (
	id 				INTEGER PRIMARY KEY AUTO_INCREMENT,
	nombre 			VARCHAR(30) NOT NULL,
	descripción 	VARCHAR(80),
	precio 			FLOAT4 NOT NULL,
	tiempoMaxEntrega INTEGER NOT NULL, # Horas
	categoria 		VARCHAR(10),
	idVendedor 		INTEGER,
    image_path 		VARCHAR(30),
    
	FOREIGN KEY (idVendedor) REFERENCES Usuario(id)
);




DROP TABLE IF EXISTS Pedidos;
CREATE TABLE Pedidos(
	id 			INTEGER PRIMARY KEY AUTO_INCREMENT,
    cantidad 	INTEGER,
	fecha 		DATE NOT NULL,
	estado 		CHAR(1) DEFAULT 'P', # E entregado; P pendiente; A anulado 
	idComprador INTEGER,
    idArticulo 	INTEGER,
    
    FOREIGN KEY (idArticulo) REFERENCES Articulos(id),
	FOREIGN KEY (idComprador) REFERENCES Usuario(id)
);



DROP VIEW IF EXISTS Ventas;
CREATE VIEW Ventas AS
    SELECT id, cantidad, fecha, estado, idComprador, idArticulo
    FROM Pedidos p WHERE p.estado = "E" ;
      


DROP TABLE IF EXISTS Calificacion_Vendedor ;
CREATE TABLE Calificacion_Vendedor (
	id 			INTEGER PRIMARY KEY AUTO_INCREMENT,
	NoEstrellas INTEGER,
	idVendedor 	INTEGER,
	idUsuario 	INTEGER,
    
	FOREIGN KEY (idVendedor) REFERENCES Usuario (id),
    FOREIGN KEY (idUsuario) REFERENCES Usuario (id)
);



DROP TABLE IF EXISTS Calificacion_Producto ;
CREATE TABLE Calificacion_Producto (

	id 			INT PRIMARY KEY AUTO_INCREMENT,
	NoEstrellas INTEGER,
	idProducto 	INTEGER,
	idComprador INTEGER,
	FOREIGN KEY (idProducto) REFERENCES Articulos(id),
	FOREIGN KEY (idComprador) REFERENCES Usuario(id)
);



DROP TABLE IF EXISTS Pagos ;
CREATE TABLE Pagos (
	id 		INTEGER PRIMARY KEY AUTO_INCREMENT,
	metodo	VARCHAR(1),  # B = efectivo; E = electronico
    estado 	CHAR(1),  # P = pendiente; R = realizado
    fecha	DATE,
	idPedido INTEGER,
	FOREIGN KEY (idPedido) REFERENCES Pedidos(id)
);

 