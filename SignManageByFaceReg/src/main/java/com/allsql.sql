# 学生表
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `pri_id`   int         NOT NULL AUTO_INCREMENT,
    `id`       varchar(20) NOT NULL,
    `name`     varchar(30) NOT NULL,
    `password` varchar(30) NOT NULL,
    #班级
    #所学课程
    PRIMARY KEY (`pri_id`),
    UNIQUE KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4
  AUTO_INCREMENT = 1;

# 老师表
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`
(
    `pri_id`   int         NOT NULL AUTO_INCREMENT,
    `id`       varchar(20) NOT NULL,
    `name`     varchar(30) NOT NULL,
    `password` varchar(30) NOT NULL,
    #所教课程
    PRIMARY KEY (`pri_id`),
    UNIQUE KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4
  AUTO_INCREMENT = 1;
# 管理员表
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`
(
    `pri_id`   int         NOT NULL AUTO_INCREMENT,
    `id`       varchar(20) NOT NULL,
    `name`     varchar(30) NOT NULL,
    `password` varchar(30) NOT NULL,
    PRIMARY KEY (`pri_id`),
    UNIQUE KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4
  AUTO_INCREMENT = 1;

# 创建 URL 权限表 anon,authc 等拦截器,以及角色设置
DROP TABLE IF EXISTS `sm_action_role_filter`;
CREATE TABLE `sm_action_role_filter`
(
    `action_id`     int         NOT NULL AUTO_INCREMENT,
    `action_url`    varchar(50) NOT NULL,
    `action_filter` varchar(20) DEFAULT 'authc',
    `action_desc`   varchar(30),
    `action_role`   varchar(30) DEFAULT 'admin,teacher,student',
    PRIMARY KEY (action_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4
  AUTO_INCREMENT = 1;

# 不需要角色表

insert into student(id, name, password)
values ('04161173', '刘生玺', '123456');
insert into teacher(id, name, password)
values ('001', '王春梅', '123456');
insert into admin(id, name, password)
values ('111', '孙雪华', '123456');

# 课程表（id,名称，学分，起始时间，结束时间）
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`
(
    `course_id`     int         NOT NULL AUTO_INCREMENT,
    `course_name`   varchar(30) NOT NULL,
    `course_credit` tinyint     NOT NULL,
    `course_start`  date        NOT NULL,
    `course_end`    date        NOT NULL,
    PRIMARY KEY (`course_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4
  AUTO_INCREMENT = 1;

# insert into
# 如何分班，如何表示我自己要上的课？？人数基数是一个年级。
# 课程与学生关联表
DROP TABLE IF EXISTS `course_student`;
CREATE TABLE `course_student`
(
    `student_id` varchar(20) NOT NULL,
    `course_id`  tinyint     NOT NULL,
    PRIMARY KEY (`student_id`, `course_id`),
    FOREIGN KEY (student_id) REFERENCES student (id),
    FOREIGN KEY (course_id) REFERENCES course (course_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4
  AUTO_INCREMENT = 1;




