USE POLIVENTAS;

DELIMITER //
CREATE PROCEDURE checkUserAndPass(IN nickname VARCHAR(10), IN pass VARCHAR(20), OUT id INTEGER)
	BEGIN
		SET id := (SELECT id_usuario FROM Login WHERE username = nickname   AND contrasena = pass);
    END //
    

CREATE PROCEDURE getUser(IN id_user INTEGER)
	BEGIN
		SELECT * FROM Usuario WHERE Usuario.id = id_user;
    END//