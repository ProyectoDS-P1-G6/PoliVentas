
#drop schema poliventas;
CREATE SCHEMA IF NOT EXISTS POLIVENTAS;
USE POLIVENTAS ;

DROP TABLE IF EXISTS POLIVENTAS.Administrador ;

CREATE TABLE IF NOT EXISTS POLIVENTAS.Administrador (
  idAdministrador INT NOT NULL,
  PRIMARY KEY (idAdministrador));


DROP TABLE IF EXISTS POLIVENTAS.Usuario ;

CREATE TABLE POLIVENTAS.Usuario (
  cédula VARCHAR(10) NOT NULL,
  Nombres VARCHAR(50) NOT NULL,
  Apellidos VARCHAR(50) NOT NULL,
  Telefono VARCHAR(10) NOT NULL,
  Whatsapp VARCHAR(2) NULL,
  email VARCHAR(45) NULL,
  direccion VARCHAR(60) NULL,
  matricula VARCHAR(9) NOT NULL,
  username VARCHAR(45) NOT NULL,
  contrasena VARCHAR(45) NOT NULL,
  PRIMARY KEY (cédula));
  

DROP TABLE IF EXISTS POLIVENTAS.Vendedor ;

CREATE TABLE POLIVENTAS.Vendedor (
  idVendedor VARCHAR(9) NOT NULL,
  idUsuario VARCHAR(10) NOT NULL,
  PRIMARY KEY (idVendedor),
  INDEX idUsuario_idx (idUsuario ASC),
    FOREIGN KEY (idUsuario)
    REFERENCES POLIVENTAS.Usuario (cédula));
 

DROP TABLE IF EXISTS POLIVENTAS.Categoría ;

CREATE TABLE POLIVENTAS.Categoría (
  idCategoría INT NOT NULL,
  Nombre VARCHAR(45) NOT NULL,
  PRIMARY KEY (idCategoría));


DROP TABLE IF EXISTS POLIVENTAS.Comprador ;

CREATE TABLE POLIVENTAS.Comprador (
  idComprador VARCHAR(9) NOT NULL,
  idUsuario VARCHAR(10) NOT NULL,
  PRIMARY KEY (idComprador),
  INDEX `fk_Comprador_Usuario1_idx` (idUsuario ASC),
    FOREIGN KEY (idUsuario)
    REFERENCES POLIVENTAS.Usuario (cédula)
    );


DROP TABLE IF EXISTS POLIVENTAS.Producto ;

CREATE TABLE POLIVENTAS.Producto (
  idProducto INT NOT NULL,
  Nombre VARCHAR(45) NOT NULL,
  Descripción VARCHAR(80) NOT NULL,
  Precio VARCHAR(45) NOT NULL,
  TiempoMaxEntrega INT NOT NULL,
  idCategoria INT NOT NULL,
  idVendedor VARCHAR(9) NOT NULL,
  PRIMARY KEY (idProducto),
  INDEX idCategoria_idx (idCategoria ASC),
  INDEX fk_Producto_Vendedor1_idx (idVendedor ASC),
    FOREIGN KEY (idCategoria)
    REFERENCES POLIVENTAS.Categoría (idCategoría),
    FOREIGN KEY (idVendedor)
    REFERENCES POLIVENTAS.Vendedor (idVendedor));



DROP TABLE IF EXISTS POLIVENTAS.Pedidos ;

CREATE TABLE POLIVENTAS.Pedidos (
  noPedido INT NOT NULL,
  PrecioTotal INT NOT NULL,
  FechaPedido DATE NOT NULL,
  Estado VARCHAR(45) NULL,
  idUsuario VARCHAR(9) NOT NULL,
  PRIMARY KEY (noPedido),
  INDEX fk_Pedidos_Comprador1_idx (idUsuario ASC),
    FOREIGN KEY (idUsuario)
    REFERENCES POLIVENTAS.Comprador (idUsuario));


DROP TABLE IF EXISTS POLIVENTAS.Calificacion_Vendedor ;

CREATE TABLE POLIVENTAS.Calificacion_Vendedor (
  idCalificacion_Vendedor INT NOT NULL,
  NoEstrellas VARCHAR(45) NULL,
  idVendedor VARCHAR(9) NOT NULL,
  idUsuario VARCHAR(9) NOT NULL,
  PRIMARY KEY (idCalificacion_Vendedor),
  INDEX fk_Calificacion_Vendedor_Vendedor1_idx (idVendedor ASC),
    FOREIGN KEY (idVendedor)
    REFERENCES POLIVENTAS.Vendedor (idVendedor));


DROP TABLE IF EXISTS POLIVENTAS.Calificacion_Producto ;

CREATE TABLE POLIVENTAS.Calificacion_Producto (
  idCalificacion_Producto INT NOT NULL,
  NoEstrellas VARCHAR(45) NULL,
  idProducto INT NULL,
  idVendedor VARCHAR(9) NULL,
  idComprador VARCHAR(9) NULL,
  PRIMARY KEY (idCalificacion_Producto),
  INDEX idProducto_idx (idProducto ASC),
  INDEX fk_Calificacion_Producto_Vendedor1_idx (idVendedor ASC),
  INDEX fk_Calificacion_Producto_Comprador1_idx (idComprador ASC),
    FOREIGN KEY (idProducto)
    REFERENCES POLIVENTAS.Producto (idProducto),
    FOREIGN KEY (idVendedor)
    REFERENCES POLIVENTAS.Vendedor (idVendedor),
    FOREIGN KEY (idComprador)
    REFERENCES POLIVENTAS.Comprador (idUsuario));



DROP TABLE IF EXISTS POLIVENTAS.DetallePedido ;

CREATE TABLE POLIVENTAS.DetallePedido (
  idDetallePedido INT NOT NULL,
  noPedido INT NOT NULL,
  idProducto INT NOT NULL,
  Cantidad INT NOT NULL,
  PrecioUnitario INT NOT NULL,
  PRIMARY KEY (idDetallePedido),
  INDEX noPedido_idx (noPedido ASC),
  INDEX idProducto_idx (idProducto ASC),
    FOREIGN KEY (noPedido)
    REFERENCES POLIVENTAS.Pedidos (noPedido),
    FOREIGN KEY (idProducto)
    REFERENCES POLIVENTAS.Producto (idProducto)
    );



DROP TABLE IF EXISTS POLIVENTAS.Ventas ;

CREATE TABLE POLIVENTAS.Ventas (
  noVenta INT NOT NULL,
  Estado VARCHAR(15) NOT NULL,
  idVendedor VARCHAR(9) NOT NULL,
  noPedido INT NOT NULL,
  INDEX fk_Ventas_Vendedor1_idx (idVendedor ASC),
  INDEX fk_Ventas_Pedidos1_idx (noPedido ASC),
  PRIMARY KEY (noVenta),
    FOREIGN KEY (idVendedor)
    REFERENCES POLIVENTAS.Vendedor (idVendedor),
    FOREIGN KEY (noPedido)
    REFERENCES POLIVENTAS.Pedidos (noPedido));


DROP TABLE IF EXISTS POLIVENTAS.Pagos ;

CREATE TABLE POLIVENTAS.Pagos (
  noPago INT NOT NULL,
  Valor VARCHAR(45) NOT NULL,
  idMetodoPago VARCHAR(45) NOT NULL,
  noPedido INT NOT NULL,
  PRIMARY KEY (noPago),
  INDEX fk_Pagos_Ventas1_idx (noPedido ASC),
    FOREIGN KEY (noPedido)
    REFERENCES POLIVENTAS.Ventas (noPedido));


DROP TABLE IF EXISTS POLIVENTAS.MetodoPago ;

CREATE TABLE POLIVENTAS.MetodoPago (
  idMetodoPago VARCHAR(8) NOT NULL,
  Moneda VARCHAR(45) NULL,
  noPago INT NULL,
  PRIMARY KEY (idMetodoPago),
  INDEX noPago_idx (noPago ASC),
    FOREIGN KEY (noPago)
    REFERENCES POLIVENTAS.Pagos (noPago));
