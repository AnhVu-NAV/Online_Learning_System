create database SWP391;
use SWP391;
create table Account (
	id integer(10) primary key auto_increment, 
    email varchar(256) unique not null, 
    password varchar(256) not null, 
    dob date, 
    role_id int not null,
    created_date date not null, 
    status int not null,
    phone_number varchar(11), 
    gender tinyint,
    first_name VARCHAR(256),
    last_name VARCHAR(256),
    image_url varchar(255) not null 
);

insert into Account (id, email, dob, first_name, last_name, phone_number, password, role_id, gender, created_date, status, image_url)
values (2, 'b@gmail.com', '1995-10-05', 'Hoan', 'Tran', '0949279204', 'hahahaha', 2, 1, '2024-11-05', 0, 'https://'),
(3, 'c@gmail.com', '1995-10-05', 'Hoan', 'Tran', '0949279204', 'hahahaha', 2, 1, '2024-11-05', 0, 'https://');

select * from Account;

use swp391;
create table SettingType (
	id integer(10) primary key auto_increment,
    name varchar(255) not null
);
create table Setting(
	id integer(10) primary key auto_increment,
    setting_type_id integer(10) not null,
    value varchar(255) not null,
    status integer(10) not null,
    description varchar(255),
    created_date date not null,
    updated_date date not null,
    foreign key (setting_type_id) references SettingType(id) 
);
create table UserLog (
	id integer(10) primary key auto_increment,
    created_date date not null,
    type_id integer(10) not null,
    account_id integer(10) not null,
    foreign key (type_id) references Setting(id),
    foreign key (account_id) references Account(id)
);
create table Slider (
	id int primary key auto_increment,
    image_url varchar(255),
    author_id int not null,
    backlink_url varchar(255) not null,
    status integer(10) not null,
    foreign key (author_id) references Account(id)
);
create table Blog (
	id integer(10) primary key auto_increment,
    created_date date not null,
    updated_date date not null,
    author_id int,
    thumbnail_url varchar(255) not null,
    title varchar(255) not null,
    brief_info varchar(255) not null,
    blog_detail varchar(255) not null,
    status integer(10) not null,
    category_id integer(10) not null,
    foreign key (author_id) references Account (id),
    foreign key (category_id) references Setting(id)
);
create table Course (
	id integer(10) primary key auto_increment,
    title varchar(255) not null,
    expert_id integer(10) not null,
    total_duration float(10) not null,
    status integer(10) not null,
    description varchar(255) not null,
    category_id integer(10) not null,
    created_date date not null,
    updated_date date not null,
    thumbnail_url varchar(255) not null,
    number_of_lesson integer(10) not null,
    foreign key (expert_id) references Account (id),
    foreign key (category_id) references Setting(id)
);
create table PricePackage(
	course_id integer(10) not null primary key,
    price_package_id integer(10) primary key auto_increment,
    title varchar(255) not null,
    price float(10) not null,
    sale_price float(10) not null,
    duration integer(10) not null,
    foreign key (course_id) references Course(id)
);
create table PersonalCourse(
	enroll_date date not null,
    expire_date date,
    progress integer(10) not null,
    customer_id integer(10) not null,
    course_id integer(10) not null,
    foreign key (customer_id) references Account(id),
    foreign key (course_id) references Course(id)
);
SHOW VARIABLES LIKE 'secure_file_priv';
INSERT INTO Slider (image_data)
VALUES (LOAD_FILE('D:/FPTU/Personal Project/Slider/images/001.jpg'));
select * from Slider;

drop table Account