show tables;

#drop table user;

CREATE TABLE user (
    id VARCHAR(40) PRIMARY KEY,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO esd.user (id, username, password, name, email, createdAt, updatedAt) VALUES ('4e86ba58-871a-41c5-bca3-2f2b657db219', 'j', 'j', 'j', 'j@gmail.com', '2023-11-24 18:57:08', '2023-11-24 18:57:08');
INSERT INTO esd.user (id, username, password, name, email, createdAt, updatedAt) VALUES ('b47777a8-2ca9-479f-9cb1-19acaf328bdf', 'rocky', 'stone', 'handsome', 'rock@sand.com', '2023-11-22 20:53:22', '2023-11-22 20:53:22');
INSERT INTO esd.user (id, username, password, name, email, createdAt, updatedAt) VALUES ('id', 'user1', 'password1', 'User-Name', 'user-email@esd.com', '2023-11-23 01:03:37', '2023-11-23 01:03:37');

select * from user;


#alter table user drop constraint FKrkujcs2j2ql1ca7orcymb403y;

# To see constraint over a table
select COLUMN_NAME, CONSTRAINT_NAME, REFERENCED_COLUMN_NAME, REFERENCED_TABLE_NAME
from information_schema.KEY_COLUMN_USAGE
where TABLE_NAME = 'user';



#drop table salary_structure;

CREATE TABLE salary_structure (
    user_id VARCHAR(40) PRIMARY KEY , FOREIGN KEY (user_id) REFERENCES user(id),
    description VARCHAR(255) NOT NULL,
    basic_pay float NOT NULL,
    hra_pay float NOT NULL,
    other_allowance float NOT NULL,
    total_salary float NOT NULL,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

select * from salary_structure;

insert into salary_structure(user_id, description, basic_pay, hra_pay, other_allowance, total_salary) value('b47777a8-2ca9-479f-9cb1-19acaf328bdf','First-Job',10000,8000,4000,2200);
insert into salary_structure(user_id, description, basic_pay, hra_pay, other_allowance, total_salary) value('4e86ba58-871a-41c5-bca3-2f2b657db219','Professor',20000,10000,4000,34000);

#drop table salary_detail;

CREATE TABLE salary_detail (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(40), FOREIGN KEY (user_id) REFERENCES user(id),
    description VARCHAR(255) NOT NULL,
    amount float NOT NULL,
    month VARCHAR(10),
    year VARCHAR(4),
    paymentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    salary_slip VARCHAR(255)
);

insert into salary_detail(user_id,description,amount,month,year,salary_slip) value ('b47777a8-2ca9-479f-9cb1-19acaf328bdf','first salary',22000,'DEC','2023','dummy_file.pdf');
insert into salary_detail(user_id,description,amount,month,year,salary_slip) value ('b47777a8-2ca9-479f-9cb1-19acaf328bdf','second salary',22000,'JAN','2024','dummy_file.pdf');

insert into salary_detail(user_id,description,amount,month,year,salary_slip) value ('4e86ba58-871a-41c5-bca3-2f2b657db219','nov salary',34000,'NOV','2023','dummy_file.pdf');
insert into salary_detail(user_id,description,amount,month,year,salary_slip) value ('4e86ba58-871a-41c5-bca3-2f2b657db219','dec salary',34000,'DEC','2023','dummy_file.pdf');

#alter table salary_detail add column salary_slip VARCHAR(255);
select * from salary_detail;

select u1_0.id,u1_0.createdAt,u1_0.email,u1_0.name,u1_0.password,s1_0.user_id,s1_0.basic_pay,s1_0.description,s1_0.hra_pay,s1_0.other_allowance,s1_0.total_salary,s1_0.updatedAt,u1_0.updatedAt,u1_0.username from user u1_0 left join salary_structure s1_0 on u1_0.id=s1_0.user_id where u1_0.id='b47777a8-2ca9-479f-9cb1-19acaf328bdf';