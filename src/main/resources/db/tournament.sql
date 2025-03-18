
-- Tournament Status
INSERT INTO t_tournament_status (status_name, description, is_active) VALUES ("Upcoming", "Torneo aún no ha comenzado", true);
INSERT INTO t_tournament_status (status_name, description, is_active) VALUES ("Ongoing", "Torneo se esta jugando", true);
INSERT INTO t_tournament_status (status_name, description, is_active) VALUES ("Finished", "Torneo finalizado", true);
INSERT INTO t_tournament_status (status_name, description, is_active) VALUES ("Cancelled", "Torneo ha sido cancelado", true);

-- Tournaments
INSERT INTO t_tournament (name, start_date, end_date, number_of_teams, is_active, user_id, tournament_status_id) VALUES ("Segunda División Profesional de Chile 2024", '2024-03-02', '2024-10-31', 14, false, 1, 3);
INSERT INTO t_tournament (name, start_date, end_date, number_of_teams, is_active, user_id, tournament_status_id) VALUES ("Copa Chile 2024", '2024-04-27', '2024-11-20', 65, false, 2, 3);
INSERT INTO t_tournament (name, start_date, end_date, number_of_teams, is_active, user_id, tournament_status_id) VALUES ("Liga de Primera 2025", '2025-02-14', '2025-12-07', 16, true, 1, 2);
INSERT INTO t_tournament (name, start_date, end_date, number_of_teams, is_active, user_id, tournament_status_id) VALUES ("Liga de Ascenso 2025", '2025-02-21', '2025-11-30', 16, true, 1, 2);
INSERT INTO t_tournament (name, start_date, end_date, number_of_teams, is_active, user_id, tournament_status_id) VALUES ("Segunda La Liga 2D 2025", '2025-03-01', '2025-11-30', 14, true, 1, 1);
INSERT INTO t_tournament (name, start_date, end_date, number_of_teams, is_active, user_id, tournament_status_id) VALUES ("Copa Chile 2025", '2025-01-26', null, 32, true, 1, 2);

-- Tournament Teams

-- Equipos de Primera 2025
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 1);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 2);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 3);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 4);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 5);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 6);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 7);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 8);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 9);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 10);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 11);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 12);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 13);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 14);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 15);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (3, 16);

-- Equipos de Primera B 2025
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 17);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 18);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 19);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 20);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 21);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 22);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 23);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 24);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 25);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 26);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 27);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 28);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 29);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 30);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 31);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (4, 32);

-- Equipos Segunda 2025
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (5, 33);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (5, 34);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (5, 35);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (5, 36);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (5, 37);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (5, 38);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (5, 39);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (5, 40);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (5, 41);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (5, 42);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (5, 43);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (5, 44);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (5, 45);