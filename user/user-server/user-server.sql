use public;
create TABLE user_info
(
    id       bigint PRIMARY KEY,
    username varchar(64) unique,
    password varchar(64)
);