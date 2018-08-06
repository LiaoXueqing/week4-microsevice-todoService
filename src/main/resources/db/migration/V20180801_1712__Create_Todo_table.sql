CREATE table Todo(
    id int primary key auto_increment,
    name VARCHAR(255),
    deleted BOOLEAN default false,
    completed BOOLEAN default false,
    time DATETIME default now(),
    user_id INTEGER
);