/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : accounts

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2014-04-02 18:46:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_comsume_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_comsume_record`;
CREATE TABLE `t_comsume_record` (
  `id` varchar(32) NOT NULL,
  `createDate` date DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `comsume_type` varchar(32) DEFAULT NULL,
  `comsume_money` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comsume_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_comsume_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_comsume_type`;
CREATE TABLE `t_comsume_type` (
  `id` varchar(32) NOT NULL,
  `createDate` date DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comsume_type
-- ----------------------------

-- ----------------------------
-- Table structure for `t_income`
-- ----------------------------
DROP TABLE IF EXISTS `t_income`;
CREATE TABLE `t_income` (
  `id` varchar(32) NOT NULL,
  `createDate` date DEFAULT NULL,
  `sumMoney` double DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_income
-- ----------------------------

-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `permission_id` varchar(32) NOT NULL DEFAULT '',
  `permission_name` varchar(100) DEFAULT NULL,
  `permission_type` int(11) DEFAULT NULL COMMENT '资源类型，1为URL，2为页面表单',
  `request_method` char(1) DEFAULT NULL COMMENT '请求URL的method,G为GET,P为POST,A为支持所有请求',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('p1', '/user/getUser', '1', 'A');
INSERT INTO `t_permission` VALUES ('p2', '/user/addUser', '1', 'A');
INSERT INTO `t_permission` VALUES ('p3', '/user/updateUser', '1', 'A');
INSERT INTO `t_permission` VALUES ('p4', 'user:update', '2', null);
INSERT INTO `t_permission` VALUES ('p6', '/user/delUser', '1', 'A');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` varchar(32) NOT NULL DEFAULT '',
  `role_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('role1', 'admin');
INSERT INTO `t_role` VALUES ('role2', 'user1');

-- ----------------------------
-- Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `role_id` varchar(32) NOT NULL DEFAULT '',
  `permission_id` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('role1', 'p1');
INSERT INTO `t_role_permission` VALUES ('role1', 'p2');
INSERT INTO `t_role_permission` VALUES ('role1', 'p3');
INSERT INTO `t_role_permission` VALUES ('role1', 'p4');
INSERT INTO `t_role_permission` VALUES ('role1', 'p5');
INSERT INTO `t_role_permission` VALUES ('role1', 'p6');
INSERT INTO `t_role_permission` VALUES ('role2', 'p1');
INSERT INTO `t_role_permission` VALUES ('role2', 'p2');
INSERT INTO `t_role_permission` VALUES ('role2', 'p3');
INSERT INTO `t_role_permission` VALUES ('role2', 'p4');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('admin', 'a');
INSERT INTO `t_user` VALUES ('lisi', '1');
INSERT INTO `t_user` VALUES ('zhang3', '1');

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `username` varchar(20) NOT NULL DEFAULT '',
  `role_id` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`username`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('admin', 'role1');
INSERT INTO `t_user_role` VALUES ('admin', 'role2');
INSERT INTO `t_user_role` VALUES ('zhang3', 'role2');
