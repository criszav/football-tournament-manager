
-- Tournament Status
INSERT INTO t_tournament_status (status_name, description, is_active) VALUES ("Upcoming", "Torneo aún no ha comenzado", true);
INSERT INTO t_tournament_status (status_name, description, is_active) VALUES ("Ongoing", "Torneo se esta jugando", true);
INSERT INTO t_tournament_status (status_name, description, is_active) VALUES ("Finished", "Torneo finalizado", true);
INSERT INTO t_tournament_status (status_name, description, is_active) VALUES ("Cancelled", "Torneo ha sido cancelado", true);

-- Tournaments
INSERT INTO t_tournament (name, start_date, end_date, number_of_teams, is_active, user_id, tournament_status_id) VALUES ("Segunda División Profesional de Chile 2024", '2024-03-02', '2024-10-31', 14, false, 1, 3);
INSERT INTO t_tournament (name, start_date, end_date, number_of_teams, is_active, user_id, tournament_status_id) VALUES ("Segunda División Profesional de Chile 2023", '2023-02-24', '2023-10-15', 14, false, 1, 3);
INSERT INTO t_tournament (name, start_date, end_date, number_of_teams, is_active, user_id, tournament_status_id) VALUES ("Copa Chile 2024", '2024-04-27', '2024-11-20', 65, false, 2, 3);
INSERT INTO t_tournament (name, start_date, end_date, number_of_teams, is_active, user_id, tournament_status_id) VALUES ("Copa Chile 2023", '2023-03-08', '2023-12-13', 76, false, 2, 3);
INSERT INTO t_tournament (name, start_date, end_date, number_of_teams, is_active, user_id, tournament_status_id) VALUES ("Primera División de Chile 2025", '2025-02-16', '2025-11-10', 16, false, 2, 1);

-- Tournament Teams
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (1, 1);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (1, 2);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (1, 3);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (1, 4);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (1, 5);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (1, 6);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (1, 7);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (1, 8);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (1, 9);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (1, 10);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (1, 11);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (1, 12);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (1, 13);
INSERT INTO t_tournament_team (tournament_id, team_id) VALUES (1, 14);