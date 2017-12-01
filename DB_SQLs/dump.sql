-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: forecasts
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `forecasts`
--

DROP TABLE IF EXISTS `forecasts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forecasts` (
  `forecast_id` int(11) NOT NULL AUTO_INCREMENT,
  `match_id` int(11) NOT NULL,
  `first_team_forecast` int(11) NOT NULL,
  `second_team_forecast` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`forecast_id`),
  UNIQUE KEY `unique_index` (`match_id`,`user_id`),
  KEY `FORECASTS_fk1` (`user_id`),
  CONSTRAINT `FORECASTS_fk0` FOREIGN KEY (`match_id`) REFERENCES `matches` (`match_id`),
  CONSTRAINT `FORECASTS_fk1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=337 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forecasts`
--

LOCK TABLES `forecasts` WRITE;
/*!40000 ALTER TABLE `forecasts` DISABLE KEYS */;
INSERT INTO `forecasts` VALUES (1,1,3,0,1),(2,2,1,2,1),(3,3,1,1,1),(4,4,2,0,1),(5,5,1,2,1),(6,6,3,1,1),(7,7,2,0,1),(8,8,2,0,1),(9,9,0,1,1),(10,10,1,1,1),(11,11,2,1,1),(12,12,1,0,1),(13,13,1,2,1),(14,14,1,2,1),(15,15,2,0,1),(16,16,2,0,1),(17,17,1,0,1),(18,18,2,1,1),(19,19,2,1,1),(20,20,1,1,1),(21,21,3,1,1),(22,22,2,1,1),(23,23,1,0,1),(24,24,1,1,1),(25,25,0,0,1),(26,26,1,2,1),(27,27,1,0,1),(28,28,1,2,1),(29,29,1,2,1),(30,30,1,3,1),(31,31,1,1,1),(32,32,1,2,1),(33,33,0,1,1),(34,34,0,2,1),(35,35,3,1,1),(36,36,1,1,1),(37,1,1,0,2),(38,2,1,2,2),(40,3,1,1,2),(41,4,1,1,2),(42,5,1,3,2),(43,6,2,2,2),(44,7,3,0,2),(45,8,1,0,2),(46,9,0,0,2),(47,10,2,2,2),(48,11,1,4,2),(49,12,3,3,2),(50,13,1,0,2),(51,14,0,2,2),(52,15,2,0,2),(53,16,2,1,2),(54,17,1,2,2),(55,18,1,2,2),(56,19,1,0,2),(57,20,1,1,2),(58,21,2,0,2),(59,22,2,2,2),(60,23,2,0,2),(61,24,1,0,2),(62,25,2,1,2),(63,26,1,1,2),(64,27,1,1,2),(65,28,1,2,2),(66,29,1,1,2),(67,30,0,3,2),(68,31,1,0,2),(69,32,0,1,2),(70,33,3,1,2),(71,34,0,2,2),(72,35,1,1,2),(73,36,0,1,2),(74,38,2,1,2),(75,38,2,2,1),(76,37,2,2,2),(77,37,2,1,1),(78,1,1,0,3),(79,2,1,3,3),(80,3,2,1,3),(81,4,1,0,3),(82,5,1,3,3),(83,6,2,1,3),(84,7,4,0,3),(85,8,1,2,3),(86,9,1,1,3),(87,10,0,1,3),(88,11,2,1,3),(89,12,3,0,3),(90,13,1,1,3),(91,14,0,0,3),(92,15,2,0,3),(93,16,3,2,3),(94,17,1,3,3),(95,18,2,3,3),(96,19,3,1,3),(97,20,2,2,3),(98,21,1,2,3),(99,22,3,0,3),(100,23,0,0,3),(101,24,2,0,3),(102,25,2,2,3),(103,26,1,2,3),(104,27,1,2,3),(105,28,1,2,3),(106,29,1,3,3),(107,30,1,4,3),(108,31,1,1,3),(109,32,2,2,3),(110,33,0,0,3),(111,34,1,3,3),(112,35,1,1,3),(113,36,0,3,3),(114,1,3,0,4),(115,2,1,2,4),(116,3,2,2,4),(117,4,2,0,4),(118,5,1,2,4),(119,6,3,0,4),(120,7,2,1,4),(121,8,1,0,4),(122,9,1,3,4),(123,10,2,0,4),(124,11,3,0,4),(125,12,4,0,4),(126,13,2,1,4),(127,14,1,1,4),(128,15,3,0,4),(129,16,2,0,4),(130,17,3,0,4),(131,18,3,1,4),(132,19,1,0,4),(133,20,2,2,4),(134,21,3,0,4),(135,22,3,0,4),(136,23,0,1,4),(137,24,1,0,4),(138,25,1,0,4),(139,26,0,1,4),(140,27,1,0,4),(141,28,0,3,4),(142,29,2,2,4),(143,30,0,5,4),(144,31,3,2,4),(145,32,1,0,4),(146,33,1,2,4),(147,34,1,4,4),(148,35,1,0,4),(149,36,0,1,4),(150,1,3,1,5),(151,2,1,1,5),(152,3,1,1,5),(153,4,2,1,5),(154,5,0,1,5),(155,6,2,2,5),(156,7,2,0,5),(157,8,3,2,5),(158,9,0,1,5),(159,10,2,2,5),(160,11,1,1,5),(161,12,2,0,5),(162,13,0,0,5),(163,14,1,1,5),(164,15,2,0,5),(165,16,2,1,5),(166,17,3,2,5),(167,18,4,1,5),(168,19,2,1,5),(169,20,1,1,5),(170,21,3,1,5),(171,22,3,1,5),(172,23,0,0,5),(173,24,2,1,5),(174,25,2,1,5),(175,26,0,2,5),(176,27,1,1,5),(177,28,0,1,5),(178,29,1,0,5),(179,30,0,1,5),(180,31,0,1,5),(181,32,3,3,5),(182,33,2,0,5),(183,34,1,3,5),(184,35,1,0,5),(185,36,1,2,5),(186,1,4,0,6),(187,2,1,2,6),(188,3,2,1,6),(189,4,3,1,6),(190,5,2,2,6),(191,6,3,1,6),(192,7,2,0,6),(193,8,2,1,6),(194,9,1,1,6),(195,10,0,1,6),(196,11,2,0,6),(197,12,1,0,6),(198,13,2,1,6),(199,14,0,2,6),(200,15,2,0,6),(201,16,3,2,6),(202,17,1,0,6),(203,18,2,1,6),(204,19,2,0,6),(205,20,1,2,6),(206,21,3,2,6),(207,22,1,0,6),(208,23,1,0,6),(209,24,1,1,6),(210,25,1,2,6),(211,26,1,2,6),(212,27,1,0,6),(213,28,0,2,6),(214,29,0,0,6),(215,30,0,3,6),(216,31,1,1,6),(217,32,1,2,6),(218,33,1,2,6),(219,34,0,2,6),(220,35,1,0,6),(221,36,0,1,6),(222,40,1,3,1),(223,41,2,2,1),(224,1,2,0,8),(225,2,1,1,8),(226,3,1,2,8),(227,4,1,2,8),(228,5,2,2,8),(229,6,2,1,8),(230,7,3,1,8),(231,8,2,1,8),(232,9,1,1,8),(233,10,0,0,8),(234,11,2,1,8),(235,12,2,0,8),(236,13,2,1,8),(237,14,0,1,8),(238,15,3,0,8),(239,16,3,2,8),(240,17,3,1,8),(241,18,2,1,8),(242,19,2,1,8),(243,20,1,3,8),(244,21,3,2,8),(245,22,2,0,8),(246,23,1,1,8),(247,24,0,0,8),(248,25,1,1,8),(249,26,1,1,8),(250,27,3,1,8),(251,28,1,1,8),(252,29,2,1,8),(253,30,1,4,8),(254,31,1,2,8),(255,32,1,1,8),(256,33,0,1,8),(257,34,1,3,8),(258,35,2,1,8),(259,36,1,1,8),(260,1,2,0,9),(261,2,0,1,9),(262,3,2,0,9),(263,4,1,1,9),(264,5,0,1,9),(265,6,2,0,9),(266,7,2,0,9),(267,8,2,1,9),(268,9,0,1,9),(269,10,2,1,9),(270,11,1,0,9),(271,12,3,1,9),(272,13,1,0,9),(273,14,0,2,9),(274,15,2,0,9),(275,16,2,0,9),(276,17,1,0,9),(277,18,2,1,9),(278,19,2,2,9),(279,20,0,1,9),(280,21,3,0,9),(281,22,1,0,9),(282,23,0,1,9),(283,24,2,0,9),(284,25,1,1,9),(285,26,1,2,9),(286,27,1,0,9),(287,28,0,3,9),(288,29,2,2,9),(289,30,1,4,9),(290,31,2,0,9),(291,32,2,3,9),(292,33,0,1,9),(293,34,0,2,9),(294,35,2,1,9),(295,36,2,2,9),(296,1,2,0,10),(297,2,0,2,10),(298,3,1,0,10),(299,4,1,2,10),(300,5,1,2,10),(301,6,1,1,10),(302,7,2,0,10),(303,8,2,0,10),(304,9,1,2,10),(305,10,1,1,10),(306,11,1,0,10),(307,12,2,0,10),(308,13,1,0,10),(309,14,0,2,10),(310,15,3,0,10),(311,16,2,0,10),(312,17,2,1,10),(313,18,2,0,10),(314,19,2,1,10),(315,20,1,2,10),(316,21,3,0,10),(317,22,3,1,10),(318,23,2,1,10),(319,24,2,0,10),(320,25,0,0,10),(321,26,2,2,10),(322,27,2,1,10),(323,28,0,2,10),(324,29,1,0,10),(325,30,0,2,10),(326,31,1,2,10),(327,32,2,3,10),(328,33,2,2,10),(329,34,1,3,10),(330,35,0,0,10),(331,36,0,1,10),(332,42,4,4,1),(333,44,1,1,2),(334,40,3,3,52),(335,45,4,4,52),(336,43,5,5,52);
/*!40000 ALTER TABLE `forecasts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_comments`
--

DROP TABLE IF EXISTS `match_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match_comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_txt` text NOT NULL,
  `match_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `comment_datetime` datetime NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `MATCH_COMMENTS_fk0` (`match_id`),
  KEY `MATCH_COMMENTS_fk1` (`user_id`),
  CONSTRAINT `MATCH_COMMENTS_fk0` FOREIGN KEY (`match_id`) REFERENCES `matches` (`match_id`),
  CONSTRAINT `MATCH_COMMENTS_fk1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_comments`
--

LOCK TABLES `match_comments` WRITE;
/*!40000 ALTER TABLE `match_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `match_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_states`
--

DROP TABLE IF EXISTS `match_states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match_states` (
  `match_state_id` int(11) NOT NULL AUTO_INCREMENT,
  `match_state` varchar(30) NOT NULL,
  PRIMARY KEY (`match_state_id`),
  UNIQUE KEY `state_name` (`match_state`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_states`
--

LOCK TABLES `match_states` WRITE;
/*!40000 ALTER TABLE `match_states` DISABLE KEYS */;
INSERT INTO `match_states` VALUES (2,'Прием прогнозов окончен'),(1,'Принимаются прогнозы на матч');
/*!40000 ALTER TABLE `match_states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_types`
--

DROP TABLE IF EXISTS `match_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match_types` (
  `match_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `match_type` varchar(30) NOT NULL,
  PRIMARY KEY (`match_type_id`),
  UNIQUE KEY `match_type` (`match_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_types`
--

LOCK TABLES `match_types` WRITE;
/*!40000 ALTER TABLE `match_types` DISABLE KEYS */;
INSERT INTO `match_types` VALUES (1,'Обычный'),(2,'Плей-офф');
/*!40000 ALTER TABLE `match_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matches` (
  `match_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_team_result` int(11) DEFAULT NULL,
  `second_team_result` int(11) DEFAULT NULL,
  `match_datetime` datetime NOT NULL,
  `match_state_id` int(11) NOT NULL,
  `match_type_id` int(11) NOT NULL,
  `first_team_id` int(11) NOT NULL,
  `second_team_id` int(11) NOT NULL,
  `tournament_id` int(11) NOT NULL,
  PRIMARY KEY (`match_id`),
  KEY `PLANNED_MATCHES_fk0` (`match_state_id`),
  KEY `PLANNED_MATCHES_fk1` (`match_type_id`),
  KEY `PLANNED_MATCHES_fk2` (`first_team_id`),
  KEY `PLANNED_MATCHES_fk3` (`second_team_id`),
  KEY `PLANNED_MATCHES_fk4` (`tournament_id`),
  CONSTRAINT `PLANNED_MATCHES_fk0` FOREIGN KEY (`match_state_id`) REFERENCES `match_states` (`match_state_id`),
  CONSTRAINT `PLANNED_MATCHES_fk1` FOREIGN KEY (`match_type_id`) REFERENCES `match_types` (`match_type_id`),
  CONSTRAINT `PLANNED_MATCHES_fk2` FOREIGN KEY (`first_team_id`) REFERENCES `teams` (`team_id`),
  CONSTRAINT `PLANNED_MATCHES_fk3` FOREIGN KEY (`second_team_id`) REFERENCES `teams` (`team_id`),
  CONSTRAINT `PLANNED_MATCHES_fk4` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`tournament_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matches`
--

LOCK TABLES `matches` WRITE;
/*!40000 ALTER TABLE `matches` DISABLE KEYS */;
INSERT INTO `matches` VALUES (1,2,1,'2016-06-10 17:00:00',2,1,1,2,1),(2,0,1,'2016-06-10 17:00:00',2,1,3,4,1),(3,2,1,'2016-06-11 17:00:00',2,1,5,6,1),(4,1,1,'2016-06-11 19:00:00',2,1,7,8,1),(5,0,1,'2016-06-12 17:00:00',2,1,9,10,1),(6,1,0,'2016-06-12 19:00:00',2,1,11,12,1),(7,2,0,'2016-06-12 21:00:00',2,1,13,14,1),(8,1,0,'2016-06-13 17:00:00',2,1,15,16,1),(9,1,1,'2016-06-13 19:00:00',2,1,17,18,1),(10,0,2,'2016-06-13 21:00:00',2,1,19,20,1),(11,0,2,'2016-06-14 17:00:00',2,1,21,22,1),(12,1,1,'2016-06-14 19:00:00',2,1,23,24,1),(13,1,2,'2016-06-15 17:00:00',2,1,8,6,1),(14,1,1,'2016-06-15 19:00:00',2,1,2,4,1),(15,2,0,'2016-06-15 21:00:00',2,1,1,3,1),(16,2,1,'2016-06-16 17:00:00',2,1,7,5,1),(17,0,2,'2016-06-16 19:00:00',2,1,14,12,1),(18,0,0,'2016-06-16 21:00:00',2,1,13,11,1),(19,1,0,'2016-06-17 17:00:00',2,1,20,18,1),(20,2,2,'2016-06-17 19:00:00',2,1,16,10,1),(21,3,0,'2016-06-17 21:00:00',2,1,15,9,1),(22,3,0,'2016-06-18 17:00:00',2,1,19,17,1),(23,1,1,'2016-06-18 19:00:00',2,1,24,22,1),(24,0,0,'2016-06-18 21:00:00',2,1,23,21,1),(25,0,1,'2016-06-19 17:00:00',2,1,2,3,1),(26,0,0,'2016-06-19 19:00:00',2,1,4,1,1),(27,0,3,'2016-06-20 17:00:00',2,1,8,5,1),(28,0,0,'2016-06-20 19:00:00',2,1,6,7,1),(29,0,1,'2016-06-21 17:00:00',2,1,14,11,1),(30,0,1,'2016-06-21 19:00:00',2,1,12,13,1),(31,0,2,'2016-06-21 21:00:00',2,1,16,9,1),(32,2,1,'2016-06-21 23:00:00',2,1,10,15,1),(33,2,1,'2016-06-22 17:00:00',2,1,24,21,1),(34,3,3,'2016-06-22 19:00:00',2,1,22,23,1),(35,0,1,'2016-06-22 21:00:00',2,1,20,17,1),(36,0,1,'2016-06-22 23:00:00',2,1,18,19,1),(37,243439,1,'2018-12-12 00:00:00',1,1,3,4,2),(38,243439,1,'2018-12-12 00:00:00',1,1,3,4,2),(39,243439,1,'2018-12-12 00:00:00',1,1,3,4,2),(40,2,2,'2018-12-12 00:00:00',1,1,4,5,9),(41,2,2,'2018-12-12 00:00:00',1,1,4,5,9),(42,2,2,'2018-12-12 00:00:00',1,1,4,5,9),(43,2,2,'2018-12-12 00:00:00',1,1,4,5,9),(44,NULL,NULL,'2018-12-12 00:00:00',1,1,10,12,10),(45,2,2,'2018-12-12 00:00:00',1,1,4,5,9),(46,2,2,'2018-12-12 00:00:00',1,1,4,5,9),(47,2,2,'2018-12-12 00:00:00',1,1,4,5,9),(48,243439,1,'2018-12-12 00:00:00',1,1,3,4,2);
/*!40000 ALTER TABLE `matches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration_desc`
--

DROP TABLE IF EXISTS `registration_desc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration_desc` (
  `tournament_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  UNIQUE KEY `UNIQUE CONSTRAINTE1` (`tournament_id`,`user_id`),
  KEY `REGISTRATION_DESC_fk1` (`user_id`),
  CONSTRAINT `REGISTRATION_DESC_fk0` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`tournament_id`),
  CONSTRAINT `REGISTRATION_DESC_fk1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration_desc`
--

LOCK TABLES `registration_desc` WRITE;
/*!40000 ALTER TABLE `registration_desc` DISABLE KEYS */;
INSERT INTO `registration_desc` VALUES (1,1),(7,1),(9,1),(10,1),(11,1),(12,1),(15,1),(1,2),(1,3),(4,3),(1,4),(1,5),(1,6),(1,7),(1,8),(6,8),(7,8),(9,8),(1,9),(1,10),(2,11),(3,11),(4,11),(9,52),(11,52),(19,52),(24,52),(25,52),(29,52),(31,52);
/*!40000 ALTER TABLE `registration_desc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teams` (
  `team_id` int(11) NOT NULL AUTO_INCREMENT,
  `team_name` varchar(30) NOT NULL,
  PRIMARY KEY (`team_id`),
  UNIQUE KEY `team_name` (`team_name`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES (21,'Австрия'),(3,'Албания'),(7,'Англия'),(38,'Аргентина'),(19,'Бельгия'),(25,'Бразилия'),(22,'Венгрия'),(13,'Германия'),(44,'Греция'),(33,'Египет'),(26,'Иран'),(17,'Ирландия'),(24,'Исландия'),(15,'Испания'),(20,'Италия'),(39,'Колумбия'),(29,'Корея'),(32,'Коста-Рика'),(28,'Мексика'),(31,'Нигерия'),(40,'Панама'),(11,'Польша'),(23,'Португалия'),(8,'Россия'),(2,'Румыния'),(30,'Саудовская Аравия'),(12,'Сев. Ирландия'),(35,'Сербия'),(6,'Словакия'),(9,'Турция'),(14,'Украина'),(37,'Уругвай'),(5,'Уэльс'),(1,'Франция'),(10,'Хорватия'),(16,'Чехия'),(4,'Швейцария'),(18,'Швеция'),(27,'Япония');
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tournament_states`
--

DROP TABLE IF EXISTS `tournament_states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournament_states` (
  `tournament_state_id` int(11) NOT NULL AUTO_INCREMENT,
  `tournament_state` varchar(50) NOT NULL,
  PRIMARY KEY (`tournament_state_id`),
  UNIQUE KEY `tornament_state` (`tournament_state`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournament_states`
--

LOCK TABLES `tournament_states` WRITE;
/*!40000 ALTER TABLE `tournament_states` DISABLE KEYS */;
INSERT INTO `tournament_states` VALUES (1,'Принимаются заявки на регистрацию'),(2,'Турнир окончен');
/*!40000 ALTER TABLE `tournament_states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tournaments`
--

DROP TABLE IF EXISTS `tournaments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournaments` (
  `tournament_id` int(11) NOT NULL AUTO_INCREMENT,
  `tournament_name` varchar(30) NOT NULL,
  `team_organizer_id` int(11) NOT NULL,
  `tournament_start_date` date NOT NULL,
  `tournament_state_id` int(11) NOT NULL,
  PRIMARY KEY (`tournament_id`),
  UNIQUE KEY `tournament_name` (`tournament_name`),
  KEY `TOURNAMENTS_fk0` (`team_organizer_id`),
  KEY `TOURNAMENTS_fk1` (`tournament_state_id`),
  CONSTRAINT `TOURNAMENTS_fk0` FOREIGN KEY (`team_organizer_id`) REFERENCES `teams` (`team_id`),
  CONSTRAINT `TOURNAMENTS_fk1` FOREIGN KEY (`tournament_state_id`) REFERENCES `tournament_states` (`tournament_state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournaments`
--

LOCK TABLES `tournaments` WRITE;
/*!40000 ALTER TABLE `tournaments` DISABLE KEYS */;
INSERT INTO `tournaments` VALUES (1,'Чемпионат Европы 2016',1,'2016-06-10',2),(2,'updated964000',2,'2018-06-10',2),(3,'808373',2,'2018-06-10',2),(4,'857167',2,'2018-06-10',2),(5,'529727',2,'2018-06-10',2),(6,'964626',2,'2018-06-10',2),(7,'Тестовый турнир12',39,'2020-08-12',2),(8,'Турнирчик',31,'2019-12-12',2),(9,'Тестовый турнир13',32,'2018-10-10',1),(10,'New tournament 15',38,'2020-08-12',1),(11,'New Tournament 16',17,'2020-10-10',1),(12,'тур12',22,'2020-08-12',2),(13,'729468',2,'2017-11-29',2),(14,'401983',2,'2017-11-30',2),(15,'Турнир в Японии',26,'2018-12-10',2),(19,'Турнир в Японии 2',21,'2018-12-10',1),(22,'Турнир в Японии 3',21,'2018-12-12',1),(24,'Турнир в Японии 4',21,'2018-12-10',2),(25,'Турнир в Японии 5',21,'2018-12-12',2),(29,'Турнир в Японии 7',21,'2018-12-12',2),(31,'Турнир в Японии 10',44,'2018-12-10',2);
/*!40000 ALTER TABLE `tournaments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_states`
--

DROP TABLE IF EXISTS `user_states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_states` (
  `user_state_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_state` varchar(50) NOT NULL,
  PRIMARY KEY (`user_state_id`),
  UNIQUE KEY `user_state_name_uindex` (`user_state`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_states`
--

LOCK TABLES `user_states` WRITE;
/*!40000 ALTER TABLE `user_states` DISABLE KEYS */;
INSERT INTO `user_states` VALUES (4,'Администратор'),(3,'Заблокирован'),(2,'Зарегистрирован'),(1,'Ожидание регистрации');
/*!40000 ALTER TABLE `user_states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `second_name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `user_state_id` int(11) NOT NULL,
  `login` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  KEY `user_stets_fk1_idx` (`user_state_id`),
  CONSTRAINT `user_stets_fk1` FOREIGN KEY (`user_state_id`) REFERENCES `user_states` (`user_state_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Андрей','Рижницын','ra@tut.by',2,'rizhnitsyn','11111'),(2,'Сергей','Гусаков','sg@tut.by',2,'',''),(3,'Роман','Жук','rzh@tut.by',2,'',''),(4,'Никита','Щербич','nsch@tut.by',2,'',''),(5,'Виталий','Назарян','vn@tut.by',2,'',''),(6,'Гайк','Бегларян','gb@tut.by',2,'',''),(7,'Руслан','Акиловский','ar@tut.by',2,'',''),(8,'Андрей','Иванов','ai@tut.by',2,'',''),(9,'Камал','Бикулов','kb@tut.by',2,'',''),(10,'Изабелла','Аванессова','izab@tut.by',2,'',''),(11,'updated415002','Ivanov','test@gmail.com',2,'',''),(14,'Ivan','129618','test37447@gmail.com',2,'',''),(15,'Ivan','851874','test78828@gmail.com',1,'',''),(16,'Тестик','Номер1','выаыва',3,'',''),(17,'Иван ','Иван ','iv@bn.by',2,'',''),(20,'Иваныч ','Иваныч ','ivb@bnb.by',3,'',''),(23,'Петя','Петя','ivbb@bnb.by',2,'',''),(24,'иван','Ткачев','ert@tut.by',1,'',''),(26,'иван','Ткачевич','ertm@tut.by',1,'',''),(27,'Ivan','180264','test4180@gmail.com',1,'',''),(28,'Андрей','Коньков','kon@tut.by',1,'konkov','11111'),(33,'Иван','Петро','petro@tut.by',3,'petro','11111'),(34,'Олег','Петрович','oleg@tut.by',1,'oleg','11111'),(35,'Кирилл ','Вольски','vol@tut.by',1,'volskij','11111'),(36,'Андрей','Копылов','kopi@tut.by',1,'kopilov','11111'),(37,'Андре','Мелешкевич','mele@tut.by',1,'melesh','11111'),(38,'Глеб','Маеев','makeev@tut.by',2,'ьфлуум','11111'),(39,'sdfdsf','sdfsdfdf','@tut.nby',3,'sdfsdfsdfsdf','11111'),(40,'sdfsdf','sdfsdf','sdfsdf',3,'sdfsdf','sfsdf'),(42,'huhuh','hgfhfh','gihuhuh',3,'dytvjbgy','11111'),(44,'huhuhwerw','hgfhfhwere','gihuhuhwer',3,'dytvjbgywer','11111ere'),(45,'sdfsdf','gawghsvb','sdfhjwrtjjdf',1,'ghjrjhyjds','asfe4tjtj'),(46,'asdasd','asdfaasdad','sfsdfsdfsdf',3,'sdfsdfsdfsdf','asdasdasdasd'),(47,'tyu','tyu','tyu',3,'tyu','tyu'),(48,'fgjfgjh','fjfgj','fgjhfgj',3,'fjfgj','af242e2abfda7079d2d56c8987013cd2'),(49,'shshs','shshsh','shshshsh',1,'shshsh','2087d87063b08c54aa31012ed012bacb'),(50,'sdfsdfsdf','sdfsdfsdfsdf','asdasd',1,'test','b59c67bf196a4758191e42f76670ceba'),(51,'test1','kasjdaskdj','dasd',1,'test3','b0baee9d279d34fa1dfd71aadb908c3f'),(52,'admin','admin','admin@tut.by',4,'admin','b0baee9d279d34fa1dfd71aadb908c3f');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-01 16:30:28
