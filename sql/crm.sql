/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2018-10-29 21:52:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `job_id` bigint(20) DEFAULT NULL,
  `source_id` bigint(20) DEFAULT NULL,
  `seller_id` bigint(20) DEFAULT NULL,
  `input_user_id` bigint(20) DEFAULT NULL,
  `input_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '黄蓉', '22', '0', '14266', '543543', '4', '6', '1', '1', '2018-07-01 15:41:42', '2');
INSERT INTO `customer` VALUES ('2', '东方白', '18', '0', '16574', '435435', '3', '6', '2', '1', '2018-08-03 15:17:57', '1');
INSERT INTO `customer` VALUES ('3', '周粥', '25', '0', '64564', '654654', '1', '6', '2', '1', '2018-08-03 15:56:30', '0');
INSERT INTO `customer` VALUES ('4', '歪果仁', '17', '1', '45435', '543535', '2', '7', '1', '1', '2018-08-03 16:24:09', '3');
INSERT INTO `customer` VALUES ('5', '杏鲍菇', '30', '1', '12518', '112232', '3', '7', '2', '3', '2018-08-30 15:44:40', '0');
INSERT INTO `customer` VALUES ('6', '张全蛋', '32', '1', '55837', '952773', '2', '7', '3', '3', '2018-08-30 15:45:26', '0');
INSERT INTO `customer` VALUES ('7', 'papi酱', '30', '0', '33446', '987657', '3', '8', '2', '3', '2018-08-30 15:46:21', '1');

-- ----------------------------
-- Table structure for customertracehistory
-- ----------------------------
DROP TABLE IF EXISTS `customertracehistory`;
CREATE TABLE `customertracehistory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trace_time` date DEFAULT NULL,
  `trace_details` varchar(255) DEFAULT NULL,
  `trace_type_id` bigint(20) DEFAULT NULL,
  `trace_result` int(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `input_user_id` bigint(20) DEFAULT NULL,
  `input_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customertracehistory
-- ----------------------------
INSERT INTO `customertracehistory` VALUES ('1', '2018-08-02', '无人接听电话，联系不上', '9', '2', '无人接听电话，联系不上', '1', '1', '2018-08-03 17:27:20');
INSERT INTO `customertracehistory` VALUES ('2', '2018-08-04', '考虑中', '10', '2', '考虑中', '4', '1', '2018-08-04 15:49:18');
INSERT INTO `customertracehistory` VALUES ('3', '2018-08-06', '了解使用感受', '12', '3', '了解使用感受', '4', '1', '2018-08-04 18:40:00');

-- ----------------------------
-- Table structure for customertransfer
-- ----------------------------
DROP TABLE IF EXISTS `customertransfer`;
CREATE TABLE `customertransfer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  `operate_time` datetime DEFAULT NULL,
  `old_seller_id` bigint(20) DEFAULT NULL,
  `new_seller_id` bigint(20) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customertransfer
-- ----------------------------
INSERT INTO `customertransfer` VALUES ('1', '5', '1', '2018-08-31 13:38:01', '3', '2', '话不投机');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '总经办', 'Manager Department');
INSERT INTO `department` VALUES ('2', '人力资源部', 'Human Resources Department');
INSERT INTO `department` VALUES ('3', '采购部', 'Order Department');
INSERT INTO `department` VALUES ('4', '销售部', 'Sales Department');
INSERT INTO `department` VALUES ('5', '财务部', 'Finance Department');
INSERT INTO `department` VALUES ('6', '技术部', 'Technolog Department ');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'admin', 'e00cf25ad42683b3df678c61f42c6bda', 'admin@abc.com', '20', '', '1');
INSERT INTO `employee` VALUES ('2', '赵总', 'e3b418e0ce4c24facedac2a7c38e07c1', 'xx@xx.com', '35', '\0', '2');
INSERT INTO `employee` VALUES ('3', '赵一明', '06e51d5ad307f0de40522f85d02e4192', 'xx@xx.com', '25', '\0', '4');
INSERT INTO `employee` VALUES ('4', '李总', '3f0ee07e0615324740d615ac638a1c42', 'xx@xx.com', '35', '\0', '4');
INSERT INTO `employee` VALUES ('5', '李四明', 'dd55dcf2550ba7af0b8adbf65db1cd6e', 'xx@xx.com', '25', '\0', '3');
INSERT INTO `employee` VALUES ('6', '周总', 'd6b3bd5c4894bbf6dd7c52c2a0434e02', 'xx@xx.com', '35', '\0', '5');
INSERT INTO `employee` VALUES ('7', '周五明', '0d93cc2b138262a79272c9ee5a3587b4', 'xx@xx.com', '25', '\0', '2');
INSERT INTO `employee` VALUES ('8', '吴总', 'dec8018fc83e031d9914f9d94a362dba', 'xx@xx.com', '35', '\0', '6');

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role` (
  `employee_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_role
-- ----------------------------
INSERT INTO `employee_role` VALUES ('4', '3');
INSERT INTO `employee_role` VALUES ('7', '2');
INSERT INTO `employee_role` VALUES ('2', '3');

-- ----------------------------
-- Table structure for formalcustomer
-- ----------------------------
DROP TABLE IF EXISTS `formalcustomer`;
CREATE TABLE `formalcustomer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vdate` datetime DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `curriculum_id` bigint(20) DEFAULT NULL,
  `seller_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formalcustomer
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `expression` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '删除员工', 'employee:delete');
INSERT INTO `permission` VALUES ('2', '员工列表', 'employee:list');
INSERT INTO `permission` VALUES ('3', '编辑员工', 'employee:input');
INSERT INTO `permission` VALUES ('4', '更新/保存员工', 'employee:saveOrUpdate');
INSERT INTO `permission` VALUES ('5', '权限列表', 'permission:list');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '人力资源主管', 'HR_MGR');
INSERT INTO `role` VALUES ('2', '客户专员', 'SALEMAN');
INSERT INTO `role` VALUES ('3', '客户经理', 'CLIENT_MGR');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '2');
INSERT INTO `role_permission` VALUES ('1', '3');
INSERT INTO `role_permission` VALUES ('1', '4');

-- ----------------------------
-- Table structure for systemdictionary
-- ----------------------------
DROP TABLE IF EXISTS `systemdictionary`;
CREATE TABLE `systemdictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `intro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemdictionary
-- ----------------------------
INSERT INTO `systemdictionary` VALUES ('1', 'job', '职业', '做什么的');
INSERT INTO `systemdictionary` VALUES ('2', 'source', '来源', '客户来源渠道');
INSERT INTO `systemdictionary` VALUES ('3', 'communicationMethod', '交流方式', '跟踪潜在学员的方式');
INSERT INTO `systemdictionary` VALUES ('4', 'curriculum', '课程名称', '学科名称');

-- ----------------------------
-- Table structure for systemdictionaryitem
-- ----------------------------
DROP TABLE IF EXISTS `systemdictionaryitem`;
CREATE TABLE `systemdictionaryitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `sequence` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemdictionaryitem
-- ----------------------------
INSERT INTO `systemdictionaryitem` VALUES ('1', '1', '老师', '2');
INSERT INTO `systemdictionaryitem` VALUES ('2', '1', '司机', '1');
INSERT INTO `systemdictionaryitem` VALUES ('3', '1', '老板', '3');
INSERT INTO `systemdictionaryitem` VALUES ('4', '1', '学生', '4');
INSERT INTO `systemdictionaryitem` VALUES ('5', '1', '秘书', '5');
INSERT INTO `systemdictionaryitem` VALUES ('6', '2', '老学员推荐', '3');
INSERT INTO `systemdictionaryitem` VALUES ('7', '2', '自投罗网', '2');
INSERT INTO `systemdictionaryitem` VALUES ('8', '2', '街头小广告', '1');
INSERT INTO `systemdictionaryitem` VALUES ('9', '3', '微信', '5');
INSERT INTO `systemdictionaryitem` VALUES ('10', '3', '营销QQ', '3');
INSERT INTO `systemdictionaryitem` VALUES ('11', '3', '来电咨询', '2');
INSERT INTO `systemdictionaryitem` VALUES ('12', '3', '去电跟踪', '4');
INSERT INTO `systemdictionaryitem` VALUES ('13', '3', '短信', '1');
INSERT INTO `systemdictionaryitem` VALUES ('14', '4', 'Java', '3');
INSERT INTO `systemdictionaryitem` VALUES ('15', '4', '前端H5', '2');
INSERT INTO `systemdictionaryitem` VALUES ('16', '4', '平面设计', '1');
