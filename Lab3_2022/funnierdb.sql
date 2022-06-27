CREATE DATABASE  IF NOT EXISTS `funnierdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `funnierdb`;
-- MySQL dump 10.13  Distrib 8.0.29, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: funnierdb
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `userID` int NOT NULL,
  `followedUser` int NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`userID`,`followedUser`),
  KEY `followedID_idx` (`followedUser`),
  CONSTRAINT `followedID` FOREIGN KEY (`followedUser`) REFERENCES `user` (`id`),
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,3,'2022-06-27 19:07:28'),(1,4,'2022-06-03 07:03:25'),(1,5,'2022-06-27 19:07:25'),(1,7,'2022-06-27 19:07:26'),(2,1,'2022-06-27 19:09:36'),(2,3,'2022-06-03 02:11:53'),(3,1,'2022-06-26 19:34:59'),(3,2,'2022-06-27 08:11:20'),(3,4,'2022-06-27 18:38:18'),(3,5,'2022-06-27 18:38:20'),(3,6,'2022-06-27 08:11:09'),(3,7,'2022-06-27 18:45:14'),(4,1,'2022-06-03 07:01:23'),(4,2,'2022-06-03 21:14:22'),(4,3,'2022-06-03 03:21:41'),(5,1,'2022-06-27 19:05:38'),(5,2,'2022-06-27 19:04:41'),(5,3,'2022-06-27 19:04:45'),(5,4,'2022-06-27 19:04:37'),(5,6,'2022-06-27 19:04:43'),(6,3,'2022-06-26 19:50:11'),(6,4,'2022-06-26 19:50:08');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funny`
--

DROP TABLE IF EXISTS `funny`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funny` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parentID` int DEFAULT NULL,
  `authorID` int NOT NULL,
  `content` varchar(200) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `parentID_idx` (`parentID`),
  KEY `authorID_idx` (`authorID`),
  CONSTRAINT `authorID` FOREIGN KEY (`authorID`) REFERENCES `user` (`id`),
  CONSTRAINT `parentID` FOREIGN KEY (`parentID`) REFERENCES `funny` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funny`
--

LOCK TABLES `funny` WRITE;
/*!40000 ALTER TABLE `funny` DISABLE KEYS */;
INSERT INTO `funny` VALUES (1,NULL,2,'Hello funniers, here Marcos speaking!','2022-06-02 18:40:35'),(2,1,5,'Hello Marcos, here Maria speaking!','2022-06-02 19:36:19'),(3,NULL,3,'Hello everyone, here Xavi talking!','2022-06-02 21:46:09'),(25,NULL,3,'Funny funny funny funny funny funny :)','2022-06-27 18:56:08'),(26,NULL,1,'\"Two go, and the one in the middle falls.\"- Paulo Coelho','2022-06-27 19:08:59'),(27,NULL,2,'What do you call a fish with no eyes? A fsh.','2022-06-27 19:10:15'),(28,NULL,2,'hahahahhaha','2022-06-27 19:10:35'),(30,NULL,3,'There are three types of people in the world:  Those who can count and those who can’t.?','2022-06-27 19:11:58');
/*!40000 ALTER TABLE `funny` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funstate`
--

DROP TABLE IF EXISTS `funstate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funstate` (
  `userID` int NOT NULL,
  `funnyID` int NOT NULL,
  `state` tinyint NOT NULL,
  PRIMARY KEY (`userID`,`funnyID`),
  KEY `funnyID_idx` (`funnyID`),
  CONSTRAINT `FSfunnyID` FOREIGN KEY (`funnyID`) REFERENCES `funny` (`id`),
  CONSTRAINT `FSuserID` FOREIGN KEY (`userID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funstate`
--

LOCK TABLES `funstate` WRITE;
/*!40000 ALTER TABLE `funstate` DISABLE KEYS */;
INSERT INTO `funstate` VALUES (1,2,1),(1,25,1),(1,26,1),(2,25,1),(2,26,1),(3,1,1),(3,2,1),(3,3,1),(3,25,1),(5,1,1),(5,25,1),(6,3,1),(6,25,1);
/*!40000 ALTER TABLE `funstate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nickname` varchar(30) NOT NULL,
  `gender` int NOT NULL,
  `birth` date NOT NULL,
  `profilePhoto` varchar(200) NOT NULL,
  `isAdmin` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'david','david.grao01@estudiant.upf.edu','Hola123%','david',1,'2000-02-07','imgs/profiles/david.png',1),(2,'marcos','marcosariel.chindemi01@estudiant.upf.edu','Hola123%','elchindi',1,'1997-12-23','imgs/profiles/marcos.png',1),(3,'xavi','xavi.gallardo01@estudiant.upf.edu','Hola123%','xvgg',1,'1999-11-22','imgs/profiles/xavi.png',1),(4,'dave','david.quincoces01@estudiant.upf.edu','Hola123%','davee',1,'2000-12-25','imgs/profiles/dave.png',1),(5,'maria','maria.montserrat01@estudiant.upf.edu','Hola123%','maria_muntaner',2,'2000-04-12','imgs/profiles/maria.png',0),(6,'David2','david@keonn','Hola123%','el gato',1,'2000-02-07','imgs/profiles/David2.png',0),(7,'xaaavi','xaaavi@gmail.com','Hola123%','XAVI',1,'1999-11-22','imgs/me.png',0);
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

-- Dump completed on 2022-06-27 21:45:46
