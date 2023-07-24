CREATE SCHEMA IF NOT EXISTS users;

CREATE TABLE IF NOT EXISTS users.users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS users.role (
    id SERIAL PRIMARY KEY,
    chat_id BIGINT NOT NULL,
    role_description VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS users.users_to_roles (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users.users(id),
    FOREIGN KEY (role_id) REFERENCES users.role(id)
);

INSERT INTO users.users (first_name, last_name, login, password, created_at)
VALUES ('Serhii', 'Diakonov', 'serg_dyak', 'qweqweqwe', '2023-02-28'),
       ('Jane', 'White', 'janny@Wee', 'qweqweqwe', '2023-05-30'),
       ('Nikolai', 'Shabelnik', 'kolya_daa', 'qweqweqwe', '2023-02-28'),
       ('Sir', 'Reginald', 'sir_reginald', 'qweqweqwe', '2023-06-30'),
       ('Manila', '', 'manilaa_mantis', 'qweqweqwe', '2023-06-30'),
       ('Nairobi', 'Diakonov', 'nairobi', 'qweqweqwe', '2023-06-30'),
       ('Sirena', 'Olivero', 'sirena_not_mermaid', 'qweqweqwe', '2023-06-30'),
       ('Berlin', '', 'berlin', 'qweqweqwe', '2023-06-30'),
       ('Bubble', 'Waffle', 'waffle', 'qweqweqwe', '2023-06-30');

INSERT INTO users.role (chat_id, role_description)
VALUES (1, 'CREATOR'),
       (1, 'ADMIN'),
       (1, 'USER'),
       (2, 'CREATOR'),
       (2, 'ADMIN'),
       (2, 'READER'),
       (2, 'USER');

INSERT INTO users.users_to_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (2, 4),
       (1, 5),
       (3, 5),
       (4, 7),
       (5, 7),
       (6, 6),
       (7, 6),
       (8, 6),
       (9, 6),
       (3, 3),
       (4, 3),
       (5, 3),
       (6, 3),
       (7, 3),
       (8, 3),
       (9, 3);