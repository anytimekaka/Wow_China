DROP  TABLE IF EXISTS `intern`.`user_basic`;
CREATE TABLE IF NOT EXISTS `intern`.`user_basic` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `username` VARCHAR(45) DEFAULT '' COMMENT '用户名',
  `password` VARCHAR(100) DEFAULT '' COMMENT '密码',
  `token` VARCHAR(100) DEFAULT '' COMMENT '用户验证token',
  `userimage` VARCHAR(45) DEFAULT '' COMMENT '用户头像图片名称，默认为系统自带图片，上传后用用户名重命名，沿用之前后缀',
  `realname` varchar(100) DEFAULT NULL COMMENT '用户真实姓名',
  `sex` int(4) default 0 COMMENT '性别',
  `birthday` VARCHAR(20) DEFAULT '' COMMENT '生日',
  `experience` VARCHAR(500) DEFAULT '' COMMENT '经验',
  `university` VARCHAR(45) DEFAULT '' COMMENT '学校',
  `majorid` VARCHAR(45) DEFAULT '' COMMENT '所学专业id（仅此一个）',
  `industryids` VARCHAR(45) DEFAULT '' COMMENT '工作行业（多选，以『,』分割）',
  `skills` VARCHAR(400) DEFAULT '' COMMENT '特长技能描述',
  `location` VARCHAR(100) DEFAULT '' COMMENT '当前所在地',
  `email` VARCHAR(45) DEFAULT '' COMMENT 'email',
  `tel` VARCHAR(45) DEFAULT '' COMMENT '手机号码',
  `certificates` VARCHAR(100) DEFAULT '' COMMENT '证书',
  `linkedinid` VARCHAR(45) DEFAULT '' COMMENT 'LinkedIn id',
  `linkedinusername` VARCHAR(45) DEFAULT '' COMMENT 'LinkedIn username',
  `linkedinprofileurl` VARCHAR(100) DEFAULT '' COMMENT 'LinkedIn profile url',
  `facebook` VARCHAR(45) DEFAULT '' COMMENT 'FaceBook账号',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB COMMENT '注册用户表';

DROP  TABLE IF EXISTS `intern`.`post`;
CREATE TABLE IF NOT EXISTS `intern`.`post` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Id主键',
  `title` VARCHAR(100) DEFAULT '' COMMENT '标题',
  `company` VARCHAR(45) DEFAULT '' COMMENT '公司',
  `website` VARCHAR(45) DEFAULT '' COMMENT '公司网站',
  `industryid` INT DEFAULT 0 COMMENT '所属行业',
  `opento` VARCHAR(45) DEFAULT '' COMMENT '面向专业',
  `headcount` INT DEFAULT 1 COMMENT '预计人数',
  `eligible` INT DEFAULT 0 COMMENT '已确定人数，不能大于预计需要人数',
  `status` INT DEFAULT 0 COMMENT '状态：0-进行中，1-已关闭',
  `cityid` INT DEFAULT 1 COMMENT '所在城市',
  `address` VARCHAR(200) DEFAULT '' COMMENT '详细地址',
  `reward` VARCHAR(45) DEFAULT '' COMMENT '薪水与报酬',
  `description` VARCHAR(1000) DEFAULT '' COMMENT '职责描述',
  `deadline` VARCHAR(20) DEFAULT '' COMMENT '截止时间',
  `updatetime` DATETIME NULL COMMENT '更新时间',
  `userid` INT NULL COMMENT '发布者ID',
  PRIMARY KEY (`id`)  COMMENT '')
  ENGINE = InnoDB COMMENT '发布的信息';

DROP  TABLE IF EXISTS `intern`.`apply`;
CREATE TABLE IF NOT EXISTS `intern`.`apply` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `userid` INT NULL COMMENT '申请者Id',
  `postid` INT NULL COMMENT 'post id',
  PRIMARY KEY (`id`)  COMMENT '')
  ENGINE = InnoDB COMMENT '申请记录表';

DROP  TABLE IF EXISTS `intern`.`collection`;
CREATE TABLE IF NOT EXISTS `intern`.`collection`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '用户收藏',
  `userid` INT NULL COMMENT '用户id',
  `postid` INT NULL COMMENT 'postid',
  `posttitle` VARCHAR(100) DEFAULT '' COMMENT 'post标题',
  PRIMARY KEY (`id`) COMMENT '主键',
  KEY (`userid`) COMMENT '',
  KEY (`postid`) COMMENT '')
  ENGINE = InnoDB COMMENT '用户收藏';

DROP  TABLE IF EXISTS `intern`.`message`;
CREATE TABLE IF NOT EXISTS `intern`.`message`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `relatedid` INT NULL COMMENT '关联另一个消息的id',
  `userid` INT NULL COMMENT '用户id',
  `postid` INT NULL COMMENT '消息关联的post id',
  `status` INT NULL COMMENT '发起者状态：未回复1，已回复2，申请者状态：已申请3，接受4，拒绝5',
  `updatetime` DATETIME NULL COMMENT '消息的更新时间',
  PRIMARY KEY (`id`) COMMENT '主键',
  KEY (`userid`) COMMENT '',
  KEY (`postid`) COMMENT '')
  ENGINE = InnoDB COMMENT '消息';

DROP  TABLE IF EXISTS `intern`.`city`;
CREATE TABLE IF NOT EXISTS `intern`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '城市id',
  `cityname` VARCHAR(45) DEFAULT '' COMMENT '城市名',
  `country` VARCHAR(45) DEFAULT '' COMMENT '国家',
  PRIMARY KEY (`id`) COMMENT '主键')
  ENGINE = InnoDB COMMENT '城市表';

DROP  TABLE IF EXISTS `intern`.`major`;
CREATE TABLE IF NOT EXISTS `intern`.`major` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `major` VARCHAR(45) DEFAULT '' COMMENT '专业名',
  PRIMARY KEY (`id`) COMMENT '主键')
  ENGINE = InnoDB COMMENT '专业';

DROP  TABLE IF EXISTS `intern`.`industry`;
CREATE TABLE IF NOT EXISTS `intern`.`industry` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `industry` VARCHAR(45) DEFAULT '' COMMENT '行业和兴趣名',
  PRIMARY KEY (`id`) COMMENT '主键')
  ENGINE = InnoDB COMMENT '行业和兴趣';

DROP  TABLE IF EXISTS `intern`.`major_post_map`;
CREATE TABLE IF NOT EXISTS `intern`.`major_post_map` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `majorid` INT NULL COMMENT '面向专业',
  `postid` INT NULL COMMENT '关联的postid',
  PRIMARY KEY (`id`) COMMENT '主键')
  ENGINE = InnoDB COMMENT '面向专业与post的映射表';

alter table `post` add `postimage` varchar(200) COMMENT 'post图片名称';

INSERT INTO `industry` (`industry`) VALUES ('Administration and office support');
INSERT INTO `industry` (`industry`) VALUES ('Advertising');
INSERT INTO `industry` (`industry`) VALUES ('Arts');
INSERT INTO `industry` (`industry`) VALUES ('Media');
INSERT INTO `industry` (`industry`) VALUES ('Creative Industries');
INSERT INTO `industry` (`industry`) VALUES ('Education and training');
INSERT INTO `industry` (`industry`) VALUES ('Entertainment & Recreation');
INSERT INTO `industry` (`industry`) VALUES ('Hospitality and tourism');
INSERT INTO `industry` (`industry`) VALUES ('Marketing and Communications');
INSERT INTO `industry` (`industry`) VALUES ('NGOs and Charities');
INSERT INTO `industry` (`industry`) VALUES ('Research & Academic');
INSERT INTO `industry` (`industry`) VALUES ('Science and Technology');
INSERT INTO `industry` (`industry`) VALUES ('Sports Recreation');
INSERT INTO `industry` (`industry`) VALUES ('Transport and Logistics');
INSERT INTO `industry` (`industry`) VALUES ('Travel & Tourism');
INSERT INTO `industry` (`industry`) VALUES ('Acting & Modeling');
INSERT INTO `industry` (`industry`) VALUES ('Photography');
INSERT INTO `industry` (`industry`) VALUES ('Others');

INSERT INTO `major` (`major`) VALUES ('Philosophy');
INSERT INTO `major` (`major`) VALUES ('Logic');
INSERT INTO `major` (`major`) VALUES ('Ethics');
INSERT INTO `major` (`major`) VALUES ('Aesthetics');
INSERT INTO `major` (`major`) VALUES ('Science of Religion');
INSERT INTO `major` (`major`) VALUES ('Economics');
INSERT INTO `major` (`major`) VALUES ('Public Finance');
INSERT INTO `major` (`major`) VALUES ('Statistics');
INSERT INTO `major` (`major`) VALUES ('Law');
INSERT INTO `major` (`major`) VALUES ('Diplomacy');
INSERT INTO `major` (`major`) VALUES ('Education');
INSERT INTO `major` (`major`) VALUES ('Psychology');

INSERT INTO `city` (`cityname`,`country`) VALUES ('Shanghai','China');
INSERT INTO `city` (`cityname`,`country`) VALUES ('Beijing','China');
INSERT INTO `city` (`cityname`,`country`) VALUES ('Hangzhou','China');
INSERT INTO `city` (`cityname`,`country`) VALUES ('Others','China');
