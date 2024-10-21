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
    address VARCHAR(255),	
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
    category_id INT(10),
    foreign key (author_id) references User(id),
    foreign key (category_id) references Setting(id)
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
CREATE TABLE Course_Tagline (
    tagline_id INT(10),
    course_id INT(10),
    PRIMARY KEY (tagline_id, course_id),
    FOREIGN KEY (tagline_id) REFERENCES Tagline(id),
    FOREIGN KEY (course_id) REFERENCES Course(id)
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
CREATE TABLE SaleNoteVisualContent (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255),
    type TINYINT(1) -- 0 is image, 1 is video
);

CREATE TABLE SaleNote (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    text_content VARCHAR(255),
    created_date DATE,
    update_date DATE
);

CREATE TABLE SaleNoteVisualContent_SaleNote (
    sale_note_id INT(10),
    sale_note_visual_content_id INT(10),
    status INT(10), -- 0 is deactiavted, 1 is activated
    PRIMARY KEY (sale_note_id, sale_note_visual_content_id),
    FOREIGN KEY (sale_note_id) REFERENCES SaleNote(id) ON DELETE CASCADE,
    FOREIGN KEY (sale_note_visual_content_id) REFERENCES SaleNoteVisualContent(id) ON DELETE CASCADE
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
    sale_note_id INT(10) null,
    FOREIGN KEY (customer_id) REFERENCES User(id),
    FOREIGN KEY (course_id) REFERENCES Course(id),
    FOREIGN KEY (price_package_id) REFERENCES PricePackage(id),
    FOREIGN KEY (sale_note_id) REFERENCES SaleNote(id)
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
    title VARCHAR(255),
    upload_date DATE,
    duration INT(10),
    FOREIGN KEY (lesson_id) REFERENCES Lesson(id)
);
CREATE TABLE Quiz (
    lesson_id INT(10) PRIMARY KEY,
    title VARCHAR(255),
    duration INT(10),
    pass_rate FLOAT(10, 2),    
    updated_date DATE,
    description VARCHAR(255) null,        
    subtitle VARCHAR(255) null,
    markable_by_ai TINYINT(1) null,
    markable_by_expert TINYINT(1) null,  
    FOREIGN KEY (lesson_id) REFERENCES Lesson(id)
);
CREATE TABLE PersonalQuiz (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    personal_lesson_id INT(10),
    quiz_id INT(10),
    is_passed TINYINT(1),
    taken_date date,
    score INT(10),
    FOREIGN KEY (personal_lesson_id) REFERENCES PersonalLesson(id),
    FOREIGN KEY (quiz_id) REFERENCES Quiz(lesson_id)
);
CREATE TABLE VideoContent (
    lesson_id INT(10) PRIMARY KEY,
    video_url VARCHAR(255),
    video_summary VARCHAR(255),
    description VARCHAR(255),
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
    status INT, -- 0 is deactivated, 1 is deactivated
    content VARCHAR(255),
    question_type_id INT,
    hint VARCHAR(255) null,
    FOREIGN KEY (quiz_id) REFERENCES Quiz(lesson_id),
    FOREIGN KEY (level_id) REFERENCES Setting(id),
    FOREIGN KEY (question_type_id) REFERENCES Setting(id)
);
CREATE TABLE `Option` (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    question_id INT,
	status INT, -- 0 is deactivated, 1 is deactivated
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
    video_content VARCHAR(255) null,
    img_conten VARCHAR(255) null,
    FOREIGN KEY (personal_question_id) REFERENCES PersonalQuestion(id)
);
CREATE TABLE BlogCourse (
	blog_id int(10),
    course_id int(10),
    primary key (blog_id, course_id),
    FOREIGN KEY (blog_id) REFERENCES Blog(id),
    FOREIGN KEY (course_id) REFERENCES Course(id)
);
CREATE TABLE CourseVisualContent (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255),
    type TINYINT(1) -- 0 is image, 1 is video
);
CREATE TABLE CourseVisualContent_Course (
    course_visual_content_id INT(10),
    course_id INT(10),
    status INT(10), -- 0 is deactive, 1 is active
    PRIMARY KEY (course_visual_content_id, course_id),
    FOREIGN KEY (course_visual_content_id) REFERENCES CourseVisualContent(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES Course(id) ON DELETE CASCADE
);

INSERT INTO SettingType (name) 
VALUES 
('User Role'),
('Course Category'),
('Lesson Type'),
('UserLog Type'),
('Question Level'),
('Question Type');

INSERT INTO Setting (setting_type_id, value, status, description, created_date, updated_date) 
VALUES 
(1, 'Admin', 1, 'Admin role', '2023-01-01', '2023-01-01'),
(1, 'Customer', 1, 'Customer role', '2023-01-01', '2023-01-01'),
(1, 'Marketing', 1, 'Marketing role', '2023-01-01', '2023-01-01'),
(1, 'Expert', 1, 'Expert role', '2023-01-01', '2023-01-01'),
(1, 'Sale', 1, 'Sale role', '2023-01-01', '2023-01-01'),
(2, 'Technology', 1, 'Courses about technology', '2023-01-01', '2023-01-01'),
(2, 'Language', 1, 'Courses about language', '2023-01-01', '2023-01-01'),
(2, 'Business', 1, 'Courses about business', '2023-01-01', '2023-01-01'),
(2, 'Health', 1, 'Health and fitness courses', '2023-01-01', '2023-01-01'),
(2, 'Art', 1, 'Art-related courses', '2023-01-01', '2023-01-01'),
(3, 'Quiz', 1, 'Quiz type lessons', '2023-01-01', '2023-01-01'),
(3, 'Learning Material', 1, 'Learning materials type lessons', '2023-01-01', '2023-01-01'),
(4, 'Login', 1, 'User login event', '2023-01-01', '2023-01-01'),
(4, 'Logout', 1, 'User logout event', '2023-01-01', '2023-01-01'),
(5, 'Easy', 1, 'Easy level questions', '2023-01-01', '2023-01-01'),
(5, 'Medium', 1, 'Medium level questions', '2023-01-01', '2023-01-01'),
(5, 'Hard', 1, 'Hard level questions', '2023-01-01', '2023-01-01'),
(6, 'Multiple Choice', 1, 'Multiple choice questions', '2023-01-01', '2023-01-01'),
(6, 'Short Answer', 1, 'Short answer questions', '2023-01-01', '2023-01-01');


INSERT INTO User (primary_email, password, role_id, created_date, status, first_name, last_name, dob, gender, first_phone, image_url, prefer_contact) 
VALUES 
('admin1@example.com', 'password123', 1, '2023-01-01', 1, 'John', 'Doe', '1990-01-01', 1, '0123456789', 'admin_image.jpg', 'admin1@example.com'),
('admin2@example.com', 'password123', 1, '2023-01-02', 1, 'Jane', 'Smith', '1991-02-01', 0, '0987654321', 'admin_image2.jpg', '0987654321'),
('customer1@example.com', 'password123', 2, '2023-01-03', 1, 'Alice', 'Johnson', '1992-03-01', 0, '0978123456', 'customer_image1.jpg', '0978123456'),
('customer2@example.com', 'password123', 2, '2023-01-04', 1, 'Bob', 'Williams', '1993-04-01', 1, '0912345678', 'customer_image2.jpg', 'customer2@example.com'),
('expertit@example.com', 'password123', 4, '2023-01-05', 1, 'Charlie', 'Brown', '1994-05-01', 1, '0911222333', 'expertit_image.jpg', '0911222333'),
('experteng@example.com', 'password123', 4, '2023-01-05', 1, 'Louis', 'Nguyen', '1994-05-01', 1, '0911222333', 'experteng_image.jpg', '0911222333'),
('expert1@example.com', 'password123', 4, '2023-01-05', 1, 'Charlie', 'Green', '1994-05-01', 1, '0911222333', 'expert_image1.jpg', '0911222333'),
('expert2@example.com', 'password123', 4, '2023-01-05', 1, 'Throm', 'Brown', '1994-05-01', 1, '0911222333', 'expert_image2.jpg', '0911222333'),
('marketing1@example.com', 'password123', 3, '2023-01-06', 1, 'Emily', 'Davis', '1995-06-01', 0, '0933444555', 'marketing_image1.jpg', 'marketing1@example.com'),
('sale1@example.com', 'password123', 5, '2023-01-07', 1, 'Daniel', 'Garcia', '1996-07-01', 1, '0955666777', 'sale_image1.jpg', '0955666777');


INSERT INTO Course (title, subtitle, expert_id, total_duration, category_id, description, thumbnail_url, status, created_date, updated_date, number_of_learner) 
VALUES 
('Java Programming', 'Learn Java', 5, 20, 6, 'Java course for beginners', 'java_thumbnail.jpg', 1, '2023-01-01', '2023-01-01', 100),
('Python Programming', 'Learn Python', 5, 15, 6, 'Python course for data analysis', 'python_thumbnail.jpg', 1, '2023-01-02', '2023-01-02', 80),
('Web Development', 'Full stack web development', 5, 25, 6, 'Become a web developer', 'webdev_thumbnail.jpg', 1, '2023-01-03', '2023-01-03', 150),
('Digital Marketing', 'Marketing in the digital age', 5, 10, 6, 'Master digital marketing', 'digital_marketing_thumbnail.jpg', 1, '2023-01-04', '2023-01-04', 120),
('Business Management', 'Manage your business', 5, 30, 8, 'Learn business management', 'business_management_thumbnail.jpg', 1, '2023-01-05', '2023-01-05', 50),
('Graphic Design', 'Design like a pro', 5, 18, 10, 'Learn graphic design skills', 'graphic_design_thumbnail.jpg', 1, '2023-01-06', '2023-01-06', 90),
('Photography', 'Master the camera', 5, 12, 10, 'Learn photography basics', 'photography_thumbnail.jpg', 1, '2023-01-07', '2023-01-07', 110),
('English for Beginners', 'Learn basic English', 6, 30, 7, 'This course is designed for beginners who want to learn English from scratch.', 'english_beginners.jpg', 1, '2024-01-10', '2024-01-01', 200),
('English Intermediate', 'Improve your English skills', 6, 45, 7, 'This course is for learners who have basic knowledge and want to improve their English skills.', 'english_intermediate.jpg', 1, '2024-01-10', '2024-01-01', 150),
('English Advanced', 'Master advanced English', 6, 60, 7, 'This course is for advanced learners who want to master English at a professional level.', 'english_advanced.jpg', 1, '2024-01-10', '2024-01-01', 100),
('English Grammar Basics', 'Learn basic grammar', 6, 25, 7, 'This course covers the basics of English grammar for beginners.', 'grammar_basics.jpg', 1, '2024-01-10', '2024-01-01', 180),
('English Vocabulary Building', 'Expand your vocabulary', 6, 35, 7, 'This course helps learners expand their English vocabulary with common and useful words.', 'vocabulary_building.jpg', 1, '2024-01-10', '2024-01-01', 220),
('English Conversation Practice', 'Practice speaking English', 6, 40, 7, 'This course focuses on improving English speaking and conversation skills.', 'conversation_practice.jpg', 1, '2024-01-10', '2024-01-01', 160),
('Business English', 'Learn English for business', 6, 50, 7, 'This course is designed for professionals who want to improve their English for business communication.', 'business_english.jpg', 1, '2024-01-10', '2024-01-01', 130),
('English Pronunciation', 'Improve pronunciation', 6, 20, 7, 'This course helps learners improve their English pronunciation.', 'pronunciation.jpg', 1, '2024-01-10', '2024-01-01', 140),
('English for Travel', 'Learn English for traveling', 6, 30, 7, 'This course focuses on English phrases and vocabulary for traveling.', 'english_travel.jpg', 1, '2024-01-10', '2024-01-01', 180),
('English for Work', 'Improve your workplace English', 6, 40, 7, 'This course is designed for improving English communication in the workplace.', 'english_work.jpg', 1, '2024-01-10', '2024-01-01', 150),
('TOEFL Preparation', 'Prepare for the TOEFL exam', 6, 60, 7, 'This course helps learners prepare for the TOEFL exam.', 'toefl_preparation.jpg', 1, '2024-01-10', '2024-01-01', 100),
('IELTS Preparation', 'Get ready for the IELTS test', 6, 60, 7, 'This course prepares learners for the IELTS exam.', 'ielts_preparation.jpg', 1, '2024-01-10', '2024-01-01', 250),
('TOEIC Preparation', 'Master the TOEIC exam', 6, 50, 7, 'This course is designed for students preparing for the TOEIC exam.', 'toeic_preparation.jpg', 1, '2024-01-10', '2024-01-01', 230),
('English Listening Skills', 'Improve your listening skills', 6, 20, 7, 'This course focuses on improving English listening skills.', 'listening_skills.jpg', 1, '2024-01-10', '2024-01-01', 190),
('English Writing Skills', 'Master English writing', 6, 35, 7, 'This course helps learners improve their writing skills in English.', 'writing_skills.jpg', 1, '2024-01-10', '2024-01-01', 200),
('Conversational English for Beginners', 'Learn English through conversations', 6, 25, 7, 'This course focuses on building conversational skills for English beginners.', 'conversation_beginners.jpg', 1, '2024-01-10', '2024-01-01', 220),
('Advanced Business English', 'Enhance your business communication', 6, 45, 7, 'This course is for advanced learners aiming to improve their business communication skills.', 'advanced_business_english.jpg', 1, '2024-01-10', '2024-01-01', 240),
('English for Public Speaking', 'Improve your public speaking skills', 6, 30, 7, 'This course is designed to help learners improve their public speaking in English.', 'public_speaking.jpg', 1, '2024-01-10', '2024-01-01', 160),
('English for Hospitality', 'Learn English for the hospitality industry', 6, 40, 7, 'This course focuses on English for the hospitality and tourism industry.', 'hospitality_english.jpg', 1, '2024-01-10', '2024-01-01', 180),
('English for Healthcare', 'Communicate effectively in healthcare settings', 6, 50, 7, 'This course focuses on English communication for healthcare professionals.', 'healthcare_english.jpg', 1, '2024-01-10', '2024-01-01', 150);

INSERT INTO PricePackage (course_id, title, price, sale_price, start_date, end_date) 
VALUES 
(1, 'Basic Package', 100, 80, '2023-01-01', '2023-06-30'),
(1, 'Premium Package', 200, 160, '2023-01-01', '2023-12-31'),
(2, 'Basic Package', 120, 100, '2023-01-02', '2023-07-30'),
(2, 'Premium Package', 250, 200, '2023-01-02', '2023-12-31'),
(3, 'Standard Package', 300, 250, '2023-01-03', '2023-09-30'),
(3, 'Premium Package', 400, 350, '2023-01-03', '2023-12-31'),
(4, 'Basic Package', 150, 120, '2023-01-04', '2023-06-30'),
(5, 'Premium Package', 500, 450, '2023-01-05', '2023-12-31'),
(6, 'Standard Package', 1500, 1200, '2024-01-01', '2024-03-31'),
(6, 'Premium Package', 2500, 2000, '2024-01-01', '2024-06-30'),
(7, 'Standard Package', 1800, 1500, '2024-01-01', '2024-03-31'),
(7, 'Premium Package', 2800, 2300, '2024-01-01', '2024-06-30'),
(8, 'Standard Package', 2000, 1700, '2024-01-01', '2024-03-31'),
(8, 'Premium Package', 3000, 2500, '2024-01-01', '2024-06-30'),
(9, 'Basic Package', 1000, 800, '2024-01-01', '2024-03-31'),
(9, 'Premium Package', 2000, 1600, '2024-01-01', '2024-06-30'),
(10, 'Standard Package', 1200, 1000, '2024-01-01', '2024-03-31'),
(10, 'Premium Package', 2200, 1800, '2024-01-01', '2024-06-30'),
(11, 'Standard Package', 1300, 1100, '2024-01-01', '2024-03-31'),
(11, 'Premium Package', 2300, 1900, '2024-01-01', '2024-06-30'),
(12, 'Standard Package', 1700, 1400, '2024-01-01', '2024-03-31'),
(12, 'Premium Package', 2700, 2300, '2024-01-01', '2024-06-30'),
(13, 'Standard Package', 1100, 900, '2024-01-01', '2024-03-31'),
(13, 'Premium Package', 2100, 1700, '2024-01-01', '2024-06-30'),
(14, 'Basic Package', 1300, 1000, '2024-01-01', '2024-03-31'),
(14, 'Premium Package', 2300, 1900, '2024-01-01', '2024-06-30'),
(15, 'Basic Package', 1400, 1100, '2024-01-01', '2024-03-31'),
(15, 'Premium Package', 2400, 2000, '2024-01-01', '2024-06-30'),
(16, 'Basic Package', 1600, 1300, '2024-01-01', '2024-03-31'),
(16, 'Premium Package', 2600, 2200, '2024-01-01', '2024-06-30'),
(17, 'Basic Package', 1800, 1500, '2024-01-01', '2024-03-31'),
(17, 'Premium Package', 2800, 2400, '2024-01-01', '2024-06-30'),
(18, 'Basic Package', 1700, 1400, '2024-01-01', '2024-03-31'),
(18, 'Premium Package', 2700, 2300, '2024-01-01', '2024-06-30'),
(19, 'Basic Package', 1200, 1000, '2024-01-01', '2024-03-31'),
(19, 'Premium Package', 2200, 1800, '2024-01-01', '2024-06-30'),
(20, 'Basic Package', 1400, 1100, '2024-01-01', '2024-03-31'),
(20, 'Premium Package', 2400, 2000, '2024-01-01', '2024-06-30'),
(21, 'Basic Package', 1100, 900, '2024-01-01', '2024-03-31'),
(21, 'Premium Package', 2100, 1700, '2024-01-01', '2024-06-30'),
(22, 'Basic Package', 1600, 1300, '2024-01-01', '2024-03-31'),
(22, 'Premium Package', 2600, 2200, '2024-01-01', '2024-06-30'),
(23, 'Basic Package', 1300, 1000, '2024-01-01', '2024-03-31'),
(23, 'Premium Package', 2300, 1900, '2024-01-01', '2024-06-30'),
(24, 'Basic Package', 1500, 1200, '2024-01-01', '2024-03-31'),
(24, 'Premium Package', 2500, 2100, '2024-01-01', '2024-06-30'),
(25, 'Basic Package', 1700, 1400, '2024-01-01', '2024-03-31'),
(25, 'Premium Package', 2700, 2300, '2024-01-01', '2024-06-30');


-- Java Programming (Course 1)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Java Basics - Part 1', 'Introduction to Java Programming', 'Getting started with Java', 'Learn the fundamentals of Java programming', 1, 1, 60),
(2, 'Java Basics - Part 2', 'Java Variables and Data Types', 'Working with variables', 'Learn how to declare and use variables in Java', 1, 1, 90),
(3, 'Java Advanced - Part 1', 'Object-Oriented Programming in Java', 'Introduction to OOP concepts', 'Learn about classes, objects, inheritance, and polymorphism in Java', 1, 1, 120);

-- Python Programming (Course 2)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Python Basics - Part 1', 'Introduction to Python Programming', 'Getting started with Python', 'Learn the basics of Python syntax and data types', 2, 1, 60),
(2, 'Python Basics - Part 2', 'Control Flow in Python', 'Working with loops and conditions', 'Learn about loops, if-else conditions, and functions in Python', 2, 1, 90),
(3, 'Python Advanced - Part 1', 'Advanced Python Functions', 'Understanding decorators and generators', 'Explore advanced Python concepts like decorators and generators', 2, 1, 120);

-- Web Development (Course 3)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Web Development Basics - Part 1', 'Introduction to HTML and CSS', 'Learn how to build web pages', 'An introduction to creating web pages using HTML and CSS', 3, 1, 90),
(2, 'Web Development Basics - Part 2', 'Responsive Web Design', 'Making web pages responsive', 'Learn how to make your web pages responsive using CSS and media queries', 3, 1, 120),
(3, 'Web Development Advanced - Part 1', 'JavaScript for Web Development', 'Introduction to JavaScript', 'Learn how to add interactivity to web pages using JavaScript', 3, 1, 150);

-- Digital Marketing (Course 4)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Digital Marketing Basics - Part 1', 'Introduction to Digital Marketing', 'Understanding the basics', 'Learn the fundamentals of digital marketing strategies', 4, 1, 60),
(2, 'Digital Marketing Basics - Part 2', 'SEO and SEM', 'Improving website visibility', 'Learn how to improve website visibility using SEO and SEM techniques', 4, 1, 90),
(3, 'Digital Marketing Advanced - Part 1', 'Social Media Marketing', 'Leveraging social media platforms', 'Learn how to use social media platforms effectively for marketing', 4, 1, 120);

-- Business Management (Course 5)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Business Management Basics - Part 1', 'Introduction to Business Management', 'Understanding management concepts', 'Learn the key concepts of business management', 5, 1, 60),
(2, 'Business Management Basics - Part 2', 'Team Leadership', 'Managing teams effectively', 'Learn how to lead and manage teams within a business environment', 5, 1, 90),
(3, 'Business Management Advanced - Part 1', 'Strategic Management', 'Developing business strategies', 'Learn how to develop and implement business strategies', 5, 1, 120);

-- Graphic Design (Course 6)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Graphic Design Basics - Part 1', 'Introduction to Graphic Design', 'Understanding the design process', 'Learn the fundamentals of graphic design, including typography and color theory', 6, 1, 60),
(2, 'Graphic Design Basics - Part 2', 'Design Software', 'Working with design tools', 'Learn how to use graphic design software like Photoshop and Illustrator', 6, 1, 90),
(3, 'Graphic Design Advanced - Part 1', 'Design for Print', 'Preparing designs for print media', 'Learn how to design print media, including brochures, posters, and business cards', 6, 1, 120);

-- Photography (Course 7)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Photography Basics - Part 1', 'Introduction to Photography', 'Understanding camera settings', 'Learn how to use a camera to take professional-quality photos', 7, 1, 60),
(2, 'Photography Basics - Part 2', 'Lighting Techniques', 'Working with natural and artificial light', 'Learn how to use different lighting techniques to enhance your photos', 7, 1, 90),
(3, 'Photography Advanced - Part 1', 'Photo Editing', 'Using editing software', 'Learn how to edit photos using software like Lightroom and Photoshop', 7, 1, 120);

-- English for Beginners (Course 8)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'English for Beginners - Part 1', 'Introduction to English', 'Learning basic English words', 'This chapter introduces common English words and phrases', 8, 1, 60),
(2, 'English for Beginners - Part 2', 'Basic Grammar', 'Understanding simple grammar rules', 'Learn the basics of English grammar for beginners', 8, 1, 90),
(3, 'English for Beginners - Part 3', 'Conversational English', 'Practicing basic conversations', 'Learn how to carry out simple conversations in English', 8, 1, 120);

-- English Intermediate (Course 9)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'English Intermediate - Part 1', 'Improving Vocabulary', 'Expanding your English vocabulary', 'Learn new vocabulary to enhance your English skills', 9, 1, 60),
(2, 'English Intermediate - Part 2', 'Intermediate Grammar', 'Mastering grammar concepts', 'Learn more advanced grammar concepts and sentence structures', 9, 1, 90),
(3, 'English Intermediate - Part 3', 'Conversational Practice', 'Enhancing conversation skills', 'Practice conversations in various real-life scenarios', 9, 1, 120);

-- English Advanced (Course 10)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'English Advanced - Part 1', 'Advanced Vocabulary', 'Learning complex English words', 'This chapter covers more advanced English vocabulary and phrases', 10, 1, 90),
(2, 'English Advanced - Part 2', 'Advanced Grammar', 'Understanding complex grammar rules', 'This chapter explains complex grammar rules for advanced learners', 10, 1, 120),
(3, 'English Advanced - Part 3', 'Advanced Conversational Skills', 'Practicing advanced conversations', 'Learn how to speak fluently in more complex conversations', 10, 1, 150);

-- English Grammar Basics (Course 11)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'English Grammar Basics - Part 1', 'Introduction to Grammar', 'Understanding basic grammar rules', 'Learn the basics of English grammar, including nouns, verbs, and adjectives', 11, 1, 60),
(2, 'English Grammar Basics - Part 2', 'Sentence Structure', 'Building sentences with correct grammar', 'Learn how to structure simple sentences with proper grammar', 11, 1, 90),
(3, 'English Grammar Basics - Part 3', 'Tenses and Verbs', 'Understanding English tenses and verb forms', 'Learn the different tenses and how to use verbs correctly in sentences', 11, 1, 120);

-- English Vocabulary Building (Course 12)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Vocabulary Building - Part 1', 'Basic Vocabulary', 'Learning common words', 'Learn essential words for everyday conversations', 12, 1, 60),
(2, 'Vocabulary Building - Part 2', 'Intermediate Vocabulary', 'Expanding your vocabulary', 'Learn more advanced words for various situations', 12, 1, 90),
(3, 'Vocabulary Building - Part 3', 'Advanced Vocabulary', 'Mastering complex words', 'Learn advanced vocabulary to improve fluency and comprehension', 12, 1, 120);

-- English Conversation Practice (Course 13)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Conversation Practice - Part 1', 'Basic Conversations', 'Practicing simple dialogues', 'Learn how to carry out simple conversations in everyday scenarios', 13, 1, 60),
(2, 'Conversation Practice - Part 2', 'Intermediate Conversations', 'Improving conversational skills', 'Practice conversations in real-life settings with expanded vocabulary', 13, 1, 90),
(3, 'Conversation Practice - Part 3', 'Advanced Conversations', 'Speaking fluently in conversations', 'Learn to handle complex conversations with confidence', 13, 1, 120);

-- Business English (Course 14)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Business English - Part 1', 'Business Vocabulary', 'Learning essential business terms', 'This chapter introduces key business vocabulary used in professional settings', 14, 1, 60),
(2, 'Business English - Part 2', 'Business Meetings', 'Conducting effective meetings in English', 'Learn how to communicate effectively during business meetings', 14, 1, 90),
(3, 'Business English - Part 3', 'Email Communication', 'Writing professional emails', 'Learn how to write clear and professional business emails in English', 14, 1, 120);

-- English for Travel (Course 16)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'English for Travel - Part 1', 'Basic Travel Phrases', 'Learning essential phrases for traveling', 'Learn common English phrases for navigating travel situations', 16, 1, 60),
(2, 'English for Travel - Part 2', 'At the Airport', 'Communicating at airports', 'Learn how to communicate effectively at airports and during travel', 16, 1, 90),
(3, 'English for Travel - Part 3', 'Hotels and Restaurants', 'Handling hotel and restaurant conversations', 'Learn how to make reservations and order food in English during travel', 16, 1, 120);

-- English for Work (Course 17)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'English for Work - Part 1', 'Workplace Communication', 'Improving communication in professional settings', 'Learn how to communicate effectively with colleagues and superiors', 17, 1, 60),
(2, 'English for Work - Part 2', 'Presentations and Reports', 'Creating presentations and reports in English', 'Learn how to prepare and deliver presentations and write reports in English', 17, 1, 90),
(3, 'English for Work - Part 3', 'Negotiation Skills', 'Negotiating in business contexts', 'Learn key phrases and strategies for successful negotiations in English', 17, 1, 120);

-- English for Public Speaking (Course 15)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Public Speaking - Part 1', 'Introduction to Public Speaking', 'Understanding the basics of public speaking', 'Learn how to speak confidently in front of an audience', 15, 1, 60),
(2, 'Public Speaking - Part 2', 'Speech Preparation', 'Writing and preparing speeches', 'Learn how to structure and prepare a speech effectively', 15, 1, 90),
(3, 'Public Speaking - Part 3', 'Delivering a Speech', 'Practicing speech delivery', 'Learn techniques for delivering powerful speeches', 15, 1, 120);

-- English for Travel (Course 16)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'English for Travel - Part 1', 'Basic Travel Phrases', 'Learning essential phrases for traveling', 'Learn common English phrases for navigating travel situations', 16, 1, 60),
(2, 'English for Travel - Part 2', 'At the Airport', 'Communicating at airports', 'Learn how to communicate effectively at airports and during travel', 16, 1, 90),
(3, 'English for Travel - Part 3', 'Hotels and Restaurants', 'Handling hotel and restaurant conversations', 'Learn how to make reservations and order food in English during travel', 16, 1, 120),
(4, 'English for Travel - Part 4', 'Transportation', 'Getting around using public transportation', 'Learn key phrases for using buses, taxis, and trains while traveling', 16, 1, 75),
(5, 'English for Travel - Part 5', 'Asking for Directions', 'How to ask for and understand directions', 'Learn how to ask for and understand directions in English while traveling', 16, 1, 80);

-- English for Work (Course 17)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'English for Work - Part 1', 'Workplace Communication', 'Improving communication in professional settings', 'Learn how to communicate effectively with colleagues and superiors', 17, 1, 60),
(2, 'English for Work - Part 2', 'Presentations and Reports', 'Creating presentations and reports in English', 'Learn how to prepare and deliver presentations and write reports in English', 17, 1, 90),
(3, 'English for Work - Part 3', 'Negotiation Skills', 'Negotiating in business contexts', 'Learn key phrases and strategies for successful negotiations in English', 17, 1, 120),
(4, 'English for Work - Part 4', 'Email Etiquette', 'Writing professional emails in the workplace', 'Learn how to write clear, concise, and professional emails in English', 17, 1, 70),
(5, 'English for Work - Part 5', 'Handling Phone Calls', 'Improving phone communication skills', 'Learn how to handle professional phone calls with confidence and clarity', 17, 1, 85);

-- TOEFL Preparation (Course 18)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'TOEFL Preparation - Part 1', 'Introduction to TOEFL', 'Understanding the TOEFL structure', 'This chapter introduces the TOEFL test structure and strategies', 18, 1, 90),
(2, 'TOEFL Preparation - Part 2', 'Listening Section', 'Practicing TOEFL listening skills', 'This chapter focuses on improving listening skills for the TOEFL test', 18, 1, 120),
(3, 'TOEFL Preparation - Part 3', 'Speaking Section', 'Improving speaking for TOEFL', 'This chapter covers speaking techniques for the TOEFL exam', 18, 1, 150);

-- IELTS Preparation (Course 19)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'IELTS Preparation - Part 1', 'Introduction to IELTS', 'Understanding the IELTS structure', 'This chapter introduces the IELTS test structure and strategies', 19, 1, 90),
(2, 'IELTS Preparation - Part 2', 'Reading Section', 'Practicing IELTS reading skills', 'This chapter focuses on improving reading skills for the IELTS test', 19, 1, 120),
(3, 'IELTS Preparation - Part 3', 'Writing Section', 'Improving writing for IELTS', 'This chapter covers writing techniques for the IELTS exam', 19, 1, 150);

-- TOEIC Preparation (Course 20)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status,duration) 
VALUES 
(1, 'TOEIC Preparation - Part 1', 'Introduction to TOEIC', 'Understanding the TOEIC structure', 'This chapter introduces the TOEIC test structure and strategies', 20, 1, 90),
(2, 'TOEIC Preparation - Part 2', 'Listening Section', 'Practicing TOEIC listening skills', 'This chapter focuses on improving listening skills for the TOEIC test', 20, 1, 120),
(3, 'TOEIC Preparation - Part 3', 'Reading Section', 'Improving reading skills for TOEIC', 'This chapter covers techniques for reading comprehension in TOEIC', 20, 1, 150);

-- English Listening Skills (Course 21)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Listening Skills - Part 1', 'Basic Listening Comprehension', 'Improving basic listening skills', 'Learn how to improve your basic listening skills with simple English dialogues', 21, 1, 45),
(2, 'Listening Skills - Part 2', 'Listening to Conversations', 'Understanding real-world conversations', 'Practice listening to common English conversations and improve comprehension', 21, 1, 60),
(3, 'Listening Skills - Part 3', 'Listening for Key Information', 'How to focus on key points', 'Learn how to listen for key points and important details in conversations', 21, 1, 75),
(4, 'Listening Skills - Part 4', 'Listening in Work Situations', 'Understanding work-related conversations', 'Improve listening comprehension in work-related conversations', 21, 1, 80),
(5, 'Listening Skills - Part 5', 'Listening to Stories and News', 'Comprehending stories and news in English', 'Practice listening to longer English stories and news reports', 21, 1, 90);

-- English Writing Skills (Course 22)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Writing Skills - Part 1', 'Basic Writing Techniques', 'Improving basic writing skills', 'Learn how to structure sentences and write clearly', 22, 1, 60),
(2, 'Writing Skills - Part 2', 'Writing Essays', 'How to write essays in English', 'Learn how to write structured essays and organize your thoughts', 22, 1, 90),
(3, 'Writing Skills - Part 3', 'Writing Reports and Emails', 'Formal and informal writing', 'Learn how to write reports and professional emails', 22, 1, 80),
(4, 'Writing Skills - Part 4', 'Creative Writing', 'Writing stories and narratives', 'Explore creative writing and learn how to write stories in English', 22, 1, 100),
(5, 'Writing Skills - Part 5', 'Advanced Writing Techniques', 'Mastering advanced writing skills', 'Learn advanced techniques to write with clarity and precision', 22, 1, 120);

-- Conversational English for Beginners (Course 23)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Conversational English - Part 1', 'Introducing Yourself', 'How to introduce yourself in English', 'Learn how to introduce yourself and others in English', 23, 1, 45),
(2, 'Conversational English - Part 2', 'Talking About Daily Activities', 'Common phrases for daily activities', 'Learn how to talk about your daily routine in English', 23, 1, 60),
(3, 'Conversational English - Part 3', 'Talking About Your Interests', 'Discussing hobbies and interests', 'Learn how to talk about your hobbies and interests in English', 23, 1, 75),
(4, 'Conversational English - Part 4', 'Engaging in Small Talk', 'Basic conversational skills', 'Learn how to start and engage in small talk in English', 23, 1, 80),
(5, 'Conversational English - Part 5', 'Talking About Future Plans', 'Discussing future events and plans', 'Learn how to talk about future plans using simple English phrases', 23, 1, 90);

-- Advanced Business English (Course 24)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Business English - Part 1', 'Advanced Workplace Communication', 'Improving communication in professional settings', 'Learn how to handle complex business communications', 24, 1, 75),
(2, 'Business English - Part 2', 'Negotiation Skills', 'Advanced negotiation strategies', 'Learn advanced negotiation strategies and key phrases in English', 24, 1, 90),
(3, 'Business English - Part 3', 'Presenting and Public Speaking', 'Delivering presentations in business settings', 'Learn how to deliver effective presentations in English', 24, 1, 120),
(4, 'Business English - Part 4', 'Email and Report Writing', 'Writing professional business documents', 'Learn how to write professional business emails and reports', 24, 1, 80),
(5, 'Business English - Part 5', 'Cross-Cultural Communication', 'Communicating with international clients', 'Learn strategies for effective communication in cross-cultural business settings', 24, 1, 100);

-- English for Public Speaking (Course 25)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Public Speaking - Part 1', 'Overcoming Stage Fright', 'Building confidence in public speaking', 'Learn techniques to overcome fear of public speaking', 25, 1, 60),
(2, 'Public Speaking - Part 2', 'Structuring Your Speech', 'How to structure a speech', 'Learn how to organize your thoughts and structure a compelling speech', 25, 1, 90),
(3, 'Public Speaking - Part 3', 'Engaging the Audience', 'How to connect with your audience', 'Learn techniques to engage and captivate your audience', 25, 1, 120),
(4, 'Public Speaking - Part 4', 'Using Visual Aids', 'Enhancing your speech with visuals', 'Learn how to effectively use visual aids in your presentations', 25, 1, 70),
(5, 'Public Speaking - Part 5', 'Delivering Your Speech', 'Practice and delivery techniques', 'Master the art of delivering a speech with confidence and clarity', 25, 1, 100);

-- English for Hospitality (Course 26)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Hospitality English - Part 1', 'Greeting Guests', 'How to greet guests professionally', 'Learn how to greet and welcome guests in the hospitality industry', 26, 1, 45),
(2, 'Hospitality English - Part 2', 'Handling Guest Inquiries', 'Responding to common guest questions', 'Learn how to respond to guest inquiries and provide assistance', 26, 1, 60),
(3, 'Hospitality English - Part 3', 'Taking Reservations', 'Booking rooms and services', 'Learn how to take reservations and bookings professionally', 26, 1, 75),
(4, 'Hospitality English - Part 4', 'Managing Complaints', 'Handling guest complaints', 'Learn how to manage guest complaints and resolve issues', 26, 1, 80),
(5, 'Hospitality English - Part 5', 'Offering Recommendations', 'Providing recommendations to guests', 'Learn how to offer recommendations on local attractions and services', 26, 1, 90);

-- English for Healthcare (Course 27)
INSERT INTO Chapter (`order`, title, subtitle, brief_information, description, course_id, status, duration) 
VALUES 
(1, 'Healthcare English - Part 1', 'Communicating with Patients', 'Basic patient communication', 'Learn how to communicate effectively with patients in healthcare settings', 27, 1, 50),
(2, 'Healthcare English - Part 2', 'Describing Symptoms', 'Helping patients describe their symptoms', 'Learn how to assist patients in describing their symptoms and medical conditions', 27, 1, 60),
(3, 'Healthcare English - Part 3', 'Giving Instructions', 'Providing clear instructions to patients', 'Learn how to give patients clear and concise medical instructions', 27, 1, 75),
(4, 'Healthcare English - Part 4', 'Medical Terminology', 'Understanding and using medical terms', 'Learn key medical terms used in healthcare communication', 27, 1, 90),
(5, 'Healthcare English - Part 5', 'Handling Emergencies', 'Communicating in emergency situations', 'Learn how to handle communication during medical emergencies', 27, 1, 100);

INSERT INTO Lesson (status, lesson_type_id, title, chapter_id, `order`) 
VALUES 
(1, 3, 'Java Basics', 1, 1),
(1, 3, 'Java Advanced', 2, 2),
(1, 3, 'Python Basics', 1, 1),
(1, 3, 'Python Advanced', 2, 2),
(1, 3, 'Web Development Basics', 3, 1),
(1, 3, 'Web Development Advanced', 4, 2),
(1, 3, 'Digital Marketing Basics', 5, 1),
(1, 3, 'Business Management Basics', 6, 1);

-- Insert more tags into the Tagline table
INSERT INTO Tagline (name) 
VALUES 
('Programming'),
('Technology'),
('Business'),
('Marketing'),
('Design'),
('English'),
('Travel'),
('Healthcare'),
('Hospitality'),
('Communication'),
('Education'),
('Language Learning'),
('Photography'),
('Graphic Design'),
('Management'),
('Web Development'),
('Software Development'),
('Data Analysis'),
('Public Speaking'),
('Leadership'),
('Customer Service'),
('Exam Preparation'),
('Beginner'),
('Advanced');

-- Insert multiple tag associations for each course into Course_Tagline table
INSERT INTO Course_Tagline (tagline_id, course_id) 
VALUES 
-- Java Programming (tags: Programming, Technology, Software Development, Beginner)
(1, 1),
(2, 1),
(16, 1),
(23, 1),  -- Beginner

-- Python Programming (tags: Programming, Technology, Data Analysis, Software Development, Beginner)
(1, 2),
(2, 2),
(17, 2),
(18, 2),
(23, 2),  -- Beginner

-- Web Development (tags: Programming, Technology, Web Development, Software Development, Beginner)
(1, 3),
(2, 3),
(16, 3),
(17, 3),
(23, 3),  -- Beginner

-- Digital Marketing (tags: Marketing, Business, Technology, Communication, Beginner)
(4, 4),
(3, 4),
(2, 4),
(10, 4),
(23, 4),  -- Beginner

-- Business Management (tags: Business, Management, Leadership, Communication, Advanced)
(3, 5),
(15, 5),
(20, 5),
(10, 5),
(24, 5),  -- Advanced

-- Graphic Design (tags: Design, Technology, Graphic Design, Creativity, Beginner)
(5, 6),
(2, 6),
(14, 6),
(4, 6),
(23, 6),  -- Beginner

-- Photography (tags: Photography, Technology, Creativity, Graphic Design, Beginner)
(13, 7),
(2, 7),
(14, 7),
(4, 7),
(23, 7),  -- Beginner

-- English for Beginners (tags: English, Communication, Language Learning, Education, Beginner)
(6, 8),
(10, 8),
(12, 8),
(11, 8),
(23, 8),  -- Beginner

-- English Intermediate (tags: English, Communication, Language Learning, Education, Advanced)
(6, 9),
(10, 9),
(12, 9),
(11, 9),
(24, 9),  -- Advanced

-- English Advanced (tags: English, Communication, Language Learning, Education, Advanced)
(6, 10),
(10, 10),
(12, 10),
(11, 10),
(24, 10),  -- Advanced

-- English Grammar Basics (tags: English, Education, Language Learning, Beginner)
(6, 11),
(11, 11),
(12, 11),
(23, 11),  -- Beginner

-- English Vocabulary Building (tags: English, Communication, Language Learning, Education, Beginner)
(6, 12),
(10, 12),
(12, 12),
(11, 12),
(23, 12),  -- Beginner

-- English Conversation Practice (tags: English, Communication, Public Speaking, Language Learning, Beginner)
(6, 13),
(10, 13),
(19, 13),
(12, 13),
(23, 13),  -- Beginner

-- Business English (tags: English, Business, Communication, Language Learning, Advanced)
(6, 14),
(3, 14),
(10, 14),
(12, 14),
(24, 14),  -- Advanced

-- English Pronunciation (tags: English, Communication, Public Speaking, Language Learning, Beginner)
(6, 15),
(10, 15),
(19, 15),
(12, 15),
(23, 15),  -- Beginner

-- English for Travel (tags: English, Travel, Communication, Language Learning, Beginner)
(6, 16),
(7, 16),
(10, 16),
(12, 16),
(23, 16),  -- Beginner

-- English for Work (tags: English, Business, Communication, Leadership, Advanced)
(6, 17),
(3, 17),
(10, 17),
(20, 17),
(24, 17),  -- Advanced

-- TOEFL Preparation (tags: English, Exam Preparation, Education, Advanced)
(6, 18),
(22, 18),
(11, 18),
(24, 18),  -- Advanced

-- IELTS Preparation (tags: English, Exam Preparation, Education, Advanced)
(6, 19),
(22, 19),
(11, 19),
(24, 19),  -- Advanced

-- TOEIC Preparation (tags: English, Exam Preparation, Education, Advanced)
(6, 20),
(22, 20),
(11, 20),
(24, 20),  -- Advanced

-- English Listening Skills (tags: English, Communication, Language Learning, Education, Beginner)
(6, 21),
(10, 21),
(12, 21),
(11, 21),
(23, 21),  -- Beginner

-- English Writing Skills (tags: English, Communication, Language Learning, Education, Advanced)
(6, 22),
(10, 22),
(12, 22),
(11, 22),
(24, 22),  -- Advanced

-- Conversational English for Beginners (tags: English, Communication, Public Speaking, Language Learning, Beginner)
(6, 23),
(10, 23),
(19, 23),
(12, 23),
(23, 23),  -- Beginner

-- Advanced Business English (tags: English, Business, Communication, Leadership, Advanced)
(6, 24),
(3, 24),
(10, 24),
(20, 24),
(24, 24),  -- Advanced

-- English for Public Speaking (tags: English, Communication, Public Speaking, Leadership, Advanced)
(6, 25),
(10, 25),
(19, 25),
(20, 25),
(24, 25),  -- Advanced

-- English for Hospitality (tags: English, Hospitality, Customer Service, Communication, Beginner)
(6, 26),
(9, 26),
(21, 26),
(10, 26),
(23, 26),  -- Beginner

-- English for Healthcare (tags: English, Healthcare, Communication, Customer Service, Advanced)
(6, 27),
(8, 27),
(10, 27),
(21, 27),
(24, 27);  -- Advanced


INSERT INTO Quiz (lesson_id, title, duration, pass_rate, updated_date, description, subtitle) 
VALUES 
(1, 'Basic Java Quiz', 30, 0.8, '2023-01-01', 'Java Quiz 1', 'Java'),
(2, 'Advanced Java Quiz', 25, 0.7, '2023-01-02', 'Java Quiz 2', 'Advanced Java Quiz'),
(3, 'Python Basics Quiz', 20, 0.8, '2023-01-03', 'Python Quiz 1', 'Python Basics Quiz'),
(4, 'Advanced Python Quiz', 30, 0.75, '2023-01-04', 'Python Quiz 2', 'Advanced Python Quiz'),
(5, 'Web Development Quiz 1', 15, 0.7, '2023-01-05', 'Web Dev Quiz 1', 'Introduction to Web Development'),
(6, 'Web Development Quiz 2', 25, 0.85, '2023-01-06', 'Web Dev Quiz 2', 'Advanced Web Development Concepts'),
(7, 'Digital Marketing Quiz 1', 20, 0.6, '2023-01-07', 'Digital Marketing Quiz 1', 'Marketing Basics'),
(8, 'Business Management Quiz 1', 35, 0.9, '2023-01-08', 'Business Management Quiz 1', 'Managing Teams and Resources');

INSERT INTO Question (quiz_id, level_id, status, content, question_type_id) 
VALUES 
(1, 5, 1, 'What is Java?', 6),
(1, 5, 1, 'Explain the JVM architecture.', 6),
(2, 6, 1, 'What is the purpose of garbage collection in Java?', 6),
(2, 6, 1, 'Explain the concept of Java multithreading.', 6),
(3, 5, 1, 'What is Python?', 6),
(3, 5, 1, 'Describe the difference between Python 2 and Python 3.', 6),
(4, 6, 1, 'Explain Python decorators.', 6),
(4, 6, 1, 'What are Python generators?', 6),
(5, 5, 1, 'What is HTML?', 6),
(5, 5, 1, 'Describe the CSS box model.', 6),
(6, 6, 1, 'What is React?', 6),
(6, 6, 1, 'Explain the concept of component-based architecture.', 6),
(7, 5, 1, 'What is SEO?', 6),
(7, 6, 1, 'Explain the process of running a successful marketing campaign.', 6),
(8, 5, 1, 'What is team management?', 6),
(8, 6, 1, 'Describe the importance of resource allocation.', 6);

INSERT INTO `Option` (question_id, status, isTrue, explaination) 
VALUES 
(1, 1, 1, 'Java is a programming language.'),
(2, 1, 0, 'Incorrect. JVM is part of Java architecture.'),
(3, 1, 1, 'Garbage collection helps in automatic memory management.'),
(4, 1, 0, 'Incorrect. Java multithreading is unrelated to garbage collection.'),
(5, 1, 1, 'Python is a high-level programming language.'),
(6, 1, 0, 'Incorrect. Python 2 and 3 differ in syntax and features.'),
(7, 1, 1, 'Python decorators are used to modify functions or methods.'),
(8, 1, 0, 'Incorrect. Python generators yield values instead of returning them.'),
(9, 1, 1, 'HTML stands for HyperText Markup Language.'),
(10, 1, 0, 'Incorrect. The CSS box model is a container wrapping around HTML elements.'),
(11, 1, 1, 'React is a JavaScript library for building user interfaces.'),
(12, 1, 0, 'Incorrect. Component-based architecture breaks down UI into smaller components.'),
(13, 1, 1, 'SEO stands for Search Engine Optimization.'),
(14, 1, 0, 'Incorrect. Running a marketing campaign involves multiple strategic phases.'),
(15, 1, 1, 'Team management involves leading and organizing a team.'),
(16, 1, 0, 'Incorrect. Resource allocation is essential for team productivity.');

INSERT INTO PersonalCourse (customer_id, course_id, price_package_id, status, enroll_date, progress) 
VALUES 
(1, 1, 1, 1, '2023-01-10', 80),
(2, 2, 2, 1, '2023-01-11', 70),
(3, 3, 3, 1, '2023-01-12', 90),
(4, 4, 4, 1, '2023-01-13', 85),
(5, 5, 5, 1, '2023-01-14', 95),
(6, 6, 6, 1, '2023-01-15', 60),
(7, 7, 7, 1, '2023-01-16', 75),
(8, 8, 8, 1, '2023-01-17', 50);

INSERT INTO PersonalChapter (personal_course_id, chapter_id, is_done) 
VALUES 
(1, 1, 1),
(1, 2, 1),
(1, 3, 0),
(2, 1, 1),
(2, 2, 0),
(2, 3, 1),
(3, 1, 1),
(3, 2, 1),
(3, 3, 0),
(4, 1, 1),
(4, 2, 0),
(4, 3, 1),
(5, 1, 1),
(5, 2, 1),
(5, 3, 0),
(6, 1, 1),
(6, 2, 1),
(6, 3, 0),
(7, 1, 1),
(7, 2, 1),
(7, 3, 0),
(8, 1, 1),
(8, 2, 0),
(8, 3, 1);


INSERT INTO PersonalLesson (personal_chapter_id, lesson_id, is_done) 
VALUES 
(1, 1, 1),
(2, 2, 0),
(3, 3, 1),
(4, 4, 0),
(5, 5, 1),
(6, 6, 0),
(7, 7, 1),
(8, 8, 0);

INSERT INTO PersonalQuiz (personal_lesson_id, quiz_id, is_passed, taken_date, score) 
VALUES 
(1, 1, 1, '2023-01-10', 85),
(2, 2, 1, '2023-01-11', 75),
(3, 3, 1, '2023-01-12', 90),
(4, 4, 0, '2023-01-13', 65),
(5, 5, 1, '2023-01-14', 80),
(6, 6, 1, '2023-01-15', 88),
(7, 7, 1, '2023-01-16', 92),
(8, 8, 0, '2023-01-17', 70);

INSERT INTO PersonalQuestion (personal_quiz_id, question_id, is_answered, is_marked) 
VALUES 
(1, 1, 1, 0),
(1, 2, 1, 1),
(2, 3, 1, 0),
(2, 4, 0, 1),
(3, 5, 1, 0),
(3, 6, 1, 1),
(4, 7, 0, 0),
(4, 8, 1, 1),
(5, 9, 1, 0),
(5, 10, 1, 1),
(6, 11, 0, 0),
(6, 12, 1, 1),
(7, 13, 1, 0),
(7, 14, 1, 1),
(8, 15, 0, 0),
(8, 16, 1, 1);

INSERT INTO PersonalOption (personal_question_id, option_id, is_choosed) 
VALUES 
(1, 1, 1),
(2, 2, 0),
(3, 3, 1),
(4, 4, 0),
(5, 5, 1),
(6, 6, 0),
(7, 7, 1),
(8, 8, 0),
(9, 9, 1),
(10, 10, 0),
(11, 11, 1),
(12, 12, 0),
(13, 13, 1),
(14, 14, 0),
(15, 15, 1),
(16, 16, 0);

INSERT INTO PersonalAnswer (personal_question_id, answer, is_true) 
VALUES 
(1, 'Java is a programming language.', 1),
(2, 'Incorrect JVM is part of Java architecture.', 0),
(3, 'Garbage collection helps in automatic memory management.', 1),
(4, 'Incorrect Java multithreading is unrelated to garbage collection.', 0),
(5, 'Python is a high-level programming language.', 1),
(6, 'Incorrect Python 2 and 3 differ in syntax and features.', 0),
(7, 'Python decorators are used to modify functions or methods.', 1),
(8, 'Incorrect Python generators yield values instead of returning them.', 0),
(9, 'HTML stands for HyperText Markup Language.', 1),
(10, 'Incorrect The CSS box model is a container wrapping around HTML elements.', 0),
(11, 'React is a JavaScript library for building user interfaces.', 1),
(12, 'Incorrect Component-based architecture breaks down UI into smaller components.', 0),
(13, 'SEO stands for Search Engine Optimization.', 1),
(14, 'Incorrect Running a marketing campaign involves multiple strategic phases.', 0),
(15, 'Team management involves leading and organizing a team.', 1),
(16, 'Incorrect Resource allocation is essential for team productivity.', 0);

INSERT INTO Blog (thumbnail_url, title, content, brief_info, author_id, status, created_date, updated_date) 
VALUES 
('thumbnail1.jpg', 'Java Programming Basics', 'This blog covers the basics of Java programming.', 'Java basics for beginners', 1, 1, '2023-01-10', '2023-01-10'),
('thumbnail2.jpg', 'Advanced Java Programming', 'This blog explains advanced Java programming concepts.', 'Advanced Java topics for experienced developers', 2, 1, '2023-01-15', '2023-01-15'),
('thumbnail3.jpg', 'Python Programming Introduction', 'An introduction to Python programming.', 'Python basics for beginners', 3, 1, '2023-02-10', '2023-02-10'),
('thumbnail4.jpg', 'Advanced Python Techniques', 'Learn advanced techniques in Python.', 'Advanced Python programming for experts', 4, 1, '2023-03-01', '2023-03-01'),
('thumbnail5.jpg', 'Web Development Basics', 'An introduction to web development using HTML, CSS, and JavaScript.', 'Learn how to build websites from scratch', 5, 1, '2023-04-10', '2023-04-10'),
('thumbnail6.jpg', 'Digital Marketing Strategies', 'This blog discusses key strategies in digital marketing.', 'Master digital marketing to grow your business', 6, 1, '2023-05-05', '2023-05-05'),
('thumbnail7.jpg', 'Business Management Fundamentals', 'Learn the core principles of managing a business.', 'Business management basics for entrepreneurs', 7, 1, '2023-06-12', '2023-06-12'),
('thumbnail8.jpg', 'Graphic Design Techniques', 'This blog explains graphic design techniques for beginners.', 'Learn graphic design principles and tools', 8, 1, '2023-07-20', '2023-07-20'),
('thumbnail9.jpg', 'Photography Tips and Tricks', 'This blog shares tips for improving your photography skills.', 'Photography basics for amateurs', 1, 1, '2023-08-10', '2023-08-10'),
('thumbnail10.jpg', 'Health and Wellness Tips', 'Discover tips for maintaining health and wellness.', 'Improve your health with simple habits', 2, 1, '2023-09-05', '2023-09-05'),
('thumbnail11.jpg', 'English Learning Tips', 'This blog provides tips for learning English more effectively.', 'Master English with these simple tips', 3, 1, '2023-10-12', '2023-10-12'),
('thumbnail12.jpg', 'IELTS Preparation Guide', 'A comprehensive guide for preparing for the IELTS exam.', 'Get ready for IELTS with these steps', 4, 1, '2023-11-01', '2023-11-01'),
('thumbnail13.jpg', 'TOEFL Exam Strategies', 'This blog covers strategies for acing the TOEFL exam.', 'Key strategies for TOEFL success', 5, 1, '2023-12-10', '2023-12-10'),
('thumbnail14.jpg', 'Travel English Phrases', 'Learn key English phrases for traveling abroad.', 'Essential English for travelers', 6, 1, '2024-01-05', '2024-01-05'),
('thumbnail15.jpg', 'Business English for Professionals', 'This blog covers important English phrases for business settings.', 'Improve your business English communication', 7, 1, '2024-02-15', '2024-02-15'),
('thumbnail16.jpg', 'Public Speaking in English', 'Learn how to improve your public speaking skills in English.', 'Master public speaking with these tips', 8, 1, '2024-03-10', '2024-03-10'),
('thumbnail17.jpg', 'Hospitality English Phrases', 'This blog provides English phrases useful in the hospitality industry.', 'English for hotel and tourism professionals', 1, 1, '2024-04-01', '2024-04-01'),
('thumbnail18.jpg', 'Healthcare English Vocabulary', 'Learn important English vocabulary for healthcare professionals.', 'English for healthcare settings', 2, 1, '2024-05-05', '2024-05-05'),
('thumbnail19.jpg', 'TOEIC Preparation Strategies', 'This blog covers strategies for acing the TOEIC exam.', 'TOEIC exam strategies and tips', 3, 1, '2024-06-01', '2024-06-01'),
('thumbnail20.jpg', 'English Writing Skills for Business', 'Learn how to improve English writing skills for business communication.', 'Business English writing tips', 4, 1, '2024-07-10', '2024-07-10'),
('thumbnail21.jpg', 'Conversational English for Beginners', 'This blog helps beginners improve their conversational English skills.', 'Basic conversational English tips', 5, 1, '2024-08-05', '2024-08-05'),
('thumbnail22.jpg', 'Advanced Business English Techniques', 'Learn advanced techniques in business English communication.', 'Enhance your business English proficiency', 6, 1, '2024-09-01', '2024-09-01'),
('thumbnail23.jpg', 'Public Speaking Mastery', 'A guide to mastering public speaking in English.', 'Master advanced public speaking techniques', 7, 1, '2024-10-01', '2024-10-01'),
('thumbnail24.jpg', 'English for Travel and Tourism', 'Key English phrases for travel and tourism professionals.', 'Learn essential travel-related English', 8, 1, '2024-11-01', '2024-11-01'),
('thumbnail25.jpg', 'Advanced Healthcare English Vocabulary', 'Master advanced English vocabulary for healthcare professionals.', 'Essential English for advanced healthcare communication', 1, 1, '2024-12-01', '2024-12-01');

INSERT INTO BlogCourse (blog_id, course_id) 
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20);

INSERT INTO LearningMaterial (lesson_id, title, upload_date, duration) 
VALUES 
(1, 'Java Basics Learning Material', '2023-01-01', 10),
(2, 'Advanced Java Learning Material', '2023-01-02', 20),
(3, 'Python Basics Learning Material', '2023-01-03', 15),
(4, 'Advanced Python Learning Material', '2023-01-04', 25),
(5, 'Web Development Basics Learning Material', '2023-01-05', 30),
(6, 'Advanced Web Development Learning Material', '2023-01-06', 35),
(7, 'Digital Marketing Basics Learning Material', '2023-01-07', 40),
(8, 'Business Management Learning Material', '2023-01-08', 45);

INSERT INTO VideoContent (lesson_id, video_url, video_summary, description) 
VALUES 
(1, 'video1.mp4', 'Introduction to Java', 'This video covers the basics of Java programming.'),
(2, 'video2.mp4', 'Advanced Java Concepts', 'This video explains advanced concepts in Java.'),
(3, 'video3.mp4', 'Introduction to Python', 'Learn the basics of Python programming.'),
(4, 'video4.mp4', 'Advanced Python Topics', 'Explore advanced Python topics.'),
(5, 'video5.mp4', 'Web Development Basics', 'An introduction to web development.'),
(6, 'video6.mp4', 'Advanced Web Development', 'Learn advanced web development techniques.'),
(7, 'video7.mp4', 'Marketing 101', 'Introduction to digital marketing.'),
(8, 'video8.mp4', 'Business Management Tips', 'Tips and strategies for managing a business.');

INSERT INTO TextContent (lesson_id, text_content) 
VALUES 
(1, 'This lesson covers the basics of Java programming.'),
(2, 'This lesson explains advanced Java topics.'),
(3, 'This lesson introduces Python programming.'),
(4, 'This lesson covers advanced Python concepts.'),
(5, 'This lesson introduces web development basics.'),
(6, 'This lesson explains advanced web development topics.'),
(7, 'This lesson covers basic marketing principles.'),
(8, 'This lesson provides tips on business management.');

