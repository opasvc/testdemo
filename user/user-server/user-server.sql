use public;
create TABLE user_server
(
    id       bigint PRIMARY KEY,
    username varchar(64) unique,
    password varchar(64)
);