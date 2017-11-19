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
-- Table structure for table `planned_matches`
--

DROP TABLE IF EXISTS matches;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `planned_matches` (
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planned_matches`
--

LOCK TABLES matches WRITE;
/*!40000 ALTER TABLE matches DISABLE KEYS */;
INSERT INTO matches VALUES (1,2,1,'2016-06-10 17:00:00',2,1,1,2,1),(2,0,1,'2016-06-10 17:00:00',2,1,3,4,1),(3,2,1,'2016-06-11 17:00:00',2,1,5,6,1),(4,1,1,'2016-06-11 19:00:00',2,1,7,8,1),(5,0,1,'2016-06-12 17:00:00',2,1,9,10,1),(6,1,0,'2016-06-12 19:00:00',2,1,11,12,1),(7,2,0,'2016-06-12 21:00:00',2,1,13,14,1),(8,1,0,'2016-06-13 17:00:00',2,1,15,16,1),(9,1,1,'2016-06-13 19:00:00',2,1,17,18,1),(10,0,2,'2016-06-13 21:00:00',2,1,19,20,1),(11,0,2,'2016-06-14 17:00:00',2,1,21,22,1),(12,1,1,'2016-06-14 19:00:00',2,1,23,24,1),(13,1,2,'2016-06-15 17:00:00',2,1,8,6,1),(14,1,1,'2016-06-15 19:00:00',2,1,2,4,1),(15,2,1,'2016-06-15 21:00:00',2,1,1,3,1),(16,2,1,'2016-06-16 17:00:00',2,1,7,5,1),(17,0,2,'2016-06-16 19:00:00',2,1,14,12,1),(18,0,0,'2016-06-16 21:00:00',2,1,13,11,1),(19,1,0,'2016-06-17 17:00:00',2,1,20,18,1),(20,2,2,'2016-06-17 19:00:00',2,1,16,10,1),(21,3,0,'2016-06-17 21:00:00',2,1,15,9,1),(22,3,0,'2016-06-18 17:00:00',2,1,19,17,1),(23,1,1,'2016-06-18 19:00:00',2,1,24,22,1),(24,0,0,'2016-06-18 21:00:00',2,1,23,21,1),(25,0,1,'2016-06-19 17:00:00',2,1,2,3,1),(26,0,0,'2016-06-19 19:00:00',2,1,4,1,1),(27,0,3,'2016-06-20 17:00:00',2,1,8,5,1),(28,0,0,'2016-06-20 19:00:00',2,1,6,7,1),(29,0,1,'2016-06-21 17:00:00',2,1,14,11,1),(30,0,1,'2016-06-21 19:00:00',2,1,12,13,1),(31,0,2,'2016-06-21 21:00:00',2,1,16,9,1),(32,2,1,'2016-06-21 23:00:00',2,1,10,15,1),(33,2,1,'2016-06-22 17:00:00',2,1,24,21,1),(34,3,3,'2016-06-22 19:00:00',2,1,22,23,1),(35,0,1,'2016-06-22 21:00:00',2,1,20,17,1),(36,0,1,'2016-06-22 23:00:00',2,1,18,19,1);
/*!40000 ALTER TABLE matches ENABLE KEYS */;
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
