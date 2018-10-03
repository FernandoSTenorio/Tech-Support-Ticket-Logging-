# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.1.26-MariaDB)
# Database: ticket
# Generation Time: 2017-12-13 09:55:25 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table tickets
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tickets`;

CREATE TABLE `tickets` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tech_id` int(11) unsigned NOT NULL,
  `creation` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `close` timestamp NULL DEFAULT NULL,
  `priority` enum('urgente','normal','longterm') DEFAULT NULL,
  `description` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `TICKETS_TECHID` (`tech_id`),
  CONSTRAINT `TICKETS_TECHID` FOREIGN KEY (`tech_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;

INSERT INTO `tickets` (`id`, `tech_id`, `creation`, `close`, `priority`, `description`)
VALUES
	(7,1,'2017-11-21 06:59:07',NULL,'longterm',NULL),
	(30,2,'2017-11-21 06:59:07','2017-11-21 19:33:04','normal','vbbcvbcvbcvbcv'),
	(32,2,'2017-11-21 07:24:05','2017-11-21 20:59:36','normal','gdfggf'),
	(38,3,'2017-11-24 10:40:45','2017-12-07 22:19:35','longterm',NULL),
	(39,3,'2017-11-26 06:21:09',NULL,'urgente',NULL),
	(48,1,'2017-12-02 11:45:04','2017-12-02 23:56:24','longterm','fdfdfdfdfdfd'),
	(49,1,'2017-12-03 08:51:48','2017-12-03 20:51:55','urgente','hard worker'),
	(51,2,'2017-12-04 11:39:18',NULL,'normal','help'),
	(52,3,'2017-12-06 01:10:30','2017-12-06 01:10:38','normal','test harcore'),
	(53,1,'2017-12-06 01:44:57',NULL,'longterm','i got it'),
	(54,3,'2017-12-06 10:48:52','2017-12-07 22:15:29','urgente','sudhsudsud'),
	(55,1,'2017-12-06 12:47:34',NULL,'normal','ronaldo lindaaaa');

/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `type` enum('admin','manager','techsuport') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `username`, `password`, `type`)
VALUES
	(1,'john','dublin123','techsuport'),
	(2,'James','jmdublin','techsuport'),
	(3,'Johan','jh1234','techsuport'),
	(4,'Sora','s1234','manager'),
	(5,'Gustavo','gu1234','admin'),
	(6,'Patrick','pat456','admin'),
	(7,'Ronaldo','rd4321','admin');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
