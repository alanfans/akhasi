/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.0.96-community-nt : Database - akhasi
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`akhasi` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `akhasi`;

/*Table structure for table `tb_blog` */

DROP TABLE IF EXISTS `tb_blog`;

CREATE TABLE `tb_blog` (
  `id` int(11) NOT NULL auto_increment,
  `author` varchar(50) default NULL,
  `createTime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `readCount` int(11) default '0',
  `title` varchar(100) default NULL,
  `blogType` varchar(50) default NULL,
  `content` text,
  `abstracts` text,
  `blogClassName` varchar(50) default NULL,
  `blogClassId` int(11) default NULL,
  `keyWords` varchar(225) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `tb_blog` */

insert  into `tb_blog`(`id`,`author`,`createTime`,`readCount`,`title`,`blogType`,`content`,`abstracts`,`blogClassName`,`blogClassId`,`keyWords`) values (28,'tnmao','2016-06-23 09:56:19',0,'测试','原创','<ol><li><p>这是一个测试代码如下</p></li></ol><pre>package&nbsp;com.luosl.akhasi.controller.manager;\n\nimport&nbsp;com.luosl.akhasi.controller.base.Controller;\nimport&nbsp;com.luosl.akhasi.domain.Blog;\nimport&nbsp;com.luosl.akhasi.domain.User;\nimport&nbsp;org.springframework.ui.Model;\nimport&nbsp;org.springframework.web.bind.annotation.RequestMapping;\nimport&nbsp;org.springframework.web.bind.annotation.RequestMethod;\n\n/**\n&nbsp;*\n&nbsp;*&nbsp;Created&nbsp;by&nbsp;Administrator&nbsp;on&nbsp;2016/5/30.\n&nbsp;*/\n@org.springframework.stereotype.Controller\n@RequestMapping(&quot;manager&quot;)\npublic&nbsp;class&nbsp;Manager&nbsp;extends&nbsp;Controller{\n&nbsp;&nbsp;&nbsp;&nbsp;@RequestMapping(&quot;index&quot;)\n&nbsp;&nbsp;&nbsp;&nbsp;public&nbsp;String&nbsp;index(Model&nbsp;model){\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;model.addAttribute(&quot;pager&quot;,getDao(Blog.class).findByPage(1,&quot;select&nbsp;*&nbsp;from&nbsp;tbl&quot;));\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;&quot;manager/index&quot;;\n&nbsp;&nbsp;&nbsp;&nbsp;}\n\n&nbsp;&nbsp;&nbsp;&nbsp;@RequestMapping(value&nbsp;=&nbsp;&quot;login&quot;,method&nbsp;=&nbsp;RequestMethod.GET)\n&nbsp;&nbsp;&nbsp;&nbsp;public&nbsp;String&nbsp;login(){\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;&quot;manager/login&quot;;\n&nbsp;&nbsp;&nbsp;&nbsp;}\n\n&nbsp;&nbsp;&nbsp;&nbsp;@RequestMapping(value&nbsp;=&nbsp;&quot;login&quot;,method&nbsp;=&nbsp;RequestMethod.POST)\n&nbsp;&nbsp;&nbsp;&nbsp;public&nbsp;String&nbsp;login(String&nbsp;userName,String&nbsp;passwd){\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;User&nbsp;user&nbsp;=&nbsp;getDao(User.class).findSingle(&quot;select&nbsp;*&nbsp;from&nbsp;tbl&nbsp;where&nbsp;userName&nbsp;=&nbsp;?&nbsp;and&nbsp;passwd&nbsp;=&nbsp;?&nbsp;and&nbsp;userType&nbsp;=&nbsp;1&quot;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;,userName,passwd);\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if(null==user){\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;redirect(&quot;login&quot;);\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}else&nbsp;{\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;login(user);\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;redirect(&quot;index&quot;);\n&nbsp;&nbsp;&nbsp;&nbsp;}\n}</pre><p><br/></p>','','其他',8,'测试');

/*Table structure for table `tb_blogclass` */

DROP TABLE IF EXISTS `tb_blogclass`;

CREATE TABLE `tb_blogclass` (
  `id` int(11) NOT NULL auto_increment,
  `className` varchar(50) default NULL,
  `createTime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tb_blogclass` */

insert  into `tb_blogclass`(`id`,`className`,`createTime`) values (1,'移动开发','2016-05-31 09:53:47'),(2,'前端开发','2016-05-31 09:53:58'),(3,'服务端开发','2016-05-31 09:54:35'),(4,'游戏开发','2016-05-31 09:54:50'),(5,'编程语言','2016-05-31 09:54:57'),(6,'数据库','2016-05-31 09:55:37'),(7,'云计算/大数据','2016-05-31 09:55:35'),(8,'其他','2016-05-31 09:55:44');

/*Table structure for table `tb_reply` */

DROP TABLE IF EXISTS `tb_reply`;

CREATE TABLE `tb_reply` (
  `id` int(11) NOT NULL auto_increment,
  `blogId` int(11) default NULL,
  `rootId` int(11) default NULL,
  `content` text,
  `createTime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `ownerId` int(11) default NULL,
  `toUserId` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

/*Data for the table `tb_reply` */

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL auto_increment,
  `userName` varchar(30) default NULL,
  `passwd` varchar(64) default NULL,
  `nickName` varchar(30) default NULL,
  `userType` int(11) default NULL,
  `sex` char(4) default NULL,
  `img` varchar(225) default NULL,
  `openId` char(32) default NULL,
  `createTime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`userName`,`passwd`,`nickName`,`userType`,`sex`,`img`,`openId`,`createTime`) values (1,NULL,NULL,'joker',0,'男','http://qzapp.qlogo.cn/qzapp/101319517/D6A0FE611D8612A24180D9B1B4D215F3/100','D6A0FE611D8612A24180D9B1B4D215F3','2016-06-15 16:13:55'),(2,'tnmao','123456','天猫',1,'男','http://qzapp.qlogo.cn/qzapp/101319517/D6A0FE611D8612A24180D9B1B4D215F3/100',NULL,'2016-06-23 09:42:17');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
