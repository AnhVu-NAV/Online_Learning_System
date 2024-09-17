CREATE DATABASE OnlineLearningSystem
USE OnlineLearningSystem
CREATE TABLE Accounts(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(256),
    first_name VARCHAR(256),
    last_name VARCHAR(256),
    password VARCHAR(256),
    dob DATE,
    gender BIT,
    role_id INT,accounts
    created_date DATE,
    status INT,
    UNIQUE (email),
    phone_number VARCHAR(256) 
);
CREATE TABLE UserLogs(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    account_id INT,
    created_date DATE,
    type_id INT
);
CREATE TABLE Settings(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    setting_type_id INT,
    value VARCHAR(256),
    description VARCHAR(256),
    status INT,
    created_date DATE,
    updated_date DATE
);
CREATE TABLE SettingTypes(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(256)
);
ALTER TABLE userlogs
ADD CONSTRAINT fk_userlog_account
FOREIGN KEY (account_id)
REFERENCES accounts (id);

ALTER TABLE accounts
ADD CONSTRAINT fk_account_setting
FOREIGN KEY (role_id)
REFERENCES settings (id);

ALTER TABLE userlogs
ADD CONSTRAINT fk_userlog_setting
FOREIGN KEY (type_id)
REFERENCES settings (id);

ALTER TABLE settings
ADD CONSTRAINT fk_setting_settingtype
FOREIGN KEY (setting_type_id)
REFERENCES settingtypes (id);

INSERT INTO settingtypes(name)
VALUES 
("account_role"),
("userlog_type");

INSERT INTO settings(setting_type_id,value,description,status,created_date,updated_date)
VALUES 
(1, 'admin', 'Admin role', 1, '2024-09-15', '2024-09-15'),
(1, 'customer', 'Customer role', 1, '2024-09-15', '2024-09-15'),
(1, 'expert', 'Expert role', 1, '2024-09-15', '2024-09-15'),
(1, 'marketing', 'Marketing role', 1, '2024-09-15', '2024-09-15'),
(2, 'login', 'Login event', 1, '2024-09-15', '2024-09-15'),
(2, 'logout', 'Logout event', 1, '2024-09-15', '2024-09-15');

INSERT INTO Accounts (email, first_name, last_name, password, dob, gender, role_id, created_date, status, phone_number)
VALUES ('example@example.com', 'John', 'Doe', 'securepassword', '1990-01-01', 0, 2, '2024-09-16', 1, '123-456-7890');


ALTER TABLE accounts
ADD phone_number VARCHAR(256) ;