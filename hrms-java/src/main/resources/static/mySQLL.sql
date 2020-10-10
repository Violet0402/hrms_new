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
  `officialTime` date DEFAULT NULL,
  `endTime` date DEFAULT NULL,
  `gender` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `roleId` int(10) DEFAULT NULL,
  `education` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `major` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `salary` int(200) DEFAULT NULL,
  `isEnd` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `isOfficial` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `post` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `employee` */

insert  into `employee`(`id`,`name`,`departmentId`,`joinTime`,`officialTime`,`endTime`,`gender`,`roleId`,`education`,`major`,`salary`,`isEnd`,`isOfficial`,`post`) values (1,'susu',1,'2020-09-01','2020-09-15',NULL,'女',1,'本科','工商管理',10000,'否','是','人力资源'),(2,'shu',2,'2020-09-01','2020-10-10',NULL,'男',2,'本科','计算机',10000,'否','是','java开发');

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

/*Table structure for table `interview` */

DROP TABLE IF EXISTS `interview`;

CREATE TABLE `interview` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `interviewTime` date DEFAULT NULL,
  `name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `gender` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `age` int(30) DEFAULT NULL,
  `job` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `departmentId` bigint(64) DEFAULT NULL,
  `stage` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `isStore` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `assess` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  `education` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `major` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `result` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1314495974909214723 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `interview` */

insert  into `interview`(`id`,`interviewTime`,`name`,`gender`,`age`,`job`,`departmentId`,`stage`,`isStore`,`assess`,`education`,`major`,`result`) values (1,'2020-10-10','张三','男',18,'java开发',2,'复试','否','好','本科','计算机','通过'),(1314481890516037633,'2020-10-15','苏','男',23,'java开发',2,'复试','是','好','硕士','计算机','通过'),(1314487003733372930,'2020-10-07','22','男',2,'java高级',2,'初试','否','22','本科','22','通过'),(1314495974909214722,'2020-10-12','666','男',555,'hrbp',1,'初试','是','666','本科','555','通过');

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` bigint(200) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `router` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `style` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `permission` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `menu` */

insert  into `menu`(`id`,`menuName`,`router`,`style`,`permission`) values (1,'首页','index','el-icon-s-home',2),(2,'岗位需求','jobRequire','el-icon-circle-plus',1),(3,'面试管理','interviewM','el-icon-reading',5),(4,'入职管理','joinManager','el-icon-box',5),(5,'员工信息表','employee','el-icon-user',5);

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
) ENGINE=InnoDB AUTO_INCREMENT=1314495836631400450 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `requirement` */

insert  into `requirement`(`id`,`departmentId`,`proposer`,`post`,`requirement`,`education`,`major`,`hopeTime`,`createTime`,`nums`) values (1,2,2,'java开发','技术好','本科','计算机','2020-09-30','2020-09-01',10),(2,2,2,'java高级','技术特别好','本科','计算机','2020-09-30','2020-09-01',5),(1310518752917688321,1,1,'人力资源','人好看','不限','不限','2020-09-30','2020-09-28',10),(1310520128741601281,2,2,'测试','不限','不限','不限','2020-10-23','2020-09-28',60),(1310520408677838849,1,1,'人力资源培训方向','人必须好看','不限','不限','2020-09-28','2020-09-28',1),(1314495836631400449,1,1,'hrbp','能力强','本科','不限','2020-10-08','2020-10-09',1);

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
