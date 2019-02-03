USE POLIVENTAS;
DROP PROCEDURE IF EXISTS checkUserAndPass;
DROP PROCEDURE IF EXISTS getUser;
DROP PROCEDURE IF EXISTS getAllUser;
DROP PROCEDURE IF EXISTS buscarArticulo;
DROP PROCEDURE IF EXISTS getArticulo;
DROP PROCEDURE IF EXISTS addBusqueda;
DROP PROCEDURE IF EXISTS getArticulosMasBuscados;
DROP PROCEDURE IF EXISTS getPedidos;
DROP PROCEDURE IF EXISTS getPedidosPendientes;
DROP PROCEDURE IF EXISTS getSaldo;
DROP PROCEDURE IF EXISTS setSaldo;
DROP PROCEDURE IF EXISTS getMisArticulos;
DROP PROCEDURE IF EXISTS getVentas;
DROP PROCEDURE IF EXISTS registrarPedido;
DROP PROCEDURE IF EXISTS createUsuarios;
DROP PROCEDURE IF EXISTS changeRolUsuario;
DROP PROCEDURE IF EXISTS updateUsuario;
DROP PROCEDURE IF EXISTS updateUsuario;
DROP PROCEDURE IF EXISTS deleteUsuario;
DROP PROCEDURE IF EXISTS getVentasPendientes;
DROP PROCEDURE IF EXISTS agregarProducto;
DROP PROCEDURE IF EXISTS anularVenta;
DROP PROCEDURE IF EXISTS modificarProducto;

DELIMITER //
CREATE PROCEDURE checkUserAndPass(IN nickname VARCHAR(10), IN pass VARCHAR(20), OUT id INTEGER)
	BEGIN
		SET id := (SELECT id_usuario 
					FROM Login 
                    WHERE username = nickname   AND contrasena = pass);
    END //
    


CREATE PROCEDURE getUser(IN id_user INTEGER)
	BEGIN
		SELECT * 
        FROM Usuario 
        WHERE Usuario.id = id_user;
    END//

CREATE PROCEDURE getAllUser()
	BEGIN
		SELECT * 
        FROM Usuario
        ORDER BY apellidos DESC;
        
	END//    
    
CREATE PROCEDURE buscarArticulo(IN entry VARCHAR(20))
	BEGIN
		SELECT * 
        FROM Articulos 
        WHERE LOWER(nombre) LIKE concat('%', entry, '%') OR LOWER(descripcion) LIKE concat('%', entry, '%');
	END//



CREATE PROCEDURE getArticulo(IN id_articulo INTEGER)
	BEGIN
		SELECT a.id, a.nombre, c.nombre_categoria, a.descripcion, a.precio, a.tiempo_max_entrega, a.image_path, a.id_vendedor, a.numero_busquedas,a.eliminado
        FROM Articulos a, Categorias c
        WHERE a.id = id_articulo and a.id_categoria = c.id;
        
    END//

CREATE PROCEDURE getAllArticulos()
	BEGIN
		SELECT * 
        FROM Articulos
        ORDER BY nombre DESC;
        
        
	END//
    
CREATE PROCEDURE addBusqueda(IN id_articulo INTEGER)
	BEGIN
		UPDATE Articulos
        SET numero_busquedas = numero_busquedas + 1
        WHERE id = id_articulo;
    END//



CREATE PROCEDURE getArticulosMasBuscados(IN cantidad INTEGER)
	BEGIN
		SELECT *
        FROM Articulos
        WHERE numero_busquedas > 0
		ORDER BY numero_busquedas DESC
		LIMIT 5;
    END//



CREATE PROCEDURE getPedidos(IN id_user INTEGER)
	BEGIN
		SELECT * 
        FROM Pedidos 
        WHERE id_comprador = id_user;
    END//



CREATE PROCEDURE getPedidosPendientes(IN id_user INTEGER)
	BEGIN
		SELECT * 
        FROM PedidosPendientes
        WHERE id_comprador = id_user;
    END//



CREATE PROCEDURE getSaldo(IN id_user INTEGER, OUT saldo_t DOUBLE)
	BEGIN
		SET saldo_t := (SELECT saldo from Credito_cuenta WHERE id_usuario = id_user);
    END//
    
    
  

CREATE PROCEDURE setSaldo(IN id_user INTEGER, IN saldo_t DOUBLE)
	BEGIN
		UPDATE Credito_cuenta 
        SET saldo = saldo_t 
        WHERE id_usuario = id_user;
    END//
    


CREATE PROCEDURE getMisArticulos(IN id_vendedor INTEGER)
	BEGIN
		Select a.id,a.nombre,c.nombre_categoria,a.descripcion,a.precio,a.tiempo_max_entrega,a.image_path,a.id_vendedor,a.numero_busquedas
        from Articulos a inner join Categorias c on a.id_categoria = c.id
        where id_vendedor = a.id_vendedor;
	END//
    

CREATE PROCEDURE getVentas(IN id_vendedor INTEGER)
	BEGIN
		SELECT p.id, a.nombre, p.cantidad, p.fecha, a.precio*p.cantidad as total, p.estado, p.id_articulo, p.id_comprador
        FROM Pedidos p inner join Articulos a on p.id_articulo = a.id
        where a.id_vendedor = id_vendedor;
	END//

CREATE PROCEDURE getVentasPendientes(IN id_vendedor INTEGER)
	BEGIN
		SELECT p.id, a.nombre, p.cantidad, p.fecha, a.precio*p.cantidad as total, p.estado, p.id_articulo, p.id_comprador
        FROM Pedidos p inner join Articulos a on p.id_articulo = a.id
        where a.id_vendedor = id_vendedor and p.estado = 'P';
	END//

CREATE PROCEDURE registrarPedido(IN cantidad_t INTEGER, IN fecha_t DATE, IN id_comprador_t INTEGER, IN id_articulo_t INTEGER )
	BEGIN
		INSERT INTO Pedidos(cantidad, fecha, id_comprador, id_articulo)
        VALUES (cantidad_t, fecha_t, id_comprador_t, id_articulo_t) ;
    END//


    
CREATE PROCEDURE agregarProducto (IN nom VARCHAR(30), IN cat VARCHAR(10),IN des VARCHAR(80), IN prec DOUBLE, IN tiempo INTEGER, IN id INTEGER)
BEGIN
		INSERT INTO
		Articulos(nombre, id_categoria, descripcion, precio, tiempo_max_entrega, id_vendedor)
        VALUES
        (nom,cat,des,prec,tiempo,id);
	END//
    
CREATE PROCEDURE anularVenta(IN id_pedido INTEGER)
BEGIN
		UPDATE Pedidos
			SET
				estado = 'A' WHERE  id = id_pedido;
	END//
    
CREATE PROCEDURE modificarProducto(IN id_art INTEGER,IN nom VARCHAR(30), IN cat varchar(10),IN des VARCHAR(80), IN prec DOUBLE, IN tiempo INTEGER)
BEGIN
    UPDATE Articulos a inner join Categorias c on a.id_categoria = c.id
    SET 
		a.nombre= nom,
        c.nombre_categoria = cat,
        a.descripcion= des,
        a.precio= prec,
        a.tiempo_max_entrega=tiempo
	WHERE a.id= id_art;
    END//
    
CREATE PROCEDURE getCategorias()
BEGIN
		SELECT nombre_categoria
        FROM Categorias;
	END//   
    
    
CREATE PROCEDURE deleteUsuario(IN id_user INTEGER)
	BEGIN
        DELETE FROM Pedidos WHERE id_articulo = id_user;
        DELETE FROM Pedidos WHERE id_comprador = id_user;
        DELETE FROM Articulos WHERE id_vendedor = id_user;
        DELETE FROM Usuario WHERE id = id_user;
        
		
    END//
CREATE PROCEDURE deleteUsuarioLogic(IN id_user INTEGER)
	BEGIN
         DELETE FROM Pedidos WHERE id_articulo = id_user;
         DELETE FROM Pedidos WHERE id_comprador = id_user;
         UPDATE Articulos SET eliminado=true WHERE id_vendedor = id_user;
         UPDATE Usuario SET eliminado=true WHERE id = id_user;
		
    END//
    
CREATE PROCEDURE deleteArticulo(IN id_art INTEGER)
	BEGIN
         UPDATE Articulos SET eliminado=true WHERE id = id_art;
         		
    END//
    
CREATE PROCEDURE updateUsuario(IN id_user INTEGER)
	BEGIN
		UPDATE Usuario set nombres="", 
                           apellidos="",
                           email="",
                           telefono="",
                           usa_whatsapp="",
                           direccion="",
                           matricula="",
                           tipo=""
		WHERE Usuario.id = id_user;
    END//
    
CREATE PROCEDURE changeRolUsuario(IN id_user INTEGER, IN rol_user CHAR)
	BEGIN
		UPDATE Usuario set tipo=rol_user
		WHERE id = id_user;
    END//

CREATE PROCEDURE createUsuarios(IN id_user INTEGER,
							   NOM VARCHAR(50),
                               APELL VARCHAR(50),
                               CORREO VARCHAR(40),
                               TELF VARCHAR(10),
                               WHATS TINYINT(1),
                               ADRESS VARCHAR(60),
                               IDM INT(11),
                               TYP CHAR(1))
	BEGIN
		INSERT INTO Usuario(id,
						   nombres, 
                           apellidos,
                           email,
                           telefono,
                           usa_whatsapp,
                           direccion,
                           matricula,
                           tipo)
		VALUES(id_user,NOM,APELL,CORREO,TELF,WHATS,ADRESS,IDM,TYP);
        
	END//
