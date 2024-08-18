CREATE TABLE IF NOT EXISTS `employee_skills` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '员工的唯一标识，用于关联主表 employee',
    `ppt` BIT COMMENT '是否具备 PPT 制作技能',
    `customer_service` BIT COMMENT '是否具备客服服务技能',
    `poster` BIT COMMENT '是否具备海报设计技能',
    `album` BIT COMMENT '是否具备画册设计技能',
    `thesis` VARCHAR(255) COMMENT '关于论文写作的相关描述或标识',
    `copywriting` BIT COMMENT '是否具备文案写作技能',
    `professional_skill` VARCHAR(255) COMMENT '专业技能的相关描述或标识',
    `store_manager` BIT COMMENT '是否具备店长管理技能',
    `ppt_score` DOUBLE COMMENT 'PPT 技能的评分',
    `customer_service_score` DOUBLE COMMENT '客服技能的评分',
    `poster_score` DOUBLE COMMENT '海报设计技能的评分',
    `album_score` DOUBLE COMMENT '画册设计技能的评分',
    `thesis_score` DOUBLE COMMENT '论文写作技能的评分',
    `copywriting_score` DOUBLE COMMENT '文案写作技能的评分',
    `professional_skill_score` DOUBLE COMMENT '专业技能的评分',
    `store_manager_score` DOUBLE COMMENT '店长技能的评分',
    FOREIGN KEY (`id`) REFERENCES `employee`(`id`)
);
-- 表注释：此表用于存储员工的各项技能及对应的评分信息

ALTER TABLE `employee`
    MODIFY COLUMN `wechatNumber` VARCHAR(255) COMMENT '员工微信号' AFTER `employeeNumber`,
    MODIFY COLUMN `wechatName` VARCHAR(255) COMMENT '员工微信名' AFTER `wechatNumber`,
    MODIFY COLUMN `phoneNumber` VARCHAR(255) COMMENT '员工手机号' AFTER `wechatName`,
    MODIFY COLUMN `enterpriseWechatName` VARCHAR(255) COMMENT '员工企业微信名' AFTER `phoneNumber`,
    MODIFY COLUMN `identity` VARCHAR(255) COMMENT '员工身份' AFTER `enterpriseWechatName`,
    MODIFY COLUMN `permission` VARCHAR(255) COMMENT '员工权限' AFTER `identity`,
    MODIFY COLUMN `isReviewed` VARCHAR(255) COMMENT '是否审核过' AFTER `permission`,
    MODIFY COLUMN `employeeRating` DOUBLE COMMENT '员工评分' AFTER `isReviewed`,
    MODIFY COLUMN `remarks` VARCHAR(255) COMMENT '备注' AFTER `employeeRating`;

CREATE TABLE IF NOT EXISTS `qr_code_image` (
    `id` bigint(20),
    `qr_code_image` LONGBLOB,
    FOREIGN KEY (`id`) REFERENCES `employee`(`id`)
);





------------------
全表：
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employeeNumber` varchar(255) DEFAULT NULL COMMENT '员工工号',
  `wechatNumber` varchar(255) DEFAULT NULL COMMENT '员工微信号',
  `wechatName` varchar(255) DEFAULT NULL COMMENT '员工微信名',
  `phoneNumber` varchar(255) DEFAULT NULL COMMENT '员工手机号',
  `enterpriseWechatName` varchar(255) DEFAULT NULL COMMENT '员工企业微信名',
  `identity` varchar(255) DEFAULT NULL COMMENT '员工身份',
  `permission` varchar(255) DEFAULT NULL COMMENT '员工权限',
  `isReviewed` varchar(255) DEFAULT NULL COMMENT '是否审核过',
  `employeeRating` double DEFAULT NULL COMMENT '员工评分',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8




CREATE TABLE `employee_skills` (
  `employee_id` bigint(20) NOT NULL COMMENT '员工的唯一标识，用于关联主表 employee',
  `ppt` bit(1) DEFAULT NULL COMMENT '是否具备 PPT 制作技能',
  `customer_service` bit(1) DEFAULT NULL COMMENT '是否具备客服服务技能',
  `poster` bit(1) DEFAULT NULL COMMENT '是否具备海报设计技能',
  `album` bit(1) DEFAULT NULL COMMENT '是否具备画册设计技能',
  `thesis` varchar(255) DEFAULT NULL COMMENT '关于论文写作的相关描述或标识',
  `copywriting` bit(1) DEFAULT NULL COMMENT '是否具备文案写作技能',
  `professional_skill` varchar(255) DEFAULT NULL COMMENT '专业技能的相关描述或标识',
  `store_manager` bit(1) DEFAULT NULL COMMENT '是否具备店长管理技能',
  `ppt_score` double DEFAULT NULL COMMENT 'PPT 技能的评分',
  `customer_service_score` double DEFAULT NULL COMMENT '客服技能的评分',
  `poster_score` double DEFAULT NULL COMMENT '海报设计技能的评分',
  `album_score` double DEFAULT NULL COMMENT '画册设计技能的评分',
  `thesis_score` double DEFAULT NULL COMMENT '论文写作技能的评分',
  `copywriting_score` double DEFAULT NULL COMMENT '文案写作技能的评分',
  `professional_skill_score` double DEFAULT NULL COMMENT '专业技能的评分',
  `store_manager_score` double DEFAULT NULL COMMENT '店长技能的评分',
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8