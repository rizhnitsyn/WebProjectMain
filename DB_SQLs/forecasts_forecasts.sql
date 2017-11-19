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
  CONSTRAINT `FORECASTS_fk0` FOREIGN KEY (`match_id`) REFERENCES matches (`match_id`),
  CONSTRAINT `FORECASTS_fk1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forecasts`
--

LOCK TABLES `forecasts` WRITE;
/*!40000 ALTER TABLE `forecasts` DISABLE KEYS */;
INSERT INTO `forecasts` VALUES (1,1,3,0,1),(2,2,1,2,1),(3,3,1,1,1),(4,4,2,0,1),(5,5,1,2,1),(6,6,3,1,1),(7,7,2,0,1),(8,8,2,0,1),(9,9,0,1,1),(10,10,1,1,1),(11,11,2,1,1),(12,12,1,0,1),(13,13,1,2,1),(14,14,1,2,1),(15,15,2,0,1),(16,16,2,0,1),(17,17,1,0,1),(18,18,2,1,1),(19,19,2,1,1),(20,20,1,1,1),(21,21,3,1,1),(22,22,2,1,1),(23,23,1,0,1),(24,24,1,1,1),(25,25,0,0,1),(26,26,1,2,1),(27,27,1,0,1),(28,28,1,2,1),(29,29,1,2,1),(30,30,1,3,1),(31,31,1,1,1),(32,32,1,2,1),(33,33,0,1,1),(34,34,0,2,1),(35,35,3,1,1),(36,36,1,1,1),(37,1,1,0,2),(38,2,1,2,2),(40,3,1,1,2),(41,4,1,1,2),(42,5,1,3,2),(43,6,2,2,2),(44,7,3,0,2),(45,8,1,0,2),(46,9,0,0,2),(47,10,2,2,2),(48,11,1,4,2),(49,12,3,3,2),(50,13,1,0,2),(51,14,0,2,2),(52,15,2,0,2),(53,16,2,1,2),(54,17,1,2,2),(55,18,1,2,2),(56,19,1,0,2),(57,20,1,1,2),(58,21,2,0,2),(59,22,2,2,2),(60,23,2,0,2),(61,24,1,0,2),(62,25,2,1,2),(63,26,1,1,2),(64,27,1,1,2),(65,28,1,2,2),(66,29,1,1,2),(67,30,0,3,2),(68,31,1,0,2),(69,32,0,1,2),(70,33,3,1,2),(71,34,0,2,2),(72,35,1,1,2),(73,36,0,1,2);
/*!40000 ALTER TABLE `forecasts` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-10 14:22:06
