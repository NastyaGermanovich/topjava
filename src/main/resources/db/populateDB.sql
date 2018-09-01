ALTER SEQUENCE global_seq RESTART WITH 100000;

DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (description, calories, datetime, user_id) VALUES
('breakfast',650, TIMESTAMP '2015-09-01 09:00:00', 100000),
('dinner',800, TIMESTAMP '2015-09-01 12:40:00', 100000),
('breakfast',550, TIMESTAMP '2015-09-01 08:30:00', 100001),
('dinner',700, TIMESTAMP '2015-09-01 14:00:00', 100001);