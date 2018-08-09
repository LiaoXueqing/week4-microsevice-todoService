
 CREATE TABLE Task(
    `id` INT PRIMARY KEY auto_increment,
    `content` VARCHAR(255),
    `todo_id` INT,
    foreign key (todo_id) references todo(id)
);