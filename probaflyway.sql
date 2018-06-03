/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.22-0ubuntu0.16.04.1 : Database - probaflyway
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`probaflyway` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `probaflyway`;

/*Table structure for table `flyway_schema_history` */

DROP TABLE IF EXISTS `flyway_schema_history`;

CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `flyway_schema_history` */

insert  into `flyway_schema_history`(`installed_rank`,`version`,`description`,`type`,`script`,`checksum`,`installed_by`,`installed_on`,`execution_time`,`success`) values (1,'1','Ispit','SQL','V1__Ispit.sql',-753007594,'root','2018-05-26 19:46:19',119,1),(2,'2','Ispitnirok','SQL','V2__Ispitnirok.sql',1140351298,'root','2018-05-26 19:46:19',137,1),(3,'3','Mesto','SQL','V3__Mesto.sql',-260105324,'root','2018-05-26 19:46:19',134,1),(4,'4','Student','SQL','V4__Student.sql',-385472511,'root','2018-05-26 19:46:19',141,1),(5,'5','User','SQL','V5__User.sql',504659396,'root','2018-05-26 19:46:19',130,1),(6,'6','Polaganje','SQL','V6__Polaganje.sql',556449941,'root','2018-05-26 19:46:20',662,1);

/*Table structure for table `ispit` */

DROP TABLE IF EXISTS `ispit`;

CREATE TABLE `ispit` (
  `SifraIspita` int(10) NOT NULL,
  `NazivIspita` varchar(30) NOT NULL,
  PRIMARY KEY (`SifraIspita`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `ispit` */

insert  into `ispit`(`SifraIspita`,`NazivIspita`) values (1,'Softverski zahtevi'),(2,'Softverski procesi'),(3,'Napredne soft. tehnologije'),(4,'Napredne soft. tehnologije 2'),(5,'Konstrukcija softvera'),(6,'Alati i metode soft. inz.'),(7,'Projektovanje softvera'),(8,'Napredne jave tehnologije'),(9,'Softverski paterni'),(10,'Kontrukcija softvera'),(11,'Testiranje i kvalitet softvera');

/*Table structure for table `ispitnirok` */

DROP TABLE IF EXISTS `ispitnirok`;

CREATE TABLE `ispitnirok` (
  `SifraIspitnogRoka` int(10) NOT NULL,
  `NazivIspitnogRoka` varchar(20) NOT NULL,
  PRIMARY KEY (`SifraIspitnogRoka`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `ispitnirok` */

insert  into `ispitnirok`(`SifraIspitnogRoka`,`NazivIspitnogRoka`) values (1,'Januar'),(2,'Februar'),(3,'Mart'),(4,'Maj'),(5,'Jun'),(6,'Septembar');

/*Table structure for table `mesto` */

DROP TABLE IF EXISTS `mesto`;

CREATE TABLE `mesto` (
  `Ptt` int(10) NOT NULL,
  `Naziv` varchar(40) NOT NULL,
  PRIMARY KEY (`Ptt`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `mesto` */

insert  into `mesto`(`Ptt`,`Naziv`) values (11000,'Beograd'),(11070,'Novi Beograd'),(12223,'Golubac'),(18000,'Nis'),(21000,'Novi Sad'),(34000,'Kragujevac');

/*Table structure for table `polaganje` */

DROP TABLE IF EXISTS `polaganje`;

CREATE TABLE `polaganje` (
  `PolaganjeId` int(11) NOT NULL AUTO_INCREMENT,
  `BrInd` varchar(10) NOT NULL,
  `SifraIspita` int(10) NOT NULL,
  `SifraIspitnogRoka` int(10) NOT NULL,
  `Ocena` int(10) NOT NULL,
  `Datum` date NOT NULL,
  PRIMARY KEY (`PolaganjeId`,`BrInd`,`SifraIspita`,`SifraIspitnogRoka`),
  KEY `fk_BrInd` (`BrInd`),
  KEY `fk_SifraIspita` (`SifraIspita`),
  KEY `fk_SifraIspitnogRoka` (`SifraIspitnogRoka`),
  CONSTRAINT `fk_BrInd` FOREIGN KEY (`BrInd`) REFERENCES `student` (`BrInd`),
  CONSTRAINT `fk_SifraIspita` FOREIGN KEY (`SifraIspita`) REFERENCES `ispit` (`SifraIspita`),
  CONSTRAINT `fk_SifraIspitnogRoka` FOREIGN KEY (`SifraIspitnogRoka`) REFERENCES `ispitnirok` (`SifraIspitnogRoka`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `polaganje` */

insert  into `polaganje`(`PolaganjeId`,`BrInd`,`SifraIspita`,`SifraIspitnogRoka`,`Ocena`,`Datum`) values (1,'2017/3701',3,2,6,'2018-06-03'),(2,'2017/3713',2,2,10,'2017-02-28'),(3,'2017/3701',1,1,10,'2018-05-31'),(4,'2017/3713',6,5,7,'2018-06-04'),(5,'2017/3713',4,5,10,'2018-06-07'),(6,'2017/3701',6,4,8,'2018-06-01'),(7,'2017/3701',4,1,9,'2018-02-07'),(8,'2017/3707',8,5,10,'2018-05-02'),(9,'2017/3713',1,2,10,'2017-02-28'),(10,'2017/3713',9,2,9,'2017-02-14');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `BrInd` varchar(10) NOT NULL,
  `Ime` varchar(30) NOT NULL,
  `Prezime` varchar(30) NOT NULL,
  `Ptt` int(10) NOT NULL,
  PRIMARY KEY (`BrInd`),
  KEY `Ptt` (`Ptt`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`Ptt`) REFERENCES `mesto` (`Ptt`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `student` */

insert  into `student`(`BrInd`,`Ime`,`Prezime`,`Ptt`) values ('2017/3701','Marko','Markovic',11000),('2017/3702','Marko','Adamovic',12223),('2017/3703','Nikola','Belovanovic',18000),('2017/3704','Pera','Peric',18000),('2017/3705','Ivana','Ivanovic',11000),('2017/3706','Zika','Zivkovic',34000),('2017/3707','Ilija','Ivanovic',12223),('2017/3708','Marko','Simic',21000),('2017/3709','Marijana','Pajic',11070),('2017/3713','Filip','Ivanovic',12223);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `UserId` int(10) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`UserId`,`Username`,`Password`) values (1,'filip','filip'),(2,'marko','marko');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
