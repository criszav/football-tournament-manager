--- User Roles
INSERT INTO t_user_role (role_name, description) VALUES ("SUPER_ADMIN", "Administrador con todos los permisos del sistema (developers)");
INSERT INTO t_user_role (role_name, description) VALUES ("ADMIN", "Administrador general del sistema (encargado)");
INSERT INTO t_user_role (role_name, description) VALUES ("TOURNAMENT_MANAGER", "Encargado de gestionar torneos");
INSERT INTO t_user_role (role_name, description) VALUES ("TEAM_MANAGER", "Manager y representante de un equipo");
INSERT INTO t_user_role (role_name, description) VALUES ("PLAYER", "Jugador de futbol");

-- Users
INSERT INTO t_user (firstname, lastname, username, email, password, run, is_active, user_role_id) VALUES ("Cristian", "Zavala", "czavala", "czavala@gmail.com", "clave123", "123456789", true, 1)
INSERT INTO t_user (firstname, lastname, username, email, password, run, is_active, user_role_id) VALUES ("Alexis", "Sanchez", "asanchez", "asanchez@gmail.com", "clave456", "111111111", true, 2)
INSERT INTO t_user (firstname, lastname, username, email, password, run, is_active, user_role_id) VALUES ("Cristiano", "Ronaldo", "cronaldo", "cronaldo@gmail.com", "clave789", "222222222", true, 3)
INSERT INTO t_user (firstname, lastname, username, email, password, run, is_active, user_role_id) VALUES ("Arturo", "Vidal", "avidal", "avidal@gmail.com", "clave000", "333333333", true, 4)




