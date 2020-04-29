/*
SQLyog Community v13.1.1 (64 bit)
MySQL - 5.0.7-beta-nt : Database - xlibrarydb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`xlibrarydb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `xlibrarydb`;

/*Table structure for table `booklends` */

DROP TABLE IF EXISTS `booklends`;

CREATE TABLE `booklends` (
  `RecordNo` varchar(50) NOT NULL,
  `MemberNo` int(50) default NULL,
  `MemberName` varchar(50) default NULL,
  `MemberType` varchar(50) default NULL,
  `BookNo` varchar(50) default NULL,
  `BookName` varchar(50) default NULL,
  `BookType` varchar(50) default NULL,
  `IssuesDate` varchar(50) default NULL,
  `ReturnDate` varchar(50) default NULL,
  `Marks` int(50) default NULL,
  PRIMARY KEY  (`RecordNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `booklends` */

/*Table structure for table `books` */

DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
  `BookID` int(50) NOT NULL,
  `BookName` varchar(50) default NULL,
  `Category` varchar(50) default NULL,
  `Author` varchar(50) default NULL,
  `Edition` varchar(50) default NULL,
  `Publisher` varchar(50) default NULL,
  `Date` varchar(50) default NULL,
  `BookType` varchar(50) default NULL,
  `TotalBooks` varchar(50) default NULL,
  `Price` double default NULL,
  `ISBN` varchar(50) default NULL,
  `Year` varchar(50) default NULL,
  `Marks` int(50) default NULL,
  PRIMARY KEY  (`BookID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `books` */

/*Table structure for table `members` */

DROP TABLE IF EXISTS `members`;

CREATE TABLE `members` (
  `MemberID` int(50) NOT NULL,
  `MemberName` varchar(50) default NULL,
  `DateRegister` varchar(50) default NULL,
  `Email` varchar(50) default NULL,
  `Contact` varchar(50) default NULL,
  `Gender` varchar(50) default NULL,
  `MemberType` varchar(50) default NULL,
  `Birthday` varchar(50) default NULL,
  `Address` text,
  PRIMARY KEY  (`MemberID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `members` */

/*Table structure for table `returns` */

DROP TABLE IF EXISTS `returns`;

CREATE TABLE `returns` (
  `ReturnID` int(11) NOT NULL auto_increment,
  `Bookid` varchar(50) default NULL,
  `BookTitle` varchar(50) default NULL,
  `Memberid` varchar(50) default NULL,
  `MemberName` varchar(50) default NULL,
  `LateDays` varchar(50) default NULL,
  `Fine` varchar(50) default NULL,
  `AmountBill` varchar(50) default NULL,
  PRIMARY KEY  (`ReturnID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `returns` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
