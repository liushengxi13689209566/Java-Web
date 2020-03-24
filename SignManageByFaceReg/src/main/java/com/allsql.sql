# 学生表
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `student_id` int         NOT NULL AUTO_INCREMENT,
    `name`       varchar(30) NOT NULL,
    `password`   varchar(40) NOT NULL,
    PRIMARY KEY (`student_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1;
# 老师表
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`
(
    `teacher_id` int         NOT NULL AUTO_INCREMENT,
    `name`       varchar(30) NOT NULL,
    `password`   varchar(40) NOT NULL,
    PRIMARY KEY (`teacher_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1;
# 管理员表
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`
(
    `admin_id` int         NOT NULL AUTO_INCREMENT,
    `name`     varchar(30) NOT NULL,
    `password` varchar(40) NOT NULL,
    PRIMARY KEY (`admin_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1;