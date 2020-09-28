/*
SQLyog v10.2 
MySQL - 5.5.27 : Database - hrms
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
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `department` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `department` */

insert  into `department`(`id`,`department`) values (1,'人事行政部'),(2,'运营部'),(3,'采购部'),(4,'技术中心部'),(5,'物流部'),(6,'客服部'),(7,'财务部');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `department_id` int(64) DEFAULT NULL,
  `join_time` date DEFAULT NULL,
  `full_member` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `gender` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `employee` */

insert  into `employee`(`id`,`name`,`department_id`,`join_time`,`full_member`,`end_time`,`gender`) values (1,'susu',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `identity` */

DROP TABLE IF EXISTS `identity`;

CREATE TABLE `identity` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `uid` int(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `identity` */

insert  into `identity`(`id`,`username`,`password`,`uid`) values (1,'admin','123456',1);

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `style` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `router` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `menu` */

insert  into `menu`(`id`,`menuName`,`style`,`router`) values (1,'首页','el-icon-s-home','index'),(2,'岗位需求','el-icon-circle-plus','jobRequire'),(3,'面试管理','el-icon-reading','interviewM'),(4,'入职管理','el-icon-box','joinManager');

/*Table structure for table `requirement` */

DROP TABLE IF EXISTS `requirement`;

CREATE TABLE `requirement` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `departmentId` int(20) DEFAULT NULL,
  `proposer` int(20) DEFAULT NULL,
  `post` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `nums` int(20) DEFAULT NULL,
  `requirement` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `education` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `major` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `hopeTime` date DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `requirement` */

insert  into `requirement`(`id`,`departmentId`,`proposer`,`post`,`nums`,`requirement`,`education`,`major`,`hopeTime`,`createTime`) values (1,1,1,'java开发',3,'技术好','本科','计算机','2020-09-25','2020-09-16');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `permission` int(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
