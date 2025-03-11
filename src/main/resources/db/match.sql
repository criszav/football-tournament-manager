INSERT INTO t_match_status (status_name, description, is_active) VALUES ("Upcoming", "Partido programado que aún no se juega", true);
INSERT INTO t_match_status (status_name, description, is_active) VALUES ("Finished", "Partido finalizado sin eventos adicionales", true);
INSERT INTO t_match_status (status_name, description, is_active) VALUES ("Suspended", "Partido suspendido", true);
INSERT INTO t_match_status (status_name, description, is_active) VALUES ("Postponed", "Partido reprogramado", true);


-- Matches Primera 2025
-- Fecha 1
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-14", "21:00", 1, 0, 3, 2, 3, 5, 4, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-15", "18:00", 1, 0, 0, 2, 3, 11, 9, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-15", "18:00", 1, 2, 1, 2, 3, 12, 2, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-15", "20:30", 1, 5, 0, 2, 3, 16, 10, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-16", "12:00", 1, 1, 3, 2, 3, 6, 3, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-16", "20:30", 1, 1, 1, 2, 3, 7, 8, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-17", "18:00", 1, 3, 1, 2, 3, 15, 1, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-17", "20:30", 1, 4, 0, 2, 3, 14, 13, null);

-- Fecha 2
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-21", "18:00", 2, 4, 2, 2, 3, 1, 5, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-21", "18:00", 2, 0, 3, 2, 3, 13, 12, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-22", "18:00", 2, 1, 0, 2, 3, 4, 15, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-22", "20:30", 2, 1, 1, 2, 3, 7, 10, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-23", "12:00", 2, 4, 0, 2, 3, 9, 8, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-23", "18:00", 2, 3, 1, 2, 3, 2, 6, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-23", "20:30", 2, 1, 0, 2, 3, 16, 14, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-24", "20:00", 2, 0, 1, 2, 3, 3, 11, null);

-- Fecha 3
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-28", "20:00", 3, 1, 2, 2, 3, 7, 4, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-01", "12:00", 3, 1, 0, 2, 3, 6, 13, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-01", "18:00", 3, 0, 2, 2, 3, 12, 1, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-01", "18:00", 3, 1, 0, 2, 3, 15, 5, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-01", "20:30", 3, 1, 2, 2, 3, 8, 10, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-02", "12:00", 3, 2, 1, 2, 3, 9, 3, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-02", "18:00", 3, 2, 1, 2, 3, 2, 16, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-02", "20:30", 3, 2, 2, 2, 3, 14, 11, null);

-- Fecha 4
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-07", "20:00", 4, null, null, 1, 3, 14, 9, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-08", "18:00", 4, null, null, 1, 3, 10, 15, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-08", "20:30", 4, null, null, 1, 3, 5, 12, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-08", "20:30", 4, null, null, 1, 3, 1, 7, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-09", "12:00", 4, null, null, 1, 3, 4, 2, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-09", "18:00", 4, null, null, 1, 3, 11, 6, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-09", "20:30", 4, null, null, 1, 3, 3, 8, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-10", "20:00", 4, null, null, 1, 3, 13, 16, null);

-- Fecha 5
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-14", "18:00", 5, null, null, 1, 3, 1, 15, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-14", "20:30", 5, null, null, 1, 3, 7, 9, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-15", "18:00", 5, null, null, 1, 3, 15, 3, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-15", "20:30", 5, null, null, 1, 3, 8, 4, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-16", "12:00", 5, null, null, 1, 3, 6, 14, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-16", "18:00", 5, null, null, 1, 3, 2, 11, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-16", "18:00", 5, null, null, 1, 3, 12, 10, null);
INSERT INTO t_match (match_date, match_kick_off,matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-16", "20:30", 5, null, null, 1, 3, 5, 13, null);

-- Fecha 6
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-27", "18:15", 6, null, null, 1, 3, 3, 12, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-27", "20:30", 6, null, null, 1, 3, 8, 15, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-28", "18:15", 6, null, null, 1, 3, 13, 15, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-28", "20:30", 6, null, null, 1, 3, 3, 10, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-29", "18:00", 6, null, null, 1, 3, 11, 7, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-30", "12:00", 6, null, null, 1, 3, 9, 6, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-30", "18:00", 6, null, null, 1, 3, 4, 1, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-30", "20:30", 6, null, null, 1, 3, 14, 2, null);

-- Matches Primera B 2025
-- Fecha 1
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-21", "20:30", 1, 2, 2, 2, 4, 20, 24, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-21", "20:30", 1, 0, 0, 2, 4, 30, 32, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-22", "18:00", 1, 3, 0, 2, 4, 25, 22, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-22", "18:00", 1, 2, 2, 2, 4, 21, 29, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-22", "20:30", 1, 0, 0, 2, 4, 27, 19, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-23", "18:00", 1, 1, 1, 2, 4, 17, 31, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-23", "20:30", 1, 0, 3, 2, 4, 23, 26, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-24", "21:00", 1, 1, 2, 2, 4, 28, 18, null);

-- Fecha 2
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-28", "18:00", 2, 2, 1, 2, 4, 29, 24, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-28", "18:00", 2, 3, 2, 2, 4, 22, 23, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-02-28", "20:30", 2, 2, 1, 2, 4, 32, 25, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-01", "18:00", 2, 2, 2, 2, 4, 26, 27, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-01", "20:30", 2, 1, 1, 2, 4, 17, 28, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-02", "18:00", 2, 0, 1, 2, 4, 31, 30, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-02", "18:00", 2, 3, 1, 2, 4, 19, 20, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-02", "20:30", 2, 1, 1, 2, 4, 18, 21, null);

-- Fecha 3
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-07", "18:00", 3, null, null, 1, 4, 25, 28, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-07", "20:30", 3, null, null, 1, 4, 17, 32, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-08", "18:00", 3, null, null, 1, 4, 24, 31, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-08", "18:00", 3, null, null, 1, 4, 21, 26, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-09", "12:00", 3, null, null, 1, 4, 20, 22, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-09", "18:00", 3, null, null, 1, 4, 29, 18, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-09", "20:30", 3, null, null, 1, 4, 27, 23, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-10", "20:00", 3, null, null, 1, 4, 30, 19, null);


-- Matches Segunda 2025
--Fecha 1
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-01", "18:00", 1, 3, 1, 2, 5, 40, 36, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-01", "20:30", 1, 4, 0, 2, 5, 37, 33, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-02", "18:00", 1, 0, 1, 2, 5, 44, 45, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-02", "18:00", 1, 3, 0, 3, 5, 34, 39, "Partido suspendido por ANFP por no pago de garantía de Gral. Velasquez. Se da por ganador a Concón 3-0.");
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-02", "20:30", 1, 2, 1, 2, 5, 43, 35, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-03", "20:30", 1, 1, 1, 2, 5, 38, 41, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES (null, null, null, null, null, 3, 5, 42, 46, "AC Barnechea declinó participar del campeonato Segunda La Liga 2D 2025.");

-- Fecha 2
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-08", "18:00", 2, null, null, 1, 5, 36, 34, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-08", "18:00", 2, null, null, 1, 5, 37, 43, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-08", "20:30", 2, null, null, 1, 5, 35, 42, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-09", "18:00", 2, null, null, 1, 5, 39, 40, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-09", "20:30", 2, null, null, 1, 5, 45, 33, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES ("2025-03-10", "20:30", 2, null, null, 1, 5, 41, 44, null);
INSERT INTO t_match (match_date, match_kick_off, matchday, home_team_goals, away_team_goals, match_status_id, tournament_id, home_team_id, away_team_id, notes)
VALUES (null, null, null, null, null, 3, 5, 46, 38, "AC Barnechea declinó participar del campeonato Segunda La Liga 2D 2025.");