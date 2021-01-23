INSERT INTO users (username, password, enable)
 value ('user', 'pass', true);

INSERT INTO users (username, password, enable)
    value ('admin', 'pass', true);

INSERT INTO authorities (username, authority)
    values('user', 'ROLE_USER');

INSERT INTO authorities (username, authority)
values('admin', 'ROLE_ADMIN');