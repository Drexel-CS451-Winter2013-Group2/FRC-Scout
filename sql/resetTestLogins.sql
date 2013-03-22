delete from user_roles;
delete from users;
insert into users (email, password, first_name, last_name) values ('admin', MD5('admin'), 'admin', 'admin');
insert into user_roles values ('admin', 'administrator'), ('admin', 'team_member'), ('admin', 'scout');
insert into users (email, password, first_name, last_name)  values ('scout', MD5('scout'), 'scout', 'scout');
insert into user_roles values ('scout', 'team_member'), ('scout', 'scout');
insert into users (email, password, first_name, last_name)  values ('team', MD5('team'), 'team', 'team');
insert into user_roles values ('team', 'team_member');
