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
DROP TABLE IF EXISTS `sm_role`;

# 考虑 专业，班级
# 专业表。这里简化了一下，没有再进行学院等的划分
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`
(
    `major_id`   int         NOT NULL AUTO_INCREMENT,
    `major_name` varchar(30) NOT NULL,
    `major_desc` varchar(30) NOT NULL,
    PRIMARY KEY (`major_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4
  AUTO_INCREMENT = 1;
insert into major(major_name, major_desc)
values ('计算机科学与技术', '');
insert into major(major_name, major_desc)
values ('软件工程', '');
insert into major(major_name, major_desc)
values ('大数据', '');
insert into major(major_name, major_desc)
values ('网络工程', '');

# 班级表
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`
(
    `class_id`   int         NOT NULL AUTO_INCREMENT,
    `class_name` varchar(30) NOT NULL,
    `class_desc` varchar(30) NOT NULL,
    PRIMARY KEY (`class_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4
  AUTO_INCREMENT = 1;

insert into class(class_name, class_desc)
values ('1606', '');
insert into class(class_name, class_desc)
values ('1607', '');
insert into class(class_name, class_desc)
values ('1601', '');
insert into class(class_name, class_desc)
values ('1509', '');

# 学生表
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `pri_id`        int            NOT NULL AUTO_INCREMENT,
    #下面这个字段是学号
    `id`            varchar(20)    NOT NULL,
    `name`          varchar(30)    NOT NULL,
    `password`      varchar(30)    NOT NULL,
    #专业，班级
    `major_id`      int            NOT NULL,
    `class_id`      int            NOT NULL,
    `identity_card` varchar(20)    NOT NULL,
    `sex`           enum ('男','女') NOT NULL,
    PRIMARY KEY (`pri_id`),
    UNIQUE KEY (`id`),
    FOREIGN KEY (major_id) REFERENCES major (major_id),
    FOREIGN KEY (class_id) REFERENCES class (class_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4
  AUTO_INCREMENT = 1;

insert into student(id, name, password, major_id, class_id, identity_card, sex)
values ('04161173', '刘生玺', '111', 1, 3, '620123199802285118', '男');
insert into student(id, name, password, major_id, class_id, identity_card, sex)
values ('04161172', '刘玉洁', '111', 2, 1, '621123196602285117', '女');
insert into student(id, name, password, major_id, class_id, identity_card, sex)
values ('04161177', '张良', '111', 4, 4, '620123199802286332', '男');
insert into student(id, name, password, major_id, class_id, identity_card, sex)
values ('04161178', '李立', '111', 3, 1, '720123199802286666', '男');
insert into student(id, name, password, major_id, class_id, identity_card, sex)
values ('04161179', '王晓', '111', 1, 1, '623123200002286445', '女');

INSERT INTO student(id, name, password, major_id, class_id)
VALUES ((SELECT MAX(id) FROM student temp) + 1, '李进', '111', 1, 4);

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

insert into teacher(id, name, password)
values ('001', '王春梅', '111');
insert into teacher(id, name, password)
values ('002', '刘老师', '111');

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


insert into admin(id, name, password)
values ('111', '孙雪华', '111');


# 课程表（id,名称，课时，学分，起始时间，结束时间,课程名也是唯一的）
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`
(
    `course_id`     int         NOT NULL AUTO_INCREMENT,
    `course_name`   varchar(30) NOT NULL,
    `course_times`  tinyint     NOT NULL,
    `course_credit` tinyint     NOT NULL,
    `course_start`  date        NOT NULL,
    `course_end`    date        NOT NULL,
    PRIMARY KEY (`course_id`),
    UNIQUE KEY (`course_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4
  AUTO_INCREMENT = 1;
#默认 3 课时
insert into course(course_name, course_times, course_credit, course_start, course_end)
values ('嵌入式实验', 3, 4, '2019-1-2', '2020-1-2');
insert into course(course_name, course_times, course_credit, course_start, course_end)
values ('课程设计实验', 3, 4, '2019-1-2', '2020-1-2');
insert into course(course_name, course_times, course_credit, course_start, course_end)
values ('电路板实验', 3, 4, '2019-1-2', '2020-1-2');
insert into course(course_name, course_times, course_credit, course_start, course_end)
values ('大物实验', 3, 4, '2019-1-2', '2020-1-2');
insert into course(course_name, course_times, course_credit, course_start, course_end)
values ('英语实验', 3, 4, '2019-1-2', '2020-1-2');

# 如何分班，如何表示我自己要上的课？？人数基数是一个年级。
# 课程与学生关联表
# insert into student(id, name, password, major_id, class_id)
# values ('04161173', '刘生玺', '111', 1, 3);
# insert into student(id, name, password, major_id, class_id)
# values ('04161172', '刘玉洁', '111', 2, 1);
# insert into student(id, name, password, major_id, class_id)
# values ('04161177', '张良', '111', 4, 4);
# insert into student(id, name, password, major_id, class_id)
# values ('04161178', '李立', '111', 3, 1);
# insert into student(id, name, password, major_id, class_id)
# values ('04161179', '王晓', '111', 1, 1);
DROP TABLE IF EXISTS `course_student`;
CREATE TABLE `course_student`
(
    `student_id` varchar(20) NOT NULL,
    `course_id`  int         NOT NULL,
    PRIMARY KEY (`student_id`, `course_id`),
    FOREIGN KEY (student_id) REFERENCES student (id),
    FOREIGN KEY (course_id) REFERENCES course (course_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;
# 测试阶段：我上两节课
# 刘贵财上两节课
insert into course_student(student_id, course_id)
values ('04161173', 2);
insert into course_student(student_id, course_id)
values ('04161173', 3);
insert into course_student(student_id, course_id)
values ('04161177', 3);
insert into course_student(student_id, course_id)
values ('04161178', 3);
insert into course_student(student_id, course_id)
values ('04161179', 3);
insert into course_student(student_id, course_id)
values ('04161172', 1);
insert into course_student(student_id, course_id)
values ('04161172', 5);

# 课程与老师关联表
DROP TABLE IF EXISTS `course_teacher`;
CREATE TABLE `course_teacher`
(
    `teacher_id` varchar(20) NOT NULL,
    `course_id`  int         NOT NULL,
    PRIMARY KEY (`teacher_id`, `course_id`),
    FOREIGN KEY (teacher_id) REFERENCES teacher (id),
    FOREIGN KEY (course_id) REFERENCES course (course_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;
# 测试阶段：王春梅上两节课(电路板实验，大物实验)
# 刘老师上两节课(嵌入式实验，英语实验)
insert into course_teacher(teacher_id, course_id)
values ('001', 3);
insert into course_teacher(teacher_id, course_id)
values ('001', 4);
insert into course_teacher(teacher_id, course_id)
values ('002', 1);
insert into course_teacher(teacher_id, course_id)
values ('002', 5);


# 课程上课时间表(timestamp 只到 2038 年)
DROP TABLE IF EXISTS `sm_course_time`;
CREATE TABLE `sm_course_time`
(
    `id`                     int       NOT NULL AUTO_INCREMENT,
    `course_id`              int       NOT NULL,
    `course_start_timestamp` timestamp NOT NULL,
    `course_end_timestamp`   timestamp NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (course_id) REFERENCES course (course_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4
  AUTO_INCREMENT = 1;
#嵌入式实验课程表
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (1, '2020-4-4 8:00:00', '2020-4-4 10:00:00');
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (1, '2020-4-5 10:00:00', '2020-4-5 12:00:00');
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (1, '2020-4-7 13:00:00', '2020-4-7 16:00:00');
#课程设计实验课程表
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (2, '2020-4-4 8:00:00', '2020-4-4 10:00:00');
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (2, '2020-4-5 10:00:00', '2020-4-5 12:00:00');
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (2, '2020-4-7 13:00:00', '2020-4-7 16:00:00');
#电路板实验课程表
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (3, '2020-4-4 8:00:00', '2020-4-4 10:00:00');
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (3, '2020-4-5 10:00:00', '2020-4-5 12:00:00');
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (3, '2020-4-7 13:00:00', '2020-4-7 16:00:00');
#大物实验课程表
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (4, '2020-4-4 8:00:00', '2020-4-4 10:00:00');
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (4, '2020-4-5 10:00:00', '2020-4-5 12:00:00');
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (4, '2020-4-7 13:00:00', '2020-4-7 16:00:00');
#英语实验课程表
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (5, '2020-4-4 8:00:00', '2020-4-4 10:00:00');
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (5, '2020-4-5 10:00:00', '2020-4-5 12:00:00');
insert into sm_course_time(course_id, course_start_timestamp, course_end_timestamp)
values (5, '2020-4-7 13:00:00', '2020-4-7 16:00:00');

SELECT course_id,
       course_start_timestamp,
       course_end_timestamp,
       UNIX_TIMESTAMP(course_start_timestamp)
FROM sm_course_time;

# 实现考勤功能的表（迟到，考勤成功，未考勤）
# 所有课程学生的考勤表(学生id)
# varbinary 最小单元是字节以后可以用其代替，以获得更多内存空间
# char[] 我们用 0 1 2 表示 未考勤，考勤成功，迟到，后续还可以加入更多(课时不可能超过 100 节)
DROP TABLE IF EXISTS `sm_sign_case`;
CREATE TABLE `sm_sign_case`
(
    `sign_case_id`     int         NOT NULL AUTO_INCREMENT,
    `course_id`        int         NOT NULL,
    `student_id`       varchar(20) NOT NULL,
    `sign_case_bitmap` char(100)   NOT NULL DEFAULT (REPEAT('0', 100)),
    PRIMARY KEY (`sign_case_id`),
    FOREIGN KEY (student_id) REFERENCES student (id),
    FOREIGN KEY (course_id) REFERENCES course (course_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4
  AUTO_INCREMENT = 1;

insert into sm_sign_case(course_id, student_id)
values (3, '04161173');

insert into sm_sign_case(course_id, student_id, sign_case_bitmap)
values (2, '04161173', '012');


# 课程设计实验考勤表
#待完成
# 电路板实验考勤表
#待完成

#测试阶段数据
DROP TABLE IF EXISTS `test_111`;
CREATE TABLE `test_111`
(
    `id`           int         NOT NULL AUTO_INCREMENT,
    `test_varchar` varchar(20) NOT NULL AUTO_INCREMENT,
    `name`         varchar(30) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4
  AUTO_INCREMENT = 1;

insert into test_111(test_varchar, name)
values ('04161173', 'vbdfvb');
insert into test_111(name)
values ('vbdfvb');
insert into test_111(name)
values ('vbdfvb');
insert into test_111(name)
values ('vbdfvb');



DROP TABLE IF EXISTS `test_222`;
CREATE TABLE `test_222`
(
    id   int primary key auto_increment,
    name varchar(90)  not null,
    val  varbinary(6) not null default 0
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4
  AUTO_INCREMENT = 1;

insert into test_222(name)
values ('刘生玺');

#今天不签，明天不签，后天签，
# 需要有 直接设置某一位，默认设置所有为0。的操作。
DROP TABLE IF EXISTS `vbinary`;
create table vbinary
(
    id   int primary key auto_increment,
    name varchar(90)  not null,
    val  varbinary(6) not null default 0
) engine = innodb
  default charset = utf8mb4;

insert into vbinary(name)
values ('123321');

show variables like '%time_zone%';





