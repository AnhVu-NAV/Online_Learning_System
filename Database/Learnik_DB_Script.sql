CREATE DATABASE  IF NOT EXISTS `learnik` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `learnik`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: learnik
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `brief_info` varchar(255) DEFAULT NULL,
  `author_id` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`),
  CONSTRAINT `blog_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `setting` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,'thumbnail1.jpg','Java Programming Basics','This blog covers the basics of Java programming.','Java basics for beginners',1,1,'2023-01-10','2023-01-10',NULL),(2,'thumbnail2.jpg','Advanced Java Programming','This blog explains advanced Java programming concepts.','Advanced Java topics for experienced developers',2,1,'2023-01-15','2023-01-15',NULL),(3,'thumbnail3.jpg','Python Programming Introduction','An introduction to Python programming.','Python basics for beginners',3,1,'2023-02-10','2023-02-10',NULL),(4,'thumbnail4.jpg','Advanced Python Techniques','Learn advanced techniques in Python.','Advanced Python programming for experts',4,1,'2023-03-01','2023-03-01',NULL),(5,'thumbnail5.jpg','Web Development Basics','An introduction to web development using HTML, CSS, and JavaScript.','Learn how to build websites from scratch',5,1,'2023-04-10','2023-04-10',NULL),(6,'thumbnail6.jpg','Digital Marketing Strategies','This blog discusses key strategies in digital marketing.','Master digital marketing to grow your business',6,1,'2023-05-05','2023-05-05',NULL),(7,'thumbnail7.jpg','Business Management Fundamentals','Learn the core principles of managing a business.','Business management basics for entrepreneurs',7,1,'2023-06-12','2023-06-12',NULL),(8,'thumbnail8.jpg','Graphic Design Techniques','This blog explains graphic design techniques for beginners.','Learn graphic design principles and tools',8,1,'2023-07-20','2023-07-20',NULL),(9,'thumbnail9.jpg','Photography Tips and Tricks','This blog shares tips for improving your photography skills.','Photography basics for amateurs',1,1,'2023-08-10','2023-08-10',NULL),(10,'thumbnail10.jpg','Health and Wellness Tips','Discover tips for maintaining health and wellness.','Improve your health with simple habits',2,1,'2023-09-05','2023-09-05',NULL),(11,'thumbnail11.jpg','English Learning Tips','This blog provides tips for learning English more effectively.','Master English with these simple tips',3,1,'2023-10-12','2023-10-12',NULL),(12,'thumbnail12.jpg','IELTS Preparation Guide','A comprehensive guide for preparing for the IELTS exam.','Get ready for IELTS with these steps',4,1,'2023-11-01','2023-11-01',NULL),(13,'thumbnail13.jpg','TOEFL Exam Strategies','This blog covers strategies for acing the TOEFL exam.','Key strategies for TOEFL success',5,1,'2023-12-10','2023-12-10',NULL),(14,'thumbnail14.jpg','Travel English Phrases','Learn key English phrases for traveling abroad.','Essential English for travelers',6,1,'2024-01-05','2024-01-05',NULL),(15,'thumbnail15.jpg','Business English for Professionals','This blog covers important English phrases for business settings.','Improve your business English communication',7,1,'2024-02-15','2024-02-15',NULL),(16,'thumbnail16.jpg','Public Speaking in English','Learn how to improve your public speaking skills in English.','Master public speaking with these tips',8,1,'2024-03-10','2024-03-10',NULL),(17,'thumbnail17.jpg','Hospitality English Phrases','This blog provides English phrases useful in the hospitality industry.','English for hotel and tourism professionals',1,1,'2024-04-01','2024-04-01',NULL),(18,'thumbnail18.jpg','Healthcare English Vocabulary','Learn important English vocabulary for healthcare professionals.','English for healthcare settings',2,1,'2024-05-05','2024-05-05',NULL),(19,'thumbnail19.jpg','TOEIC Preparation Strategies','This blog covers strategies for acing the TOEIC exam.','TOEIC exam strategies and tips',3,1,'2024-06-01','2024-06-01',NULL),(20,'thumbnail20.jpg','English Writing Skills for Business','Learn how to improve English writing skills for business communication.','Business English writing tips',4,1,'2024-07-10','2024-07-10',NULL),(21,'thumbnail21.jpg','Conversational English for Beginners','This blog helps beginners improve their conversational English skills.','Basic conversational English tips',5,1,'2024-08-05','2024-08-05',NULL),(22,'thumbnail22.jpg','Advanced Business English Techniques','Learn advanced techniques in business English communication.','Enhance your business English proficiency',6,1,'2024-09-01','2024-09-01',NULL),(23,'thumbnail23.jpg','Public Speaking Mastery','A guide to mastering public speaking in English.','Master advanced public speaking techniques',7,1,'2024-10-01','2024-10-01',NULL),(24,'thumbnail24.jpg','English for Travel and Tourism','Key English phrases for travel and tourism professionals.','Learn essential travel-related English',8,1,'2024-11-01','2024-11-01',NULL),(25,'thumbnail25.jpg','Advanced Healthcare English Vocabulary','Master advanced English vocabulary for healthcare professionals.','Essential English for advanced healthcare communication',1,1,'2024-12-01','2024-12-01',NULL);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blogcourse`
--

DROP TABLE IF EXISTS `blogcourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogcourse` (
  `blog_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`blog_id`,`course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `blogcourse_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`),
  CONSTRAINT `blogcourse_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogcourse`
--

LOCK TABLES `blogcourse` WRITE;
/*!40000 ALTER TABLE `blogcourse` DISABLE KEYS */;
INSERT INTO `blogcourse` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),(19,19),(20,20);
/*!40000 ALTER TABLE `blogcourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapter`
--

DROP TABLE IF EXISTS `chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapter` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `brief_information` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `duration` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `chapter_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter`
--

LOCK TABLES `chapter` WRITE;
/*!40000 ALTER TABLE `chapter` DISABLE KEYS */;
INSERT INTO `chapter` VALUES (1,1,'Java Basics - Part 1','Introduction to Java Programming','Getting started with Java','Learn the fundamentals of Java programming',1,1,60),(2,2,'Java Basics - Part 2','Java Variables and Data Types','Working with variables','Learn how to declare and use variables in Java',1,1,90),(3,3,'Java Advanced - Part 1','Object-Oriented Programming in Java','Introduction to OOP concepts','Learn about classes, objects, inheritance, and polymorphism in Java',1,1,120),(4,1,'Python Basics - Part 1','Introduction to Python Programming','Getting started with Python','Learn the basics of Python syntax and data types',2,1,60),(5,2,'Python Basics - Part 2','Control Flow in Python','Working with loops and conditions','Learn about loops, if-else conditions, and functions in Python',2,1,90),(6,3,'Python Advanced - Part 1','Advanced Python Functions','Understanding decorators and generators','Explore advanced Python concepts like decorators and generators',2,1,120),(7,1,'Web Development Basics - Part 1','Introduction to HTML and CSS','Learn how to build web pages','An introduction to creating web pages using HTML and CSS',3,1,90),(8,2,'Web Development Basics - Part 2','Responsive Web Design','Making web pages responsive','Learn how to make your web pages responsive using CSS and media queries',3,1,120),(9,3,'Web Development Advanced - Part 1','JavaScript for Web Development','Introduction to JavaScript','Learn how to add interactivity to web pages using JavaScript',3,1,150),(10,1,'Digital Marketing Basics - Part 1','Introduction to Digital Marketing','Understanding the basics','Learn the fundamentals of digital marketing strategies',4,1,60),(11,2,'Digital Marketing Basics - Part 2','SEO and SEM','Improving website visibility','Learn how to improve website visibility using SEO and SEM techniques',4,1,90),(12,3,'Digital Marketing Advanced - Part 1','Social Media Marketing','Leveraging social media platforms','Learn how to use social media platforms effectively for marketing',4,1,120),(13,1,'Business Management Basics - Part 1','Introduction to Business Management','Understanding management concepts','Learn the key concepts of business management',5,1,60),(14,2,'Business Management Basics - Part 2','Team Leadership','Managing teams effectively','Learn how to lead and manage teams within a business environment',5,1,90),(15,3,'Business Management Advanced - Part 1','Strategic Management','Developing business strategies','Learn how to develop and implement business strategies',5,1,120),(16,1,'Graphic Design Basics - Part 1','Introduction to Graphic Design','Understanding the design process','Learn the fundamentals of graphic design, including typography and color theory',6,1,60),(17,2,'Graphic Design Basics - Part 2','Design Software','Working with design tools','Learn how to use graphic design software like Photoshop and Illustrator',6,1,90),(18,3,'Graphic Design Advanced - Part 1','Design for Print','Preparing designs for print media','Learn how to design print media, including brochures, posters, and business cards',6,1,120),(19,1,'Photography Basics - Part 1','Introduction to Photography','Understanding camera settings','Learn how to use a camera to take professional-quality photos',7,1,60),(20,2,'Photography Basics - Part 2','Lighting Techniques','Working with natural and artificial light','Learn how to use different lighting techniques to enhance your photos',7,1,90),(21,3,'Photography Advanced - Part 1','Photo Editing','Using editing software','Learn how to edit photos using software like Lightroom and Photoshop',7,1,120),(22,1,'English for Beginners - Part 1','Introduction to English','Learning basic English words','This chapter introduces common English words and phrases',8,1,60),(23,2,'English for Beginners - Part 2','Basic Grammar','Understanding simple grammar rules','Learn the basics of English grammar for beginners',8,1,90),(24,3,'English for Beginners - Part 3','Conversational English','Practicing basic conversations','Learn how to carry out simple conversations in English',8,1,120),(25,1,'English Intermediate - Part 1','Improving Vocabulary','Expanding your English vocabulary','Learn new vocabulary to enhance your English skills',9,1,60),(26,2,'English Intermediate - Part 2','Intermediate Grammar','Mastering grammar concepts','Learn more advanced grammar concepts and sentence structures',9,1,90),(27,3,'English Intermediate - Part 3','Conversational Practice','Enhancing conversation skills','Practice conversations in various real-life scenarios',9,1,120),(28,1,'English Advanced - Part 1','Advanced Vocabulary','Learning complex English words','This chapter covers more advanced English vocabulary and phrases',10,1,90),(29,2,'English Advanced - Part 2','Advanced Grammar','Understanding complex grammar rules','This chapter explains complex grammar rules for advanced learners',10,1,120),(30,3,'English Advanced - Part 3','Advanced Conversational Skills','Practicing advanced conversations','Learn how to speak fluently in more complex conversations',10,1,150),(31,1,'English Grammar Basics - Part 1','Introduction to Grammar','Understanding basic grammar rules','Learn the basics of English grammar, including nouns, verbs, and adjectives',11,1,60),(32,2,'English Grammar Basics - Part 2','Sentence Structure','Building sentences with correct grammar','Learn how to structure simple sentences with proper grammar',11,1,90),(33,3,'English Grammar Basics - Part 3','Tenses and Verbs','Understanding English tenses and verb forms','Learn the different tenses and how to use verbs correctly in sentences',11,1,120),(34,1,'Vocabulary Building - Part 1','Basic Vocabulary','Learning common words','Learn essential words for everyday conversations',12,1,60),(35,2,'Vocabulary Building - Part 2','Intermediate Vocabulary','Expanding your vocabulary','Learn more advanced words for various situations',12,1,90),(36,3,'Vocabulary Building - Part 3','Advanced Vocabulary','Mastering complex words','Learn advanced vocabulary to improve fluency and comprehension',12,1,120),(37,1,'Conversation Practice - Part 1','Basic Conversations','Practicing simple dialogues','Learn how to carry out simple conversations in everyday scenarios',13,1,60),(38,2,'Conversation Practice - Part 2','Intermediate Conversations','Improving conversational skills','Practice conversations in real-life settings with expanded vocabulary',13,1,90),(39,3,'Conversation Practice - Part 3','Advanced Conversations','Speaking fluently in conversations','Learn to handle complex conversations with confidence',13,1,120),(40,1,'Business English - Part 1','Business Vocabulary','Learning essential business terms','This chapter introduces key business vocabulary used in professional settings',14,1,60),(41,2,'Business English - Part 2','Business Meetings','Conducting effective meetings in English','Learn how to communicate effectively during business meetings',14,1,90),(42,3,'Business English - Part 3','Email Communication','Writing professional emails','Learn how to write clear and professional business emails in English',14,1,120),(43,1,'English for Travel - Part 1','Basic Travel Phrases','Learning essential phrases for traveling','Learn common English phrases for navigating travel situations',16,1,60),(44,2,'English for Travel - Part 2','At the Airport','Communicating at airports','Learn how to communicate effectively at airports and during travel',16,1,90),(45,3,'English for Travel - Part 3','Hotels and Restaurants','Handling hotel and restaurant conversations','Learn how to make reservations and order food in English during travel',16,1,120),(46,1,'English for Work - Part 1','Workplace Communication','Improving communication in professional settings','Learn how to communicate effectively with colleagues and superiors',17,1,60),(47,2,'English for Work - Part 2','Presentations and Reports','Creating presentations and reports in English','Learn how to prepare and deliver presentations and write reports in English',17,1,90),(48,3,'English for Work - Part 3','Negotiation Skills','Negotiating in business contexts','Learn key phrases and strategies for successful negotiations in English',17,1,120),(49,1,'Public Speaking - Part 1','Introduction to Public Speaking','Understanding the basics of public speaking','Learn how to speak confidently in front of an audience',15,1,60),(50,2,'Public Speaking - Part 2','Speech Preparation','Writing and preparing speeches','Learn how to structure and prepare a speech effectively',15,1,90),(51,3,'Public Speaking - Part 3','Delivering a Speech','Practicing speech delivery','Learn techniques for delivering powerful speeches',15,1,120),(52,1,'English for Travel - Part 1','Basic Travel Phrases','Learning essential phrases for traveling','Learn common English phrases for navigating travel situations',16,1,60),(53,2,'English for Travel - Part 2','At the Airport','Communicating at airports','Learn how to communicate effectively at airports and during travel',16,1,90),(54,3,'English for Travel - Part 3','Hotels and Restaurants','Handling hotel and restaurant conversations','Learn how to make reservations and order food in English during travel',16,1,120),(55,4,'English for Travel - Part 4','Transportation','Getting around using public transportation','Learn key phrases for using buses, taxis, and trains while traveling',16,1,75),(56,5,'English for Travel - Part 5','Asking for Directions','How to ask for and understand directions','Learn how to ask for and understand directions in English while traveling',16,1,80),(57,1,'English for Work - Part 1','Workplace Communication','Improving communication in professional settings','Learn how to communicate effectively with colleagues and superiors',17,1,60),(58,2,'English for Work - Part 2','Presentations and Reports','Creating presentations and reports in English','Learn how to prepare and deliver presentations and write reports in English',17,1,90),(59,3,'English for Work - Part 3','Negotiation Skills','Negotiating in business contexts','Learn key phrases and strategies for successful negotiations in English',17,1,120),(60,4,'English for Work - Part 4','Email Etiquette','Writing professional emails in the workplace','Learn how to write clear, concise, and professional emails in English',17,1,70),(61,5,'English for Work - Part 5','Handling Phone Calls','Improving phone communication skills','Learn how to handle professional phone calls with confidence and clarity',17,1,85),(62,1,'TOEFL Preparation - Part 1','Introduction to TOEFL','Understanding the TOEFL structure','This chapter introduces the TOEFL test structure and strategies',18,1,90),(63,2,'TOEFL Preparation - Part 2','Listening Section','Practicing TOEFL listening skills','This chapter focuses on improving listening skills for the TOEFL test',18,1,120),(64,3,'TOEFL Preparation - Part 3','Speaking Section','Improving speaking for TOEFL','This chapter covers speaking techniques for the TOEFL exam',18,1,150),(65,1,'IELTS Preparation - Part 1','Introduction to IELTS','Understanding the IELTS structure','This chapter introduces the IELTS test structure and strategies',19,1,90),(66,2,'IELTS Preparation - Part 2','Reading Section','Practicing IELTS reading skills','This chapter focuses on improving reading skills for the IELTS test',19,1,120),(67,3,'IELTS Preparation - Part 3','Writing Section','Improving writing for IELTS','This chapter covers writing techniques for the IELTS exam',19,1,150),(68,1,'TOEIC Preparation - Part 1','Introduction to TOEIC','Understanding the TOEIC structure','This chapter introduces the TOEIC test structure and strategies',20,1,90),(69,2,'TOEIC Preparation - Part 2','Listening Section','Practicing TOEIC listening skills','This chapter focuses on improving listening skills for the TOEIC test',20,1,120),(70,3,'TOEIC Preparation - Part 3','Reading Section','Improving reading skills for TOEIC','This chapter covers techniques for reading comprehension in TOEIC',20,1,150),(71,1,'Listening Skills - Part 1','Basic Listening Comprehension','Improving basic listening skills','Learn how to improve your basic listening skills with simple English dialogues',21,1,45),(72,2,'Listening Skills - Part 2','Listening to Conversations','Understanding real-world conversations','Practice listening to common English conversations and improve comprehension',21,1,60),(73,3,'Listening Skills - Part 3','Listening for Key Information','How to focus on key points','Learn how to listen for key points and important details in conversations',21,1,75),(74,4,'Listening Skills - Part 4','Listening in Work Situations','Understanding work-related conversations','Improve listening comprehension in work-related conversations',21,1,80),(75,5,'Listening Skills - Part 5','Listening to Stories and News','Comprehending stories and news in English','Practice listening to longer English stories and news reports',21,1,90),(76,1,'Writing Skills - Part 1','Basic Writing Techniques','Improving basic writing skills','Learn how to structure sentences and write clearly',22,1,60),(77,2,'Writing Skills - Part 2','Writing Essays','How to write essays in English','Learn how to write structured essays and organize your thoughts',22,1,90),(78,3,'Writing Skills - Part 3','Writing Reports and Emails','Formal and informal writing','Learn how to write reports and professional emails',22,1,80),(79,4,'Writing Skills - Part 4','Creative Writing','Writing stories and narratives','Explore creative writing and learn how to write stories in English',22,1,100),(80,5,'Writing Skills - Part 5','Advanced Writing Techniques','Mastering advanced writing skills','Learn advanced techniques to write with clarity and precision',22,1,120),(81,1,'Conversational English - Part 1','Introducing Yourself','How to introduce yourself in English','Learn how to introduce yourself and others in English',23,1,45),(82,2,'Conversational English - Part 2','Talking About Daily Activities','Common phrases for daily activities','Learn how to talk about your daily routine in English',23,1,60),(83,3,'Conversational English - Part 3','Talking About Your Interests','Discussing hobbies and interests','Learn how to talk about your hobbies and interests in English',23,1,75),(84,4,'Conversational English - Part 4','Engaging in Small Talk','Basic conversational skills','Learn how to start and engage in small talk in English',23,1,80),(85,5,'Conversational English - Part 5','Talking About Future Plans','Discussing future events and plans','Learn how to talk about future plans using simple English phrases',23,1,90),(86,1,'Business English - Part 1','Advanced Workplace Communication','Improving communication in professional settings','Learn how to handle complex business communications',24,1,75),(87,2,'Business English - Part 2','Negotiation Skills','Advanced negotiation strategies','Learn advanced negotiation strategies and key phrases in English',24,1,90),(88,3,'Business English - Part 3','Presenting and Public Speaking','Delivering presentations in business settings','Learn how to deliver effective presentations in English',24,1,120),(89,4,'Business English - Part 4','Email and Report Writing','Writing professional business documents','Learn how to write professional business emails and reports',24,1,80),(90,5,'Business English - Part 5','Cross-Cultural Communication','Communicating with international clients','Learn strategies for effective communication in cross-cultural business settings',24,1,100),(91,1,'Public Speaking - Part 1','Overcoming Stage Fright','Building confidence in public speaking','Learn techniques to overcome fear of public speaking',25,1,60),(92,2,'Public Speaking - Part 2','Structuring Your Speech','How to structure a speech','Learn how to organize your thoughts and structure a compelling speech',25,1,90),(93,3,'Public Speaking - Part 3','Engaging the Audience','How to connect with your audience','Learn techniques to engage and captivate your audience',25,1,120),(94,4,'Public Speaking - Part 4','Using Visual Aids','Enhancing your speech with visuals','Learn how to effectively use visual aids in your presentations',25,1,70),(95,5,'Public Speaking - Part 5','Delivering Your Speech','Practice and delivery techniques','Master the art of delivering a speech with confidence and clarity',25,1,100),(96,1,'Hospitality English - Part 1','Greeting Guests','How to greet guests professionally','Learn how to greet and welcome guests in the hospitality industry',26,1,45),(97,2,'Hospitality English - Part 2','Handling Guest Inquiries','Responding to common guest questions','Learn how to respond to guest inquiries and provide assistance',26,1,60),(98,3,'Hospitality English - Part 3','Taking Reservations','Booking rooms and services','Learn how to take reservations and bookings professionally',26,1,75),(99,4,'Hospitality English - Part 4','Managing Complaints','Handling guest complaints','Learn how to manage guest complaints and resolve issues',26,1,80),(100,5,'Hospitality English - Part 5','Offering Recommendations','Providing recommendations to guests','Learn how to offer recommendations on local attractions and services',26,1,90),(101,1,'Healthcare English - Part 1','Communicating with Patients','Basic patient communication','Learn how to communicate effectively with patients in healthcare settings',27,1,50),(102,2,'Healthcare English - Part 2','Describing Symptoms','Helping patients describe their symptoms','Learn how to assist patients in describing their symptoms and medical conditions',27,1,60),(103,3,'Healthcare English - Part 3','Giving Instructions','Providing clear instructions to patients','Learn how to give patients clear and concise medical instructions',27,1,75),(104,4,'Healthcare English - Part 4','Medical Terminology','Understanding and using medical terms','Learn key medical terms used in healthcare communication',27,1,90),(105,5,'Healthcare English - Part 5','Handling Emergencies','Communicating in emergency situations','Learn how to handle communication during medical emergencies',27,1,100);
/*!40000 ALTER TABLE `chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `expert_id` int DEFAULT NULL,
  `total_duration` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `number_of_learner` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `expert_id` (`expert_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `setting` (`id`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`expert_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Java Programming','Learn Java',5,20,8,'Java course for beginners',1,'2023-01-01','2023-01-01',100),(2,'Python Programming','Learn Python',5,15,8,'Python course for data analysis',1,'2023-01-02','2023-01-02',80),(3,'Web Development','Full stack web development',5,25,8,'Become a web developer',1,'2023-01-03','2023-01-03',150),(4,'Digital Marketing','Marketing in the digital age',5,10,8,'Master digital marketing',1,'2023-01-04','2023-01-04',120),(5,'Business Management','Manage your business',5,30,10,'Learn business management',1,'2023-01-05','2023-01-05',50),(6,'Graphic Design','Design like a pro',5,18,12,'Learn graphic design skills',1,'2023-01-06','2023-01-06',90),(7,'Photography','Master the camera',5,12,12,'Learn photography basics',1,'2023-01-07','2023-01-07',110),(8,'English for Beginners','Learn basic English',6,30,9,'This course is designed for beginners who want to learn English from scratch.',1,'2024-01-01','2024-01-10',200),(9,'English Intermediate','Improve your English skills',6,45,9,'This course is for learners who have basic knowledge and want to improve their English skills.',1,'2024-01-01','2024-01-10',150),(10,'English Advanced','Master advanced English',6,60,9,'This course is for advanced learners who want to master English at a professional level.',1,'2024-01-01','2024-01-10',100),(11,'English Grammar Basics','Learn basic grammar',6,25,9,'This course covers the basics of English grammar for beginners.',1,'2024-01-01','2024-01-10',180),(12,'English Vocabulary Building','Expand your vocabulary',6,35,9,'This course helps learners expand their English vocabulary with common and useful words.',1,'2024-01-01','2024-01-10',220),(13,'English Conversation Practice','Practice speaking English',6,40,9,'This course focuses on improving English speaking and conversation skills.',1,'2024-01-01','2024-01-10',160),(14,'Business English','Learn English for business',6,50,9,'This course is designed for professionals who want to improve their English for business communication.',1,'2024-01-01','2024-01-10',130),(15,'English Pronunciation','Improve pronunciation',6,20,9,'This course helps learners improve their English pronunciation.',1,'2024-01-01','2024-01-10',140),(16,'English for Travel','Learn English for traveling',6,30,9,'This course focuses on English phrases and vocabulary for traveling.',1,'2024-01-01','2024-01-10',180),(17,'English for Work','Improve your workplace English',6,40,9,'This course is designed for improving English communication in the workplace.',1,'2024-01-01','2024-01-10',150),(18,'TOEFL Preparation','Prepare for the TOEFL exam',6,60,9,'This course helps learners prepare for the TOEFL exam.',1,'2024-01-01','2024-01-10',100),(19,'IELTS Preparation','Get ready for the IELTS test',6,60,9,'This course prepares learners for the IELTS exam.',1,'2024-01-01','2024-01-10',250),(20,'TOEIC Preparation','Master the TOEIC exam',6,50,9,'This course is designed for students preparing for the TOEIC exam.',1,'2024-01-01','2024-01-10',230),(21,'English Listening Skills','Improve your listening skills',6,20,9,'This course focuses on improving English listening skills.',1,'2024-01-01','2024-01-10',190),(22,'English Writing Skills','Master English writing',6,35,9,'This course helps learners improve their writing skills in English.',1,'2024-01-01','2024-01-10',200),(23,'Conversational English for Beginners','Learn English through conversations',6,25,9,'This course focuses on building conversational skills for English beginners.',1,'2024-01-01','2024-01-10',220),(24,'Advanced Business English','Enhance your business communication',6,45,9,'This course is for advanced learners aiming to improve their business communication skills.',1,'2024-01-01','2024-01-10',240),(25,'English for Public Speaking','Improve your public speaking skills',6,30,9,'This course is designed to help learners improve their public speaking in English.',1,'2024-01-01','2024-01-10',160),(26,'English for Hospitality','Learn English for the hospitality industry',6,40,9,'This course focuses on English for the hospitality and tourism industry.',1,'2024-01-01','2024-01-10',180),(27,'English for Healthcare','Communicate effectively in healthcare settings',6,50,9,'This course focuses on English communication for healthcare professionals.',1,'2024-01-01','2024-01-10',150);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_tagline`
--

DROP TABLE IF EXISTS `course_tagline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_tagline` (
  `tagline_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`tagline_id`,`course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_tagline_ibfk_1` FOREIGN KEY (`tagline_id`) REFERENCES `tagline` (`id`),
  CONSTRAINT `course_tagline_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_tagline`
--

LOCK TABLES `course_tagline` WRITE;
/*!40000 ALTER TABLE `course_tagline` DISABLE KEYS */;
INSERT INTO `course_tagline` VALUES (1,1),(2,1),(16,1),(23,1),(1,2),(2,2),(17,2),(18,2),(23,2),(1,3),(2,3),(16,3),(17,3),(23,3),(2,4),(3,4),(4,4),(10,4),(23,4),(3,5),(10,5),(15,5),(20,5),(24,5),(2,6),(4,6),(5,6),(14,6),(23,6),(2,7),(4,7),(13,7),(14,7),(23,7),(6,8),(10,8),(11,8),(12,8),(23,8),(6,9),(10,9),(11,9),(12,9),(24,9),(6,10),(10,10),(11,10),(12,10),(24,10),(6,11),(11,11),(12,11),(23,11),(6,12),(10,12),(11,12),(12,12),(23,12),(6,13),(10,13),(12,13),(19,13),(23,13),(3,14),(6,14),(10,14),(12,14),(24,14),(6,15),(10,15),(12,15),(19,15),(23,15),(6,16),(7,16),(10,16),(12,16),(23,16),(3,17),(6,17),(10,17),(20,17),(24,17),(6,18),(11,18),(22,18),(24,18),(6,19),(11,19),(22,19),(24,19),(6,20),(11,20),(22,20),(24,20),(6,21),(10,21),(11,21),(12,21),(23,21),(6,22),(10,22),(11,22),(12,22),(24,22),(6,23),(10,23),(12,23),(19,23),(23,23),(3,24),(6,24),(10,24),(20,24),(24,24),(6,25),(10,25),(19,25),(20,25),(24,25),(6,26),(9,26),(10,26),(21,26),(23,26),(6,27),(8,27),(10,27),(21,27),(24,27);
/*!40000 ALTER TABLE `course_tagline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_thumbnails`
--

DROP TABLE IF EXISTS `course_thumbnails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_thumbnails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int DEFAULT NULL,
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_thumbnails_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_thumbnails`
--

LOCK TABLES `course_thumbnails` WRITE;
/*!40000 ALTER TABLE `course_thumbnails` DISABLE KEYS */;
INSERT INTO `course_thumbnails` VALUES (1,1,'img/java_thumbnail1.jpg',NULL),(2,1,'img/java_thumbnail2.jpg',NULL),(3,1,'img/java_thumbnail3.jpg',NULL),(4,2,'img/python_thumbnail1.jpg',NULL),(5,2,'img/python_thumbnail2.jpg',NULL),(6,2,'img/python_thumbnail3.jpg',NULL),(7,3,'img/webdev_thumbnail1.jpg',NULL),(8,3,'img/webdev_thumbnail2.jpg',NULL),(9,3,'img/webdev_thumbnail3.jpg',NULL),(10,4,'img/digital_marketing_thumbnail1.avif',NULL),(11,4,'img/digital_marketing_thumbnail2.jpg',NULL),(12,4,'img/digital_marketing_thumbnail3.jpg',NULL),(13,5,'img/business_management_thumbnail1.avif',NULL),(14,5,'img/business_management_thumbnail2.jpg',NULL),(15,5,'img/business_management_thumbnail3.jpg',NULL),(16,6,'img/graphic_design_thumbnail1.avif',NULL),(17,6,'img/graphic_design_thumbnail2.jpg',NULL),(18,6,'img/graphic_design_thumbnail3.jpg',NULL),(19,7,'img/photography_thumbnail1.jpg',NULL),(20,7,'img/photography_thumbnail2.jpg',NULL),(21,7,'img/photography_thumbnail3.jpg',NULL),(22,8,'img/english_beginners_thumbnail1.jpg',NULL),(23,8,'img/english_beginners_thumbnail2.jpg',NULL),(24,8,'img/english_beginners_thumbnail3.jpg',NULL),(25,9,'img/english_intermediate_thumbnail1.jpg',NULL),(26,9,'img/english_intermediate_thumbnail2.jpg',NULL),(27,9,'img/english_intermediate_thumbnail3.jpg',NULL),(28,10,'img/english_advanced_thumbnail1.jpg',NULL),(29,10,'img/english_advanced_thumbnail2.jpg',NULL),(30,10,'img/english_advanced_thumbnail3.jpg',NULL),(31,11,'img/grammar_basics_thumbnail1.avif',NULL),(32,11,'img/grammar_basics_thumbnail2.jpg',NULL),(33,11,'img/grammar_basics_thumbnail3.jpg',NULL),(34,12,'img/vocabulary_building_thumbnail1.avif',NULL),(35,12,'img/vocabulary_building_thumbnail2.jpg',NULL),(36,12,'img/vocabulary_building_thumbnail3.jpg',NULL),(37,13,'img/conversation_practice_thumbnail1.avif',NULL),(38,13,'img/conversation_practice_thumbnail2.jpg',NULL),(39,13,'img/conversation_practice_thumbnail3.jpg',NULL),(40,14,'img/business_english_thumbnail1.avif',NULL),(41,14,'img/business_english_thumbnail2.jpg',NULL),(42,14,'img/business_english_thumbnail3.jpg',NULL),(43,15,'img/pronunciation_thumbnail1.avif',NULL),(44,15,'img/pronunciation_thumbnail2.jpg',NULL),(45,15,'img/pronunciation_thumbnail3.jpg',NULL),(46,16,'img/english_travel_thumbnail1.avif',NULL),(47,16,'img/english_travel_thumbnail2.jpg',NULL),(48,16,'img/english_travel_thumbnail3.jpg',NULL),(49,17,'img/english_work_thumbnail1.avif',NULL),(50,17,'img/english_work_thumbnail2.jpg',NULL),(51,17,'img/english_work_thumbnail3.jpg',NULL),(52,18,'img/toefl_preparation_thumbnail1.jpg',NULL),(53,18,'img/toefl_preparation_thumbnail2.jpg',NULL),(54,18,'img/toefl_preparation_thumbnail3.jpg',NULL),(55,19,'img/ielts_preparation_thumbnail1.jpg',NULL),(56,19,'img/ielts_preparation_thumbnail2.jpg',NULL),(57,19,'img/ielts_preparation_thumbnail3.jpg',NULL),(58,20,'img/toeic_preparation_thumbnail1.jpg',NULL),(59,20,'img/toeic_preparation_thumbnail2.jpg',NULL),(60,20,'img/toeic_preparation_thumbnail3.jpg',NULL),(61,21,'img/listening_skills_thumbnail1.jpg',NULL),(62,21,'img/listening_skills_thumbnail2.jpg',NULL),(63,21,'img/listening_skills_thumbnail3.jpg',NULL),(64,22,'img/writing_skills_thumbnail1.jpeg',NULL),(65,22,'img/writing_skills_thumbnail2.jpg',NULL),(66,22,'img/writing_skills_thumbnail3.jpg',NULL),(67,23,'img/conversation_beginners_thumbnail1.jpg',NULL),(68,23,'img/conversation_beginners_thumbnail2.jpg',NULL),(69,23,'img/conversation_beginners_thumbnail3.jpg',NULL),(70,24,'img/advanced_business_english_thumbnail1.avif',NULL),(71,24,'img/advanced_business_english_thumbnail2.jpg',NULL),(72,24,'img/advanced_business_english_thumbnail3.jpg',NULL),(73,25,'img/public_speaking_thumbnail1.avif',NULL),(74,25,'img/public_speaking_thumbnail2.jpg',NULL),(75,25,'img/public_speaking_thumbnail3.jpg',NULL),(76,26,'img/hospitality_english_thumbnail1.avif',NULL),(77,26,'img/hospitality_english_thumbnail2.jpg',NULL),(78,26,'img/hospitality_english_thumbnail3.jpg',NULL),(79,27,'img/healthcare_english_thumbnail1.avif',NULL),(80,27,'img/healthcare_english_thumbnail2.jpg',NULL),(81,27,'img/healthcare_english_thumbnail3.jpg',NULL);
/*!40000 ALTER TABLE `course_thumbnails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursevisualcontent`
--

DROP TABLE IF EXISTS `coursevisualcontent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coursevisualcontent` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursevisualcontent`
--

LOCK TABLES `coursevisualcontent` WRITE;
/*!40000 ALTER TABLE `coursevisualcontent` DISABLE KEYS */;
/*!40000 ALTER TABLE `coursevisualcontent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursevisualcontent_course`
--

DROP TABLE IF EXISTS `coursevisualcontent_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coursevisualcontent_course` (
  `course_visual_content_id` int NOT NULL,
  `course_id` int NOT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`course_visual_content_id`,`course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `coursevisualcontent_course_ibfk_1` FOREIGN KEY (`course_visual_content_id`) REFERENCES `coursevisualcontent` (`id`) ON DELETE CASCADE,
  CONSTRAINT `coursevisualcontent_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursevisualcontent_course`
--

LOCK TABLES `coursevisualcontent_course` WRITE;
/*!40000 ALTER TABLE `coursevisualcontent_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `coursevisualcontent_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `learningmaterial`
--

DROP TABLE IF EXISTS `learningmaterial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `learningmaterial` (
  `lesson_id` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `upload_date` date DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`lesson_id`),
  CONSTRAINT `learningmaterial_ibfk_1` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `learningmaterial`
--

LOCK TABLES `learningmaterial` WRITE;
/*!40000 ALTER TABLE `learningmaterial` DISABLE KEYS */;
INSERT INTO `learningmaterial` VALUES (1,'Video Lesson 1 - Workplace Communication Basics','2024-01-01',10,1),(2,'Video Lesson 2 - Workplace Communication Advanced','2024-01-02',12,1),(3,'Text Lesson 1 - Workplace Communication Guide','2024-01-03',8,3),(4,'Video Lesson 1 - Presentation Skills Basics','2024-01-04',14,1),(5,'Video Lesson 2 - Presentation Skills Advanced','2024-01-05',16,1),(6,'Text Lesson 1 - Writing Reports Guide','2024-01-06',7,2),(7,'Video Lesson 1 - Negotiation Strategies Basics','2024-01-07',15,1),(8,'Video Lesson 2 - Negotiation Strategies Advanced','2024-01-08',18,1),(9,'Text Lesson 1 - Negotiation Key Phrases','2024-01-09',9,2),(10,'Video Lesson 1 - Email Etiquette Basics','2024-01-10',10,1),(11,'Video Lesson 2 - Writing Professional Emails','2024-01-11',12,1),(12,'Text Lesson 1 - Email Structure Guide','2024-01-12',8,1),(13,'Video Lesson 1 - Handling Phone Calls Basics','2024-01-13',14,1),(14,'Video Lesson 2 - Handling Difficult Phone Conversations','2024-01-14',16,1),(15,'Text Lesson 1 - Phone Call Tips & Tricks','2024-01-15',7,1);
/*!40000 ALTER TABLE `learningmaterial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` int DEFAULT NULL,
  `lesson_type_id` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `chapter_id` int DEFAULT NULL,
  `order` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `lesson_type_id` (`lesson_type_id`),
  KEY `chapter_id` (`chapter_id`),
  CONSTRAINT `lesson_ibfk_1` FOREIGN KEY (`lesson_type_id`) REFERENCES `setting` (`id`),
  CONSTRAINT `lesson_ibfk_2` FOREIGN KEY (`chapter_id`) REFERENCES `chapter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (1,1,1,'Video Lesson 1 - Workplace Communication Basics',57,1),(2,1,1,'Video Lesson 2 - Workplace Communication Advanced',57,2),(3,1,1,'Text Lesson 1 - Workplace Communication Guide',57,3),(4,1,1,'Video Lesson 1 - Presentation Skills Basics',58,1),(5,1,1,'Video Lesson 2 - Presentation Skills Advanced',58,2),(6,1,1,'Text Lesson 1 - Writing Reports Guide',58,3),(7,1,1,'Video Lesson 1 - Negotiation Strategies Basics',59,1),(8,1,1,'Video Lesson 2 - Negotiation Strategies Advanced',59,2),(9,1,1,'Text Lesson 1 - Negotiation Key Phrases',59,3),(10,1,1,'Video Lesson 1 - Email Etiquette Basics',60,1),(11,1,1,'Video Lesson 2 - Writing Professional Emails',60,2),(12,1,1,'Text Lesson 1 - Email Structure Guide',60,3),(13,1,1,'Video Lesson 1 - Handling Phone Calls Basics',61,1),(14,1,1,'Video Lesson 2 - Handling Difficult Phone Conversations',61,2),(15,1,1,'Text Lesson 1 - Phone Call Tips & Tricks',61,3),(16,1,2,'Quiz Lesson 1',57,4),(17,1,2,'Quiz Lesson 2',59,4),(18,1,2,'Quiz Lesson 3',61,4),(19,1,2,'Experience Question 2',60,4),(20,1,2,'Experience Question 1',58,4);
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `option`
--

DROP TABLE IF EXISTS `option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `option` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_id` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `isTrue` tinyint(1) DEFAULT NULL,
  `explaination` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `option_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `option`
--

LOCK TABLES `option` WRITE;
/*!40000 ALTER TABLE `option` DISABLE KEYS */;
INSERT INTO `option` VALUES (1,1,1,1,'Correct: Children is the plural form of child','Children'),(2,1,1,0,'Incorrect: Childs is not a correct plural form','Childs'),(3,1,1,0,'Incorrect: Childer is not a correct word','Childer'),(4,1,1,0,'Incorrect: Childies is not a valid form','Childies'),(5,2,1,1,'Correct: Fluffy is the adjective describing the cat','Fluffy'),(6,2,1,0,'Incorrect: \"The\" is an article, not an adjective','The'),(7,2,1,0,'Incorrect: \"Cat\" is a noun, not an adjective','Cat'),(8,2,1,0,'Incorrect: \"Is\" is a verb','Is'),(9,3,1,1,'Correct: The past tense of run is \"ran\"','Ran'),(10,3,1,0,'Incorrect: \"Runned\" is not a correct word','Runned'),(11,3,1,0,'Incorrect: \"Running\" is present continuous','Running'),(12,3,1,0,'Incorrect: \"Runs\" is present tense','Runs'),(13,4,1,1,'Correct: \"Teacher\" is the noun in the sentence','Teacher'),(14,4,1,0,'Incorrect: \"She\" is a pronoun','She'),(15,4,1,0,'Incorrect: \"Is\" is a verb','Is'),(16,4,1,0,'Incorrect: \"A\" is an article','A'),(17,5,1,1,'Correct: \"Run\" is a verb, indicating action','Run'),(18,5,1,0,'Incorrect: \"Happy\" is an adjective','Happy'),(19,5,1,0,'Incorrect: \"Blue\" is an adjective','Blue'),(20,5,1,0,'Incorrect: None of these are correct verbs except \"run\"','None'),(21,6,1,1,'Correct: \"I\" is a first-person singular pronoun','I'),(22,6,1,0,'Incorrect: \"Me\" is an object pronoun','Me'),(23,6,1,0,'Incorrect: \"You\" is a second-person pronoun','You'),(24,6,1,0,'Incorrect: None of these are first-person singular pronouns','None'),(25,7,1,1,'Correct: \"An\" is used before vowels like \"apple\"','An'),(26,7,1,0,'Incorrect: \"A\" is used before consonants','A'),(27,7,1,0,'Incorrect: \"The\" is a definite article but not specific for vowels','The'),(28,7,1,0,'Incorrect: None of these are appropriate before \"apple\"','None'),(29,8,1,1,'Correct: \"Large\" is a synonym for \"big\"','Large'),(30,8,1,0,'Incorrect: \"Small\" is the opposite of big','Small'),(31,8,1,0,'Incorrect: \"Tiny\" is much smaller than big','Tiny'),(32,8,1,0,'Incorrect: \"Huge\" is an exaggeration of big, not a synonym','Huge'),(33,9,1,1,'Correct: \"Quickly\" is an adverb, describing how something is done','Quickly'),(34,9,1,0,'Incorrect: \"Apple\" is a noun','Apple'),(35,9,1,0,'Incorrect: \"Beautiful\" is an adjective','Beautiful'),(36,9,1,0,'Incorrect: None of these are adverbs except \"quickly\"','None'),(37,10,1,1,'Correct: \"Was\" is the past tense form of \"is\"','Was'),(38,10,1,1,'Correct: \"Were\" is also a past tense form used with plural subjects','Were'),(39,10,1,0,'Incorrect: \"Is\" is the present tense form','Is'),(40,10,1,0,'Incorrect: \"Be\" is an infinitive, not a past tense form','Be');
/*!40000 ALTER TABLE `option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalanswer`
--

DROP TABLE IF EXISTS `personalanswer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personalanswer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `personal_question_id` int DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `is_true` tinyint(1) DEFAULT NULL,
  `video_content` varchar(255) DEFAULT NULL,
  `img_conten` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `personal_question_id` (`personal_question_id`),
  CONSTRAINT `personalanswer_ibfk_1` FOREIGN KEY (`personal_question_id`) REFERENCES `personalquestion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalanswer`
--

LOCK TABLES `personalanswer` WRITE;
/*!40000 ALTER TABLE `personalanswer` DISABLE KEYS */;
/*!40000 ALTER TABLE `personalanswer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalchapter`
--

DROP TABLE IF EXISTS `personalchapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personalchapter` (
  `id` int NOT NULL AUTO_INCREMENT,
  `personal_course_id` int DEFAULT NULL,
  `chapter_id` int DEFAULT NULL,
  `is_done` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `personal_course_id` (`personal_course_id`),
  KEY `chapter_id` (`chapter_id`),
  CONSTRAINT `personalchapter_ibfk_1` FOREIGN KEY (`personal_course_id`) REFERENCES `personalcourse` (`id`),
  CONSTRAINT `personalchapter_ibfk_2` FOREIGN KEY (`chapter_id`) REFERENCES `chapter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalchapter`
--

LOCK TABLES `personalchapter` WRITE;
/*!40000 ALTER TABLE `personalchapter` DISABLE KEYS */;
INSERT INTO `personalchapter` VALUES (1,1,1,1),(2,1,2,1),(3,1,3,0),(4,2,1,1),(5,2,2,0),(6,2,3,1),(7,3,1,1),(8,3,2,1),(9,3,3,0),(10,4,1,1),(11,4,2,0),(12,4,3,1),(13,5,1,1),(14,5,2,1),(15,5,3,0),(16,6,1,1),(17,6,2,1),(18,6,3,0),(19,7,1,1),(20,7,2,1),(21,7,3,0),(22,8,1,1),(23,8,2,0),(24,8,3,1);
/*!40000 ALTER TABLE `personalchapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalcourse`
--

DROP TABLE IF EXISTS `personalcourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personalcourse` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `enroll_date` date DEFAULT NULL,
  `expire_date` date DEFAULT NULL,
  `progress` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `price_package_id` int DEFAULT NULL,
  `sale_note_id` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `course_id` (`course_id`),
  KEY `price_package_id` (`price_package_id`),
  KEY `sale_note_id` (`sale_note_id`),
  CONSTRAINT `personalcourse_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`),
  CONSTRAINT `personalcourse_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `personalcourse_ibfk_3` FOREIGN KEY (`price_package_id`) REFERENCES `pricepackage` (`id`),
  CONSTRAINT `personalcourse_ibfk_4` FOREIGN KEY (`sale_note_id`) REFERENCES `salenote` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalcourse`
--

LOCK TABLES `personalcourse` WRITE;
/*!40000 ALTER TABLE `personalcourse` DISABLE KEYS */;
INSERT INTO `personalcourse` VALUES (1,1,1,'2023-01-10','2023-01-10',80,1,1,NULL,100),(2,2,2,'2023-01-10','2023-01-11',70,1,2,NULL,100),(3,3,3,'2023-01-10','2023-01-12',90,1,3,NULL,100),(4,4,4,'2023-01-10','2023-01-13',85,1,4,NULL,100),(5,5,5,'2023-01-10','2023-01-14',95,1,5,NULL,100),(6,6,6,'2023-01-10','2023-01-15',60,1,6,NULL,100),(7,7,7,'2023-01-10','2023-01-16',75,1,7,NULL,100),(8,8,8,'2023-01-10','2023-01-17',50,1,8,NULL,100);
/*!40000 ALTER TABLE `personalcourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personallesson`
--

DROP TABLE IF EXISTS `personallesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personallesson` (
  `id` int NOT NULL AUTO_INCREMENT,
  `personal_chapter_id` int DEFAULT NULL,
  `lesson_id` int DEFAULT NULL,
  `is_done` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `personal_chapter_id` (`personal_chapter_id`),
  KEY `lesson_id` (`lesson_id`),
  CONSTRAINT `personallesson_ibfk_1` FOREIGN KEY (`personal_chapter_id`) REFERENCES `personalchapter` (`id`),
  CONSTRAINT `personallesson_ibfk_2` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personallesson`
--

LOCK TABLES `personallesson` WRITE;
/*!40000 ALTER TABLE `personallesson` DISABLE KEYS */;
INSERT INTO `personallesson` VALUES (1,1,1,1),(2,2,2,0),(3,3,3,1),(4,4,4,0),(5,5,5,1),(6,6,6,0),(7,7,7,1),(8,8,8,0);
/*!40000 ALTER TABLE `personallesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personaloption`
--

DROP TABLE IF EXISTS `personaloption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personaloption` (
  `id` int NOT NULL AUTO_INCREMENT,
  `personal_question_id` int DEFAULT NULL,
  `option_id` int DEFAULT NULL,
  `is_choosed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `personal_question_id` (`personal_question_id`),
  KEY `option_id` (`option_id`),
  CONSTRAINT `personaloption_ibfk_1` FOREIGN KEY (`personal_question_id`) REFERENCES `personalquestion` (`id`),
  CONSTRAINT `personaloption_ibfk_2` FOREIGN KEY (`option_id`) REFERENCES `option` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personaloption`
--

LOCK TABLES `personaloption` WRITE;
/*!40000 ALTER TABLE `personaloption` DISABLE KEYS */;
/*!40000 ALTER TABLE `personaloption` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalquestion`
--

DROP TABLE IF EXISTS `personalquestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personalquestion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `personal_quiz_id` int DEFAULT NULL,
  `question_id` int DEFAULT NULL,
  `is_answered` tinyint(1) DEFAULT NULL,
  `is_marked` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `personal_quiz_id` (`personal_quiz_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `personalquestion_ibfk_1` FOREIGN KEY (`personal_quiz_id`) REFERENCES `personalquiz` (`id`),
  CONSTRAINT `personalquestion_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalquestion`
--

LOCK TABLES `personalquestion` WRITE;
/*!40000 ALTER TABLE `personalquestion` DISABLE KEYS */;
/*!40000 ALTER TABLE `personalquestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalquiz`
--

DROP TABLE IF EXISTS `personalquiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personalquiz` (
  `id` int NOT NULL AUTO_INCREMENT,
  `personal_lesson_id` int DEFAULT NULL,
  `quiz_id` int DEFAULT NULL,
  `is_passed` tinyint(1) DEFAULT NULL,
  `taken_date` date DEFAULT NULL,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `personal_lesson_id` (`personal_lesson_id`),
  KEY `quiz_id` (`quiz_id`),
  CONSTRAINT `personalquiz_ibfk_1` FOREIGN KEY (`personal_lesson_id`) REFERENCES `personallesson` (`id`),
  CONSTRAINT `personalquiz_ibfk_2` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`lesson_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalquiz`
--

LOCK TABLES `personalquiz` WRITE;
/*!40000 ALTER TABLE `personalquiz` DISABLE KEYS */;
/*!40000 ALTER TABLE `personalquiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricepackage`
--

DROP TABLE IF EXISTS `pricepackage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricepackage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `sale_price` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `pricepackage_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricepackage`
--

LOCK TABLES `pricepackage` WRITE;
/*!40000 ALTER TABLE `pricepackage` DISABLE KEYS */;
INSERT INTO `pricepackage` VALUES (1,1,'Basic Package',100,80,'2023-01-01','2023-06-30'),(2,1,'Premium Package',200,160,'2023-01-01','2023-12-31'),(3,2,'Basic Package',120,100,'2023-01-02','2023-07-30'),(4,2,'Premium Package',250,200,'2023-01-02','2023-12-31'),(5,3,'Standard Package',300,250,'2023-01-03','2023-09-30'),(6,3,'Premium Package',400,350,'2023-01-03','2023-12-31'),(7,4,'Basic Package',150,120,'2023-01-04','2023-06-30'),(8,5,'Premium Package',500,450,'2023-01-05','2023-12-31'),(9,6,'Standard Package',1500,1200,'2024-01-01','2024-03-31'),(10,6,'Premium Package',2500,2000,'2024-01-01','2024-06-30'),(11,7,'Standard Package',1800,1500,'2024-01-01','2024-03-31'),(12,7,'Premium Package',2800,2300,'2024-01-01','2024-06-30'),(13,8,'Standard Package',2000,1700,'2024-01-01','2024-03-31'),(14,8,'Premium Package',3000,2500,'2024-01-01','2024-06-30'),(15,9,'Basic Package',1000,800,'2024-01-01','2024-03-31'),(16,9,'Premium Package',2000,1600,'2024-01-01','2024-06-30'),(17,10,'Standard Package',1200,1000,'2024-01-01','2024-03-31'),(18,10,'Premium Package',2200,1800,'2024-01-01','2024-06-30'),(19,11,'Standard Package',1300,1100,'2024-01-01','2024-03-31'),(20,11,'Premium Package',2300,1900,'2024-01-01','2024-06-30'),(21,12,'Standard Package',1700,1400,'2024-01-01','2024-03-31'),(22,12,'Premium Package',2700,2300,'2024-01-01','2024-06-30'),(23,13,'Standard Package',1100,900,'2024-01-01','2024-03-31'),(24,13,'Premium Package',2100,1700,'2024-01-01','2024-06-30'),(25,14,'Basic Package',1300,1000,'2024-01-01','2024-03-31'),(26,14,'Premium Package',2300,1900,'2024-01-01','2024-06-30'),(27,15,'Basic Package',1400,1100,'2024-01-01','2024-03-31'),(28,15,'Premium Package',2400,2000,'2024-01-01','2024-06-30'),(29,16,'Basic Package',1600,1300,'2024-01-01','2024-03-31'),(30,16,'Premium Package',2600,2200,'2024-01-01','2024-06-30'),(31,17,'Basic Package',1800,1500,'2024-01-01','2024-03-31'),(32,17,'Premium Package',2800,2400,'2024-01-01','2024-06-30'),(33,18,'Basic Package',1700,1400,'2024-01-01','2024-03-31'),(34,18,'Premium Package',2700,2300,'2024-01-01','2024-06-30'),(35,19,'Basic Package',1200,1000,'2024-01-01','2024-03-31'),(36,19,'Premium Package',2200,1800,'2024-01-01','2024-06-30'),(37,20,'Basic Package',1400,1100,'2024-01-01','2024-03-31'),(38,20,'Premium Package',2400,2000,'2024-01-01','2024-06-30'),(39,21,'Basic Package',1100,900,'2024-01-01','2024-03-31'),(40,21,'Premium Package',2100,1700,'2024-01-01','2024-06-30'),(41,22,'Basic Package',1600,1300,'2024-01-01','2024-03-31'),(42,22,'Premium Package',2600,2200,'2024-01-01','2024-06-30'),(43,23,'Basic Package',1300,1000,'2024-01-01','2024-03-31'),(44,23,'Premium Package',2300,1900,'2024-01-01','2024-06-30'),(45,24,'Basic Package',1500,1200,'2024-01-01','2024-03-31'),(46,24,'Premium Package',2500,2100,'2024-01-01','2024-06-30'),(47,25,'Basic Package',1700,1400,'2024-01-01','2024-03-31'),(48,25,'Premium Package',2700,2300,'2024-01-01','2024-06-30');
/*!40000 ALTER TABLE `pricepackage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quiz_id` int DEFAULT NULL,
  `level_id` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `question_type_id` int DEFAULT NULL,
  `hint` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `quiz_id` (`quiz_id`),
  KEY `level_id` (`level_id`),
  KEY `question_type_id` (`question_type_id`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`lesson_id`),
  CONSTRAINT `question_ibfk_2` FOREIGN KEY (`level_id`) REFERENCES `setting` (`id`),
  CONSTRAINT `question_ibfk_3` FOREIGN KEY (`question_type_id`) REFERENCES `setting` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,16,1,1,'What is the plural of \"child\"?',1,'Think of children'),(2,16,1,1,'Identify the adjective in: \"The cat is fluffy.\"',1,'An adjective describes a noun'),(3,16,1,1,'Choose the correct past tense form of \"run\".',1,'It’s not \"runned\"'),(4,16,1,1,'Find the noun in the sentence: \"She is a teacher.\"',1,'A noun is a person, place, or thing'),(5,16,1,1,'Which word is a verb: run, happy, blue?',1,'A verb is an action'),(6,16,1,1,'What does \"I\" mean in English?',1,'First person singular pronoun'),(7,16,1,1,'Choose the correct article: \"___ apple is red.\"',1,'Remember, vowels require a different article'),(8,16,1,1,'Which is a synonym for \"big\"?',1,'Think large'),(9,16,1,1,'Which word is an adverb: quickly, apple, beautiful?',1,'An adverb describes a verb'),(10,16,1,1,'Pick the correct form: \"She ___ at school yesterday.\"',1,'Use past tense');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz` (
  `lesson_id` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `pass_rate` float(10,2) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`lesson_id`),
  CONSTRAINT `quiz_ibfk_1` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` VALUES (16,'Basic English Grammar Quiz',30,80.00,'2024-01-15','Introductory course to English grammar','Fundamentals of Grammar',1),(17,'Intermediate English Vocabulary Quiz',30,80.00,'2024-02-20','Building vocabulary skills','English Vocabulary',1),(18,'French for Beginners Quiz',30,80.00,'2024-03-05','Introductory French course','French Basics',1),(19,'Tell us about your habits in the morning in English?',15,0.00,'2024-03-05','Experience Question','Habit',2),(20,'Tell us about your habits in the morning in English?',15,0.00,'2024-03-05','Experience Question','Habit',2);
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salenote`
--

DROP TABLE IF EXISTS `salenote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salenote` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text_content` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salenote`
--

LOCK TABLES `salenote` WRITE;
/*!40000 ALTER TABLE `salenote` DISABLE KEYS */;
/*!40000 ALTER TABLE `salenote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salenotevisualcontent`
--

DROP TABLE IF EXISTS `salenotevisualcontent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salenotevisualcontent` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salenotevisualcontent`
--

LOCK TABLES `salenotevisualcontent` WRITE;
/*!40000 ALTER TABLE `salenotevisualcontent` DISABLE KEYS */;
/*!40000 ALTER TABLE `salenotevisualcontent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salenotevisualcontent_salenote`
--

DROP TABLE IF EXISTS `salenotevisualcontent_salenote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salenotevisualcontent_salenote` (
  `sale_note_id` int NOT NULL,
  `sale_note_visual_content_id` int NOT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`sale_note_id`,`sale_note_visual_content_id`),
  KEY `sale_note_visual_content_id` (`sale_note_visual_content_id`),
  CONSTRAINT `salenotevisualcontent_salenote_ibfk_1` FOREIGN KEY (`sale_note_id`) REFERENCES `salenote` (`id`) ON DELETE CASCADE,
  CONSTRAINT `salenotevisualcontent_salenote_ibfk_2` FOREIGN KEY (`sale_note_visual_content_id`) REFERENCES `salenotevisualcontent` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salenotevisualcontent_salenote`
--

LOCK TABLES `salenotevisualcontent_salenote` WRITE;
/*!40000 ALTER TABLE `salenotevisualcontent_salenote` DISABLE KEYS */;
/*!40000 ALTER TABLE `salenotevisualcontent_salenote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setting` (
  `id` int NOT NULL AUTO_INCREMENT,
  `setting_type_id` int DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `setting_type_id` (`setting_type_id`),
  CONSTRAINT `setting_ibfk_1` FOREIGN KEY (`setting_type_id`) REFERENCES `settingtype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES (1,NULL,'LearningMaterial',NULL,NULL,NULL,NULL),(2,NULL,'Quiz',NULL,NULL,NULL,NULL),(3,1,'Admin',1,'Admin role','2023-01-01','2023-01-01'),(4,1,'Customer',1,'Customer role','2023-01-01','2023-01-01'),(5,1,'Marketing',1,'Marketing role','2023-01-01','2023-01-01'),(6,1,'Expert',1,'Expert role','2023-01-01','2023-01-01'),(7,1,'Sale',1,'Sale role','2023-01-01','2023-01-01'),(8,2,'Technology',1,'Courses about technology','2023-01-01','2023-01-01'),(9,2,'Language',1,'Courses about language','2023-01-01','2023-01-01'),(10,2,'Business',1,'Courses about business','2023-01-01','2023-01-01'),(11,2,'Health',1,'Health and fitness courses','2023-01-01','2023-01-01'),(12,2,'Art',1,'Art-related courses','2023-01-01','2023-01-01'),(13,3,'Quiz',1,'Quiz type lessons','2023-01-01','2023-01-01'),(14,3,'Learning Material',1,'Learning materials type lessons','2023-01-01','2023-01-01'),(15,4,'Login',1,'User login event','2023-01-01','2023-01-01'),(16,4,'Logout',1,'User logout event','2023-01-01','2023-01-01'),(17,5,'Easy',1,'Easy level questions','2023-01-01','2023-01-01'),(18,5,'Medium',1,'Medium level questions','2023-01-01','2023-01-01'),(19,5,'Hard',1,'Hard level questions','2023-01-01','2023-01-01'),(20,6,'Multiple Choice',1,'Multiple choice questions','2023-01-01','2023-01-01'),(21,6,'Short Answer',1,'Short answer questions','2023-01-01','2023-01-01');
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settingtype`
--

DROP TABLE IF EXISTS `settingtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `settingtype` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settingtype`
--

LOCK TABLES `settingtype` WRITE;
/*!40000 ALTER TABLE `settingtype` DISABLE KEYS */;
INSERT INTO `settingtype` VALUES (1,'User Role'),(2,'Course Category'),(3,'Lesson Type'),(4,'UserLog Type'),(5,'Question Level'),(6,'Question Type'),(7,'Category Blog');
/*!40000 ALTER TABLE `settingtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slider`
--

DROP TABLE IF EXISTS `slider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slider` (
  `id` int NOT NULL AUTO_INCREMENT,
  `author_id` int DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `back_link_url` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `slider_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slider`
--

LOCK TABLES `slider` WRITE;
/*!40000 ALTER TABLE `slider` DISABLE KEYS */;
/*!40000 ALTER TABLE `slider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tagline`
--

DROP TABLE IF EXISTS `tagline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tagline` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tagline`
--

LOCK TABLES `tagline` WRITE;
/*!40000 ALTER TABLE `tagline` DISABLE KEYS */;
INSERT INTO `tagline` VALUES (1,'Programming'),(2,'Technology'),(3,'Business'),(4,'Marketing'),(5,'Design'),(6,'English'),(7,'Travel'),(8,'Healthcare'),(9,'Hospitality'),(10,'Communication'),(11,'Education'),(12,'Language Learning'),(13,'Photography'),(14,'Graphic Design'),(15,'Management'),(16,'Web Development'),(17,'Software Development'),(18,'Data Analysis'),(19,'Public Speaking'),(20,'Leadership'),(21,'Customer Service'),(22,'Exam Preparation'),(23,'Beginner'),(24,'Advanced');
/*!40000 ALTER TABLE `tagline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `textcontent`
--

DROP TABLE IF EXISTS `textcontent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `textcontent` (
  `lesson_id` int DEFAULT NULL,
  `text_content` text,
  KEY `lesson_id` (`lesson_id`),
  CONSTRAINT `textcontent_ibfk_1` FOREIGN KEY (`lesson_id`) REFERENCES `learningmaterial` (`lesson_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `textcontent`
--

LOCK TABLES `textcontent` WRITE;
/*!40000 ALTER TABLE `textcontent` DISABLE KEYS */;
INSERT INTO `textcontent` VALUES (3,'/text_descriptions/lesson_3_text.html'),(6,'/text_descriptions/lesson_6_text.html'),(9,'/text_descriptions/lesson_9_text.html'),(12,'/text_descriptions/lesson_12_text.html'),(15,'/text_descriptions/lesson_15_text.html');
/*!40000 ALTER TABLE `textcontent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `primary_email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `first_phone` varchar(255) DEFAULT NULL,
  `second_phone` varchar(255) DEFAULT NULL,
  `secondary_email` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `prefer_contact` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `primary_email` (`primary_email`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `setting` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin1@example.com','password123',3,'2023-01-01',1,'John','Doe','1990-01-01',1,'0123456789',NULL,NULL,'admin_image.jpg','admin1@example.com',NULL),(2,'admin2@example.com','password123',3,'2023-01-02',1,'Jane','Smith','1991-02-01',0,'0987654321',NULL,NULL,'admin_image2.jpg','0987654321',NULL),(3,'customer1@example.com','password123',4,'2023-01-03',1,'Alice','Johnson','1992-03-01',0,'0978123456',NULL,NULL,'customer_image1.jpg','0978123456',NULL),(4,'customer2@example.com','password123',4,'2023-01-04',1,'Bob','Williams','1993-04-01',1,'0912345678',NULL,NULL,'customer_image2.jpg','customer2@example.com',NULL),(5,'expertit@example.com','password123',6,'2023-01-05',1,'Charlie','Brown','1994-05-01',1,'0911222333',NULL,NULL,'expertit_image.jpg','0911222333',NULL),(6,'experteng@example.com','password123',6,'2023-01-05',1,'Louis','Nguyen','1994-05-01',1,'0911222333',NULL,NULL,'experteng_image.jpg','0911222333',NULL),(7,'expert1@example.com','password123',6,'2023-01-05',1,'Charlie','Green','1994-05-01',1,'0911222333',NULL,NULL,'expert_image1.jpg','0911222333',NULL),(8,'expert2@example.com','password123',6,'2023-01-05',1,'Throm','Brown','1994-05-01',1,'0911222333',NULL,NULL,'expert_image2.jpg','0911222333',NULL),(9,'marketing1@example.com','password123',5,'2023-01-06',1,'Emily','Davis','1995-06-01',0,'0933444555',NULL,NULL,'marketing_image1.jpg','marketing1@example.com',NULL),(10,'sale1@example.com','password123',7,'2023-01-07',1,'Daniel','Garcia','1996-07-01',1,'0955666777',NULL,NULL,'sale_image1.jpg','0955666777',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlog`
--

DROP TABLE IF EXISTS `userlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userlog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` date DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `userlog_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `setting` (`id`),
  CONSTRAINT `userlog_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlog`
--

LOCK TABLES `userlog` WRITE;
/*!40000 ALTER TABLE `userlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `userlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videocontent`
--

DROP TABLE IF EXISTS `videocontent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videocontent` (
  `lesson_id` int NOT NULL,
  `video_id` varchar(255) DEFAULT NULL,
  `list_id` varchar(255) DEFAULT NULL,
  `index_vid` int DEFAULT NULL,
  `video_summary` text,
  `video_url` text,
  `description` text,
  PRIMARY KEY (`lesson_id`),
  CONSTRAINT `videocontent_ibfk_1` FOREIGN KEY (`lesson_id`) REFERENCES `learningmaterial` (`lesson_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videocontent`
--

LOCK TABLES `videocontent` WRITE;
/*!40000 ALTER TABLE `videocontent` DISABLE KEYS */;
INSERT INTO `videocontent` VALUES (1,'Aj-EnsvU5Q0','PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp',1,'Preparing for a Job Interview:<br>\n\n1. Offers key strategies for professional job interviews.<br>\n2. Guides on presenting yourself confidently, answering questions effectively, and showcasing your skills and experiences.<br>\n3. Introduces useful English phrases to communicate more clearly and persuasively.<br>\n4. Includes a fun office scenario to illustrate interview dos and don\'ts.','https://www.youtube.com/watch?v=Aj-EnsvU5Q0&list=PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp','/video_descriptions/description_video1.txt'),(2,'KN2jyw6D1ak','PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp',2,'Exploring the First Major Course Topic:<br>\n\n1. Examines the critical role of the topic in both theory and practical applications.<br>\n2. Provides detailed explanations and visual aids for easier understanding.<br>\n3. Discusses challenges learners may face and offers tips to overcome them.<br>\n4. Helps learners grasp and apply the core concepts confidently.','https://www.youtube.com/watch?v=KN2jyw6D1ak&list=PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp','/video_descriptions/description_video2.txt'),(4,'sj-dX3fZmq0','PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp',3,'Applying Foundational Theories in Practice:<br>\n\n1. Walks through step-by-step examples of using theories in real-life situations.<br>\n2. Encourages critical thinking and highlights common mistakes to avoid.<br>\n3. Bridges the gap between theory and action, fostering practical experience.','https://www.youtube.com/watch?v=sj-dX3fZmq0&list=PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp','/video_descriptions/description_video4.txt'),(5,'2zyca6fGo4A','PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp',4,'Addressing Common Challenges:<br>\n\n1. Breaks down difficult concepts and offers structured problem-solving strategies.<br>\n2. Shares real-world examples and troubleshooting techniques.<br>\n3. Connects challenges to professional settings and discusses expert solutions.','https://www.youtube.com/watch?v=2zyca6fGo4A&list=PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp','/video_descriptions/description_video5.txt'),(7,'1AmS9h8g3E4','PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp',5,'Advanced Techniques and Professional Methodologies:<br>\n\n1. Reviews foundational knowledge before introducing advanced strategies.<br>\n2. Uses detailed examples and case studies to illustrate methods.<br>\n3. Discusses the advantages and limitations of each approach.<br>\n4. Equips learners to confidently apply advanced concepts in practical contexts.','https://www.youtube.com/watch?v=1AmS9h8g3E4&list=PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp','/video_descriptions/description_video7.txt'),(8,'QWBwCoecvkM','PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp',6,'Review Session:<br>\n\n1. Revisits key concepts from earlier lessons, providing summaries and clarifications.<br>\n2. Demonstrates how ideas and techniques interact and complement one another.<br>\n3. Offers additional examples to solidify understanding in real-world contexts.<br>\n4. Prepares learners for more complex material by ensuring foundational knowledge is clear.','https://www.youtube.com/watch?v=QWBwCoecvkM&list=PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp','/video_descriptions/description_video8.txt'),(10,'NzYxS_Xy0oM','PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp',7,'Case Study Analysis:<br>\n\n1. Examines detailed case studies showcasing practical applications of theories and techniques.<br>\n2. Highlights implementations across various industries, including technology, finance, healthcare, and education.<br>\n3. Analyzes challenges, solutions, and outcomes in each scenario.<br>\n4. Demonstrates how professionals tackle complex issues using course knowledge.','https://www.youtube.com/watch?v=NzYxS_Xy0oM&list=PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp','/video_descriptions/description_video10.txt'),(11,'HN5akpqjm9Q','PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp',8,'Specialized Advanced Topics:<br>\n\n1. Introduces critical, advanced topics for learners seeking a competitive edge.<br>\n2. Explores cutting-edge research and innovations shaping the industry’s future.<br>\n3. Provides tools and techniques to integrate advancements into professional work.<br>\n4. Empowers learners to go beyond basics and become leaders in their field.','https://www.youtube.com/watch?v=HN5akpqjm9Q&list=PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp','/video_descriptions/description_video11.txt'),(13,'tAR4AJgJIrs','PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp',9,'Holistic Understanding:<br>\n\n1. Explains how course components fit together to form a cohesive framework.<br>\n2. Revisits earlier lessons to show how concepts build on each other.<br>\n3. Encourages synthesis of information and innovative application.<br>\n4. Promotes critical thinking and problem-solving for deeper comprehension and real-world application.','https://www.youtube.com/watch?v=tAR4AJgJIrs&list=PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp','/video_descriptions/description_video13.txt'),(14,'F5h3tLtgVrI','PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp',10,'Comprehensive Conclusion:<br>\n\n1. Recaps major points from the entire course.<br>\n2. Provides guidance on continuing knowledge and skill development.<br>\n3. Suggests advanced resources and further readings for deeper understanding.<br>\n4. Offers advice on applying learned concepts in professional settings to transition from theory to practice.<br>\n5. Leaves learners with a sense of accomplishment and a clear path for future growth.','https://www.youtube.com/watch?v=F5h3tLtgVrI&list=PLcetZ6gSk969oGvAI0e4_PgVnlGbm64bp','/video_descriptions/description_video14.txt');
/*!40000 ALTER TABLE `videocontent` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-10  2:13:22
