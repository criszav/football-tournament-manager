-- Goal Type
INSERT INTO t_goal_type (type_name, description, is_active) VALUES ("Normal", "Gol de jugada", true);
INSERT INTO t_goal_type (type_name, description, is_active) VALUES ("Own goal", "Autogol", true);
INSERT INTO t_goal_type (type_name, description, is_active) VALUES ("Penalty", "Gol de penal", true);
INSERT INTO t_goal_type (type_name, description, is_active) VALUES ("Free kick", "Gol de tiro libre", true);


-- Goals
INSERT INTO t_goal (goal_minute, goal_type_id, match_id, player_id, team_id, tournament_id) VALUES (53, 1, 3, 25, 4, 1);
INSERT INTO t_goal (goal_minute, goal_type_id, match_id, player_id, team_id, tournament_id) VALUES (57, 4, 3, 29, 4, 1);
INSERT INTO t_goal (goal_minute, goal_type_id, match_id, player_id, team_id, tournament_id) VALUES (70, 3, 3, 32, 4, 1);
