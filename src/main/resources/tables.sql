CREATE TABLE user (
    id VARCHAR(40) PRIMARY KEY,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

insert into user(id, username, password, name, email) values( 'id','user1','password1','User-Name','user-email@esd.com');
update user set password = 'pw2' where username='user1';

select * from user;

# drop table user;

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

insert into salary_detail(user_id,description,amount,month,year,salary_slip) value ('b47777a8-2ca9-479f-9cb1-19acaf328bdf','first salary',12345.66,'DEC','2023','dummy_file.pdf');
insert into salary_detail(user_id,description,amount,month,year,salary_slip) value ('b47777a8-2ca9-479f-9cb1-19acaf328bdf','second salary',12300.66,'JAN','2024','dummy_file.pdf');

insert into salary_detail(user_id,description,amount,month,year,salary_slip) value ('4e86ba58-871a-41c5-bca3-2f2b657db219','nov salary',340000,'NOB','2023','dummy_file.pdf');
insert into salary_detail(user_id,description,amount,month,year,salary_slip) value ('4e86ba58-871a-41c5-bca3-2f2b657db219','dec salary',340000,'DEC','2023','dummy_file.pdf');


#alter table salary_detail add column salary_slip VARCHAR(255);

select * from salary_detail;