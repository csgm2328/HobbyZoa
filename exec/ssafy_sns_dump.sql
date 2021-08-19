-- MySQL dump 10.19  Distrib 10.3.31-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: ssafy_sns
-- ------------------------------------------------------
-- Server version	10.3.31-MariaDB-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alarm`
--

DROP TABLE IF EXISTS `alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alarm` (
  `alarmcode` int(11) NOT NULL AUTO_INCREMENT,
  `alarm_type` varchar(10) NOT NULL,
  `from_email` varchar(40) NOT NULL,
  `to_email` varchar(40) NOT NULL,
  `feedcode` int(11) DEFAULT 0,
  `content` varchar(100) NOT NULL,
  `alarm_check` bit(1) DEFAULT b'0',
  `create_date` datetime DEFAULT current_timestamp(),
  `check_date` datetime DEFAULT NULL,
  PRIMARY KEY (`alarmcode`),
  KEY `fk_alarm_from` (`from_email`),
  KEY `fk_alarm_to` (`to_email`),
  CONSTRAINT `fk_alarm_from` FOREIGN KEY (`from_email`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_alarm_to` FOREIGN KEY (`to_email`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm`
--

LOCK TABLES `alarm` WRITE;
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
INSERT INTO `alarm` VALUES (1,'JOIN','admin@hobbyzoa.com','csgm2328@gmail.com',0,'csj님 Hobby Zoa에 오신걸 환영합니다!😃','\0','2021-08-18 16:27:11',NULL),(2,'FOLLOW','admin@hobbyzoa.com','csgm2328@gmail.com',0,'관리자님이 회원님을 팔로우하기 시작했습니다.','\0','2021-08-18 16:31:23',NULL),(3,'JOIN','admin@hobbyzoa.com','snflo98@gmail.com',0,'hyeeun님 Hobby Zoa에 오신걸 환영합니다!😃','\0','2021-08-18 16:36:30',NULL),(4,'LIKE','admin@hobbyzoa.com','csgm2328@gmail.com',0,'','\0','2021-08-18 17:06:54',NULL),(5,'JOIN','admin@hobbyzoa.com','ssafy@ssafy.com',0,'ssafy님 Hobby Zoa에 오신걸 환영합니다!😃','','2021-08-18 17:09:01','2021-08-19 10:54:00'),(6,'SCRAP','admin@hobbyzoa.com','csgm2328@gmail.com',0,'관리자님이 별태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-18 17:25:03',NULL),(8,'JOIN','admin@hobbyzoa.com','seyoungs22@naver.com',0,'sey_zoa님 Hobby Zoa에 오신걸 환영합니다!😃','\0','2021-08-18 21:11:43',NULL),(9,'LIKE','seyoungs22@naver.com','csgm2328@gmail.com',0,'sey_zoa님이 [별] 태그가 추가된 회원님의 피드를 좋아합니다.','','2021-08-18 21:17:50','2021-08-19 01:16:07'),(10,'JOIN','admin@hobbyzoa.com','wnsdud4197@naver.com',0,'양준영님 Hobby Zoa에 오신걸 환영합니다!😃','\0','2021-08-19 10:19:02',NULL),(11,'FOLLOW','wnsdud4197@naver.com','ssafy@ssafy.com',0,'양준영님이 회원님을 팔로우하기 시작했습니다.','','2021-08-19 10:38:39','2021-08-19 10:53:59'),(12,'JOIN','admin@hobbyzoa.com','tlawldud96@naver.com',0,'Ji-0님 Hobby Zoa에 오신걸 환영합니다!😃','\0','2021-08-19 10:41:45',NULL),(13,'FOLLOW','seyoungs22@naver.com','ssafy@ssafy.com',0,'sey_zoa님이 회원님을 팔로우하기 시작했습니다.','','2021-08-19 10:44:01','2021-08-19 10:53:58'),(14,'LIKE','tlawldud96@naver.com','csgm2328@gmail.com',0,'Ji-0님이 별태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 10:46:26',NULL),(15,'LIKE','tlawldud96@naver.com','snflo98@gmail.com',0,'Ji-0님이 그림태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 10:46:34',NULL),(16,'LIKE','ssafy@ssafy.com','snflo98@gmail.com',0,'ssafy님이 그림태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 10:47:16',NULL),(17,'SCRAP','ssafy@ssafy.com','tlawldud96@naver.com',0,'ssafy님이 트레킹태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 10:47:23',NULL),(18,'LIKE','snflo98@gmail.com','tlawldud96@naver.com',0,'hyeeun님이 트레킹태그가 추가된 회원님의 피드를 좋아합니다.','','2021-08-19 10:47:37','2021-08-19 15:04:29'),(19,'SCRAP','tlawldud96@naver.com','snflo98@gmail.com',0,'Ji-0님이 그림태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 10:47:37',NULL),(20,'FOLLOW','snflo98@gmail.com','tlawldud96@naver.com',0,'hyeeun님이 회원님을 팔로우하기 시작했습니다.','','2021-08-19 10:47:45','2021-08-19 15:04:30'),(21,'FOLLOW','snflo98@gmail.com','ssafy@ssafy.com',0,'hyeeun님이 회원님을 팔로우하기 시작했습니다.','','2021-08-19 10:48:01','2021-08-19 10:53:57'),(22,'LIKE','tlawldud96@naver.com','snflo98@gmail.com',0,'Ji-0님이 농사태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 10:48:39',NULL),(23,'SCRAP','tlawldud96@naver.com','snflo98@gmail.com',0,'Ji-0님이 농사태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 10:49:05',NULL),(24,'FOLLOW','tlawldud96@naver.com','csgm2328@gmail.com',0,'Ji-0님이 회원님을 팔로우하기 시작했습니다.','\0','2021-08-19 10:49:49',NULL),(25,'SCRAP','snflo98@gmail.com','tlawldud96@naver.com',0,'hyeeun님이 트레킹태그가 추가된 회원님의 피드를 좋아합니다.','','2021-08-19 10:50:36','2021-08-19 15:04:31'),(26,'LIKE','seyoungs22@naver.com','ssafy@ssafy.com',0,'sey_zoa님이 운동태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 11:00:06',NULL),(27,'SCRAP','seyoungs22@naver.com','snflo98@gmail.com',0,'sey_zoa님이 그림태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 11:01:11',NULL),(28,'LIKE','seyoungs22@naver.com','tlawldud96@naver.com',0,'sey_zoa님이 맛집투어태그가 추가된 회원님의 피드를 좋아합니다.','','2021-08-19 11:02:38','2021-08-19 15:04:32'),(29,'LIKE','tlawldud96@naver.com','seyoungs22@naver.com',0,'Ji-0님이 수채화태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 11:03:07',NULL),(30,'SCRAP','tlawldud96@naver.com','seyoungs22@naver.com',0,'Ji-0님이 수채화태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 11:03:17',NULL),(31,'LIKE','tlawldud96@naver.com','ssafy@ssafy.com',0,'Ji-0님이 운동태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 11:03:52',NULL),(32,'SCRAP','tlawldud96@naver.com','ssafy@ssafy.com',0,'Ji-0님이 운동태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 11:04:20',NULL),(33,'SCRAP','tlawldud96@naver.com','ssafy@ssafy.com',0,'Ji-0님이 운동태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 11:09:00',NULL),(34,'FOLLOW','tlawldud96@naver.com','seyoungs22@naver.com',0,'Ji-0님이 회원님을 팔로우하기 시작했습니다.','\0','2021-08-19 11:09:31',NULL),(35,'FOLLOW','seyoungs22@naver.com','tlawldud96@naver.com',0,'sey_zoa님이 회원님을 팔로우하기 시작했습니다.','','2021-08-19 11:15:47','2021-08-19 15:04:33'),(36,'FOLLOW','tlawldud96@naver.com','snflo98@gmail.com',0,'Ji-0님이 회원님을 팔로우하기 시작했습니다.','\0','2021-08-19 11:22:36',NULL),(37,'SCRAP','tlawldud96@naver.com','snflo98@gmail.com',0,'Ji-0님이 농사태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 11:24:08',NULL),(38,'SCRAP','snflo98@gmail.com','csgm2328@gmail.com',0,'hyeeun_jjang님이 서핑태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 11:37:06',NULL),(39,'LIKE','snflo98@gmail.com','csgm2328@gmail.com',0,'hyeeun_jjang님이 서핑태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 11:37:08',NULL),(40,'LIKE','tlawldud96@naver.com','csgm2328@gmail.com',0,'Ji-0님이 서핑태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 11:52:12',NULL),(41,'LIKE','csgm2328@gmail.com','ssafy@ssafy.com',0,'추승지님이 운동태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 11:55:56',NULL),(42,'SCRAP','wnsdud4197@naver.com','csgm2328@gmail.com',0,'양준영님이 스노우보드태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 13:31:13',NULL),(43,'SCRAP','wnsdud4197@naver.com','csgm2328@gmail.com',0,'양준영님이 스노우보드태그가 추가된 회원님의 피드를 좋아합니다.','\0','2021-08-19 13:31:51',NULL),(44,'JOIN','admin@hobbyzoa.com','wnsdud4197@kakao.com',0,'test님 Hobby Zoa에 오신걸 환영합니다!😃','\0','2021-08-19 14:11:13',NULL),(45,'FOLLOW','csgm2328@gmail.com','tlawldud96@naver.com',0,'추승지님이 회원님을 팔로우하기 시작했습니다.','\0','2021-08-19 15:27:18',NULL);
/*!40000 ALTER TABLE `alarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendance` (
  `checkcode` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `hobbycode` int(11) DEFAULT NULL,
  `start` int(11) DEFAULT NULL,
  `end` int(11) DEFAULT NULL,
  `regtime` timestamp NULL DEFAULT current_timestamp(),
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`checkcode`),
  KEY `fk_check_email` (`email`),
  KEY `fk_check_hobbycode` (`hobbycode`),
  CONSTRAINT `fk_check_email` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_check_hobbycode` FOREIGN KEY (`hobbycode`) REFERENCES `hobby` (`hobbycode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (2,'tlawldud96@naver.com',2,7,9,'2021-08-19 02:07:56','가볍게 아침 트레킹'),(3,'snflo98@gmail.com',3,9,11,'2021-08-19 02:23:20','모닝 운동!'),(5,'seyoungs22@naver.com',4,14,16,'2021-08-19 02:27:46','동명동 ‘성수동 미술관’ 수채화그리기'),(6,'ssafy@ssafy.com',8,18,20,'2021-08-19 02:28:09','오늘도 운동 열심히!'),(7,'wnsdud4197@naver.com',9,2,23,'2021-08-19 02:28:44','ㅎㅇ'),(8,'csgm2328@gmail.com',10,11,13,'2021-08-19 02:36:57','서핑 시작'),(12,'tlawldud96@naver.com',6,13,16,'2021-08-19 06:13:51','오일파스텔로 곰돌이 푸 그리기');
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendanceacc`
--

DROP TABLE IF EXISTS `attendanceacc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendanceacc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hobbycode` int(11) DEFAULT NULL,
  `timetot` int(11) DEFAULT 0,
  `daytot` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `fk_attendanceacc_hobbycode` (`hobbycode`),
  CONSTRAINT `fk_attendanceacc_hobbycode` FOREIGN KEY (`hobbycode`) REFERENCES `hobby` (`hobbycode`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendanceacc`
--

LOCK TABLES `attendanceacc` WRITE;
/*!40000 ALTER TABLE `attendanceacc` DISABLE KEYS */;
INSERT INTO `attendanceacc` VALUES (1,3,2,1),(2,2,2,1),(4,4,2,1),(5,8,2,1),(6,9,21,1),(7,10,2,1),(8,6,3,1);
/*!40000 ALTER TABLE `attendanceacc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `badge`
--

DROP TABLE IF EXISTS `badge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `badge` (
  `badgecode` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `hobbycode` int(11) DEFAULT NULL,
  PRIMARY KEY (`badgecode`),
  KEY `fk_badge_hobbycode` (`hobbycode`),
  CONSTRAINT `fk_badge_hobbycode` FOREIGN KEY (`hobbycode`) REFERENCES `hobby` (`hobbycode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `badge`
--

LOCK TABLES `badge` WRITE;
/*!40000 ALTER TABLE `badge` DISABLE KEYS */;
INSERT INTO `badge` VALUES (2,'beginner',2),(3,'beginner',3),(4,'beginner',4),(6,'beginner',6),(8,'beginner',8),(9,'beginner',9),(10,'beginner',10),(11,'beginner',11),(15,'beginner',13),(16,'beginner',14);
/*!40000 ALTER TABLE `badge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_token`
--

DROP TABLE IF EXISTS `email_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_token` (
  `id` varchar(36) NOT NULL,
  `user_email` varchar(40) NOT NULL,
  `create_date` datetime DEFAULT current_timestamp(),
  `expiration_date` datetime DEFAULT NULL,
  `expired` bit(1) DEFAULT b'0',
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_emailToken_email` (`user_email`),
  CONSTRAINT `fk_emailToken_email` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_token`
--

LOCK TABLES `email_token` WRITE;
/*!40000 ALTER TABLE `email_token` DISABLE KEYS */;
INSERT INTO `email_token` VALUES ('07b023cc-5553-4aa0-ae3f-8df565b572e8','wnsdud4197@naver.com',NULL,'2021-08-19 10:24:02','',NULL),('3fadad0e-41d8-4b00-a315-6c50a337f0a1','csgm2328@gmail.com',NULL,'2021-08-18 16:32:11','',NULL),('42998584-44fd-4c43-b474-460cb0f485d6','tlawldud96@naver.com',NULL,'2021-08-19 10:46:45','',NULL),('5cb87102-f81b-41e5-8bd8-ceba38b3cafa','ssafy@ssafy.com',NULL,'2021-08-18 17:14:27','',NULL),('962e52fa-4f43-42ab-80ea-33c324e1335b','wnsdud4197@kakao.com',NULL,'2021-08-19 14:16:13','\0',NULL),('aba02f00-8120-41b5-9ae6-e2c815d149ba','seyoungs22@naver.com',NULL,'2021-08-18 21:16:43','',NULL),('cd791520-596a-4de5-a7da-3c4ecbbe2361','admin@hobbyzoa.com',NULL,'2021-08-18 16:34:40','',NULL),('e44844cf-8b81-4d5b-9f72-ae1ae443b3ea','snflo98@gmail.com',NULL,'2021-08-18 16:41:30','',NULL);
/*!40000 ALTER TABLE `email_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed`
--

DROP TABLE IF EXISTS `feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feed` (
  `email` varchar(40) NOT NULL,
  `feedcode` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(40) NOT NULL,
  `regtime` datetime DEFAULT current_timestamp(),
  `comment` varchar(100) DEFAULT NULL,
  `likes` int(11) DEFAULT 0,
  `scrap` int(11) DEFAULT 0,
  `tag` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`feedcode`),
  KEY `fk_feed_email` (`email`),
  CONSTRAINT `fk_feed_email` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed`
--

LOCK TABLES `feed` WRITE;
/*!40000 ALTER TABLE `feed` DISABLE KEYS */;
INSERT INTO `feed` VALUES ('csgm2328@gmail.com',1,'csj','2021-08-18 16:27:48','별 구경',4,0,NULL),('snflo98@gmail.com',2,'hyeeun','2021-08-19 10:45:05','카페에서 그림그리기~!! 햅삐햅삐',2,0,NULL),('tlawldud96@naver.com',3,'Ji-0','2021-08-19 10:45:21','장성으로 트레킹 간 날!\n열심히 운동하고 돈까스먹음~_~\n재크와 돈까스 추천😍',1,0,NULL),('snflo98@gmail.com',4,'hyeeun','2021-08-19 10:47:08','이제는 자급자족의 시대! 얼른얼른 커서 빨리 나에게로 ㅎ',1,0,NULL),('ssafy@ssafy.com',5,'ssafy','2021-08-19 10:49:13','오늘도 열심히 운동!',3,0,NULL),('seyoungs22@naver.com',6,'sey_zoa','2021-08-19 10:52:45','오늘은 그림그리기 카페 방문~',1,0,NULL),('tlawldud96@naver.com',7,'Ji-0','2021-08-19 11:02:27','순천 맛집 투어~~\n맛집 투어야말로 최고의 취미,,아니겠어요,,?ㅎㅎ\n옥천 귀뚜라미 가면 전복장 비빔밥 필수필수!!!!!!!!',1,0,NULL),('seyoungs22@naver.com',8,'sey_zoa','2021-08-19 11:14:40','어버이날 선물로 수제 떡케이크 만들기~다음주에는 떡마카롱 만들러 가야지! 운암동 수제 떡케이크 클래스 추천!',0,0,NULL),('csgm2328@gmail.com',9,'추승지','2021-08-19 11:34:52','캘리포니아에서 서핑하고 싶다',2,0,NULL),('snflo98@gmail.com',10,'hyeeun','2021-08-19 11:37:49','나랑 게임할 사람~',0,0,NULL),('csgm2328@gmail.com',12,'추승지','2021-08-19 13:16:54','겨울엔 보드 타러가야지~',0,0,NULL);
/*!40000 ALTER TABLE `feed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed_like`
--

DROP TABLE IF EXISTS `feed_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feed_like` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `feedcode` int(11) NOT NULL,
  PRIMARY KEY (`code`),
  KEY `fk_like_email` (`email`),
  KEY `fk_like_feedcode` (`feedcode`),
  CONSTRAINT `fk_like_email` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE CASCADE,
  CONSTRAINT `fk_like_feedcode` FOREIGN KEY (`feedcode`) REFERENCES `feed` (`feedcode`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed_like`
--

LOCK TABLES `feed_like` WRITE;
/*!40000 ALTER TABLE `feed_like` DISABLE KEYS */;
INSERT INTO `feed_like` VALUES (10,'admin@hobbyzoa.com',1),(14,'csgm2328@gmail.com',1),(15,'seyoungs22@naver.com',1),(16,'tlawldud96@naver.com',1),(17,'tlawldud96@naver.com',2),(18,'ssafy@ssafy.com',2),(19,'snflo98@gmail.com',3),(20,'tlawldud96@naver.com',4),(21,'ssafy@ssafy.com',5),(24,'tlawldud96@naver.com',6),(25,'tlawldud96@naver.com',5),(26,'snflo98@gmail.com',9),(27,'tlawldud96@naver.com',9),(28,'csgm2328@gmail.com',5);
/*!40000 ALTER TABLE `feed_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedtags`
--

DROP TABLE IF EXISTS `feedtags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedtags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `feedcode` int(11) NOT NULL,
  `tagcode` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_feedtags_feedcode` (`feedcode`),
  KEY `fk_feedtags_tagcode` (`tagcode`),
  CONSTRAINT `fk_feedtags_feedcode` FOREIGN KEY (`feedcode`) REFERENCES `feed` (`feedcode`) ON DELETE CASCADE,
  CONSTRAINT `fk_feedtags_tagcode` FOREIGN KEY (`tagcode`) REFERENCES `tag` (`tagcode`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedtags`
--

LOCK TABLES `feedtags` WRITE;
/*!40000 ALTER TABLE `feedtags` DISABLE KEYS */;
INSERT INTO `feedtags` VALUES (10,1,1),(11,2,2),(12,2,3),(13,2,4),(14,3,5),(15,4,6),(16,4,7),(17,4,8),(18,5,9),(19,5,10),(20,6,3),(21,6,2),(22,7,11),(23,7,12),(24,8,13),(25,8,14),(26,9,15),(27,9,16),(28,10,17),(29,10,18),(30,10,19),(32,12,20),(34,12,22);
/*!40000 ALTER TABLE `feedtags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `follow` (
  `followcode` int(11) NOT NULL AUTO_INCREMENT,
  `from_email` varchar(40) NOT NULL,
  `to_email` varchar(40) NOT NULL,
  PRIMARY KEY (`followcode`),
  KEY `fk_follow_from` (`from_email`),
  KEY `fk_follow_to` (`to_email`),
  CONSTRAINT `fk_follow_from` FOREIGN KEY (`from_email`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_follow_to` FOREIGN KEY (`to_email`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,'admin@hobbyzoa.com','csgm2328@gmail.com'),(2,'wnsdud4197@naver.com','ssafy@ssafy.com'),(3,'seyoungs22@naver.com','ssafy@ssafy.com'),(4,'snflo98@gmail.com','tlawldud96@naver.com'),(5,'snflo98@gmail.com','ssafy@ssafy.com'),(6,'tlawldud96@naver.com','csgm2328@gmail.com'),(7,'tlawldud96@naver.com','seyoungs22@naver.com'),(8,'seyoungs22@naver.com','tlawldud96@naver.com'),(9,'tlawldud96@naver.com','snflo98@gmail.com'),(10,'csgm2328@gmail.com','tlawldud96@naver.com');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `searchword` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (1,'snflo98@gmail.com','ssafy'),(2,'ssafy@ssafy.com','se'),(3,'snflo98@gmail.com','ji-0'),(4,'snflo98@gmail.com','ji-0'),(5,'snflo98@gmail.com','준영'),(6,'snflo98@gmail.com','ssafy'),(7,'snflo98@gmail.com','ji-0'),(8,'snflo98@gmail.com','ji-0'),(9,'snflo98@gmail.com','ji-0'),(10,'snflo98@gmail.com','준영'),(11,'snflo98@gmail.com','ssafy'),(12,'snflo98@gmail.com','ssafy'),(13,'snflo98@gmail.com','ji-0'),(14,'snflo98@gmail.com','ji-0'),(15,'snflo98@gmail.com','ssafy'),(16,'snflo98@gmail.com','ssafy'),(17,'snflo98@gmail.com','ssafy'),(18,'snflo98@gmail.com','ssafy'),(19,'snflo98@gmail.com','ji-0'),(20,'wnsdud4197@naver.com','ji');
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hobby`
--

DROP TABLE IF EXISTS `hobby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hobby` (
  `hobbycode` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`hobbycode`),
  UNIQUE KEY `name` (`name`,`email`),
  KEY `fk_hobby_email` (`email`),
  CONSTRAINT `fk_hobby_email` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hobby`
--

LOCK TABLES `hobby` WRITE;
/*!40000 ALTER TABLE `hobby` DISABLE KEYS */;
INSERT INTO `hobby` VALUES (10,'서핑','csgm2328@gmail.com'),(4,'수채화그리기','seyoungs22@naver.com'),(14,'스노우보드','csgm2328@gmail.com'),(6,'오일파스텔','tlawldud96@naver.com'),(3,'운동','snflo98@gmail.com'),(8,'운동','ssafy@ssafy.com'),(9,'운동','wnsdud4197@naver.com'),(11,'축구','csgm2328@gmail.com'),(13,'코딩','ssafy@ssafy.com'),(2,'트레킹','tlawldud96@naver.com');
/*!40000 ALTER TABLE `hobby` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `imgcode` int(11) NOT NULL AUTO_INCREMENT,
  `orgname` varchar(255) DEFAULT NULL,
  `newname` varchar(255) DEFAULT NULL,
  `imgpath` varchar(255) DEFAULT NULL,
  `imgsize` bigint(20) NOT NULL,
  `feedcode` int(11) DEFAULT NULL,
  PRIMARY KEY (`imgcode`),
  KEY `fk_image_feedcode` (`feedcode`),
  CONSTRAINT `fk_image_feedcode` FOREIGN KEY (`feedcode`) REFERENCES `feed` (`feedcode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'d93b9gv-a1574c22-ddc5-4597-8752-75daba6c0439.jpg','2428742377405228.jpg','images/feeds/20210818/2428742377405228.jpg',1027321,1),(2,'그림.jpg','2494579245030261.jpg','images/feeds/20210819/2494579245030261.jpg',244690,2),(3,'KakaoTalk_20210818_102459211.jpg','2494595092499959.jpg','images/feeds/20210819/2494595092499959.jpg',2284340,3),(4,'딸기.jpg','2494702027059833.jpg','images/feeds/20210819/2494702027059833.jpg',539971,4),(5,'토마토.jpg','2494702027144572.jpg','images/feeds/20210819/2494702027144572.jpg',885178,4),(6,'45AA7AFA-7FC6-408F-B641-5CFED3932D81.png','2494827327771413.png','images/feeds/20210819/2494827327771413.png',363566,5),(7,'13A32EED-3240-4282-A66F-1815D386E5D7.jpeg','2495038771407803.jpg','images/feeds/20210819/2495038771407803.jpg',3630954,6),(8,'KakaoTalk_20210819_105951325.jpg','2495621398289366.jpg','images/feeds/20210819/2495621398289366.jpg',2234291,7),(9,'KakaoTalk_20210819_105952334.jpg','2495621398378525.jpg','images/feeds/20210819/2495621398378525.jpg',2445402,7),(10,'A85BB56A-BB18-4E4A-98F2-4F2A01C33ED4.jpeg','2496354364848876.jpg','images/feeds/20210819/2496354364848876.jpg',1570552,8),(11,'vc_californiasbestwaves_windansea_st_rf_9313807_1280x640.jpg','2497566433620341.jpg','images/feeds/20210819/2497566433620341.jpg',98233,9),(12,'어몽1.jpg','2497742815376225.jpg','images/feeds/20210819/2497742815376225.jpg',239812,10),(13,'어몽2.jpg','2497742815452923.jpg','images/feeds/20210819/2497742815452923.jpg',194968,10),(15,'39659395-산-언덕에서-스노우-보드-점프-남자.jpg','2503688339584351.jpg','images/feeds/20210819/2503688339584351.jpg',125655,12);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifiication`
--

DROP TABLE IF EXISTS `notifiication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifiication` (
  `email` varchar(40) NOT NULL,
  `requestcode` int(11) NOT NULL,
  `create_date` datetime DEFAULT current_timestamp(),
  KEY `fk_notification_email` (`email`),
  KEY `fk_notification_requestcode` (`requestcode`),
  CONSTRAINT `fk_notification_email` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE CASCADE,
  CONSTRAINT `fk_notification_requestcode` FOREIGN KEY (`requestcode`) REFERENCES `request` (`requestcode`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifiication`
--

LOCK TABLES `notifiication` WRITE;
/*!40000 ALTER TABLE `notifiication` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifiication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `email` varchar(40) NOT NULL,
  `nickname` varchar(40) NOT NULL,
  `following` int(11) DEFAULT 0,
  `follower` int(11) DEFAULT 0,
  `feeds` int(11) DEFAULT 0,
  `imgpath` varchar(255) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`email`),
  CONSTRAINT `fk_profile_email` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES ('admin@hobbyzoa.com','관리자',1,0,0,NULL,'관리자 계정입니다'),('csgm2328@gmail.com','추승지',0,2,3,'images/profiles/csgm2328@gmail.com.jpg','같이 서핑하실분?🏄'),('seyoungs22@naver.com','sey_zoa',2,1,2,'images/profiles/seyoungs22@naver.com.jpg','주말엔 취미활동! ‘수채화 그리기’ 함께 하실 분 👩‍🎨'),('snflo98@gmail.com','hyeeun_jjang',2,1,3,'images/profiles/snflo98@gmail.com.jpg','나는야 겸둥잉'),('ssafy@ssafy.com','ssafy',0,3,1,'images/profiles/ssafy@ssafy.com.png','싸피 5기'),('tlawldud96@naver.com','Ji-0',3,3,2,'images/profiles/tlawldud96@naver.com.jpg','취미 부자가 목표 : )'),('wnsdud4197@kakao.com','test',0,0,0,NULL,NULL),('wnsdud4197@naver.com','양준영',1,0,0,NULL,'하이');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile_image`
--

DROP TABLE IF EXISTS `profile_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile_image` (
  `email` varchar(40) NOT NULL,
  `imgname` varchar(255) NOT NULL,
  `content_type` varchar(10) NOT NULL,
  `imgsize` bigint(20) NOT NULL,
  `imgpath` varchar(255) NOT NULL,
  PRIMARY KEY (`email`),
  CONSTRAINT `fk_profile_image_email` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile_image`
--

LOCK TABLES `profile_image` WRITE;
/*!40000 ALTER TABLE `profile_image` DISABLE KEYS */;
INSERT INTO `profile_image` VALUES ('csgm2328@gmail.com','46a2e13136a67931ecf9e32a3379fdfef43b7601d2def99221fc9ccdaa7a4f58.jpg','image/jpeg',516730,'images/profiles/csgm2328@gmail.com.jpg'),('seyoungs22@naver.com','9D2951D7-07F1-4DB1-9C78-55DD708496B9.jpeg','image/jpeg',2189393,'images/profiles/seyoungs22@naver.com.jpg'),('snflo98@gmail.com','토마토.jpg','image/jpeg',885178,'images/profiles/snflo98@gmail.com.jpg'),('ssafy@ssafy.com','FD0E95E3-1230-4C9C-A6D5-63878F1956A3.png','image/png',4565,'images/profiles/ssafy@ssafy.com.png'),('tlawldud96@naver.com','profile.jpg','image/jpeg',2328629,'images/profiles/tlawldud96@naver.com.jpg');
/*!40000 ALTER TABLE `profile_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reply` (
  `replycode` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `feedcode` int(11) NOT NULL,
  `nickname` varchar(40) NOT NULL,
  `content` varchar(100) NOT NULL,
  `hide` tinyint(4) NOT NULL DEFAULT 0,
  `regtime` datetime DEFAULT current_timestamp(),
  `feed_feedcode` int(11) DEFAULT NULL,
  PRIMARY KEY (`replycode`),
  KEY `fk_reply_email` (`email`),
  KEY `fk_reply_feedcode` (`feedcode`),
  KEY `FKemc4ajlsnlcrdfpkpd1k8guaq` (`feed_feedcode`),
  CONSTRAINT `FKemc4ajlsnlcrdfpkpd1k8guaq` FOREIGN KEY (`feed_feedcode`) REFERENCES `feed` (`feedcode`),
  CONSTRAINT `fk_reply_email` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE CASCADE,
  CONSTRAINT `fk_reply_feedcode` FOREIGN KEY (`feedcode`) REFERENCES `feed` (`feedcode`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (1,'admin@hobbyzoa.com',1,'관리자','댓글도?',0,'2021-08-18 16:55:11',NULL),(2,'csgm2328@gmail.com',1,'csj','댓',0,'2021-08-18 16:58:41',NULL),(3,'csgm2328@gmail.com',1,'csj','대ㅑㅅ글',0,'2021-08-18 17:24:48',NULL),(4,'tlawldud96@naver.com',2,'Ji-0','그림 너무 잘 그린 거 아닌가요? 디즈니에서 데려갈듯',0,'2021-08-19 10:47:37',NULL),(5,'tlawldud96@naver.com',4,'Ji-0','ㅋㅋㅋㅋㅋ뭐 키우고 있는거야?? ',0,'2021-08-19 10:49:05',NULL),(6,'ssafy@ssafy.com',5,'ssafy','오늘도 화이팅!',1,'2021-08-19 10:49:51',NULL),(7,'snflo98@gmail.com',3,'hyeeun','아 저랑도 한번 갔다가 돈까스 드시져~!',0,'2021-08-19 10:50:36',NULL),(8,'snflo98@gmail.com',4,'hyeeun','딸기 && 토마토쓰.. 근데 현재 딸기는 죽어가는중😂',0,'2021-08-19 10:52:03',NULL),(9,'tlawldud96@naver.com',6,'Ji-0','어디 카페인지 궁금해요~~',0,'2021-08-19 11:03:17',NULL),(10,'tlawldud96@naver.com',5,'Ji-0','운동 열심히 하네요ㅠㅠ 자극받고 갑니당',0,'2021-08-19 11:04:20',NULL),(11,'seyoungs22@naver.com',6,'sey_zoa','동명동 ‘성수동미술관’이에요~~네이버로 예약하고 가세요~',0,'2021-08-19 11:18:31',NULL),(12,'tlawldud96@naver.com',4,'Ji-0','앗...ㅠㅠ😅 그래서 토마토 언제 먹으러 가면 돼?ㅎ',0,'2021-08-19 11:24:08',NULL),(13,'tlawldud96@naver.com',3,'Ji-0','아 좋습니당~~ 돈까스 맛집 갑시다ㅇ ㅏ ~~',0,'2021-08-19 11:27:27',NULL),(14,'snflo98@gmail.com',4,'hyeeun','ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ일단 살아있는지 확인해볼께^^',0,'2021-08-19 13:11:40',NULL),(15,'csgm2328@gmail.com',12,'추승지','#스노우보드, #겨울',0,'2021-08-19 13:18:16',NULL);
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `requestcode` int(11) NOT NULL,
  `type` varchar(40) NOT NULL,
  `content` varchar(100) NOT NULL,
  `datetime` datetime NOT NULL,
  PRIMARY KEY (`requestcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scrap`
--

DROP TABLE IF EXISTS `scrap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scrap` (
  `scrapcode` int(11) NOT NULL AUTO_INCREMENT,
  `feedcode` int(11) NOT NULL,
  `email` varchar(40) NOT NULL,
  `feed_feedcode` int(11) DEFAULT NULL,
  PRIMARY KEY (`scrapcode`),
  KEY `fk_scrap_email` (`email`),
  KEY `fk_scrap_feedcode` (`feedcode`),
  KEY `FKicu7klhlh6652ixk6b9v7p6ch` (`feed_feedcode`),
  CONSTRAINT `FKicu7klhlh6652ixk6b9v7p6ch` FOREIGN KEY (`feed_feedcode`) REFERENCES `feed` (`feedcode`),
  CONSTRAINT `fk_scrap_email` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE CASCADE,
  CONSTRAINT `fk_scrap_feedcode` FOREIGN KEY (`feedcode`) REFERENCES `feed` (`feedcode`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scrap`
--

LOCK TABLES `scrap` WRITE;
/*!40000 ALTER TABLE `scrap` DISABLE KEYS */;
INSERT INTO `scrap` VALUES (2,1,'csgm2328@gmail.com',NULL),(3,1,'admin@hobbyzoa.com',NULL),(4,3,'ssafy@ssafy.com',NULL),(5,5,'ssafy@ssafy.com',NULL),(6,2,'seyoungs22@naver.com',NULL),(7,5,'tlawldud96@naver.com',NULL),(8,9,'snflo98@gmail.com',NULL);
/*!40000 ALTER TABLE `scrap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `tagcode` int(11) NOT NULL AUTO_INCREMENT,
  `tagname` varchar(45) NOT NULL,
  `cnt` int(11) NOT NULL,
  PRIMARY KEY (`tagcode`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'별',1),(2,'그림',2),(3,'수채화',4),(4,'곰돌이푸',1),(5,'트레킹',1),(6,'농사',1),(7,'식물',1),(8,'힐링',1),(9,'운동',2),(10,'헬스',1),(11,'맛집투어',1),(12,'순천',1),(13,'떡케이크만들기',1),(14,'떡케이크클래스',1),(15,'서핑',1),(16,'바다',1),(17,'게임',1),(18,'어몽어스',1),(19,'임포스터',1),(20,'스노우보드',3),(22,'겨울',2),(23,'취미',1),(24,'그리기',1);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `email` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `nickname` varchar(40) NOT NULL,
  `phone` varchar(40) NOT NULL,
  `regdate` datetime DEFAULT current_timestamp(),
  `comment` varchar(100) DEFAULT NULL,
  `email_verified` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin@hobbyzoa.com','qwerqwer1!','관리자','string','2021-08-18 16:25:51','관리자 계정입니다',''),('csgm2328@gmail.com','qwerqwer1!','추승지','string','2021-08-18 16:27:11','같이 서핑하실분?🏄',''),('seyoungs22@naver.com','Zoazoa1234!','sey_zoa','01011111111','2021-08-18 21:11:43','주말엔 취미활동! ‘수채화 그리기’ 함께 하실 분 👩‍🎨',''),('snflo98@gmail.com','admin12!@','hyeeun_jjang','123567890','2021-08-18 16:36:30','나는야 겸둥잉',''),('ssafy@ssafy.com','testtest1!','ssafy','string','2021-08-18 17:09:01','싸피 5기',''),('tlawldud96@naver.com','whdk152568!','Ji-0','01067005555','2021-08-19 10:41:45','취미 부자가 목표 : )',''),('wnsdud4197@kakao.com','123qwe!@#','test','01012341234','2021-08-19 14:11:13',NULL,'\0'),('wnsdud4197@naver.com','123qwe!@','양준영','01076108875','2021-08-19 10:19:02','하이','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-19  6:46:07
