CREATE DATABASE Learnik;
USE Learnik;

-- Bảng SettingType
CREATE TABLE SettingType (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

-- Bảng Setting
CREATE TABLE Setting (
    id INT AUTO_INCREMENT PRIMARY KEY,
    setting_type_id INT,
    value VARCHAR(255),
    status INT,
    description TEXT,
    created_date DATE,
    updated_date DATE,
    FOREIGN KEY (setting_type_id) REFERENCES SettingType(id)
);

-- Bảng Account
CREATE TABLE Account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255),
    dob DATE,
    role_id INT,
    created_date DATE,
    status INT,
    phone VARCHAR(255),
    gender TINYINT(1),
    address VARCHAR(255),
    image_url VARCHAR(255),
    FOREIGN KEY (role_id) REFERENCES Setting(id)
);

-- Bảng Course
CREATE TABLE Course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    expert_id INT,
    total_duration FLOAT,
    status INT,
    description TEXT,
    category_id INT,
    created_date DATE,
    updated_date DATE,
    thumbnail_url VARCHAR(255),
    number_of_lesson INT,
    FOREIGN KEY (category_id) REFERENCES Setting(id),
    FOREIGN KEY (expert_id) REFERENCES Account(id)
);

-- Bảng PricePackage
CREATE TABLE PricePackage (
    course_id INT,
    price_package_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    price INT,
    sale_price INT,
    duration INT,
    FOREIGN KEY (course_id) REFERENCES Course(id)
);

-- Bảng UserLog
CREATE TABLE UserLog (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_date DATE,
    type_id INT,
    account_id INT,
    FOREIGN KEY (account_id) REFERENCES Account(id),
    FOREIGN KEY (type_id) REFERENCES Setting(id)
);

-- Bảng Blog
CREATE TABLE Blog (
    id INT AUTO_INCREMENT PRIMARY KEY,
    updated_date DATE,
    created_date DATE,
    category_id INT,
    thumbnail_url VARCHAR(255),
    title VARCHAR(255),
    brief_info VARCHAR(255),
    author_id INT,
    blog_detail TEXT,
    status INT,
    FOREIGN KEY (author_id) REFERENCES Account(id),
    FOREIGN KEY (category_id) REFERENCES Setting(id)
);

-- Bảng Slider
CREATE TABLE Slider (
    id INT AUTO_INCREMENT PRIMARY KEY,
    author_id INT,
    image_url VARCHAR(255),
    backlink_url VARCHAR(255),
    status INT,
    FOREIGN KEY (author_id) REFERENCES Account(id)
);

-- Bảng PersonalCourse
CREATE TABLE PersonalCourse (
    customer_id INT,
    course_id INT,
    status INT,
    enroll_date DATE,
    expire_date DATE,
    progress INT,
    PRIMARY KEY (customer_id, course_id),
    FOREIGN KEY (customer_id) REFERENCES Account(id),
    FOREIGN KEY (course_id) REFERENCES Course(id)
);

-- Bảng Lesson
CREATE TABLE Lesson (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT,
    title VARCHAR(255),
    status INT,
    lesson_type_id INT,
    FOREIGN KEY (course_id) REFERENCES Course(id),
    FOREIGN KEY (lesson_type_id) REFERENCES Setting(id)
);

-- Bảng LearningMaterial
CREATE TABLE LearningMaterial (
    lesson_id INT,
    updated_date DATE,
    duration INT,
    video_content_url VARCHAR(255),
    html_content TEXT,
    PRIMARY KEY (lesson_id),
    FOREIGN KEY (lesson_id) REFERENCES Lesson(id)
);

-- Bảng SubjectTopic
CREATE TABLE SubjectTopic (
    lesson_id INT,
    description VARCHAR(255),
    PRIMARY KEY (lesson_id),
    FOREIGN KEY (lesson_id) REFERENCES Lesson(id)
);

-- Bảng Quiz
CREATE TABLE Quiz (
    lesson_id INT PRIMARY KEY,
    duration INT,
    pass_rate FLOAT,
    updated_date DATE, 
    number_of_question INT,
    description TEXT,
    FOREIGN KEY (lesson_id) REFERENCES Lesson(id)
);

-- Bảng Question
CREATE TABLE Question (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT,
    content TEXT,
    update_date DATE,
    level_id INT,
    FOREIGN KEY (quiz_id) REFERENCES Quiz(lesson_id),
    FOREIGN KEY (level_id) REFERENCES Setting(id)
);

-- Bảng Option
CREATE TABLE `Option` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT,
    description VARCHAR(255),
    isTrue TINYINT(1),
    explanation VARCHAR(255),
    FOREIGN KEY (question_id) REFERENCES Question(id)
);

-- Bảng PersonalLesson
CREATE TABLE PersonalLesson (
    customer_id INT,
    course_id INT,
    lesson_id INT,
    isDone TINYINT(1),
    PRIMARY KEY (customer_id, course_id, lesson_id),
    FOREIGN KEY (customer_id) REFERENCES PersonalCourse(customer_id),
    FOREIGN KEY (course_id) REFERENCES PersonalCourse(course_id),
    FOREIGN KEY (lesson_id) REFERENCES Lesson(id)
);

-- Bảng PersonalQuiz
CREATE TABLE PersonalQuiz (
    customer_id INT,
    course_id INT,
    lesson_id INT,
    hasPassed TINYINT(1),
    PRIMARY KEY (customer_id, course_id, lesson_id),
    FOREIGN KEY (customer_id) REFERENCES PersonalLesson(customer_id),
    FOREIGN KEY (course_id) REFERENCES PersonalLesson(course_id),
    FOREIGN KEY (lesson_id) REFERENCES PersonalLesson(lesson_id)
);

-- Bảng PersonalQuestion
CREATE TABLE PersonalQuestion (
    customer_id INT,
    course_id INT,
    lesson_id INT,
    question_id INT,
    hasAnswered TINYINT(1),
    isMarked TINYINT(1),
    PRIMARY KEY (customer_id, course_id, lesson_id, question_id),
    FOREIGN KEY (customer_id) REFERENCES PersonalQuiz(customer_id),
    FOREIGN KEY (course_id) REFERENCES PersonalQuiz(course_id),
    FOREIGN KEY (lesson_id) REFERENCES PersonalQuiz(lesson_id),
    FOREIGN KEY (question_id) REFERENCES Question(id)
);

-- Bảng PersonalOption
CREATE TABLE PersonalOption (
    customer_id INT,
    course_id INT,
    lesson_id INT,
    question_id INT,
    option_id INT,
    hasChosen TINYINT(1),
    PRIMARY KEY (customer_id, course_id, lesson_id, question_id, option_id),
    FOREIGN KEY (customer_id) REFERENCES PersonalQuestion(customer_id),
    FOREIGN KEY (course_id) REFERENCES PersonalQuestion(course_id),
    FOREIGN KEY (lesson_id) REFERENCES PersonalQuestion(lesson_id),
    FOREIGN KEY (question_id) REFERENCES PersonalQuestion(question_id),
    FOREIGN KEY (option_id) REFERENCES `Option`(id)
);

-- Bảng SettingType
INSERT INTO SettingType (name) 
VALUES 
('Account Role'), 
('Course Category'), 
('UserLog Event'), 
('Blog Category'), 
('Lesson Type'), 
('Question Level');

-- Bảng Setting cho các vai trò, danh mục và loại bài học, mức độ câu hỏi
INSERT INTO Setting (setting_type_id, value, status, description) 
VALUES 
(1, 'Guest', 1, 'Unregistered users'),
(1, 'Customer', 1, 'Registered users who are actual or potential customers'),
(1, 'Marketing', 1, 'Marketing members of the organization'),
(1, 'Sale', 1, 'Sale members of the organization'),
(1, 'Expert', 1, 'Access & prepare the course/test contents as assigned by admin'),
(1, 'Admin', 1, 'The organization leader/manager, acts as the system administrator'),
(2, 'Technology', 1, 'Courses related to technology'),
(2, 'Business', 1, 'Courses related to business'),
(2, 'Language', 1, 'Courses related to languages'),
(2, 'Art', 1, 'Courses related to art'),
(2, 'Health', 1, 'Courses related to health and fitness'),
(2, 'Science', 1, 'Courses related to science'),
(3, 'LogIn', 1, 'Login Account'),
(3, 'LogOut', 1, 'Logout Account'),
(4, 'Technology', 1, 'Blogs related to technology'),
(4, 'Business', 1, 'Blogs related to business'),
(4, 'Language', 1, 'Blogs related to language'),
(5, 'Subject Topic', 1, 'Topic of lesson subjects'),
(5, 'Learning Material', 1, 'Learning materials for lessons'),
(5, 'Quiz', 1, 'Quizzes for lessons'),
(6, 'Easy', 1, 'Easy level questions'),
(6, 'Medium', 1, 'Medium level questions'),
(6, 'Hard', 1, 'Hard level questions');


-- Bảng Account với 30 tài khoản người dùng
INSERT INTO Account (email, first_name, last_name, password, dob, role_id, created_date, status, phone, gender, address, image_url)
VALUES 
('customer1@example.com', 'John', 'Doe', 'password123', '1995-05-10', 2, '2023-02-01', 1, '0987654321', 1, '456 Customer Road', NULL),
('customer2@example.com', 'Jane', 'Doe', 'password123', '1990-07-12', 2, '2023-02-02', 1, '0987654322', 0, '457 Customer Road', NULL),
('customer3@example.com', 'Alice', 'Smith', 'password123', '1985-02-18', 2, '2023-02-03', 1, '0987654323', 0, '458 Customer Road', NULL),
-- Repeat for more Customer accounts up to customer10@example.com...
('marketing1@example.com', 'Alice', 'Smith', 'password123', '1990-02-20', 3, '2023-03-01', 1, '1234567890', 0, '789 Marketing Ave', NULL),
-- Repeat for more Marketing accounts up to marketing5@example.com...
('sale1@example.com', 'Bob', 'Brown', 'password123', '1985-03-15', 4, '2023-03-02', 1, '0987654321', 1, '321 Sale Blvd', NULL),
-- Repeat for more Sale accounts up to sale5@example.com...
('expert1@example.com', 'Charlie', 'Davis', 'password123', '1980-04-10', 5, '2023-04-01', 1, '1234567890', 0, '456 Expert Road', NULL),
-- Repeat for more Expert accounts up to expert5@example.com...
('admin1@example.com', 'Admin', 'User', 'password123', '1975-05-05', 6, '2023-05-01', 1, '1122334455', 1, '789 Admin Blvd', NULL),
-- Repeat for more Admin accounts up to admin5@example.com...
('customer30@example.com', 'Sam', 'Wilson', 'password123', '2002-06-30', 2, '2023-06-01', 1, '9876543210', 1, '123 Customer Street', NULL);

-- Bảng Course với 30 khóa học
INSERT INTO Course (title, expert_id, total_duration, status, description, category_id, created_date, updated_date, thumbnail_url, number_of_lesson)
VALUES 
('Java Programming', 6,20, 1, 'Java course for beginners', 7, '2023-01-01', '2023-01-10', 'java_thumbnail.jpg', 10),
('Python Programming',6,15, 1, 'Python course for data analysis', 7, '2023-02-01', '2023-02-10', 'python_thumbnail.jpg', 12),
('Web Development',6,30, 1, 'Full stack web development course', 7, '2023-03-01', '2023-03-10', 'webdev_thumbnail.jpg', 15),
('Digital Marketing',6,25, 1, 'Digital marketing fundamentals', 8, '2023-04-01', '2023-04-10', 'marketing_thumbnail.jpg', 10),
('Business Management',6, 40, 1, 'Managing business effectively', 8, '2023-05-01', '2023-05-10', 'business_thumbnail.jpg', 20),
('Data Science', 6, 50, 1, 'Comprehensive data science course', 7, '2023-06-01', '2023-06-10', 'datascience_thumbnail.jpg', 18),
('English for Beginners', 6, 35, 1, 'Basic English course for beginners', 9, '2023-07-01', '2023-07-10', 'english_thumbnail.jpg', 14),
('Photography Basics', 6, 20, 1, 'Basic photography skills', 10, '2023-08-01', '2023-08-10', 'photography_thumbnail.jpg', 10),
('Advanced Photoshop', 6, 30, 1, 'Advanced techniques in Photoshop', 7, '2023-09-01', '2023-09-10', 'photoshop_thumbnail.jpg', 12),
('Healthy Living', 6, 25, 1, 'Tips for a healthy lifestyle', 11, '2023-10-01', '2023-10-10', 'health_thumbnail.jpg', 8),
('Physics Fundamentals', 6, 40, 1, 'Basic principles of physics', 2, '2023-11-01', '2023-11-10', 'physics_thumbnail.jpg', 20),
('Chemistry 101', 6, 45, 1, 'Introduction to chemistry', 12, '2023-12-01', '2023-12-10', 'chemistry_thumbnail.jpg', 22),
('Biology Basics', 6, 30, 1, 'Introduction to biology', 12, '2023-01-11', '2023-01-20', 'biology_thumbnail.jpg', 15),
('Public Speaking', 6, 15, 1, 'Public speaking techniques', 8, '2023-02-11', '2023-02-20', 'publicspeaking_thumbnail.jpg', 8),
('Project Management', 6, 35, 1, 'Project management skills', 8, '2023-03-11', '2023-03-20', 'projectmanagement_thumbnail.jpg', 18),
('Spanish for Beginners', 6, 20, 1, 'Basic Spanish language course', 9, '2023-04-11', '2023-04-20', 'spanish_thumbnail.jpg', 10),
('Creative Writing', 6, 25, 1, 'Writing creatively', 8, '2023-05-11', '2023-05-20', 'writing_thumbnail.jpg', 12),
('Graphic Design', 6, 30, 1, 'Basics of graphic design', 7, '2023-06-11', '2023-06-20', 'graphicdesign_thumbnail.jpg', 15),
('Yoga for Beginners', 6, 20, 1, 'Beginner yoga poses and exercises', 11, '2023-07-11', '2023-07-20', 'yoga_thumbnail.jpg', 10),
('Meditation Techniques', 6, 15, 1, 'Meditation and relaxation techniques', 11, '2023-08-11', '2023-08-20', 'meditation_thumbnail.jpg', 8),
('Environmental Science', 6, 30, 1, 'Introduction to environmental science', 12, '2023-09-11', '2023-09-20', 'environmental_thumbnail.jpg', 14),
('Geography 101', 6, 35, 1, 'Basic geography course', 12, '2023-10-11', '2023-10-20', 'geography_thumbnail.jpg', 16),
('History of Art', 6, 40, 1, 'Overview of art history', 12, '2023-11-11', '2023-11-20', 'arthistory_thumbnail.jpg', 20),
('Philosophy Basics', 6, 25, 1, 'Introduction to philosophy', 12, '2023-12-11', '2023-12-20', 'philosophy_thumbnail.jpg', 12),
('Statistics for Beginners', 6, 30, 1, 'Basic statistics course', 12, '2023-01-21', '2023-01-30', 'statistics_thumbnail.jpg', 15),
('Machine Learning', 6, 45, 1, 'Introduction to machine learning', 7, '2023-02-21', '2023-02-28', 'machinelearning_thumbnail.jpg', 20),
('Artificial Intelligence', 6, 50, 1, 'Overview of AI techniques', 7, '2023-03-21', '2023-03-30', 'ai_thumbnail.jpg', 22),
('Business Analytics', 6, 40, 1, 'Using data in business', 8, '2023-04-21', '2023-04-30', 'businessanalytics_thumbnail.jpg', 18),
('Advanced Excel', 6, 30, 1, 'Advanced features in Excel', 8, '2023-06-21', '2023-06-30', 'excel_thumbnail.jpg', 15);

-- Chèn dữ liệu cho bảng PricePackage với các giá trị duration hợp lệ
INSERT INTO PricePackage (course_id, title, price, sale_price, duration)
VALUES 
(1,'Basic Package', 1000, 800, 90),   -- 3 tháng
(1,'Premium Package', 2000, 1600, 180), -- 6 tháng
(2,'Standard Package', 1200, 1000, 365), -- 12 tháng
(2,'Lifetime Package', 5000, 4000, 36500), -- Vĩnh viễn
(3,'Basic Package', 1500, 1300, 90), -- 3 tháng
(3,'Premium Package', 3000, 2700, 180), -- 6 tháng
(4,'Basic Package', 1800, 1500, 90), -- 3 tháng
(4,'Premium Package', 3600, 3200, 180), -- 6 tháng
(5,'Business Package', 2500, 2000, 365), -- 12 tháng
(6,'Lifetime Package', 10000, 8000, 36500), -- Vĩnh viễn
(7,'Introductory Package', 2000, 1800, 90), -- 3 tháng
(7,'Complete Package', 5000, 4000, 365), -- 12 tháng
(7,'Beginner Package', 1300, 1000, 90), -- 3 tháng
(7,'Advanced Package', 2600, 2200, 180); -- 6 tháng



-- Bảng UserLog ko can insert
-- INSERT INTO UserLog (created_date, type_id, account_id)

-- Bảng Blog  
INSERT INTO Blog (updated_date, created_date, category_id, thumbnail_url, title, brief_info, author_id, blog_detail, status)
VALUES 
('2023-01-10', '2023-01-01', 15, 'blog_thumbnail1.jpg', 'Blog Title 1', 'Brief info about blog 1', 7, 'Full blog detail 1', 1),
('2023-01-11', '2023-01-02', 16, 'blog_thumbnail2.jpg', 'Blog Title 2', 'Brief info about blog 2', 7, 'Full blog detail 2', 1),
('2023-01-12', '2023-01-03', 17, 'blog_thumbnail3.jpg', 'Blog Title 3', 'Brief info about blog 3', 7, 'Full blog detail 3', 1),
('2023-01-13', '2023-01-04', 15, 'blog_thumbnail4.jpg', 'Blog Title 4', 'Brief info about blog 4', 7, 'Full blog detail 4', 1),
('2023-01-14', '2023-01-05', 16, 'blog_thumbnail5.jpg', 'Blog Title 5', 'Brief info about blog 5', 7, 'Full blog detail 5', 1),
('2023-01-15', '2023-01-06', 17, 'blog_thumbnail6.jpg', 'Blog Title 6', 'Brief info about blog 6', 7, 'Full blog detail 6', 1),
('2023-01-16', '2023-01-07', 15, 'blog_thumbnail7.jpg', 'Blog Title 7', 'Brief info about blog 7', 7, 'Full blog detail 7', 1),
('2023-01-17', '2023-01-08', 16, 'blog_thumbnail8.jpg', 'Blog Title 8', 'Brief info about blog 8', 7, 'Full blog detail 8', 1),
('2023-01-18', '2023-01-09', 17, 'blog_thumbnail9.jpg', 'Blog Title 9', 'Brief info about blog 9', 7, 'Full blog detail 9', 1),
('2023-01-19', '2023-01-10', 15, 'blog_thumbnail10.jpg', 'Blog Title 10', 'Brief info about blog 10', 7, 'Full blog detail 10', 1),
('2023-01-20', '2023-01-11', 16, 'blog_thumbnail11.jpg', 'Blog Title 11', 'Brief info about blog 11', 7, 'Full blog detail 11', 1),
('2023-01-21', '2023-01-12', 17, 'blog_thumbnail12.jpg', 'Blog Title 12', 'Brief info about blog 12', 7, 'Full blog detail 12', 1),
('2023-01-22', '2023-01-13', 15, 'blog_thumbnail13.jpg', 'Blog Title 13', 'Brief info about blog 13', 7, 'Full blog detail 13', 1),
('2023-01-23', '2023-01-14', 16, 'blog_thumbnail14.jpg', 'Blog Title 14', 'Brief info about blog 14', 7, 'Full blog detail 14', 1),
('2023-01-24', '2023-01-15', 17, 'blog_thumbnail15.jpg', 'Blog Title 15', 'Brief info about blog 15', 7, 'Full blog detail 15', 1),
('2023-01-25', '2023-01-16', 15, 'blog_thumbnail16.jpg', 'Blog Title 16', 'Brief info about blog 16', 7, 'Full blog detail 16', 1),
('2023-01-26', '2023-01-17', 16, 'blog_thumbnail17.jpg', 'Blog Title 17', 'Brief info about blog 17', 7, 'Full blog detail 17', 1),
('2023-01-27', '2023-01-18', 17, 'blog_thumbnail18.jpg', 'Blog Title 18', 'Brief info about blog 18', 7, 'Full blog detail 18', 1),
('2023-01-28', '2023-01-19', 15, 'blog_thumbnail19.jpg', 'Blog Title 19', 'Brief info about blog 19', 7, 'Full blog detail 19', 1),
('2023-01-29', '2023-01-20', 16, 'blog_thumbnail20.jpg', 'Blog Title 20', 'Brief info about blog 20', 7, 'Full blog detail 20', 1),
('2023-01-30', '2023-01-21', 17, 'blog_thumbnail21.jpg', 'Blog Title 21', 'Brief info about blog 21', 7, 'Full blog detail 21', 1),
('2023-01-31', '2023-01-22', 15, 'blog_thumbnail22.jpg', 'Blog Title 22', 'Brief info about blog 22', 7, 'Full blog detail 22', 1),
('2023-02-01', '2023-01-23', 16, 'blog_thumbnail23.jpg', 'Blog Title 23', 'Brief info about blog 23', 7, 'Full blog detail 23', 1),
('2023-02-02', '2023-01-24', 17, 'blog_thumbnail24.jpg', 'Blog Title 24', 'Brief info about blog 24', 7, 'Full blog detail 24', 1),
('2023-02-03', '2023-01-25', 15, 'blog_thumbnail25.jpg', 'Blog Title 25', 'Brief info about blog 25', 7, 'Full blog detail 25', 1),
('2023-02-04', '2023-01-26', 16, 'blog_thumbnail26.jpg', 'Blog Title 26', 'Brief info about blog 26', 7, 'Full blog detail 26', 1),
('2023-02-05', '2023-01-27', 17, 'blog_thumbnail27.jpg', 'Blog Title 27', 'Brief info about blog 27', 7, 'Full blog detail 27', 1),
('2023-02-06', '2023-01-28', 15, 'blog_thumbnail28.jpg', 'Blog Title 28', 'Brief info about blog 28', 7, 'Full blog detail 28', 1),
('2023-02-07', '2023-01-29', 16, 'blog_thumbnail29.jpg', 'Blog Title 29', 'Brief info about blog 29', 7, 'Full blog detail 29', 1),
('2023-02-10', '2023-01-30', 17, 'blog_thumbnail30.jpg', 'Blog Title 30', 'Brief info about blog 30', 7, 'Full blog detail 30', 1);


-- Bảng Slider  
INSERT INTO Slider (author_id, image_url, backlink_url, status)
VALUES 
(7, 'slider_image1.jpg', 'http://example.com/1', 1),
(7, 'slider_image2.jpg', 'http://example.com/2', 1),
(7, 'slider_image3.jpg', 'http://example.com/3', 1),
(7, 'slider_image4.jpg', 'http://example.com/4', 1),
(7, 'slider_image5.jpg', 'http://example.com/5', 1),
(7, 'slider_image6.jpg', 'http://example.com/6', 1),
(7, 'slider_image7.jpg', 'http://example.com/7', 1),
(7, 'slider_image8.jpg', 'http://example.com/8', 1),
(7, 'slider_image9.jpg', 'http://example.com/9', 1),
(7, 'slider_image10.jpg', 'http://example.com/10', 1),
(7, 'slider_image11.jpg', 'http://example.com/11', 1),
(7, 'slider_image12.jpg', 'http://example.com/12', 1),
(7, 'slider_image13.jpg', 'http://example.com/13', 1),
(7, 'slider_image14.jpg', 'http://example.com/14', 1),
(7, 'slider_image15.jpg', 'http://example.com/15', 1),
(7, 'slider_image16.jpg', 'http://example.com/16', 1),
(7, 'slider_image17.jpg', 'http://example.com/17', 1),
(7, 'slider_image18.jpg', 'http://example.com/18', 1),
(7, 'slider_image19.jpg', 'http://example.com/19', 1),
(7, 'slider_image20.jpg', 'http://example.com/20', 1),
(7, 'slider_image21.jpg', 'http://example.com/21', 1),
(7, 'slider_image22.jpg', 'http://example.com/22', 1),
(7, 'slider_image23.jpg', 'http://example.com/23', 1),
(7, 'slider_image24.jpg', 'http://example.com/24', 1),
(7, 'slider_image25.jpg', 'http://example.com/25', 1),
(7, 'slider_image26.jpg', 'http://example.com/26', 1),
(7, 'slider_image27.jpg', 'http://example.com/27', 1),
(7, 'slider_image28.jpg', 'http://example.com/28', 1),
(7, 'slider_image29.jpg', 'http://example.com/29', 1),
(7, 'slider_image30.jpg', 'http://example.com/30', 1);

-- Đoạn phía dưới chưa cần đến nên chưa chạy
-- Bảng Lesson
INSERT INTO Lesson (course_id, title, status, lesson_type_id)
VALUES 
(1, 'Lesson 1 of Java', 1, (SELECT id FROM Setting WHERE value='Subject Topic')),
(1, 'Lesson 2 of Java', 1, (SELECT id FROM Setting WHERE value='Learning Material')),
(2, 'Lesson 1 of Python', 1, (SELECT id FROM Setting WHERE value='Quiz')),
(2, 'Lesson 2 of Python', 1, (SELECT id FROM Setting WHERE value='Subject Topic')),
(3, 'Lesson 1 of Web Development', 1, (SELECT id FROM Setting WHERE value='Learning Material')),
(3, 'Lesson 2 of Web Development', 1, (SELECT id FROM Setting WHERE value='Quiz')),
(4, 'Lesson 1 of Digital Marketing', 1, (SELECT id FROM Setting WHERE value='Subject Topic')),
(4, 'Lesson 2 of Digital Marketing', 1, (SELECT id FROM Setting WHERE value='Learning Material')),
(5, 'Lesson 1 of Business Management', 1, (SELECT id FROM Setting WHERE value='Quiz')),
(5, 'Lesson 2 of Business Management', 1, (SELECT id FROM Setting WHERE value='Subject Topic')),
(6, 'Lesson 1 of Data Science', 1, (SELECT id FROM Setting WHERE value='Learning Material')),
(6, 'Lesson 2 of Data Science', 1, (SELECT id FROM Setting WHERE value='Quiz')),
(7, 'Lesson 1 of English for Beginners', 1, (SELECT id FROM Setting WHERE value='Subject Topic')),
(7, 'Lesson 2 of English for Beginners', 1, (SELECT id FROM Setting WHERE value='Learning Material')),
(8, 'Lesson 1 of Photography Basics', 1, (SELECT id FROM Setting WHERE value='Quiz')),
(8, 'Lesson 2 of Photography Basics', 1, (SELECT id FROM Setting WHERE value='Subject Topic')),
(9, 'Lesson 1 of Advanced Photoshop', 1, (SELECT id FROM Setting WHERE value='Learning Material')),
(9, 'Lesson 2 of Advanced Photoshop', 1, (SELECT id FROM Setting WHERE value='Quiz')),
(10, 'Lesson 1 of Healthy Living', 1, (SELECT id FROM Setting WHERE value='Subject Topic')),
(10, 'Lesson 2 of Healthy Living', 1, (SELECT id FROM Setting WHERE value='Learning Material')),
(11, 'Lesson 1 of Physics Fundamentals', 1, (SELECT id FROM Setting WHERE value='Quiz')),
(11, 'Lesson 2 of Physics Fundamentals', 1, (SELECT id FROM Setting WHERE value='Subject Topic')),
(12, 'Lesson 1 of Chemistry 101', 1, (SELECT id FROM Setting WHERE value='Learning Material')),
(12, 'Lesson 2 of Chemistry 101', 1, (SELECT id FROM Setting WHERE value='Quiz')),
(13, 'Lesson 1 of Biology Basics', 1, (SELECT id FROM Setting WHERE value='Subject Topic')),
(13, 'Lesson 2 of Biology Basics', 1, (SELECT id FROM Setting WHERE value='Learning Material')),
(14, 'Lesson 1 of Public Speaking', 1, (SELECT id FROM Setting WHERE value='Quiz')),
(14, 'Lesson 2 of Public Speaking', 1, (SELECT id FROM Setting WHERE value='Subject Topic')),
(15, 'Lesson 1 of Project Management', 1, (SELECT id FROM Setting WHERE value='Learning Material')),
(15, 'Lesson 2 of Project Management', 1, (SELECT id FROM Setting WHERE value='Quiz'));


-- Bảng LearningMaterial
INSERT INTO LearningMaterial (lesson_id, updated_date, duration, video_content_url, html_content, title)
VALUES 
(1, '2023-01-05', 10, 'video1.mp4', 'HTML content 1', 'Learning Material 1'),
(2, '2023-01-06', 20, 'video2.mp4', 'HTML content 2', 'Learning Material 2'),
(3, '2023-01-07', 15, 'video3.mp4', 'HTML content 3', 'Learning Material 3'),
(4, '2023-01-08', 25, 'video4.mp4', 'HTML content 4', 'Learning Material 4'),
(5, '2023-01-09', 30, 'video5.mp4', 'HTML content 5', 'Learning Material 5'),
(6, '2023-01-10', 35, 'video6.mp4', 'HTML content 6', 'Learning Material 6'),
(7, '2023-01-11', 40, 'video7.mp4', 'HTML content 7', 'Learning Material 7'),
(8, '2023-01-12', 45, 'video8.mp4', 'HTML content 8', 'Learning Material 8'),
(9, '2023-01-13', 50, 'video9.mp4', 'HTML content 9', 'Learning Material 9'),
(10, '2023-01-14', 55, 'video10.mp4', 'HTML content 10', 'Learning Material 10'),
(11, '2023-01-15', 10, 'video11.mp4', 'HTML content 11', 'Learning Material 11'),
(12, '2023-01-16', 20, 'video12.mp4', 'HTML content 12', 'Learning Material 12'),
(13, '2023-01-17', 15, 'video13.mp4', 'HTML content 13', 'Learning Material 13'),
(14, '2023-01-18', 25, 'video14.mp4', 'HTML content 14', 'Learning Material 14'),
(15, '2023-01-19', 30, 'video15.mp4', 'HTML content 15', 'Learning Material 15'),
(16, '2023-01-20', 35, 'video16.mp4', 'HTML content 16', 'Learning Material 16'),
(17, '2023-01-21', 40, 'video17.mp4', 'HTML content 17', 'Learning Material 17'),
(18, '2023-01-22', 45, 'video18.mp4', 'HTML content 18', 'Learning Material 18'),
(19, '2023-01-23', 50, 'video19.mp4', 'HTML content 19', 'Learning Material 19'),
(20, '2023-01-24', 55, 'video20.mp4', 'HTML content 20', 'Learning Material 20'),
(21, '2023-01-25', 10, 'video21.mp4', 'HTML content 21', 'Learning Material 21'),
(22, '2023-01-26', 20, 'video22.mp4', 'HTML content 22', 'Learning Material 22'),
(23, '2023-01-27', 15, 'video23.mp4', 'HTML content 23', 'Learning Material 23'),
(24, '2023-01-28', 25, 'video24.mp4', 'HTML content 24', 'Learning Material 24'),
(25, '2023-01-29', 30, 'video25.mp4', 'HTML content 25', 'Learning Material 25'),
(26, '2023-01-30', 35, 'video26.mp4', 'HTML content 26', 'Learning Material 26'),
(27, '2023-01-31', 40, 'video27.mp4', 'HTML content 27', 'Learning Material 27'),
(28, '2023-02-01', 45, 'video28.mp4', 'HTML content 28', 'Learning Material 28'),
(29, '2023-02-02', 50, 'video29.mp4', 'HTML content 29', 'Learning Material 29'),
(30, '2023-02-05', 15, 'video30.mp4', 'HTML content 30', 'Learning Material 30');


-- Bảng SubjectTopic
INSERT INTO SubjectTopic (lesson_id, title)
VALUES 
(1, 'Subject Topic 1 of Lesson 1'),
(2, 'Subject Topic 2 of Lesson 2'),
(3, 'Subject Topic 3 of Lesson 3'),
(4, 'Subject Topic 4 of Lesson 4'),
(5, 'Subject Topic 5 of Lesson 5'),
(6, 'Subject Topic 6 of Lesson 6'),
(7, 'Subject Topic 7 of Lesson 7'),
(8, 'Subject Topic 8 of Lesson 8'),
(9, 'Subject Topic 9 of Lesson 9'),
(10, 'Subject Topic 10 of Lesson 10'),
(11, 'Subject Topic 11 of Lesson 11'),
(12, 'Subject Topic 12 of Lesson 12'),
(13, 'Subject Topic 13 of Lesson 13'),
(14, 'Subject Topic 14 of Lesson 14'),
(15, 'Subject Topic 15 of Lesson 15'),
(16, 'Subject Topic 16 of Lesson 16'),
(17, 'Subject Topic 17 of Lesson 17'),
(18, 'Subject Topic 18 of Lesson 18'),
(19, 'Subject Topic 19 of Lesson 19'),
(20, 'Subject Topic 20 of Lesson 20'),
(21, 'Subject Topic 21 of Lesson 21'),
(22, 'Subject Topic 22 of Lesson 22'),
(23, 'Subject Topic 23 of Lesson 23'),
(24, 'Subject Topic 24 of Lesson 24'),
(25, 'Subject Topic 25 of Lesson 25'),
(26, 'Subject Topic 26 of Lesson 26'),
(27, 'Subject Topic 27 of Lesson 27'),
(28, 'Subject Topic 28 of Lesson 28'),
(29, 'Subject Topic 29 of Lesson 29'),
(30, 'Subject Topic 30 of Lesson 30');

-- Bảng Quiz
INSERT INTO Quiz (lesson_id, duration, pass_rate, updated_date, title, description)
VALUES 
(1, 30, 0.6, '2023-01-15', 'Quiz 1', 'Description for quiz 1'),
(2, 20, 0.7, '2023-01-16', 'Quiz 2', 'Description for quiz 2'),
(3, 25, 0.8, '2023-01-17', 'Quiz 3', 'Description for quiz 3'),
(4, 30, 0.6, '2023-01-18', 'Quiz 4', 'Description for quiz 4'),
(5, 20, 0.7, '2023-01-19', 'Quiz 5', 'Description for quiz 5'),
(6, 25, 0.8, '2023-01-20', 'Quiz 6', 'Description for quiz 6'),
(7, 30, 0.6, '2023-01-21', 'Quiz 7', 'Description for quiz 7'),
(8, 20, 0.7, '2023-01-22', 'Quiz 8', 'Description for quiz 8'),
(9, 25, 0.8, '2023-01-23', 'Quiz 9', 'Description for quiz 9'),
(10, 30, 0.6, '2023-01-24', 'Quiz 10', 'Description for quiz 10'),
(11, 20, 0.7, '2023-01-25', 'Quiz 11', 'Description for quiz 11'),
(12, 25, 0.8, '2023-01-26', 'Quiz 12', 'Description for quiz 12'),
(13, 30, 0.6, '2023-01-27', 'Quiz 13', 'Description for quiz 13'),
(14, 20, 0.7, '2023-01-28', 'Quiz 14', 'Description for quiz 14'),
(15, 25, 0.8, '2023-01-29', 'Quiz 15', 'Description for quiz 15'),
(16, 30, 0.6, '2023-01-30', 'Quiz 16', 'Description for quiz 16'),
(17, 20, 0.7, '2023-01-31', 'Quiz 17', 'Description for quiz 17'),
(18, 25, 0.8, '2023-02-01', 'Quiz 18', 'Description for quiz 18'),
(19, 30, 0.6, '2023-02-02', 'Quiz 19', 'Description for quiz 19'),
(20, 20, 0.7, '2023-02-03', 'Quiz 20', 'Description for quiz 20'),
(21, 25, 0.8, '2023-02-04', 'Quiz 21', 'Description for quiz 21'),
(22, 30, 0.6, '2023-02-05', 'Quiz 22', 'Description for quiz 22'),
(23, 20, 0.7, '2023-02-06', 'Quiz 23', 'Description for quiz 23'),
(24, 25, 0.8, '2023-02-07', 'Quiz 24', 'Description for quiz 24'),
(25, 30, 0.6, '2023-02-08', 'Quiz 25', 'Description for quiz 25'),
(26, 20, 0.7, '2023-02-09', 'Quiz 26', 'Description for quiz 26'),
(27, 25, 0.8, '2023-02-10', 'Quiz 27', 'Description for quiz 27'),
(28, 30, 0.6, '2023-02-11', 'Quiz 28', 'Description for quiz 28'),
(29, 20, 0.7, '2023-02-12', 'Quiz 29', 'Description for quiz 29'),
(30, 25, 0.8, '2023-02-15', 'Quiz 30', 'Description for quiz 30');

-- Bảng Question
INSERT INTO Question (quiz_id, content, update_date, number_of_question, level_id)
VALUES 
(1, 'Question 1 of Quiz 1', '2023-01-05', 1, (SELECT id FROM Setting WHERE value='Easy')),
(1, 'Question 2 of Quiz 1', '2023-01-06', 2, (SELECT id FROM Setting WHERE value='Medium')),
(2, 'Question 1 of Quiz 2', '2023-01-07', 1, (SELECT id FROM Setting WHERE value='Easy')),
(2, 'Question 2 of Quiz 2', '2023-01-08', 2, (SELECT id FROM Setting WHERE value='Medium')),
(3, 'Question 1 of Quiz 3', '2023-01-09', 1, (SELECT id FROM Setting WHERE value='Easy')),
(3, 'Question 2 of Quiz 3', '2023-01-10', 2, (SELECT id FROM Setting WHERE value='Medium')),
(4, 'Question 1 of Quiz 4', '2023-01-11', 1, (SELECT id FROM Setting WHERE value='Easy')),
(4, 'Question 2 of Quiz 4', '2023-01-12', 2, (SELECT id FROM Setting WHERE value='Medium')),
(5, 'Question 1 of Quiz 5', '2023-01-13', 1, (SELECT id FROM Setting WHERE value='Easy')),
(5, 'Question 2 of Quiz 5', '2023-01-14', 2, (SELECT id FROM Setting WHERE value='Medium')),
(6, 'Question 1 of Quiz 6', '2023-01-15', 1, (SELECT id FROM Setting WHERE value='Easy')),
(6, 'Question 2 of Quiz 6', '2023-01-16', 2, (SELECT id FROM Setting WHERE value='Medium')),
(7, 'Question 1 of Quiz 7', '2023-01-17', 1, (SELECT id FROM Setting WHERE value='Easy')),
(7, 'Question 2 of Quiz 7', '2023-01-18', 2, (SELECT id FROM Setting WHERE value='Medium')),
(8, 'Question 1 of Quiz 8', '2023-01-19', 1, (SELECT id FROM Setting WHERE value='Easy')),
(8, 'Question 2 of Quiz 8', '2023-01-20', 2, (SELECT id FROM Setting WHERE value='Medium')),
(9, 'Question 1 of Quiz 9', '2023-01-21', 1, (SELECT id FROM Setting WHERE value='Easy')),
(9, 'Question 2 of Quiz 9', '2023-01-22', 2, (SELECT id FROM Setting WHERE value='Medium')),
(10, 'Question 1 of Quiz 10', '2023-01-23', 1, (SELECT id FROM Setting WHERE value='Easy')),
(10, 'Question 2 of Quiz 10', '2023-01-24', 2, (SELECT id FROM Setting WHERE value='Medium')),
(11, 'Question 1 of Quiz 11', '2023-01-25', 1, (SELECT id FROM Setting WHERE value='Easy')),
(11, 'Question 2 of Quiz 11', '2023-01-26', 2, (SELECT id FROM Setting WHERE value='Medium')),
(12, 'Question 1 of Quiz 12', '2023-01-27', 1, (SELECT id FROM Setting WHERE value='Easy')),
(12, 'Question 2 of Quiz 12', '2023-01-28', 2, (SELECT id FROM Setting WHERE value='Medium')),
(13, 'Question 1 of Quiz 13', '2023-01-29', 1, (SELECT id FROM Setting WHERE value='Easy')),
(13, 'Question 2 of Quiz 13', '2023-01-30', 2, (SELECT id FROM Setting WHERE value='Medium')),
(14, 'Question 1 of Quiz 14', '2023-01-31', 1, (SELECT id FROM Setting WHERE value='Easy')),
(14, 'Question 2 of Quiz 14', '2023-02-01', 2, (SELECT id FROM Setting WHERE value='Medium')),
(15, 'Question 1 of Quiz 15', '2023-02-02', 1, (SELECT id FROM Setting WHERE value='Easy')),
(15, 'Question 2 of Quiz 15', '2023-02-03', 2, (SELECT id FROM Setting WHERE value='Medium')),
(16, 'Question 1 of Quiz 16', '2023-02-04', 1, (SELECT id FROM Setting WHERE value='Easy')),
(16, 'Question 2 of Quiz 16', '2023-02-05', 2, (SELECT id FROM Setting WHERE value='Medium')),
(17, 'Question 1 of Quiz 17', '2023-02-06', 1, (SELECT id FROM Setting WHERE value='Easy')),
(17, 'Question 2 of Quiz 17', '2023-02-07', 2, (SELECT id FROM Setting WHERE value='Medium')),
(18, 'Question 1 of Quiz 18', '2023-02-08', 1, (SELECT id FROM Setting WHERE value='Easy')),
(18, 'Question 2 of Quiz 18', '2023-02-09', 2, (SELECT id FROM Setting WHERE value='Medium')),
(19, 'Question 1 of Quiz 19', '2023-02-10', 1, (SELECT id FROM Setting WHERE value='Easy')),
(19, 'Question 2 of Quiz 19', '2023-02-11', 2, (SELECT id FROM Setting WHERE value='Medium')),
(20, 'Question 1 of Quiz 20', '2023-02-12', 1, (SELECT id FROM Setting WHERE value='Easy')),
(20, 'Question 2 of Quiz 20', '2023-02-13', 2, (SELECT id FROM Setting WHERE value='Medium')),
(21, 'Question 1 of Quiz 21', '2023-02-14', 1, (SELECT id FROM Setting WHERE value='Easy')),
(21, 'Question 2 of Quiz 21', '2023-02-15', 2, (SELECT id FROM Setting WHERE value='Medium')),
(22, 'Question 1 of Quiz 22', '2023-02-16', 1, (SELECT id FROM Setting WHERE value='Easy')),
(22, 'Question 2 of Quiz 22', '2023-02-17', 2, (SELECT id FROM Setting WHERE value='Medium')),
(23, 'Question 1 of Quiz 23', '2023-02-18', 1, (SELECT id FROM Setting WHERE value='Easy')),
(23, 'Question 2 of Quiz 23', '2023-02-19', 2, (SELECT id FROM Setting WHERE value='Medium')),
(24, 'Question 1 of Quiz 24', '2023-02-20', 1, (SELECT id FROM Setting WHERE value='Easy')),
(24, 'Question 2 of Quiz 24', '2023-02-21', 2, (SELECT id FROM Setting WHERE value='Medium')),
(25, 'Question 1 of Quiz 25', '2023-02-22', 1, (SELECT id FROM Setting WHERE value='Easy')),
(25, 'Question 2 of Quiz 25', '2023-02-23', 2, (SELECT id FROM Setting WHERE value='Medium')),
(26, 'Question 1 of Quiz 26', '2023-02-24', 1, (SELECT id FROM Setting WHERE value='Easy')),
(26, 'Question 2 of Quiz 26', '2023-02-25', 2, (SELECT id FROM Setting WHERE value='Medium')),
(27, 'Question 1 of Quiz 27', '2023-02-26', 1, (SELECT id FROM Setting WHERE value='Easy')),
(27, 'Question 2 of Quiz 27', '2023-02-27', 2, (SELECT id FROM Setting WHERE value='Medium')),
(28, 'Question 1 of Quiz 28', '2023-02-28', 1, (SELECT id FROM Setting WHERE value='Easy')),
(28, 'Question 2 of Quiz 28', '2023-03-01', 2, (SELECT id FROM Setting WHERE value='Medium')),
(29, 'Question 1 of Quiz 29', '2023-03-02', 1, (SELECT id FROM Setting WHERE value='Easy')),
(29, 'Question 2 of Quiz 29', '2023-03-03', 2, (SELECT id FROM Setting WHERE value='Medium')),
(30, 'Question 1 of Quiz 30', '2023-03-04', 1, (SELECT id FROM Setting WHERE value='Easy')),
(30, 'Question 2 of Quiz 30', '2023-03-05', 2, (SELECT id FROM Setting WHERE value='Medium'));

-- Bảng Option
INSERT INTO `Option` (question_id, title, isTrue, explanation)
VALUES 
(1, 'Option 1 of Question 1', 1, 'Explanation for option 1'),
(2, 'Option 2 of Question 2', 0, 'Explanation for option 2'),
(3, 'Option 1 of Question 3', 1, 'Explanation for option 3'),
(4, 'Option 2 of Question 4', 0, 'Explanation for option 4'),
(5, 'Option 1 of Question 5', 1, 'Explanation for option 5'),
(6, 'Option 2 of Question 6', 0, 'Explanation for option 6'),
(7, 'Option 1 of Question 7', 1, 'Explanation for option 7'),
(8, 'Option 2 of Question 8', 0, 'Explanation for option 8'),
(9, 'Option 1 of Question 9', 1, 'Explanation for option 9'),
(10, 'Option 2 of Question 10', 0, 'Explanation for option 10'),
(11, 'Option 1 of Question 11', 1, 'Explanation for option 11'),
(12, 'Option 2 of Question 12', 0, 'Explanation for option 12'),
(13, 'Option 1 of Question 13', 1, 'Explanation for option 13'),
(14, 'Option 2 of Question 14', 0, 'Explanation for option 14'),
(15, 'Option 1 of Question 15', 1, 'Explanation for option 15'),
(16, 'Option 2 of Question 16', 0, 'Explanation for option 16'),
(17, 'Option 1 of Question 17', 1, 'Explanation for option 17'),
(18, 'Option 2 of Question 18', 0, 'Explanation for option 18'),
(19, 'Option 1 of Question 19', 1, 'Explanation for option 19'),
(20, 'Option 2 of Question 20', 0, 'Explanation for option 20'),
(21, 'Option 1 of Question 21', 1, 'Explanation for option 21'),
(22, 'Option 2 of Question 22', 0, 'Explanation for option 22'),
(23, 'Option 1 of Question 23', 1, 'Explanation for option 23'),
(24, 'Option 2 of Question 24', 0, 'Explanation for option 24'),
(25, 'Option 1 of Question 25', 1, 'Explanation for option 25'),
(26, 'Option 2 of Question 26', 0, 'Explanation for option 26'),
(27, 'Option 1 of Question 27', 1, 'Explanation for option 27'),
(28, 'Option 2 of Question 28', 0, 'Explanation for option 28'),
(29, 'Option 1 of Question 29', 1, 'Explanation for option 29'),
(30, 'Option 2 of Question 30', 0, 'Explanation for option 30'),
(31, 'Option 1 of Question 31', 1, 'Explanation for option 31'),
(32, 'Option 2 of Question 32', 0, 'Explanation for option 32'),
(33, 'Option 1 of Question 33', 1, 'Explanation for option 33'),
(34, 'Option 2 of Question 34', 0, 'Explanation for option 34'),
(35, 'Option 1 of Question 35', 1, 'Explanation for option 35'),
(36, 'Option 2 of Question 36', 0, 'Explanation for option 36'),
(37, 'Option 1 of Question 37', 1, 'Explanation for option 37'),
(38, 'Option 2 of Question 38', 0, 'Explanation for option 38'),
(39, 'Option 1 of Question 39', 1, 'Explanation for option 39'),
(40, 'Option 2 of Question 40', 0, 'Explanation for option 40'),
(41, 'Option 1 of Question 41', 1, 'Explanation for option 41'),
(42, 'Option 2 of Question 42', 0, 'Explanation for option 42'),
(43, 'Option 1 of Question 43', 1, 'Explanation for option 43'),
(44, 'Option 2 of Question 44', 0, 'Explanation for option 44'),
(45, 'Option 1 of Question 45', 1, 'Explanation for option 45'),
(46, 'Option 2 of Question 46', 0, 'Explanation for option 46'),
(47, 'Option 1 of Question 47', 1, 'Explanation for option 47'),
(48, 'Option 2 of Question 48', 0, 'Explanation for option 48'),
(49, 'Option 1 of Question 49', 1, 'Explanation for option 49'),
(50, 'Option 2 of Question 50', 0, 'Explanation for option 50'),
(51, 'Option 1 of Question 51', 1, 'Explanation for option 51'),
(52, 'Option 2 of Question 52', 0, 'Explanation for option 52'),
(53, 'Option 1 of Question 53', 1, 'Explanation for option 53'),
(54, 'Option 2 of Question 54', 0, 'Explanation for option 54'),
(55, 'Option 1 of Question 55', 1, 'Explanation for option 55'),
(56, 'Option 2 of Question 56', 0, 'Explanation for option 56'),
(57, 'Option 1 of Question 57', 1, 'Explanation for option 57'),
(58, 'Option 2 of Question 58', 0, 'Explanation for option 58'),
(59, 'Option 1 of Question 59', 1, 'Explanation for option 59'),
(60, 'Option 2 of Question 60', 0, 'Explanation for option 60');

-- Bảng PersonalCourse  
INSERT INTO PersonalCourse (customer_id, course_id, price_package_id, status, enroll_date, progress)
VALUES 
(1, 1, 1, 1, '2023-01-10', 50),
(2, 2, 2, 1, '2023-01-11', 75),
(3, 3, 3, 1, '2023-01-12', 60),
(4, 4, 4, 1, '2023-01-13', 40),
(5, 5, 5, 1, '2023-01-14', 80),
(6, 6, 6, 1, '2023-01-15', 70),
(7, 7, 7, 1, '2023-01-16', 90),
(8, 8, 8, 1, '2023-01-17', 100),
(9, 9, 9, 1, '2023-01-18', 50),
(10, 10, 10, 1, '2023-01-19', 60),
(11, 11, 11, 1, '2023-01-20', 30),
(12, 12, 12, 1, '2023-01-21', 40),
(13, 13, 13, 1, '2023-01-22', 20),
(14, 14, 14, 1, '2023-01-23', 10),
(15, 15, 15, 1, '2023-01-24', 90),
(16, 16, 16, 1, '2023-01-25', 85),
(17, 17, 17, 1, '2023-01-26', 95),
(18, 18, 18, 1, '2023-01-27', 20),
(19, 19, 19, 1, '2023-01-28', 10),
(20, 20, 20, 1, '2023-01-29', 100),
(21, 21, 21, 1, '2023-01-30', 70),
(22, 22, 22, 1, '2023-01-31', 50),
(23, 23, 23, 1, '2023-02-01', 65),
(24, 24, 24, 1, '2023-02-02', 75),
(25, 25, 25, 1, '2023-02-03', 45),
(26, 26, 26, 1, '2023-02-04', 55),
(27, 27, 27, 1, '2023-02-05', 85),
(28, 28, 28, 1, '2023-02-06', 60),
(29, 29, 29, 1, '2023-02-07', 35),
(30, 30, 30, 1, '2023-02-08', 25);

-- Bảng PersonalLesson  
INSERT INTO PersonalLesson (customer_id, course_id, lesson_id, isDone)
VALUES 
(1, 1, 1, 1),
(2, 2, 2, 0),
(3, 3, 3, 1),
(4, 4, 4, 0),
(5, 5, 5, 1),
(6, 6, 6, 0),
(7, 7, 7, 1),
(8, 8, 8, 0),
(9, 9, 9, 1),
(10, 10, 10, 0),
(11, 11, 11, 1),
(12, 12, 12, 0),
(13, 13, 13, 1),
(14, 14, 14, 0),
(15, 15, 15, 1),
(16, 16, 16, 0),
(17, 17, 17, 1),
(18, 18, 18, 0),
(19, 19, 19, 1),
(20, 20, 20, 0),
(21, 21, 21, 1),
(22, 22, 22, 0),
(23, 23, 23, 1),
(24, 24, 24, 0),
(25, 25, 25, 1),
(26, 26, 26, 0),
(27, 27, 27, 1),
(28, 28, 28, 0),
(29, 29, 29, 1),
(30, 30, 30, 0);

-- Bảng PersonalQuiz  
INSERT INTO PersonalQuiz (customer_id, course_id, lesson_id, quiz_id, hasPassed)
VALUES 
(1, 1, 1, 1, 1),
(2, 2, 2, 2, 0),
(3, 3, 3, 3, 1),
(4, 4, 4, 4, 0),
(5, 5, 5, 5, 1),
(6, 6, 6, 6, 0),
(7, 7, 7, 7, 1),
(8, 8, 8, 8, 0),
(9, 9, 9, 9, 1),
(10, 10, 10, 10, 0),
(11, 11, 11, 11, 1),
(12, 12, 12, 12, 0),
(13, 13, 13, 13, 1),
(14, 14, 14, 14, 0),
(15, 15, 15, 15, 1),
(16, 16, 16, 16, 0),
(17, 17, 17, 17, 1),
(18, 18, 18, 18, 0),
(19, 19, 19, 19, 1),
(20, 20, 20, 20, 0),
(21, 21, 21, 21, 1),
(22, 22, 22, 22, 0),
(23, 23, 23, 23, 1),
(24, 24, 24, 24, 0),
(25, 25, 25, 25, 1),
(26, 26, 26, 26, 0),
(27, 27, 27, 27, 1),
(28, 28, 28, 28, 0),
(29, 29, 29, 29, 1),
(30, 30, 30, 30, 0);

-- Bảng PersonalQuestion  
INSERT INTO PersonalQuestion (customer_id, course_id, lesson_id, question_id, hasAnswered, isMarked)
VALUES 
(1, 1, 1, 1, 1, 0),
(2, 2, 2, 2, 0, 1),
(3, 3, 3, 3, 1, 0),
(4, 4, 4, 4, 0, 1),
(5, 5, 5, 5, 1, 0),
(6, 6, 6, 6, 0, 1),
(7, 7, 7, 7, 1, 0),
(8, 8, 8, 8, 0, 1),
(9, 9, 9, 9, 1, 0),
(10, 10, 10, 10, 0, 1),
(11, 11, 11, 11, 1, 0),
(12, 12, 12, 12, 0, 1),
(13, 13, 13, 13, 1, 0),
(14, 14, 14, 14, 0, 1),
(15, 15, 15, 15, 1, 0),
(16, 16, 16, 16, 0, 1),
(17, 17, 17, 17, 1, 0),
(18, 18, 18, 18, 0, 1),
(19, 19, 19, 19, 1, 0),
(20, 20, 20, 20, 0, 1),
(21, 21, 21, 21, 1, 0),
(22, 22, 22, 22, 0, 1),
(23, 23, 23, 23, 1, 0),
(24, 24, 24, 24, 0, 1),
(25, 25, 25, 25, 1, 0),
(26, 26, 26, 26, 0, 1),
(27, 27, 27, 27, 1, 0),
(28, 28, 28, 28, 0, 1),
(29, 29, 29, 29, 1, 0),
(30, 30, 30, 30, 0, 1);

-- Bảng PersonalOption  
INSERT INTO PersonalOption (customer_id, course_id, lesson_id, question_id, option_id, hasChosen)
VALUES 
(1, 1, 1, 1, 1, 1),
(2, 2, 2, 2, 2, 0),
(3, 3, 3, 3, 3, 1),
(4, 4, 4, 4, 4, 0),
(5, 5, 5, 5, 5, 1),
(6, 6, 6, 6, 6, 0),
(7, 7, 7, 7, 7, 1),
(8, 8, 8, 8, 8, 0),
(9, 9, 9, 9, 9, 1),
(10, 10, 10, 10, 10, 0),
(11, 11, 11, 11, 11, 1),
(12, 12, 12, 12, 12, 0),
(13, 13, 13, 13, 13, 1),
(14, 14, 14, 14, 14, 0),
(15, 15, 15, 15, 15, 1),
(16, 16, 16, 16, 16, 0),
(17, 17, 17, 17, 17, 1),
(18, 18, 18, 18, 18, 0),
(19, 19, 19, 19, 19, 1),
(20, 20, 20, 20, 20, 0),
(21, 21, 21, 21, 21, 1),
(22, 22, 22, 22, 22, 0),
(23, 23, 23, 23, 23, 1),
(24, 24, 24, 24, 24, 0),
(25, 25, 25, 25, 25, 1),
(26, 26, 26, 26, 26, 0),
(27, 27, 27, 27, 27, 1),
(28, 28, 28, 28, 28, 0),
(29, 29, 29, 29, 29, 1),
(30, 30, 30, 30, 30, 0);

drop database Learnik

-- Trigger
DELIMITER //

CREATE TRIGGER update_expire_date
BEFORE INSERT ON PersonalCourse
FOR EACH ROW
BEGIN
    DECLARE pkg_duration INT;

    -- Lấy giá trị duration từ bảng PricePackage dựa trên course_id
    SELECT duration INTO pkg_duration 
    FROM PricePackage 
    WHERE course_id = NEW.course_id 
    LIMIT 1;

    -- Tính toán ngày hết hạn và gán vào trường expire_date
    SET NEW.expire_date = DATE_ADD(NEW.enroll_date, INTERVAL pkg_duration DAY);
END //

DELIMITER ;
