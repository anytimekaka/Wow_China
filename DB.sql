DROP  TABLE IF EXISTS `intern`.`user_basic`;
CREATE TABLE IF NOT EXISTS `intern`.`user_basic` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `username` VARCHAR(45) NULL COMMENT '用户名',
  `password` VARCHAR(100) NULL COMMENT '密码',
  `token` VARCHAR(100) NULL COMMENT '用户验证token',
  `userimage` VARCHAR(45) NULL COMMENT '用户头像图片名称，默认为系统自带图片，上传后用用户名重命名，沿用之前后缀',
  `realname` VARCHAR(100) NULL COMMENT '用户真实姓名',
  `university` VARCHAR(45) NULL COMMENT '学校',
  `majorid` VARCHAR(45) NULL COMMENT '所学专业id（仅此一个）',
  `industryids` VARCHAR(45) NULL COMMENT '工作行业（多选，以『,』分割）',
  `skills` VARCHAR(400) NULL COMMENT '特长技能描述',
  `location` VARCHAR(100) NULL COMMENT '当前所在地',
  `email` VARCHAR(45) NULL COMMENT 'email',
  `tel` VARCHAR(45) NULL COMMENT '手机号码',
  `certificates` VARCHAR(100) NULL COMMENT '证书',
  `linkedinid` VARCHAR(45) NULL COMMENT 'LinkedIn id',
  `linkedinusername` VARCHAR(45) NULL COMMENT 'LinkedIn username',
  `linkedinprofileurl` VARCHAR(100) NULL COMMENT 'LinkedIn profile url',
  `facebook` VARCHAR(45) NULL COMMENT 'FaceBook账号',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB COMMENT '注册用户表';

/* SELECT count(*) FROM `intern`.`user_basic` WHERE username='anytimekaka' AND password='123456'; */

DROP  TABLE IF EXISTS `intern`.`post`;
CREATE TABLE IF NOT EXISTS `intern`.`post` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Id主键',
  `title` VARCHAR(100) NULL COMMENT '标题',
  `company` VARCHAR(45) NULL COMMENT '公司',
  `website` VARCHAR(45) NULL COMMENT '公司网站',
  `industryid` INT NULL COMMENT '所属行业',
  `opento` VARCHAR(45) NULL COMMENT '面向专业',
  `headcount` INT NULL COMMENT '预计人数',
  `eligible` INT NULL COMMENT '已确定人数，不能大于预计需要人数',
  `status` INT NULL COMMENT '状态：0-进行中，1-已关闭',
  `cityid` INT NULL COMMENT '所在城市',
  `address` VARCHAR(200) NULL COMMENT '详细地址',
  `reward` VARCHAR(45) NULL COMMENT '薪水与报酬',
  `description` VARCHAR(45) NULL COMMENT '职责描述',
  `updatetime` DATE NULL COMMENT '更新时间',
  `userid` INT NULL COMMENT '发布者ID',
  `username` VARCHAR(45) NOT NULL COMMENT '发布者名称',
  `userimage` VARCHAR(45) NULL COMMENT '发布者头像图片',
  PRIMARY KEY (`id`)  COMMENT '')
  ENGINE = InnoDB COMMENT '发布的信息';
ALTER TABLE `intern`.`post` MODIFY `updatetime` DATETIME NULL COMMENT '更新时间';
ALTER TABLE `intern`.`post` DROP `username`;
ALTER TABLE `intern`.`post` DROP `userimage`;

DROP  TABLE IF EXISTS `intern`.`apply`;
CREATE TABLE IF NOT EXISTS `intern`.`apply` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `userid` INT NULL COMMENT '申请者Id',
  `postid` INT NULL COMMENT '职位信息id',
  `status` INT NULL COMMENT '申请状态（0：等待回复，1：被接受，等待对方发起面试邀请，2：已接收到面试邀请，3：已确认，4：已取消）',
  `interview` VARCHAR(200) NULL COMMENT '面试安排说明，包括时间、地点、预期需要多长时间等',
  PRIMARY KEY (`id`)  COMMENT '')
  ENGINE = InnoDB COMMENT '申请记录表';

DROP  TABLE IF EXISTS `intern`.`collection`;
CREATE TABLE IF NOT EXISTS `intern`.`collection`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '用户收藏',
  `userid` INT NULL COMMENT '用户id',
  `postid` INT NULL COMMENT 'postid',
  `posttitle` VARCHAR(100) NULL COMMENT 'post标题',
  PRIMARY KEY (`id`) COMMENT '主键',
  KEY (`userid`) COMMENT '',
  KEY (`postid`) COMMENT '')
  ENGINE = InnoDB COMMENT '用户收藏';

DROP  TABLE IF EXISTS `intern`.`message`;
CREATE TABLE IF NOT EXISTS `intern`.`message`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `userid` INT NULL COMMENT '用户id',
  `postid` INT NULL COMMENT 'postid',
  `type` INT NULL COMMENT '消息类型，0：post消息-新申请者，1：apply消息-接受，2：apply-面试邀请',
  `read` INT NULL COMMENT '是否已读，0；未读，1：已读',
  `remark` VARCHAR(100) NULL COMMENT '备注',
  PRIMARY KEY (`id`) COMMENT '主键',
  KEY (`userid`) COMMENT '',
  KEY (`postid`) COMMENT '')
  ENGINE = InnoDB COMMENT '系统消息';

DROP  TABLE IF EXISTS `intern`.`city`;
CREATE TABLE IF NOT EXISTS `intern`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '城市id',
  `cityname` VARCHAR(45) NULL COMMENT '城市名',
  `country` VARCHAR(45) NULL COMMENT '国家',
  PRIMARY KEY (`id`) COMMENT '主键')
  ENGINE = InnoDB COMMENT '城市表';

DROP  TABLE IF EXISTS `intern`.`major`;
CREATE TABLE IF NOT EXISTS `intern`.`major` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `major` VARCHAR(45) NULL COMMENT '专业名',
  PRIMARY KEY (`id`) COMMENT '主键')
  ENGINE = InnoDB COMMENT '专业';

DROP  TABLE IF EXISTS `intern`.`industry`;
CREATE TABLE IF NOT EXISTS `intern`.`industry` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `industry` VARCHAR(45) NULL COMMENT '行业和兴趣名',
  PRIMARY KEY (`id`) COMMENT '主键')
  ENGINE = InnoDB COMMENT '行业和兴趣';

INSERT INTO `industry` (`industry`) VALUES ('Administration and office support');
INSERT INTO `industry` (`industry`) VALUES ('Advertising');
INSERT INTO `industry` (`industry`) VALUES ('Arts');
INSERT INTO `industry` (`industry`) VALUES ('Media');

INSERT INTO `major` (`major`) VALUES ('Business Administration');
INSERT INTO `major` (`major`) VALUES ('Impressionist Art');

INSERT INTO `city` (`cityname`) VALUES ('shanghai');
INSERT INTO `city` (`cityname`) VALUES ('beijing');
INSERT INTO `city` (`cityname`) VALUES ('Hongkong');