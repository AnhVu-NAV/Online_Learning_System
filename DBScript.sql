-- create database
create database learnik;
-- use table;
use learnik;
-- Table SettingType
CREATE TABLE SettingType (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);
-- setting type bao gom user role (admin, customer, marketing, expert, sale), course category, lesson type (quiz, learning material), userlog type (login, logout),question level (easy, hard, medium), question type (multiple choice, short answer)
CREATE TABLE Setting (
    id INT AUTO_INCREMENT PRIMARY KEY,
    setting_type_id INT,
    value VARCHAR(255),
    status INT, -- 0 is deactivated, 1 is deactivated
    description VARCHAR(255) NULL,
    created_date DATE,
    updated_date DATE,
    foreign key (setting_type_id) references SettingType(id)
);
CREATE TABLE User (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    primary_email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    role_id INT(10),
    created_date DATE,
    status INT(10),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    dob DATE,
    gender TINYINT(1),
    first_phone VARCHAR(255) NULL,
    second_phone VARCHAR(255) NULL,
    secondary_email VARCHAR(255) NULL,
    image_url VARCHAR(255),
    prefer_contact VARCHAR(255),
    foreign key (role_id) references Setting(id)
);
CREATE TABLE Userlog (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    created_date DATE,
    type_id INT(10),
    user_id INT(10),
    foreign key (type_id) references Setting(id),
    foreign key (user_id) references User(id)
);
CREATE TABLE Tagline (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE Blog (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    thumbnail_url VARCHAR(255),
    title VARCHAR(255),
    content TEXT,
    brief_info VARCHAR(255),
    author_id INT(10),
    status INT(10), -- 0 is deactivated, 1 is activated
    created_date DATE,
    updated_date DATE,
    foreign key (author_id) references User(id)
);
CREATE TABLE Blog_Tagline (
    tagline_id INT(10),
    blog_id INT(10),
    PRIMARY KEY (tagline_id, blog_id),
    FOREIGN KEY (tagline_id) REFERENCES Tagline(id),
    FOREIGN KEY (blog_id) REFERENCES Blog(id)
);
CREATE TABLE Slider (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    author_id INT(10),
    image_url VARCHAR(255),
    back_link_url VARCHAR(255),
    status INT(10),
    FOREIGN KEY (author_id) REFERENCES User(id)
);
CREATE TABLE Course (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    subtitle VARCHAR(255),
    expert_id INT(10),
    total_duration INT(10),
    category_id INT(10),
    description VARCHAR(255),
    thumbnail_url VARCHAR(255),
    status INT(10),
    updated_date DATE,
    created_date DATE,
    number_of_learner INT(10),
    foreign key (category_id) references Setting(id),
    foreign key (expert_id) references User(id)
);
CREATE TABLE PricePackage (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    course_id INT(10),
    title VARCHAR(255),
    price INT(10),
    sale_price INT(10) null,
    start_date DATE null,
    end_date DATE null,
    FOREIGN KEY (course_id) REFERENCES Course(id)
);
CREATE TABLE PersonalCourse (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    customer_id INT(10),
    course_id INT(10),
    enroll_date DATE,
    expire_date DATE null,
    progress INT(10),
    status INT(10), -- -1-cancel, 0-submitted, 1-learning, 2-expired, 3-finished
    price_package_id INT(10),
    FOREIGN KEY (customer_id) REFERENCES User(id),
    FOREIGN KEY (course_id) REFERENCES Course(id),
    FOREIGN KEY (price_package_id) REFERENCES PricePackage(id)
);
CREATE TABLE Chapter (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    `order` INT(10),
	title VARCHAR(255),
    subtitle VARCHAR(255) null,
    brief_information VARCHAR(255) null,
    description VARCHAR(255) null,
    course_id INT(10),
    status INT(10),
    duration INT(10),
    FOREIGN KEY (course_id) REFERENCES Course(id)
);
CREATE TABLE PersonalChapter (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    personal_course_id INT(10),
    chapter_id INT(10),
    is_done TINYINT(1),
    FOREIGN KEY (personal_course_id) REFERENCES PersonalCourse(id),
    FOREIGN KEY (chapter_id) REFERENCES Chapter(id)
);
CREATE TABLE Lesson (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    status INT(10), -- 0-deactivated, 1-activated
    lesson_type_id INT(10),
    title VARCHAR(255),
    chapter_id INT(10),
    `order` INT(10),
    FOREIGN KEY (lesson_type_id) REFERENCES Setting(id),
    FOREIGN KEY (chapter_id) REFERENCES Chapter(id)
);
CREATE TABLE PersonalLesson (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    personal_chapter_id INT(10),
    lesson_id INT(10),
    is_done TINYINT(1),
    FOREIGN KEY (personal_chapter_id) REFERENCES PersonalChapter(id),
    FOREIGN KEY (lesson_id) REFERENCES Lesson(id)
);
CREATE TABLE LearningMaterial (
    lesson_id INT(10) PRIMARY KEY,
    upload_date DATE,
    duration INT(10),
    FOREIGN KEY (lesson_id) REFERENCES Lesson(id)
);
CREATE TABLE Quiz (
    lesson_id INT(10) PRIMARY KEY,
    duration INT(10),
    pass_rate FLOAT(10, 2),    
    updated_date DATE,
    description VARCHAR(255) null,        
    subtitle VARCHAR(255) null, 
    FOREIGN KEY (lesson_id) REFERENCES Lesson(id)
);
CREATE TABLE PersonalQuiz (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    personal_lesson_id INT(10),
    quiz_id INT(10),
    is_passed TINYINT(1),
    score INT(10),
    FOREIGN KEY (personal_lesson_id) REFERENCES PersonalLesson(id),
    FOREIGN KEY (quiz_id) REFERENCES Quiz(lesson_id)
);
CREATE TABLE VideoContent (
    lesson_id INT(10) PRIMARY KEY,
    video_url VARCHAR(255),
    video_summary VARCHAR(255),
    FOREIGN KEY (lesson_id) REFERENCES LearningMaterial(lesson_id)
);
CREATE TABLE TextContent (
    lesson_id INT(10),
    text_content TEXT,
    FOREIGN KEY (lesson_id) REFERENCES LearningMaterial(lesson_id)
);
CREATE TABLE Question (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT,
    level_id INT,
    content VARCHAR(255),
    question_type_id INT,
    FOREIGN KEY (quiz_id) REFERENCES Quiz(lesson_id),
    FOREIGN KEY (level_id) REFERENCES Setting(id),
    FOREIGN KEY (question_type_id) REFERENCES Setting(id)
);
CREATE TABLE `Option` (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    question_id INT,
    isTrue TINYINT(1),
    explaination VARCHAR(255) null,
    FOREIGN KEY (question_id) REFERENCES Question(id)
);
CREATE TABLE PersonalQuestion (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    personal_quiz_id INT(10),
    question_id INT(10),
    is_answered TINYINT(1),
    is_marked TINYINT(1),
    FOREIGN KEY (personal_quiz_id) REFERENCES PersonalQuiz(id),
    FOREIGN KEY (question_id) REFERENCES Question(id)
);
CREATE TABLE PersonalOption (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    personal_question_id INT(10),
    option_id INT,
    is_choosed TINYINT(1),
    FOREIGN KEY (personal_question_id) REFERENCES PersonalQuestion(id),
    FOREIGN KEY (option_id) REFERENCES `Option`(id)
);
CREATE TABLE PersonalAnswer (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    personal_question_id INT(10),
    answer VARCHAR(255),
    is_true TINYINT(1),
    FOREIGN KEY (personal_question_id) REFERENCES PersonalQuestion(id)
);
CREATE TABLE BlogCourse (
	blog_id int(10),
    course_id int(10),
    primary key (blog_id, course_id),
    FOREIGN KEY (blog_id) REFERENCES Blog(id),
    FOREIGN KEY (course_id) REFERENCES Course(id)
);
