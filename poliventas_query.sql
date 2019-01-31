
USE POLIVENTAS;
DROP PROCEDURE IF EXISTS checkUserAndPass;
DROP PROCEDURE IF EXISTS getUser;
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
    
    
CREATE PROCEDURE buscarArticulo(IN entry VARCHAR(20))
	BEGIN
		SELECT * 
        FROM Articulos 
        WHERE LOWER(nombre) LIKE concat('%', entry, '%') OR LOWER(descripcion) LIKE concat('%', entry, '%');
	END//



CREATE PROCEDURE getArticulo(IN id_articulo INTEGER)
	BEGIN
		SELECT a.id, a.nombre, c.nombre_categoria, a.descripcion, a.precio, a.tiempo_max_entrega, a.image_path, a.id_vendedor, a.numero_busquedas
        FROM Articulos a, Categorias c
        WHERE a.id = id_articulo and a.id_categoria = c.id;
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
		SELECT p.id, a.nombre, p.cantidad, p.fecha, a.precio*p.cantidad as total, p.estado, p.id_articulo
        FROM Pedidos p inner join Articulos a on p.id_articulo = a.id
        where a.id_vendedor = id_vendedor;
	END//



CREATE PROCEDURE registrarPedido(IN cantidad_t INTEGER, IN fecha_t DATE, IN id_comprador_t INTEGER, IN id_articulo_t INTEGER )
	BEGIN
		INSERT INTO Pedidos(cantidad, fecha, id_comprador, id_articulo)
        VALUES (cantidad_t, fecha_t, id_comprador_t, id_articulo_t) ;
    END//

    
