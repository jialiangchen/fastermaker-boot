/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.20-log : Database - fastermaker
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fastermaker` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `fastermaker`;

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '部门名称',
  `code` varchar(100) NOT NULL COMMENT '部门编号',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父节点id',
  `tree_path` varchar(255) NOT NULL DEFAULT '' COMMENT '父节点id路径',
  `sort` smallint(6) DEFAULT '0' COMMENT '显示顺序',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态(1-正常 0-禁用)',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识(1-已删除 0-未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_code` (`code`) USING BTREE COMMENT '部门编号唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门表';

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`id`,`name`,`code`,`parent_id`,`tree_path`,`sort`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`is_deleted`) values 
(1,'FasterMaker','FasterMaker',0,'0',1,1,1,NULL,1,'2024-07-08 19:49:51',0),
(2,'开发部门','Dev',1,'0,1',1,1,2,NULL,2,'2024-07-08 19:50:04',0),
(3,'测试部门','Test',1,'0,1',1,1,2,NULL,2,'2024-07-08 19:50:15',0);

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `name` varchar(50) DEFAULT '' COMMENT '类型名称',
  `code` varchar(50) DEFAULT '' COMMENT '类型编码',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态(0:正常;1:禁用)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除(1-删除，0-未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1809586616012972034 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典类型表';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`name`,`code`,`status`,`remark`,`create_by`,`create_time`,`update_by`,`update_time`,`is_deleted`) values 
(1,'性别','gender',1,NULL,NULL,'2019-12-06 19:03:32',NULL,'2024-07-10 09:50:54',0),
(1809586616012972033,'32','22',0,NULL,NULL,'2024-07-06 21:54:04',NULL,'2024-07-07 16:21:17',1);

/*Table structure for table `sys_dict_item` */

DROP TABLE IF EXISTS `sys_dict_item`;

CREATE TABLE `sys_dict_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_id` bigint(20) DEFAULT NULL COMMENT '字典ID',
  `name` varchar(50) DEFAULT '' COMMENT '字典项名称',
  `value` varchar(50) DEFAULT '' COMMENT '字典项值',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态（1-正常，0-禁用）',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1809586616012972035 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典数据表';

/*Data for the table `sys_dict_item` */

insert  into `sys_dict_item`(`id`,`dict_id`,`name`,`value`,`status`,`sort`,`remark`,`create_time`,`update_time`) values 
(1,1,'男','1',1,1,NULL,'2019-05-05 13:07:52','2022-06-12 23:20:39'),
(2,1,'女','2',1,2,NULL,'2019-04-19 11:33:00','2019-07-02 14:23:05'),
(3,1,'保密','0',1,3,NULL,'2020-10-17 08:09:31','2020-10-17 08:09:31'),
(1809586616012972034,1809586616012972033,'00','00',1,1,'',NULL,NULL);

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module` enum('LOGIN','USER','ROLE','DEPT','MENU','DICT','OTHER') NOT NULL COMMENT '日志模块',
  `content` varchar(255) NOT NULL COMMENT '日志内容',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求路径',
  `ip` varchar(45) DEFAULT NULL COMMENT 'IP地址',
  `province` varchar(100) DEFAULT NULL COMMENT '省份',
  `city` varchar(100) DEFAULT NULL COMMENT '城市',
  `execution_time` bigint(20) DEFAULT NULL COMMENT '执行时间(ms)',
  `browser` varchar(100) DEFAULT NULL COMMENT '浏览器',
  `browser_version` varchar(100) DEFAULT NULL COMMENT '浏览器版本',
  `os` varchar(100) DEFAULT NULL COMMENT '终端系统',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识(1-已删除 0-未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810854344409145347 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统日志表';

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`module`,`content`,`request_uri`,`ip`,`province`,`city`,`execution_time`,`browser`,`browser_version`,`os`,`create_by`,`create_time`,`is_deleted`) values 
(1810525399801315330,'LOGIN','获取验证码','/api/v1/auth/captcha','192.168.43.45','0','内网IP',143,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',NULL,'2024-07-09 12:04:28',0),
(1810525421129351169,'LOGIN','获取验证码','/api/v1/auth/captcha','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',NULL,'2024-07-09 12:04:33',0),
(1810525441249427458,'LOGIN','登录','/api/v1/auth/login','192.168.43.45','0','内网IP',183,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-09 12:04:38',0),
(1810525441610137602,'USER','获取当前登录用户信息','/api/v1/user/getCurrentUserInfo','192.168.43.45','0','内网IP',33,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-09 12:04:38',0),
(1810525448107114498,'OTHER','获取访问趋势','/api/v1/stats/visit-trend','192.168.43.45','0','内网IP',32,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-09 12:04:39',0),
(1810525469711974401,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',33,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-09 12:04:45',0),
(1810526028405850114,'MENU','菜单列表','/api/v1/menu/listPage','192.168.43.45','0','内网IP',30,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-09 12:06:58',0),
(1810526152691466241,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',9,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-09 12:07:27',0),
(1810526187512578049,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',12,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-09 12:07:36',0),
(1810843798846717954,'LOGIN','获取验证码','/api/v1/auth/captcha','192.168.43.45','0','内网IP',207,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',NULL,'2024-07-10 09:09:40',0),
(1810843840596819969,'LOGIN','获取验证码','/api/v1/auth/captcha','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',NULL,'2024-07-10 09:09:50',0),
(1810843858334531586,'LOGIN','登录','/api/v1/auth/login','192.168.43.45','0','内网IP',173,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:09:54',0),
(1810843858552635394,'USER','获取当前登录用户信息','/api/v1/user/getCurrentUserInfo','192.168.43.45','0','内网IP',35,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:09:54',0),
(1810843866236600322,'OTHER','获取访问趋势','/api/v1/stats/visit-trend','192.168.43.45','0','内网IP',29,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:09:56',0),
(1810843925288206337,'USER','获取当前登录用户信息','/api/v1/user/getCurrentUserInfo','192.168.43.45','0','内网IP',10,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:10:10',0),
(1810843930430423041,'OTHER','获取访问趋势','/api/v1/stats/visit-trend','192.168.43.45','0','内网IP',25,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:10:12',0),
(1810843950198177794,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',30,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:10:16',0),
(1810843974957154306,'OTHER','系统参数分页列表','/api/v1/param/listPage','192.168.43.45','0','内网IP',18,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:10:22',0),
(1810843981533822977,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',8,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:10:24',0),
(1810843992137023490,'USER','获取当前登录用户信息','/api/v1/user/getCurrentUserInfo','192.168.43.45','0','内网IP',6,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:10:26',0),
(1810843993835716610,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',15,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:10:27',0),
(1810844014278754305,'OTHER','获取访问趋势','/api/v1/stats/visit-trend','192.168.43.45','0','内网IP',7,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:10:32',0),
(1810844020901560322,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',13,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:10:33',0),
(1810844166724927490,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',10,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:11:08',0),
(1810844309771665409,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',23,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:11:42',0),
(1810844331577851905,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',8,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:11:47',0),
(1810844336279666690,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:11:48',0),
(1810844345704267777,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',19,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:11:51',0),
(1810844371637649409,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',7,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:11:57',0),
(1810844373810298881,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',7,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:11:57',0),
(1810844376050057218,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',14,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:11:58',0),
(1810844376922472449,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:11:58',0),
(1810844382198906881,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',7,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:11:59',0),
(1810844385902477313,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',8,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:00',0),
(1810844392294596610,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',9,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:02',0),
(1810844397927546882,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:03',0),
(1810844398980317185,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',7,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:03',0),
(1810844399890481153,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',15,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:03',0),
(1810844400586735617,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',7,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:04',0),
(1810844402054742017,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:04',0),
(1810844404151894017,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',7,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:04',0),
(1810844420719394817,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',16,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:08',0),
(1810844469629173761,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',9,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:20',0),
(1810844489023635458,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',16,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:25',0),
(1810844533147713538,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',12,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:35',0),
(1810844559718629377,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:42',0),
(1810844560767205377,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',15,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:42',0),
(1810844561811587074,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:42',0),
(1810844562583339009,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:42',0),
(1810844563283787778,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:42',0),
(1810844581759696897,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',5,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:47',0),
(1810844589141671938,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',15,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:49',0),
(1810844620166938625,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',12,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:56',0),
(1810844628194836482,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',4,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:58',0),
(1810844631797743617,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',9,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:59',0),
(1810844632493998082,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',16,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:59',0),
(1810844633223806977,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',3,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:12:59',0),
(1810844711837646849,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',10,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:13:18',0),
(1810844741768200194,'USER','获取当前登录用户信息','/api/v1/user/getCurrentUserInfo','192.168.43.45','0','内网IP',9,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:13:25',0),
(1810844744268005377,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',15,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:13:26',0),
(1810844817764794369,'USER','获取当前登录用户信息','/api/v1/user/getCurrentUserInfo','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:13:43',0),
(1810844819933249537,'OTHER','获取访问趋势','/api/v1/stats/visit-trend','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:13:44',0),
(1810844841961734145,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',9,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:13:49',0),
(1810844898228322305,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',8,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:02',0),
(1810844917035581442,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',14,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:07',0),
(1810844919552163842,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:07',0),
(1810844922773389313,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',15,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:08',0),
(1810844923406729217,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:08',0),
(1810844923763245057,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:08',0),
(1810844924178481153,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',14,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:08',0),
(1810844924950233089,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:09',0),
(1810844925709402113,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',9,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:09',0),
(1810844927101911042,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:09',0),
(1810844929131954178,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',16,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:10',0),
(1810844929769488385,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:10',0),
(1810844959830065154,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',8,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:17',0),
(1810844964397662209,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',15,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:18',0),
(1810844966847135745,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',2,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:14:19',0),
(1810845754671644673,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',50,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:26',0),
(1810845755309178881,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',10,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:27',0),
(1810845768877752322,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',9,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:30',0),
(1810845775999680514,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',1,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:32',0),
(1810845777916477441,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',5,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:32',0),
(1810845780814741505,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',16,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:33',0),
(1810845787542405122,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',11,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:34',0),
(1810845791032066049,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',16,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:35',0),
(1810845791799623681,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:35',0),
(1810845792084836353,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',12,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:35',0),
(1810845807742173186,'OTHER','代码生成表单数据','/api/v1/generator/getFormData/1810270551679881217','192.168.43.45','0','内网IP',8,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:39',0),
(1810845815560355842,'OTHER','修改代码生成','/api/v1/generator/update','192.168.43.45','0','内网IP',17,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:41',0),
(1810845816474714114,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',15,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:41',0),
(1810845860749787138,'OTHER','新增代码生成','/api/v1/generator/save','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:52',0),
(1810845862276513793,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',33,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:52',0),
(1810845880723062785,'OTHER','删除代码生成','/api/v1/generator/delete/1810845860749787137','192.168.43.45','0','内网IP',20,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:57',0),
(1810845882136543233,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:17:57',0),
(1810845899538710529,'USER','用户分页列表','/api/v1/user/listPage','192.168.43.45','0','内网IP',52,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:18:01',0),
(1810845918060756993,'ROLE','角色分页列表','/api/v1/role/listPage','192.168.43.45','0','内网IP',10,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:18:05',0),
(1810845944979800066,'USER','用户分页列表','/api/v1/user/listPage','192.168.43.45','0','内网IP',19,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:18:12',0),
(1810845947487993858,'USER','用户分页列表','/api/v1/user/listPage','192.168.43.45','0','内网IP',16,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:18:12',0),
(1810845950298177538,'USER','用户分页列表','/api/v1/user/listPage','192.168.43.45','0','内网IP',16,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:18:13',0),
(1810845951271256065,'USER','用户分页列表','/api/v1/user/listPage','192.168.43.45','0','内网IP',15,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:18:13',0),
(1810845953729118209,'USER','用户分页列表','/api/v1/user/listPage','192.168.43.45','0','内网IP',14,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:18:14',0),
(1810845983642894338,'DEPT','部门列表','/api/v1/dept/listPage','192.168.43.45','0','内网IP',9,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:18:21',0),
(1810850414795096065,'USER','获取当前登录用户信息','/api/v1/user/getCurrentUserInfo','192.168.43.45','0','内网IP',50,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:35:58',0),
(1810850424022564865,'OTHER','获取访问趋势','/api/v1/stats/visit-trend','192.168.43.45','0','内网IP',45,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:36:00',0),
(1810850434382495745,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',20,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:36:02',0),
(1810850442141958145,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',17,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:36:04',0),
(1810850448450191361,'OTHER','代码生成表单数据','/api/v1/generator/getFormData/1810270551679881217','192.168.43.45','0','内网IP',9,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:36:06',0),
(1810850454569680897,'OTHER','修改代码生成','/api/v1/generator/update','192.168.43.45','0','内网IP',16,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:36:07',0),
(1810850454733258753,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',4,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:36:07',0),
(1810850481983651842,'OTHER','新增代码生成','/api/v1/generator/save','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:36:14',0),
(1810850483032227841,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',16,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:36:14',0),
(1810850503726923777,'OTHER','删除代码生成','/api/v1/generator/delete/1810850481920737281','192.168.43.45','0','内网IP',16,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:36:19',0),
(1810850505190735874,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',16,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:36:19',0),
(1810852901568569346,'USER','获取当前登录用户信息','/api/v1/user/getCurrentUserInfo','192.168.43.45','0','内网IP',49,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:45:50',0),
(1810852904710103041,'OTHER','获取访问趋势','/api/v1/stats/visit-trend','192.168.43.45','0','内网IP',31,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:45:51',0),
(1810852907419623426,'USER','获取当前登录用户信息','/api/v1/user/getCurrentUserInfo','192.168.43.45','0','内网IP',7,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:45:52',0),
(1810852909344808961,'OTHER','获取访问趋势','/api/v1/stats/visit-trend','192.168.43.45','0','内网IP',14,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:45:52',0),
(1810852924393971714,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',33,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:45:56',0),
(1810852930463129602,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',21,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:45:57',0),
(1810852932904214529,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',19,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:45:58',0),
(1810852941234102273,'USER','用户分页列表','/api/v1/user/listPage','192.168.43.45','0','内网IP',42,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:00',0),
(1810852949400412161,'USER','用户表单数据','/api/v1/user/getFormData/2','192.168.43.45','0','内网IP',17,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:02',0),
(1810852962830573570,'USER','修改用户','/api/v1/user/update','192.168.43.45','0','内网IP',33,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:05',0),
(1810852963598131202,'USER','用户分页列表','/api/v1/user/listPage','192.168.43.45','0','内网IP',25,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:05',0),
(1810852991280537602,'USER','修改用户密码','/api/v1/user/updatePassword','192.168.43.45','0','内网IP',80,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:12',0),
(1810853010171682818,'ROLE','角色分页列表','/api/v1/role/listPage','192.168.43.45','0','内网IP',5,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:16',0),
(1810853024457482241,'ROLE','角色表单数据','/api/v1/role/getFormData/2','192.168.43.45','0','内网IP',6,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:20',0),
(1810853029666807809,'ROLE','修改角色','/api/v1/role/update','192.168.43.45','0','内网IP',35,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:21',0),
(1810853029813608450,'ROLE','角色分页列表','/api/v1/role/listPage','192.168.43.45','0','内网IP',18,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:21',0),
(1810853035345895426,'ROLE','获取角色的菜单ID集合','/api/v1/role/getRoleMenuIds/2','192.168.43.45','0','内网IP',15,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:22',0),
(1810853046364332033,'ROLE','分配菜单(包括按钮权限)给角色','/api/v1/role/updateRoleMenus/2','192.168.43.45','0','内网IP',66,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:25',0),
(1810853047207387138,'ROLE','角色分页列表','/api/v1/role/listPage','192.168.43.45','0','内网IP',15,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:25',0),
(1810853053427539969,'MENU','菜单列表','/api/v1/menu/listPage','192.168.43.45','0','内网IP',22,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:27',0),
(1810853060872429569,'MENU','菜单表单数据','/api/v1/menu/getFormData/1','192.168.43.45','0','内网IP',6,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:28',0),
(1810853068401205249,'MENU','修改菜单','/api/v1/menu/update','192.168.43.45','0','内网IP',24,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:30',0),
(1810853068766109698,'MENU','菜单列表','/api/v1/menu/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:30',0),
(1810853081776840705,'DICT','字典分页列表','/api/v1/dict/listPage','192.168.43.45','0','内网IP',16,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:46:33',0),
(1810854154453311490,'DICT','字典分页列表','/api/v1/dict/listPage','192.168.43.45','0','内网IP',16,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:50:49',0),
(1810854158723112961,'DICT','字典分页列表','/api/v1/dict/listPage','192.168.43.45','0','内网IP',8,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:50:50',0),
(1810854164934877185,'DICT','字典表单','/api/v1/dict/getFormData/1','192.168.43.45','0','内网IP',11,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:50:52',0),
(1810854176964141057,'DICT','修改字典','/api/v1/dict/update','192.168.43.45','0','内网IP',46,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:50:54',0),
(1810854178432147458,'DICT','字典分页列表','/api/v1/dict/listPage','192.168.43.45','0','内网IP',9,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:50:55',0),
(1810854217376260097,'OTHER','系统参数分页列表','/api/v1/param/listPage','192.168.43.45','0','内网IP',11,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:51:04',0),
(1810854227174154242,'OTHER','系统参数表单数据','/api/v1/param/getFormData/1810279472113012737','192.168.43.45','0','内网IP',7,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:51:06',0),
(1810854232404451330,'OTHER','修改系统参数','/api/v1/param/update','192.168.43.45','0','内网IP',10,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:51:08',0),
(1810854232488337409,'OTHER','系统参数分页列表','/api/v1/param/listPage','192.168.43.45','0','内网IP',1,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:51:08',0),
(1810854265099051009,'OTHER','系统参数分页列表','/api/v1/param/listPage','192.168.43.45','0','内网IP',10,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:51:16',0),
(1810854297139339265,'OTHER','新增系统参数','/api/v1/param/save','192.168.43.45','0','内网IP',24,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:51:23',0),
(1810854298158555137,'OTHER','系统参数分页列表','/api/v1/param/listPage','192.168.43.45','0','内网IP',8,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:51:23',0),
(1810854310636609538,'OTHER','删除系统参数','/api/v1/param/delete/1810854297080619010','192.168.43.45','0','内网IP',17,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:51:26',0),
(1810854312024924161,'OTHER','系统参数分页列表','/api/v1/param/listPage','192.168.43.45','0','内网IP',15,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:51:27',0),
(1810854330635051010,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',7,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:51:31',0),
(1810854340063846401,'OTHER','代码生成表单数据','/api/v1/generator/getFormData/1810270551679881217','192.168.43.45','0','内网IP',4,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:51:33',0),
(1810854343075356673,'OTHER','修改代码生成','/api/v1/generator/update','192.168.43.45','0','内网IP',17,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:51:34',0),
(1810854344409145346,'OTHER','代码生成分页列表','/api/v1/generator/listPage','192.168.43.45','0','内网IP',0,'Chrome','122.0.6261.95','Windows 10 or Windows Server 2016',2,'2024-07-10 09:51:34',0);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `parent_id` bigint(20) NOT NULL COMMENT '父菜单ID',
  `tree_path` varchar(255) DEFAULT NULL COMMENT '父节点ID路径',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `type` tinyint(4) NOT NULL COMMENT '菜单类型（1-菜单 2-目录 3-外链 4-按钮）',
  `route_name` varchar(255) DEFAULT NULL COMMENT '路由名称（Vue Router 中用于命名路由）',
  `route_path` varchar(128) DEFAULT '' COMMENT '路由路径（Vue Router 中定义的 URL 路径）',
  `component` varchar(128) DEFAULT NULL COMMENT '组件路径（组件页面完整路径，相对于 src/views/，缺省后缀 .vue）',
  `perm` varchar(128) DEFAULT NULL COMMENT '【按钮】权限标识',
  `always_show` tinyint(4) DEFAULT NULL COMMENT '【目录】只有一个子路由是否始终显示（1-是 0-否）',
  `keep_alive` tinyint(4) DEFAULT NULL COMMENT '【菜单】是否开启页面缓存（1-是 0-否）',
  `visible` tinyint(1) NOT NULL DEFAULT '1' COMMENT '显示状态（1-显示 0-隐藏）',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `icon` varchar(64) DEFAULT '' COMMENT '菜单图标',
  `redirect` varchar(128) DEFAULT NULL COMMENT '跳转路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `params` text COMMENT '路由参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单管理';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`parent_id`,`tree_path`,`name`,`type`,`route_name`,`route_path`,`component`,`perm`,`always_show`,`keep_alive`,`visible`,`sort`,`icon`,`redirect`,`create_time`,`update_time`,`params`) values 
(1,0,'0','系统管理',2,'','/system','Layout',NULL,NULL,NULL,1,1,'system','/system/user','2021-08-28 09:12:21','2024-07-10 09:46:30',NULL),
(2,1,'0,1','用户管理',1,'User','user','system/user/index',NULL,NULL,1,1,1,'user',NULL,'2021-08-28 09:12:21','2021-08-28 09:12:21',NULL),
(3,1,'0,1','角色管理',1,'Role','role','system/role/index',NULL,NULL,1,1,2,'role',NULL,'2021-08-28 09:12:21','2021-08-28 09:12:21',NULL),
(4,1,'0,1','菜单管理',1,'Menu','menu','system/menu/index',NULL,NULL,1,1,3,'menu',NULL,'2021-08-28 09:12:21','2021-08-28 09:12:21',NULL),
(5,1,'0,1','部门管理',1,'Dept','dept','system/dept/index',NULL,NULL,1,1,4,'tree',NULL,'2021-08-28 09:12:21','2021-08-28 09:12:21',NULL),
(6,1,'0,1','字典管理',1,'Dict','dict','system/dict/index',NULL,NULL,1,1,5,'dict',NULL,'2021-08-28 09:12:21','2021-08-28 09:12:21',NULL),
(31,2,'0,1,2','用户新增',4,NULL,'',NULL,'sys:user:save',NULL,NULL,1,1,'','','2022-10-23 11:04:08','2022-10-23 11:04:11',NULL),
(32,2,'0,1,2','用户编辑',4,NULL,'',NULL,'sys:user:update',NULL,NULL,1,2,'','','2022-10-23 11:04:08','2022-10-23 11:04:11',NULL),
(33,2,'0,1,2','用户删除',4,NULL,'',NULL,'sys:user:delete',NULL,NULL,1,3,'','','2022-10-23 11:04:08','2022-10-23 11:04:11',NULL),
(70,3,'0,1,3','角色新增',4,NULL,'',NULL,'sys:role:save',NULL,NULL,1,1,'',NULL,'2023-05-20 23:39:09','2023-05-20 23:39:09',NULL),
(71,3,'0,1,3','角色编辑',4,NULL,'',NULL,'sys:role:update',NULL,NULL,1,2,'',NULL,'2023-05-20 23:40:31','2023-05-20 23:40:31',NULL),
(72,3,'0,1,3','角色删除',4,NULL,'',NULL,'sys:role:delete',NULL,NULL,1,3,'',NULL,'2023-05-20 23:41:08','2023-05-20 23:41:08',NULL),
(73,4,'0,1,4','菜单新增',4,NULL,'',NULL,'sys:menu:save',NULL,NULL,1,1,'',NULL,'2023-05-20 23:41:35','2023-05-20 23:41:35',NULL),
(74,4,'0,1,4','菜单编辑',4,NULL,'',NULL,'sys:menu:update',NULL,NULL,1,3,'',NULL,'2023-05-20 23:41:58','2023-05-20 23:41:58',NULL),
(75,4,'0,1,4','菜单删除',4,NULL,'',NULL,'sys:menu:delete',NULL,NULL,1,3,'',NULL,'2023-05-20 23:44:18','2023-05-20 23:44:18',NULL),
(76,5,'0,1,5','部门新增',4,NULL,'',NULL,'sys:dept:save',NULL,NULL,1,1,'',NULL,'2023-05-20 23:45:00','2023-05-20 23:45:00',NULL),
(77,5,'0,1,5','部门编辑',4,NULL,'',NULL,'sys:dept:update',NULL,NULL,1,2,'',NULL,'2023-05-20 23:46:16','2023-05-20 23:46:16',NULL),
(78,5,'0,1,5','部门删除',4,NULL,'',NULL,'sys:dept:delete',NULL,NULL,1,3,'',NULL,'2023-05-20 23:46:36','2023-05-20 23:46:36',NULL),
(79,6,'0,1,6','字典类型新增',4,NULL,'',NULL,'sys:dict_type:save',NULL,NULL,1,1,'',NULL,'2023-05-21 00:16:06','2023-05-21 00:16:06',NULL),
(81,6,'0,1,6','字典类型编辑',4,NULL,'',NULL,'sys:dict_type:update',NULL,NULL,1,2,'',NULL,'2023-05-21 00:27:37','2023-05-21 00:27:37',NULL),
(84,6,'0,1,6','字典类型删除',4,NULL,'',NULL,'sys:dict_type:delete',NULL,NULL,1,3,'',NULL,'2023-05-21 00:29:39','2023-05-21 00:29:39',NULL),
(85,6,'0,1,6','字典数据新增',4,NULL,'',NULL,'sys:dict:save',NULL,NULL,1,4,'',NULL,'2023-05-21 00:46:56','2023-05-21 00:47:06',NULL),
(86,6,'0,1,6','字典数据编辑',4,NULL,'',NULL,'sys:dict:update',NULL,NULL,1,5,'',NULL,'2023-05-21 00:47:36','2023-05-21 00:47:36',NULL),
(87,6,'0,1,6','字典数据删除',4,NULL,'',NULL,'sys:dict:delete',NULL,NULL,1,6,'',NULL,'2023-05-21 00:48:10','2023-05-21 00:48:20',NULL),
(88,2,'0,1,2','重置密码',4,NULL,'',NULL,'sys:user:password:reset',NULL,NULL,1,4,'',NULL,'2023-05-21 00:49:18','2024-04-28 00:38:22',NULL),
(105,2,'0,1,2','用户查询',4,NULL,'',NULL,'sys:user:list',0,0,1,0,'',NULL,'2024-04-28 00:37:34','2024-04-28 00:37:34',NULL),
(117,1,'0,1','系统日志',1,'Log','log','system/log/index',NULL,0,1,1,6,'document',NULL,'2024-06-28 07:43:16','2024-06-28 07:43:16',NULL),
(118,1,'0,1','SQL监控',3,'Druid','http://localhost:8080/druid/sql.html','druid/sql',NULL,0,1,1,8,'captcha',NULL,'2024-07-04 10:57:55','2024-07-08 19:46:28',NULL),
(1809597027798085634,3,'0,1,3','角色查询',4,NULL,'',NULL,'sys:role:list',0,1,1,0,'',NULL,'2024-07-06 22:35:27','2024-07-06 23:28:31',NULL),
(1809610544747565058,4,'0,1,4','菜单查询',4,NULL,'',NULL,'sys:menu:list',0,1,1,0,'',NULL,'2024-07-06 23:29:09','2024-07-06 23:29:09',NULL),
(1809610642281910274,5,'0,1,5','部门查询',4,NULL,'',NULL,'sys:dept:list',0,1,1,0,'',NULL,'2024-07-06 23:29:33','2024-07-06 23:29:33',NULL),
(1809610762117369857,6,'0,1,6',' 字典查询',4,NULL,'',NULL,'sys:dict:list',0,1,1,0,'',NULL,'2024-07-06 23:30:01','2024-07-06 23:30:01',NULL),
(1809610990782435330,117,'0,1,117','日志查询',4,NULL,'',NULL,'sys:log:list',0,1,1,0,'',NULL,'2024-07-06 23:30:56','2024-07-06 23:30:56',NULL),
(1809897379755364354,0,'0','系统工具',2,NULL,'/tool','Layout',NULL,0,1,1,1,'dict',NULL,'2024-07-07 18:28:56','2024-07-07 18:28:56',NULL),
(1809971622056738818,1809897379755364354,'0,1809897379755364354','代码生成',1,'Generator','generator','tool/generator/index',NULL,NULL,NULL,1,1,'system',NULL,'2024-07-07 00:00:00','2024-07-07 23:56:38',NULL),
(1809971622056738819,1809971622056738818,'0,1,1809971622056738818','代码生成查询',4,NULL,'',NULL,'tool:generator:list',0,0,1,0,'',NULL,'2024-07-07 00:00:00','2024-07-07 00:00:00',NULL),
(1809971622056738820,1809971622056738818,'0,1,1809971622056738818','代码生成新增',4,NULL,'',NULL,'tool:generator:save',NULL,NULL,1,1,'','','2024-07-07 00:00:00','2024-07-07 00:00:00',NULL),
(1809971622056738821,1809971622056738818,'0,1,1809971622056738818','代码生成编辑',4,NULL,'',NULL,'tool:generator:update',NULL,NULL,1,2,'','','2024-07-07 00:00:00','2024-07-07 00:00:00',NULL),
(1809971622056738822,1809971622056738818,'0,1,1809971622056738818','代码生成删除',4,NULL,'',NULL,'tool:generator:delete',NULL,NULL,1,3,'','','2024-07-07 00:00:00','2024-07-07 00:00:00',NULL),
(1810274881220804609,1,'0,1','系统参数',1,'Param','param','system/param/index',NULL,NULL,NULL,1,7,'system',NULL,'2024-07-08 00:00:00','2024-07-08 19:46:37',NULL),
(1810274881220804610,1810274881220804609,'0,1809897379755364354,1810274881220804609','系统参数查询',4,NULL,'',NULL,'system:param:list',0,0,1,0,'',NULL,'2024-07-08 00:00:00','2024-07-08 00:00:00',NULL),
(1810274881220804611,1810274881220804609,'0,1809897379755364354,1810274881220804609','系统参数新增',4,NULL,'',NULL,'system:param:save',NULL,NULL,1,1,'','','2024-07-08 00:00:00','2024-07-08 00:00:00',NULL),
(1810274881220804612,1810274881220804609,'0,1809897379755364354,1810274881220804609','系统参数编辑',4,NULL,'',NULL,'system:param:update',NULL,NULL,1,2,'','','2024-07-08 00:00:00','2024-07-08 00:00:00',NULL),
(1810274881220804613,1810274881220804609,'0,1809897379755364354,1810274881220804609','系统参数删除',4,NULL,'',NULL,'system:param:delete',NULL,NULL,1,3,'','','2024-07-08 00:00:00','2024-07-08 00:00:00',NULL),
(1810489091066691585,1809897379755364354,'0,1809897379755364354','接口文档',3,'Doc','http://localhost:8080/doc.html',NULL,NULL,0,1,1,2,'el-icon-Connection',NULL,'2024-07-09 09:40:11','2024-07-09 09:40:11',NULL);

/*Table structure for table `sys_message` */

DROP TABLE IF EXISTS `sys_message`;

CREATE TABLE `sys_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识(1-已删除 0-未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统消息';

/*Data for the table `sys_message` */

/*Table structure for table `sys_param` */

DROP TABLE IF EXISTS `sys_param`;

CREATE TABLE `sys_param` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `param_name` varchar(100) NOT NULL COMMENT '参数名称',
  `param_key` varchar(100) NOT NULL COMMENT '参数键',
  `param_value` varchar(256) NOT NULL COMMENT '参数值',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识(1-已删除 0-未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统参数';

/*Data for the table `sys_param` */

insert  into `sys_param`(`id`,`param_name`,`param_key`,`param_value`,`create_by`,`create_time`,`update_by`,`update_time`,`is_deleted`) values 
(1810279472113012737,'环境','env','dev',2,'2024-07-08 19:47:14',2,'2024-07-10 09:51:08',0),
(1810854297080619010,'dd','dd','dd',2,'2024-07-10 09:51:23',NULL,'2024-07-10 09:51:26',1);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '角色名称',
  `code` varchar(32) NOT NULL COMMENT '角色编码',
  `sort` int(11) DEFAULT NULL COMMENT '显示顺序',
  `status` tinyint(1) DEFAULT '1' COMMENT '角色状态(1-正常 0-停用)',
  `data_scope` tinyint(4) DEFAULT NULL COMMENT '数据权限(0-所有数据 1-部门及子部门数据 2-本部门数据3-本人数据)',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人 ID',
  `create_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识(0-未删除 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_name` (`name`) USING BTREE COMMENT '角色名称唯一索引',
  UNIQUE KEY `uk_code` (`code`) USING BTREE COMMENT '角色编码唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`code`,`sort`,`status`,`data_scope`,`create_by`,`create_time`,`update_by`,`update_time`,`is_deleted`) values 
(1,'超级管理员','ROOT',1,1,0,NULL,'2021-05-21 14:56:51',NULL,'2018-12-23 16:00:00',0),
(2,'系统管理员','ADMIN',2,1,1,NULL,'2021-03-25 12:39:54',NULL,'2024-07-10 09:46:21',0);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  UNIQUE KEY `uk_roleid_menuid` (`role_id`,`menu_id`) USING BTREE COMMENT '角色菜单唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色和菜单关联表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values 
(2,1),
(2,2),
(2,3),
(2,4),
(2,5),
(2,6),
(2,31),
(2,32),
(2,33),
(2,70),
(2,71),
(2,72),
(2,73),
(2,74),
(2,75),
(2,76),
(2,77),
(2,78),
(2,79),
(2,81),
(2,84),
(2,85),
(2,86),
(2,87),
(2,88),
(2,105),
(2,117),
(2,118),
(2,1809597027798085634),
(2,1809610544747565058),
(2,1809610642281910274),
(2,1809610762117369857),
(2,1809610990782435330),
(2,1809897379755364354),
(2,1809971622056738818),
(2,1809971622056738819),
(2,1809971622056738820),
(2,1809971622056738821),
(2,1809971622056738822),
(2,1810274881220804609),
(2,1810274881220804610),
(2,1810274881220804611),
(2,1810274881220804612),
(2,1810274881220804613),
(2,1810489091066691585);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(11) NOT NULL,
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `gender` tinyint(1) DEFAULT '1' COMMENT '性别((1-男 2-女 0-保密)',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `avatar` varchar(255) DEFAULT '' COMMENT '用户头像',
  `mobile` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态((1-正常 0-禁用)',
  `email` varchar(128) DEFAULT NULL COMMENT '用户邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除标识(0-未删除 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `login_name` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户信息表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`nickname`,`gender`,`password`,`dept_id`,`avatar`,`mobile`,`status`,`email`,`create_time`,`create_by`,`update_time`,`update_by`,`is_deleted`) values 
(1,'root','系统管理员',0,'$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq',NULL,'','',1,'',NULL,NULL,NULL,NULL,0),
(2,'admin','系统管理员',1,'$2a$10$.rnV.NNvPIJzliF9aiM4T.pkcZSH5ox524UlwdH8J1xaYSW/f3Dme',1,'https://b0.bdstatic.com/aaa91658460731bbefcf17041aaec7e4.jpg@h_1280','',1,'',NULL,NULL,'2024-07-10 09:46:05',NULL,0);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
  UNIQUE KEY `uk_userid_roleid` (`user_id`,`role_id`) USING BTREE COMMENT '用户角色唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户和角色关联表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values 
(1,1),
(2,2),
(3,2),
(288,4),
(289,2),
(290,3),
(1809507584218677250,2),
(1809584293119320065,2);

/*Table structure for table `tool_generator` */

DROP TABLE IF EXISTS `tool_generator`;

CREATE TABLE `tool_generator` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `package_name` varchar(100) NOT NULL DEFAULT '' COMMENT '基础包名',
  `module_name` varchar(100) NOT NULL DEFAULT '' COMMENT '模块名称',
  `table_name` varchar(100) NOT NULL DEFAULT '' COMMENT '表名称',
  `table_prefix` varchar(100) DEFAULT NULL COMMENT '表前缀',
  `author` varchar(100) DEFAULT '' COMMENT '作者',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识(1-已删除 0-未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='代码生成';

/*Data for the table `tool_generator` */

insert  into `tool_generator`(`id`,`package_name`,`module_name`,`table_name`,`table_prefix`,`author`,`create_by`,`update_by`,`create_time`,`update_time`,`is_deleted`) values 
(1810148700672659458,'com.fastermaker','tool','tool_generator','tool_','fastermaker',2,2,'2024-07-08 10:59:38','2024-07-08 19:11:57',1),
(1810270551679881217,'com.fastermaker.modules','system','sys_param','sys_','fastermaker',2,2,'2024-07-08 19:11:47','2024-07-10 09:51:34',0),
(1810493454464069634,'com.fastermaker.modules','2','3','tool_','fastermaker',2,2,'2024-07-09 09:57:32','2024-07-09 09:57:38',1),
(1810845860749787137,'com.fastermaker.modules','33','33','tool_33','fastermaker',2,2,'2024-07-08 19:11:47','2024-07-10 09:17:56',1),
(1810850481920737281,'com.fastermaker.modules','33','33','tool_33','fastermaker',2,2,'2024-07-08 19:11:47','2024-07-10 09:36:19',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
