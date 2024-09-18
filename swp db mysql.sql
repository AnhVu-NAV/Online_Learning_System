create database SWP391;
use SWP391;
create table Account (
	id int primary key auto_increment, 
    email varchar(255) unique not null, 
    password varchar(32) not null, 
    dob date, 
    role_id int not null,
    created_date date not null, 
    status int not null,
    phone_number varchar(11), 
    gender tinyint,
    first_name VARCHAR(256),
    last_name VARCHAR(256)
);

insert into Account (id, email, dob, first_name, last_name, phone_number, password, role_id, gender, created_date, status)
values (2, 'b@gmail.com', '1995-10-05', 'Hoan', 'Tran', '0949279204', 'hahahaha', 2, 1, '2024-11-05', 0),
(3, 'c@gmail.com', '1995-10-05', 'Hoan', 'Tran', '0949279204', 'hahahaha', 2, 1, '2024-11-05', 0);

select * from Account;
UPDATE Account
SET phone_number = CASE 
    WHEN phone_number = '0912797815' THEN '0949279204'
    ELSE phone_number
END
WHERE id = 1;

UPDATE Account
SET password = CASE 
    WHEN password = 'haha' THEN 'hoho'
    ELSE password
END
WHERE password = 'haha';




use swp391;
create table Slider (
	id int primary key auto_increment,
    url varchar(255),
    author_id int,
    post_id int,
    foreign key (author_id) references Marketing_Member (account_id),
    foreign key (post_id) references Post(id)
);

create table Post (
	id int primary key auto_increment,
    content varchar(256),
    author_id int,
    foreign key (author_id) references Marketing_Member (account_id)
);
SHOW VARIABLES LIKE 'secure_file_priv';
INSERT INTO Slider (image_data)
VALUES (LOAD_FILE('D:/FPTU/Personal Project/Slider/images/001.jpg'));
select * from Slider;

drop table Account