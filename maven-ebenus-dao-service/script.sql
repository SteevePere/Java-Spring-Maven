-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: ebenus
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

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
-- Table structure for table `incident_types`
--

UNLOCK TABLES;
DROP TABLE IF EXISTS `incident_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `incident_types` (
                                  `TYP_ID` int(11) NOT NULL AUTO_INCREMENT,
                                  `TYP_NAME` varchar(255) NOT NULL,
                                  PRIMARY KEY (`TYP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incident_types`
--

LOCK TABLES `incident_types` WRITE;
/*!40000 ALTER TABLE `incident_types` DISABLE KEYS */;
INSERT INTO `incident_types` VALUES (51,'Robbery'),(52,'Battery'),(53,'Speeding'),(54,'Murder'),(55,'Theft'),(56,'G.T.A'),(57,'Rape');
/*!40000 ALTER TABLE `incident_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incidents`
--

DROP TABLE IF EXISTS `incidents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `incidents` (
                             `ICD_ID` int(11) NOT NULL AUTO_INCREMENT,
                             `ICD_TYPE` int(11) NOT NULL,
                             `ICD_DATE` datetime NOT NULL,
                             `ICD_LATITUDE` varchar(255) NOT NULL,
                             `ICD_LONGITUDE` varchar(255) NOT NULL,
                             `ICD_CREATED_BY` int(11) NOT NULL,
                             `ICD_CREATION_DATE` datetime NOT NULL,
                             `ICD_EDIT_DATE` datetime NOT NULL,
                             PRIMARY KEY (`ICD_ID`),
                             KEY `FK_ICD_TYP` (`ICD_TYPE`),
                             KEY `FK_ICD_USR` (`ICD_CREATED_BY`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidents`
--

LOCK TABLES `incidents` WRITE;
/*!40000 ALTER TABLE `incidents` DISABLE KEYS */;
INSERT INTO `incidents` VALUES (68,51,'2019-06-02 00:00:00','34.147044494922504','-118.65371704101564',16,'2019-06-09 11:09:28','2019-06-09 11:09:28'),(69,51,'2019-05-26 00:00:00','34.01282694464166','-117.78167724609375',16,'2019-06-09 11:09:39','2019-06-09 11:10:14'),(70,53,'2019-05-26 00:00:00','34.15499986715356','-118.84872436523438',16,'2019-06-09 11:09:51','2019-06-09 11:09:51'),(71,52,'2019-03-06 00:00:00','33.864714141777746','-118.10028076171875',16,'2019-06-09 11:10:02','2019-06-09 11:10:02'),(72,54,'2019-04-10 00:00:00','33.88295731069693','-118.23074340820311',16,'2019-06-09 11:10:35','2019-06-09 11:10:35'),(73,54,'2019-06-09 00:00:00','33.86129311351553','-118.25408935546875',16,'2019-06-09 11:10:42','2019-06-09 11:10:42'),(74,55,'2019-05-27 00:00:00','33.85559109516039','-117.89566040039062',16,'2019-06-09 11:11:07','2019-06-09 11:11:07'),(75,55,'2019-06-03 00:00:00','34.00030430441023','-117.64984130859374',16,'2019-06-09 11:11:17','2019-06-09 11:11:17'),(76,53,'2019-06-02 00:00:00','34.17772537282446','-118.17718505859375',16,'2019-06-09 11:11:35','2019-06-09 11:11:35'),(77,53,'2019-06-09 00:00:00','34.13340497084095','-117.98629760742188',16,'2019-06-09 11:11:42','2019-06-09 11:11:42'),(78,51,'2019-06-02 00:00:00','34.286722590335955','-118.44635009765624',16,'2019-06-09 11:12:32','2019-06-09 11:12:32'),(79,56,'2019-05-30 00:00:00','33.79854997801964','-118.25408935546875',16,'2019-06-09 11:13:23','2019-06-09 11:13:23'),(80,56,'2019-05-31 00:00:00','34.120900139826965','-117.78030395507814',16,'2019-06-09 11:13:34','2019-06-09 11:13:34'),(81,56,'2019-05-31 00:00:00','33.83506067707558','-118.32000732421875',16,'2019-06-09 11:13:52','2019-06-09 11:13:52'),(82,54,'2019-06-01 00:00:00','33.9285481685662','-118.24859619140626',16,'2019-06-09 11:14:03','2019-06-09 11:14:03'),(83,55,'2019-06-01 00:00:00','34.199308935560154','-119.19616699218749',16,'2019-06-09 11:14:19','2019-06-09 11:14:19'),(84,53,'2019-06-10 00:00:00','34.134541681937364','-119.09591674804688',16,'2019-06-09 11:14:31','2019-06-09 11:14:31'),(85,52,'2019-06-03 00:00:00','33.8738362136656','-117.5701904296875',16,'2019-06-09 11:14:52','2019-06-09 11:14:52'),(86,54,'2019-05-30 00:00:00','34.0105502383134','-117.44934082031249',16,'2019-06-09 11:14:59','2019-06-09 11:14:59'),(87,57,'2019-05-30 00:00:00','33.887517493601685','-117.07443237304686',16,'2019-06-09 11:15:19','2019-06-09 11:15:19'),(88,55,'2019-05-29 00:00:00','33.57000543108403','-117.65945434570312',16,'2019-06-09 11:15:55','2019-06-09 11:15:55'),(89,56,'2019-05-28 00:00:00','33.444047234512354','-117.6416015625',16,'2019-06-09 11:16:03','2019-06-09 11:16:03');
/*!40000 ALTER TABLE `incidents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
                         `ROL_ID` int(11) NOT NULL AUTO_INCREMENT,
                         `ROL_NAME` varchar(255) NOT NULL,
                         PRIMARY KEY (`ROL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Chief'),(2,'Detective'),(4,'Agent');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
                         `USR_ID` int(11) NOT NULL AUTO_INCREMENT,
                         `USR_ROLE` int(11) NOT NULL,
                         `USR_FIRST_NAME` varchar(100) NOT NULL,
                         `USR_LAST_NAME` varchar(100) NOT NULL,
                         `USR_EMAIL` varchar(100) NOT NULL,
                         `USR_PASSWORD` varchar(100) NOT NULL,
                         `USR_BIRTH_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `USR_CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `USR_EDIT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `USR_ACTIVE` tinyint(1) DEFAULT '0',
                         PRIMARY KEY (`USR_ID`),
                         KEY `FK_USR_ROL` (`USR_ROLE`),
                         CONSTRAINT `FK_USR_ROL` FOREIGN KEY (`USR_ROLE`) REFERENCES `roles` (`ROL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (16,1,'Steeve','Péré','steevepere@gmail.com','9e82dbebaebd5deaf27df1697f2679c2687d6dd8f97d9e82023196a1ea7988b6','2019-06-03 22:00:00','2019-06-08 21:41:19','2019-06-08 21:41:19',1),(17,2,'Marion','Jouvet','marionjouvet@gmail.com','9e82dbebaebd5deaf27df1697f2679c2687d6dd8f97d9e82023196a1ea7988b6','1978-06-11 22:00:00','2019-06-09 07:09:15','2019-06-09 07:09:24',1),(18,4,'Franck','Delgado','franckdelgado@gmail.com','9e82dbebaebd5deaf27df1697f2679c2687d6dd8f97d9e82023196a1ea7988b6','1986-04-10 22:00:00','2019-06-09 07:10:03','2019-06-09 07:11:57',1),(19,4,'Matthew','Landowski','mattlandowski@gmail.com','9e82dbebaebd5deaf27df1697f2679c2687d6dd8f97d9e82023196a1ea7988b6','1994-04-02 22:00:00','2019-06-09 07:12:39','2019-06-09 07:51:16',1),(22,2,'Steeve','PÃ©rÃ©','steevepere@gmail.comff','9e82dbebaebd5deaf27df1697f2679c2687d6dd8f97d9e82023196a1ea7988b6','2019-06-10 22:00:00','2019-06-09 08:58:44','2019-06-09 08:58:44',0);
INSERT INTO `users` VALUES (6,1,'Jhonny','Bgood','Jhonnybgood@gmail.com','9e82dbebaebd5deaf27df1697f2679c2687d6dd8f97d9e82023196a1ea7988b6','2019-06-03 22:00:00','2019-06-08 21:41:19','2019-06-08 21:41:19',1);
INSERT INTO `users` VALUES (5,2,'Ouioui','ouioui','ouiouiou@gmail.com','9e82dbebaebd5deaf27df1697f2679c2687d6dd8f97d9e82023196a1ea7988b6','2019-06-03 22:00:00','2019-06-08 21:41:19','2019-06-08 21:41:19',0);
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

-- Dump completed on 2019-06-09 11:40:25
ALTER TABLE `users`
    DROP FOREIGN KEY `FK_USR_ROL`;