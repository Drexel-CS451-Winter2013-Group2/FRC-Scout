use frcscout;
insert into users (email, password, first_name, last_name) 
values ('test_admin@email.com', MD5('frctest'), 'Test Admin', 'Account'),
('test_scout@email.com', MD5('frctest'), 'Test Scout', 'Account'),
('test_team@email.com', MD5('frctest'), 'Test Teammember', 'Account');

insert into user_roles (email, roles)
values ('test_admin@email.com', 'administrator'), 
('test_admin@email.com', 'scout'), 
('test_admin@email.com', 'team_member'),
('test_scout@email.com', 'scout'),
('test_scout@email.com', 'team_member'),
('test_team@email.com', 'team_member');

insert into `events` (id, name, location, start_date, end_date) 
values (1, 'Hatboro-Horsham Districts', 'Horsham, PA', '2013-03-02', '2013-03-02'),
(2, 'WPI Regional', 'Worcester, MA', '2013-03-07', '2013-03-09'),
(3, 'Chestnut Hill Districts', 'Philadelphia, PA', '2013-03-15', '2013-03-16'),
(4, 'Washington DC Regional', 'Washington, DC', '2013-03-30', '2013-03-30');

insert into `team` (id, name, location)
values (341, 'Miss Daisy', 'Ambler, PA'),
(2590, 'Nemesis', 'Robbinsville, NJ'),
(3123, 'Wildcogs', 'Pottstown, PA');

insert into `match` (match_number, event_id)
values (1, 4),
(2, 4), 
(5, 4);

INSERT INTO match_record_2013 (user, event_id, match_number, team_id,
color, auton_top, auton_middle, auton_bottom, teleop_top, teleop_middle,
teleop_bottom, teleop_pyramid, pyramid_level, play_style, confidence,
ability, fouls, technical_fouls)
VALUES
('test_admin@email.com', 4, 1, 341, 'blue', 
3, 5, 10, 2, 10, 5, 3, 2, 'Offensive', 3, 2, false, false),
('test_scout@email.com', 4, 2, 2590, 'red',
4, 2, 5, 12, 3, 6, 2, 1, 'Defensive', 2, 3, false, false),
('test_scout@email.com', 4, 1, 3123, 'red',
4, 3, 12, 4, 2, 5, 2, 4, 'Defensive', 3, 3, true, false),
('test_admin@email.com', 4, 5, 341, 'blue', 
4, 3, 15, 2, 13, 7, 2, 1, 'Offensive', 3, 2, false, false),
('test_scout@email.com', 4, 5, 2590, 'red',
7, 5, 6, 10, 2, 9, 3, 2, 'Defensive', 2, 3, false, false),
('test_scout@email.com', 4, 5, 3123, 'red',
2, 5, 7, 3, 6, 7, 2, 3, 'Defensive', 3, 3, true, false);


