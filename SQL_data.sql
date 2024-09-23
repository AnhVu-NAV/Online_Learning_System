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
  `updated_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `brief_info` varchar(255) DEFAULT NULL,
  `author_id` int DEFAULT NULL,
  `blog_detail` text,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`),
  CONSTRAINT `blog_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `setting` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,'2023-01-10','2023-01-01',15,'blog_thumbnail1.jpg','Blog Title 1','Brief info about blog 1',7,'Full blog detail 1',1),(2,'2023-01-11','2023-01-02',16,'blog_thumbnail2.jpg','Blog Title 2','Brief info about blog 2',7,'Full blog detail 2',1),(3,'2023-01-12','2023-01-03',17,'blog_thumbnail3.jpg','Blog Title 3','Brief info about blog 3',7,'Full blog detail 3',1),(4,'2023-01-13','2023-01-04',15,'blog_thumbnail4.jpg','Blog Title 4','Brief info about blog 4',7,'Full blog detail 4',1),(5,'2023-01-14','2023-01-05',16,'blog_thumbnail5.jpg','Blog Title 5','Brief info about blog 5',7,'Full blog detail 5',1),(6,'2023-01-15','2023-01-06',17,'blog_thumbnail6.jpg','Blog Title 6','Brief info about blog 6',7,'Full blog detail 6',1),(7,'2023-01-16','2023-01-07',15,'blog_thumbnail7.jpg','Blog Title 7','Brief info about blog 7',7,'Full blog detail 7',1),(8,'2023-01-17','2023-01-08',16,'blog_thumbnail8.jpg','Blog Title 8','Brief info about blog 8',7,'Full blog detail 8',1),(9,'2023-01-18','2023-01-09',17,'blog_thumbnail9.jpg','Blog Title 9','Brief info about blog 9',7,'Full blog detail 9',1),(10,'2023-01-19','2023-01-10',15,'blog_thumbnail10.jpg','Blog Title 10','Brief info about blog 10',7,'Full blog detail 10',1),(11,'2023-01-20','2023-01-11',16,'blog_thumbnail11.jpg','Blog Title 11','Brief info about blog 11',7,'Full blog detail 11',1),(12,'2023-01-21','2023-01-12',17,'blog_thumbnail12.jpg','Blog Title 12','Brief info about blog 12',7,'Full blog detail 12',1),(13,'2023-01-22','2023-01-13',15,'blog_thumbnail13.jpg','Blog Title 13','Brief info about blog 13',7,'Full blog detail 13',1),(14,'2023-01-23','2023-01-14',16,'blog_thumbnail14.jpg','Blog Title 14','Brief info about blog 14',7,'Full blog detail 14',1),(15,'2023-01-24','2023-01-15',17,'blog_thumbnail15.jpg','Blog Title 15','Brief info about blog 15',7,'Full blog detail 15',1),(16,'2023-01-25','2023-01-16',15,'blog_thumbnail16.jpg','Blog Title 16','Brief info about blog 16',7,'Full blog detail 16',1),(17,'2023-01-26','2023-01-17',16,'blog_thumbnail17.jpg','Blog Title 17','Brief info about blog 17',7,'Full blog detail 17',1),(18,'2023-01-27','2023-01-18',17,'blog_thumbnail18.jpg','Blog Title 18','Brief info about blog 18',7,'Full blog detail 18',1),(19,'2023-01-28','2023-01-19',15,'blog_thumbnail19.jpg','Blog Title 19','Brief info about blog 19',7,'Full blog detail 19',1),(20,'2023-01-29','2023-01-20',16,'blog_thumbnail20.jpg','Blog Title 20','Brief info about blog 20',7,'Full blog detail 20',1),(21,'2023-01-30','2023-01-21',17,'blog_thumbnail21.jpg','Blog Title 21','Brief info about blog 21',7,'Full blog detail 21',1),(22,'2023-01-31','2023-01-22',15,'blog_thumbnail22.jpg','Blog Title 22','Brief info about blog 22',7,'Full blog detail 22',1),(23,'2023-02-01','2023-01-23',16,'blog_thumbnail23.jpg','Blog Title 23','Brief info about blog 23',7,'Full blog detail 23',1),(24,'2023-02-02','2023-01-24',17,'blog_thumbnail24.jpg','Blog Title 24','Brief info about blog 24',7,'Full blog detail 24',1),(25,'2023-02-03','2023-01-25',15,'blog_thumbnail25.jpg','Blog Title 25','Brief info about blog 25',7,'Full blog detail 25',1),(26,'2023-02-04','2023-01-26',16,'blog_thumbnail26.jpg','Blog Title 26','Brief info about blog 26',7,'Full blog detail 26',1),(27,'2023-02-05','2023-01-27',17,'blog_thumbnail27.jpg','Blog Title 27','Brief info about blog 27',7,'Full blog detail 27',1),(28,'2023-02-06','2023-01-28',15,'blog_thumbnail28.jpg','Blog Title 28','Brief info about blog 28',7,'Full blog detail 28',1),(29,'2023-02-07','2023-01-29',16,'blog_thumbnail29.jpg','Blog Title 29','Brief info about blog 29',7,'Full blog detail 29',1),(30,'2023-02-10','2023-01-30',17,'blog_thumbnail30.jpg','Blog Title 30','Brief info about blog 30',7,'Full blog detail 30',1);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
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
  `expert_id` int DEFAULT NULL,
  `total_duration` float DEFAULT NULL,
  `status` int DEFAULT NULL,
  `description` text,
  `category_id` int DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `number_of_lesson` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `expert_id` (`expert_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `setting` (`id`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`expert_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Java Programming',6,20,1,'Java course for beginners',7,'2023-01-01','2023-01-10','java_thumbnail.jpg',10),(2,'Python Programming',6,15,1,'Python course for data analysis',7,'2023-02-01','2023-02-10','python_thumbnail.jpg',12),(3,'Web Development',6,30,1,'Full stack web development course',7,'2023-03-01','2023-03-10','webdev_thumbnail.jpg',15),(4,'Digital Marketing',6,25,1,'Digital marketing fundamentals',8,'2023-04-01','2023-04-10','marketing_thumbnail.jpg',10),(5,'Business Management',6,40,1,'Managing business effectively',8,'2023-05-01','2023-05-10','business_thumbnail.jpg',20),(6,'Data Science',6,50,1,'Comprehensive data science course',7,'2023-06-01','2023-06-10','datascience_thumbnail.jpg',18),(7,'English for Beginners',6,35,1,'Basic English course for beginners',9,'2023-07-01','2023-07-10','english_thumbnail.jpg',14),(8,'Photography Basics',6,20,1,'Basic photography skills',10,'2023-08-01','2023-08-10','photography_thumbnail.jpg',10),(9,'Advanced Photoshop',6,30,1,'Advanced techniques in Photoshop',7,'2023-09-01','2023-09-10','photoshop_thumbnail.jpg',12),(10,'Healthy Living',6,25,1,'Tips for a healthy lifestyle',11,'2023-10-01','2023-10-10','health_thumbnail.jpg',8),(11,'Physics Fundamentals',6,40,1,'Basic principles of physics',2,'2023-11-01','2023-11-10','physics_thumbnail.jpg',20),(12,'Chemistry 101',6,45,1,'Introduction to chemistry',12,'2023-12-01','2023-12-10','chemistry_thumbnail.jpg',22),(13,'Biology Basics',6,30,1,'Introduction to biology',12,'2023-01-11','2023-01-20','biology_thumbnail.jpg',15),(14,'Public Speaking',6,15,1,'Public speaking techniques',8,'2023-02-11','2023-02-20','publicspeaking_thumbnail.jpg',8),(15,'Project Management',6,35,1,'Project management skills',8,'2023-03-11','2023-03-20','projectmanagement_thumbnail.jpg',18),(16,'Spanish for Beginners',6,20,1,'Basic Spanish language course',9,'2023-04-11','2023-04-20','spanish_thumbnail.jpg',10),(17,'Creative Writing',6,25,1,'Writing creatively',8,'2023-05-11','2023-05-20','writing_thumbnail.jpg',12),(18,'Graphic Design',6,30,1,'Basics of graphic design',7,'2023-06-11','2023-06-20','graphicdesign_thumbnail.jpg',15),(19,'Yoga for Beginners',6,20,1,'Beginner yoga poses and exercises',11,'2023-07-11','2023-07-20','yoga_thumbnail.jpg',10),(20,'Meditation Techniques',6,15,1,'Meditation and relaxation techniques',11,'2023-08-11','2023-08-20','meditation_thumbnail.jpg',8),(21,'Environmental Science',6,30,1,'Introduction to environmental science',12,'2023-09-11','2023-09-20','environmental_thumbnail.jpg',14),(22,'Geography 101',6,35,1,'Basic geography course',12,'2023-10-11','2023-10-20','geography_thumbnail.jpg',16),(23,'History of Art',6,40,1,'Overview of art history',12,'2023-11-11','2023-11-20','arthistory_thumbnail.jpg',20),(24,'Philosophy Basics',6,25,1,'Introduction to philosophy',12,'2023-12-11','2023-12-20','philosophy_thumbnail.jpg',12),(25,'Statistics for Beginners',6,30,1,'Basic statistics course',12,'2023-01-21','2023-01-30','statistics_thumbnail.jpg',15),(26,'Machine Learning',6,45,1,'Introduction to machine learning',7,'2023-02-21','2023-02-28','machinelearning_thumbnail.jpg',20),(27,'Artificial Intelligence',6,50,1,'Overview of AI techniques',7,'2023-03-21','2023-03-30','ai_thumbnail.jpg',22),(28,'Business Analytics',6,40,1,'Using data in business',8,'2023-04-21','2023-04-30','businessanalytics_thumbnail.jpg',18),(29,'Advanced Excel',6,30,1,'Advanced features in Excel',8,'2023-06-21','2023-06-30','excel_thumbnail.jpg',15);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `learningmaterial`
--

DROP TABLE IF EXISTS `learningmaterial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `learningmaterial` (
  `lesson_id` int NOT NULL,
  `updated_date` date DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `video_content_url` varchar(255) DEFAULT NULL,
  `html_content` text,
  PRIMARY KEY (`lesson_id`),
  CONSTRAINT `learningmaterial_ibfk_1` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `learningmaterial`
--

LOCK TABLES `learningmaterial` WRITE;
/*!40000 ALTER TABLE `learningmaterial` DISABLE KEYS */;
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
  `course_id` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `lesson_type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `lesson_type_id` (`lesson_type_id`),
  CONSTRAINT `lesson_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `lesson_ibfk_2` FOREIGN KEY (`lesson_type_id`) REFERENCES `setting` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
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
  `description` varchar(255) DEFAULT NULL,
  `isTrue` tinyint(1) DEFAULT NULL,
  `explanation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `option_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `option`
--

LOCK TABLES `option` WRITE;
/*!40000 ALTER TABLE `option` DISABLE KEYS */;
/*!40000 ALTER TABLE `option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalcourse`
--

DROP TABLE IF EXISTS `personalcourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personalcourse` (
  `customer_id` int NOT NULL,
  `course_id` int NOT NULL,
  `status` int DEFAULT NULL,
  `enroll_date` date DEFAULT NULL,
  `expire_date` date DEFAULT NULL,
  `progress` int DEFAULT NULL,
  PRIMARY KEY (`customer_id`,`course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `personalcourse_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`),
  CONSTRAINT `personalcourse_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalcourse`
--

LOCK TABLES `personalcourse` WRITE;
/*!40000 ALTER TABLE `personalcourse` DISABLE KEYS */;
/*!40000 ALTER TABLE `personalcourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personallesson`
--

DROP TABLE IF EXISTS `personallesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personallesson` (
  `customer_id` int NOT NULL,
  `course_id` int NOT NULL,
  `lesson_id` int NOT NULL,
  `isDone` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`customer_id`,`course_id`,`lesson_id`),
  KEY `course_id` (`course_id`),
  KEY `lesson_id` (`lesson_id`),
  CONSTRAINT `personallesson_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `personalcourse` (`customer_id`),
  CONSTRAINT `personallesson_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `personalcourse` (`course_id`),
  CONSTRAINT `personallesson_ibfk_3` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personallesson`
--

LOCK TABLES `personallesson` WRITE;
/*!40000 ALTER TABLE `personallesson` DISABLE KEYS */;
/*!40000 ALTER TABLE `personallesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personaloption`
--

DROP TABLE IF EXISTS `personaloption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personaloption` (
  `customer_id` int NOT NULL,
  `course_id` int NOT NULL,
  `lesson_id` int NOT NULL,
  `question_id` int NOT NULL,
  `option_id` int NOT NULL,
  `hasChosen` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`customer_id`,`course_id`,`lesson_id`,`question_id`,`option_id`),
  KEY `course_id` (`course_id`),
  KEY `lesson_id` (`lesson_id`),
  KEY `question_id` (`question_id`),
  KEY `option_id` (`option_id`),
  CONSTRAINT `personaloption_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `personalquestion` (`customer_id`),
  CONSTRAINT `personaloption_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `personalquestion` (`course_id`),
  CONSTRAINT `personaloption_ibfk_3` FOREIGN KEY (`lesson_id`) REFERENCES `personalquestion` (`lesson_id`),
  CONSTRAINT `personaloption_ibfk_4` FOREIGN KEY (`question_id`) REFERENCES `personalquestion` (`question_id`),
  CONSTRAINT `personaloption_ibfk_5` FOREIGN KEY (`option_id`) REFERENCES `option` (`id`)
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
  `customer_id` int NOT NULL,
  `course_id` int NOT NULL,
  `lesson_id` int NOT NULL,
  `question_id` int NOT NULL,
  `hasAnswered` tinyint(1) DEFAULT NULL,
  `isMarked` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`customer_id`,`course_id`,`lesson_id`,`question_id`),
  KEY `course_id` (`course_id`),
  KEY `lesson_id` (`lesson_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `personalquestion_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `personalquiz` (`customer_id`),
  CONSTRAINT `personalquestion_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `personalquiz` (`course_id`),
  CONSTRAINT `personalquestion_ibfk_3` FOREIGN KEY (`lesson_id`) REFERENCES `personalquiz` (`lesson_id`),
  CONSTRAINT `personalquestion_ibfk_4` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
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
  `customer_id` int NOT NULL,
  `course_id` int NOT NULL,
  `lesson_id` int NOT NULL,
  `hasPassed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`customer_id`,`course_id`,`lesson_id`),
  KEY `course_id` (`course_id`),
  KEY `lesson_id` (`lesson_id`),
  CONSTRAINT `personalquiz_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `personallesson` (`customer_id`),
  CONSTRAINT `personalquiz_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `personallesson` (`course_id`),
  CONSTRAINT `personalquiz_ibfk_3` FOREIGN KEY (`lesson_id`) REFERENCES `personallesson` (`lesson_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `course_id` int DEFAULT NULL,
  `price_package_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `sale_price` int DEFAULT NULL,
  `duration` int DEFAULT NULL,
  PRIMARY KEY (`price_package_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `pricepackage_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricepackage`
--

LOCK TABLES `pricepackage` WRITE;
/*!40000 ALTER TABLE `pricepackage` DISABLE KEYS */;
INSERT INTO `pricepackage` VALUES (1,1,'Basic Package',1000,800,90),(1,2,'Premium Package',2000,1600,180),(2,3,'Standard Package',1200,1000,365),(2,4,'Lifetime Package',5000,4000,36500),(3,5,'Basic Package',1500,1300,90),(3,6,'Premium Package',3000,2700,180),(4,7,'Basic Package',1800,1500,90),(4,8,'Premium Package',3600,3200,180),(5,9,'Business Package',2500,2000,365),(6,10,'Lifetime Package',10000,8000,36500),(7,11,'Introductory Package',2000,1800,90),(7,12,'Complete Package',5000,4000,365),(7,13,'Beginner Package',1300,1000,90),(7,14,'Advanced Package',2600,2200,180);
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
  `content` text,
  `update_date` date DEFAULT NULL,
  `level_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `quiz_id` (`quiz_id`),
  KEY `level_id` (`level_id`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`lesson_id`),
  CONSTRAINT `question_ibfk_2` FOREIGN KEY (`level_id`) REFERENCES `setting` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
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
  `duration` int DEFAULT NULL,
  `pass_rate` float DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `number_of_question` int DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`lesson_id`),
  CONSTRAINT `quiz_ibfk_1` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
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
  `description` text,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `setting_type_id` (`setting_type_id`),
  CONSTRAINT `setting_ibfk_1` FOREIGN KEY (`setting_type_id`) REFERENCES `settingtype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES (1,1,'Guest',1,'Unregistered users',NULL,NULL),(2,1,'Customer',1,'Registered users who are actual or potential customers',NULL,NULL),(3,1,'Marketing',1,'Marketing members of the organization',NULL,NULL),(4,1,'Sale',1,'Sale members of the organization',NULL,NULL),(5,1,'Expert',1,'Access & prepare the course/test contents as assigned by admin',NULL,NULL),(6,1,'Admin',1,'The organization leader/manager, acts as the system administrator',NULL,NULL),(7,2,'Technology',1,'Courses related to technology',NULL,NULL),(8,2,'Business',1,'Courses related to business',NULL,NULL),(9,2,'Language',1,'Courses related to languages',NULL,NULL),(10,2,'Art',1,'Courses related to art',NULL,NULL),(11,2,'Health',1,'Courses related to health and fitness',NULL,NULL),(12,2,'Science',1,'Courses related to science',NULL,NULL),(13,3,'LogIn',1,'Login user',NULL,NULL),(14,3,'LogOut',1,'Logout user',NULL,NULL),(15,4,'Technology',1,'Blogs related to technology',NULL,NULL),(16,4,'Business',1,'Blogs related to business',NULL,NULL),(17,4,'Language',1,'Blogs related to language',NULL,NULL),(18,5,'Subject Topic',1,'Topic of lesson subjects',NULL,NULL),(19,5,'Learning Material',1,'Learning materials for lessons',NULL,NULL),(20,5,'Quiz',1,'Quizzes for lessons',NULL,NULL),(21,6,'Easy',1,'Easy level questions',NULL,NULL),(22,6,'Medium',1,'Medium level questions',NULL,NULL),(23,6,'Hard',1,'Hard level questions',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settingtype`
--

LOCK TABLES `settingtype` WRITE;
/*!40000 ALTER TABLE `settingtype` DISABLE KEYS */;
INSERT INTO `settingtype` VALUES (1,'user Role'),(2,'Course Category'),(3,'UserLog Event'),(4,'Blog Category'),(5,'Lesson Type'),(6,'Question Level');
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
  `backlink_url` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `slider_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slider`
--

LOCK TABLES `slider` WRITE;
/*!40000 ALTER TABLE `slider` DISABLE KEYS */;
INSERT INTO `slider` VALUES (1,7,'slider_image1.jpg','http://example.com/1',1),(2,7,'slider_image2.jpg','http://example.com/2',1),(3,7,'slider_image3.jpg','http://example.com/3',1),(4,7,'slider_image4.jpg','http://example.com/4',1),(5,7,'slider_image5.jpg','http://example.com/5',1),(6,7,'slider_image6.jpg','http://example.com/6',1),(7,7,'slider_image7.jpg','http://example.com/7',1),(8,7,'slider_image8.jpg','http://example.com/8',1),(9,7,'slider_image9.jpg','http://example.com/9',1),(10,7,'slider_image10.jpg','http://example.com/10',1),(11,7,'slider_image11.jpg','http://example.com/11',1),(12,7,'slider_image12.jpg','http://example.com/12',1),(13,7,'slider_image13.jpg','http://example.com/13',1),(14,7,'slider_image14.jpg','http://example.com/14',1),(15,7,'slider_image15.jpg','http://example.com/15',1),(16,7,'slider_image16.jpg','http://example.com/16',1),(17,7,'slider_image17.jpg','http://example.com/17',1),(18,7,'slider_image18.jpg','http://example.com/18',1),(19,7,'slider_image19.jpg','http://example.com/19',1),(20,7,'slider_image20.jpg','http://example.com/20',1),(21,7,'slider_image21.jpg','http://example.com/21',1),(22,7,'slider_image22.jpg','http://example.com/22',1),(23,7,'slider_image23.jpg','http://example.com/23',1),(24,7,'slider_image24.jpg','http://example.com/24',1),(25,7,'slider_image25.jpg','http://example.com/25',1),(26,7,'slider_image26.jpg','http://example.com/26',1),(27,7,'slider_image27.jpg','http://example.com/27',1),(28,7,'slider_image28.jpg','http://example.com/28',1),(29,7,'slider_image29.jpg','http://example.com/29',1),(30,7,'slider_image30.jpg','http://example.com/30',1);
/*!40000 ALTER TABLE `slider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjecttopic`
--

DROP TABLE IF EXISTS `subjecttopic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjecttopic` (
  `lesson_id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`lesson_id`),
  CONSTRAINT `subjecttopic_ibfk_1` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjecttopic`
--

LOCK TABLES `subjecttopic` WRITE;
/*!40000 ALTER TABLE `subjecttopic` DISABLE KEYS */;
/*!40000 ALTER TABLE `subjecttopic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `setting` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'customer1@example.com','John','Doe','password123','1995-05-10',2,'2023-02-01',1,'0987654321',1,'456 Customer Road',NULL),(2,'customer2@example.com','Jane','Doe','password123','1990-07-12',2,'2023-02-02',1,'0987654322',0,'457 Customer Road',NULL),(3,'customer3@example.com','Alice','Smith','password123','1985-02-18',2,'2023-02-03',1,'0987654323',0,'458 Customer Road',NULL),(4,'marketing1@example.com','Alice','Smith','password123','1990-02-20',3,'2023-03-01',1,'1234567890',0,'789 Marketing Ave',NULL),(5,'sale1@example.com','Bob','Brown','password123','1985-03-15',4,'2023-03-02',1,'0987654321',1,'321 Sale Blvd',NULL),(6,'expert1@example.com','Charlie','Davis','password123','1980-04-10',5,'2023-04-01',1,'1234567890',0,'456 Expert Road',NULL),(7,'admin1@example.com','Admin','User','password123','1975-05-05',6,'2023-05-01',1,'1122334455',1,'789 Admin Blvd',NULL),(8,'customer30@example.com','Sam','Wilson','password123','2002-06-30',2,'2023-06-01',1,'9876543210',1,'123 Customer Street',NULL);
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
  KEY `user_id` (`user_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `userlog_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `userlog_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `setting` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlog`
--

LOCK TABLES `userlog` WRITE;
/*!40000 ALTER TABLE `userlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `userlog` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-23  5:07:58
