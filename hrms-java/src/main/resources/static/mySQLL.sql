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
  `entryId` bigint(64) DEFAULT NULL,
  `endId` bigint(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1316026895908773890 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `employee` */

insert  into `employee`(`id`,`name`,`departmentId`,`joinTime`,`officialTime`,`endTime`,`gender`,`roleId`,`education`,`major`,`salary`,`isEnd`,`isOfficial`,`post`,`entryId`,`endId`) values (1,'susu',1,'2020-09-01','2020-09-15',NULL,'女',1,'本科','工商管理',10000,'否','是','人力资源',NULL,NULL),(2,'shu',2,'2020-09-01','2020-10-10',NULL,'男',2,'本科','计算机',10000,'否','是','java开发',NULL,NULL),(3,'shu2',2,'2020-09-01','2020-09-30',NULL,'男',2,'本科','计算机',10000,'否','是','测试',NULL,NULL),(1315841054735601666,'宿宿',1,'2020-10-30','2020-10-31',NULL,'女',NULL,'本科','工商管理',11000,'否','是','hrbp',1315841054676881409,NULL),(1315841168095055873,'苏',2,'2020-10-31','2020-10-31','2020-11-01','男',NULL,'硕士','计算机',16000,'是','是','java开发',1315841168057307137,1315841663069065218),(1316026895908773889,'测试通过',2,'2020-11-28','2020-11-30',NULL,'男',NULL,'本科','11',1,'否','是','测试',1316026895862636546,NULL);

/*Table structure for table `end` */

DROP TABLE IF EXISTS `end`;

CREATE TABLE `end` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `post` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `departmentId` bigint(64) DEFAULT NULL,
  `isAutoEnd` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `isDispute` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `endTime` date DEFAULT NULL,
  `compensation` int(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1315841663069065219 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `end` */

insert  into `end`(`id`,`name`,`post`,`departmentId`,`isAutoEnd`,`isDispute`,`endTime`,`compensation`) values (1315841663069065218,'苏','java开发',2,'是','否','2020-11-01',0);

/*Table structure for table `entry` */

DROP TABLE IF EXISTS `entry`;

CREATE TABLE `entry` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `joinTime` date DEFAULT NULL,
  `name` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `gender` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `age` int(20) DEFAULT NULL,
  `education` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `major` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `post` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `departmentId` bigint(64) DEFAULT NULL,
  `intershipSalary` int(20) DEFAULT NULL,
  `officialSalary` int(20) DEFAULT NULL,
  `isOfficial` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `interviewTime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1316026895862636547 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `entry` */

insert  into `entry`(`id`,`joinTime`,`name`,`gender`,`age`,`education`,`major`,`post`,`departmentId`,`intershipSalary`,`officialSalary`,`isOfficial`,`interviewTime`) values (1315841054676881409,'2020-10-30','宿宿','女',20,'本科','工商管理','hrbp',1,1000,11000,'否','2020-10-15'),(1315841168057307137,'2020-10-31','苏','男',22,'硕士','计算机','java开发',2,0,16000,'是','2020-10-17'),(1316026895862636546,'2020-11-28','测试通过','男',11,'本科','11','测试',2,1,1,'是','2020-11-20');

/*Table structure for table `identity` */

DROP TABLE IF EXISTS `identity`;

CREATE TABLE `identity` (
  `id` bigint(200) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `uid` int(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `identity` */

insert  into `identity`(`id`,`username`,`password`,`uid`) values (1,'admin','123456',1),(2,'normal','123456',2),(3,'normal1','123456',3);

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
) ENGINE=InnoDB AUTO_INCREMENT=1316026781022593026 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `interview` */

insert  into `interview`(`id`,`interviewTime`,`name`,`gender`,`age`,`job`,`departmentId`,`stage`,`isStore`,`assess`,`education`,`major`,`result`) values (1315835968223510530,'2020-10-15','宿宿','女',20,'hrbp',1,'复试','','表现好','本科','工商管理','通过'),(1315836094807605249,'2020-10-17','苏','男',22,'java开发',2,'复试','','好','硕士','计算机','通过'),(1316026213168357378,'2020-10-16','张三','男',11,'java开发',2,'初试','','好','硕士','11','不通过'),(1316026589485506562,'2020-11-05','测试','女',11,'测试',2,'初试','','1','硕士','12','不通过'),(1316026781022593025,'2020-11-20','测试通过','男',11,'测试',2,'复试','','111','本科','11','通过');

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` bigint(200) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `router` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `style` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `permission` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `menu` */

insert  into `menu`(`id`,`menuName`,`router`,`style`,`permission`) values (1,'首页','index','el-icon-s-home',2),(2,'岗位需求','jobRequire','el-icon-circle-plus',1),(3,'面试管理','interviewM','el-icon-reading',5),(4,'入职管理','joinManager','el-icon-box',5),(5,'员工信息','employee','el-icon-user',5),(6,'离职管理','endManager','el-icon-s-promotion',5);

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
) ENGINE=InnoDB AUTO_INCREMENT=1315834912454221826 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `requirement` */

insert  into `requirement`(`id`,`departmentId`,`proposer`,`post`,`requirement`,`education`,`major`,`hopeTime`,`createTime`,`nums`) values (1315834104572551169,1,1,'hrbp','人好看','本科以上','工商管理','2020-10-31','2020-10-13',1),(1315834334424604674,2,2,'java开发','能力强','本科以上','计算机','2020-10-30','2020-10-13',2),(1315834912454221825,2,3,'测试','吃苦耐劳','本科以上','计算机','2020-10-29','2020-10-13',2);

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
