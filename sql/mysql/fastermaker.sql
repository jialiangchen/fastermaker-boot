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
) ENGINE=InnoDB AUTO_INCREMENT=1809585628325683203 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门表';

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
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除(1-删除，0-未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1809586616012972034 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典类型表';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`name`,`code`,`status`,`remark`,`create_time`,`update_time`,`is_deleted`) values 
(1,'性别','gender',1,NULL,'2019-12-06 19:03:32','2024-06-22 21:14:47',0),
(1809586616012972033,'32','22',0,NULL,'2024-07-06 21:54:04','2024-07-07 16:21:17',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=1810500237475794947 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统日志表';

/*Data for the table `sys_log` */

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
(1,0,'0','系统管理',2,'','/system','Layout',NULL,NULL,NULL,1,1,'system','/system/user','2021-08-28 09:12:21','2024-06-24 23:49:04',NULL),
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
(1810279472113012737,'环境','env','dev',2,'2024-07-08 19:47:14',2,'2024-07-08 21:33:44',0);

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
) ENGINE=InnoDB AUTO_INCREMENT=1809584842539589635 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`code`,`sort`,`status`,`data_scope`,`create_by`,`create_time`,`update_by`,`update_time`,`is_deleted`) values 
(1,'超级管理员','ROOT',1,1,0,NULL,'2021-05-21 14:56:51',NULL,'2018-12-23 16:00:00',0),
(2,'系统管理员','ADMIN',2,1,1,NULL,'2021-03-25 12:39:54',NULL,NULL,0);

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
(2,'admin','系统管理员',1,'$2a$10$xKbHTw3ec7KrQyr5Q7ED4et6wFe7X9biguNG8Sb1bFtXq2../lBnO',1,'https://b0.bdstatic.com/aaa91658460731bbefcf17041aaec7e4.jpg@h_1280','',1,'',NULL,NULL,NULL,NULL,0);

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
(1810270551679881217,'com.fastermaker.modules','system','sys_param','sys_','fastermaker',2,2,'2024-07-08 19:11:47','2024-07-08 19:22:03',0),
(1810493454464069634,'com.fastermaker.modules','2','3','tool_','fastermaker',2,2,'2024-07-09 09:57:32','2024-07-09 09:57:38',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
