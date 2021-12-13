drop table user_roles;
drop table roles;
drop table users;



CREATE TABLE IF NOT EXISTS users
(
    id        INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    age       INT          NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS roles
(
    id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS user_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),

    UNIQUE (user_id, role_id)
)
    ENGINE = InnoDB;

INSERT INTO roles
VALUES (1, 'ROLE_USER');
INSERT INTO roles
VALUES (2, 'ROLE_ADMIN');

INSERT INTO users
VALUES (1, 'Admin', 'Admin', 10, 'Admin', 'Admin');

INSERT INTO user_roles
VALUES (1, 1);
INSERT INTO user_roles
VALUES (1, 2);