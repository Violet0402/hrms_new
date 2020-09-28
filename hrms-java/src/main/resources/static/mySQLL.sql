/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 5.7.25 : Database - hrms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hrms` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `hrms`;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `department` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `department` */

insert  into `department`(`id`,`department`) values (1,'人事行政部'),(2,'技术中心部');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` bigint(200) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `departmentId` int(64) DEFAULT NULL,
  `joinTime` date DEFAULT NULL,
  `fullMember` date DEFAULT NULL,
  `endTime` date DEFAULT NULL,
  `gender` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `roleId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `employee` */

insert  into `employee`(`id`,`name`,`departmentId`,`joinTime`,`fullMember`,`endTime`,`gender`,`roleId`) values (1,'susu',1,'2020-09-01','2020-09-15',NULL,'女',1),(2,'shu',2,'2020-09-01','2020-09-15',NULL,'男',2);

/*Table structure for table `identity` */

DROP TABLE IF EXISTS `identity`;

CREATE TABLE `identity` (
  `id` bigint(200) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `uid` int(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `identity` */

insert  into `identity`(`id`,`username`,`password`,`uid`) values (1,'admin','123456',1),(2,'normal','123456',2);

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` bigint(200) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `router` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `style` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `permission` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `menu` */

insert  into `menu`(`id`,`menuName`,`router`,`style`,`permission`) values (1,'首页','index','el-icon-s-home',2),(2,'岗位需求','jobRequire','el-icon-circle-plus',1),(3,'面试管理','interviewM','el-icon-reading',5),(4,'入职管理','joinManager','el-icon-box',5);

/*Table structure for table `requirement` */

DROP TABLE IF EXISTS `requirement`;

CREATE TABLE `requirement` (
  `id` bigint(200) NOT NULL AUTO_INCREMENT,
  `departmentId` bigint(200) DEFAULT NULL,
  `proposer` bigint(200) DEFAULT NULL,
  `post` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `requirement` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL,
  `education` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `major` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `hopeTime` date DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `nums` int(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1310520408677838850 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `requirement` */

insert  into `requirement`(`id`,`departmentId`,`proposer`,`post`,`requirement`,`education`,`major`,`hopeTime`,`createTime`,`nums`) values (1,1,1,'java开发','技术好','本科','计算机','2020-09-30','2020-09-01',10),(2,2,2,'java高级','技术特别好','本科','计算机','2020-09-30','2020-09-01',5),(1310518752917688321,1,1,'人力资源','人好看','不限','不限','2020-09-30','2020-09-28',10),(1310520128741601281,1,1,'测试','不限','不限','不限','2020-09-08','2020-09-28',60),(1310520408677838849,1,1,'人力资源培训方向','人必须好看','不限','不限','2020-09-08','2020-09-28',1);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(200) NOT NULL AUTO_INCREMENT,
  `role` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `resource` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `role` */

insert  into `role`(`id`,`role`,`resource`) values (1,'admin',5),(2,'normal',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
