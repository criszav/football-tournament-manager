--- User Roles
INSERT INTO t_user_role (role_name, description) VALUES ("SUPER_ADMIN", "Administrador con todos los permisos del sistema (developers)");
INSERT INTO t_user_role (role_name, description) VALUES ("ADMIN", "Administrador general del sistema (encargado)");
INSERT INTO t_user_role (role_name, description) VALUES ("TOURNAMENT_MANAGER", "Encargado de gestionar torneos");
INSERT INTO t_user_role (role_name, description) VALUES ("TEAM_MANAGER", "Manager y representante de un equipo");
INSERT INTO t_user_role (role_name, description) VALUES ("PLAYER", "Jugador de futbol");

-- Users
INSERT INTO t_user (firstname, lastname, username, email, password, run, is_active, user_role_id) VALUES ("Sergio", "Jadue", "sjadue", "sjadue@gmail.com", "$2a$10$qMZy.jrJMwZdhGHrRr23auPoZH0vCEHK4ymA4Ed7VzKig5Lm63a2a", "123456789", true, 1);
INSERT INTO t_user (firstname, lastname, username, email, password, run, is_active, user_role_id) VALUES ("Alexis", "Sanchez", "asanchez", "asanchez@gmail.com", "$2a$10$jyOk2X5gqeyTuxxSPYWGXe8Y1fwfwloYMfiV0wwsMyMOEaPjXYdnS", "111111111", true, 2);
INSERT INTO t_user (firstname, lastname, username, email, password, run, is_active, user_role_id) VALUES ("Cristiano", "Ronaldo", "cronaldo", "cronaldo@gmail.com", "$2a$10$bDvFaHuYGrzG7rJFT8z6Nu64r.gn4WNLOGNIqpctRTQbHqurQQDuS", "222222222", true, 3);
INSERT INTO t_user (firstname, lastname, username, email, password, run, is_active, user_role_id) VALUES ("Arturo", "Vidal", "avidal", "avidal@gmail.com", "$2a$10$6i3SBcD7zuk1n4ch.MPtWOegtbaztfC1hahDt5zWREsw1T2LAABX.", "333333333", true, 4);




