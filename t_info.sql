/*
Edit by dkd
last time 2018-12-28
*/

DROP DATABASE IF EXISTS `t_info`;

create database t_info default charset=utf8;
use t_info;



CREATE TABLE account (
  name varchar(20) NOT NULL,
  password varchar(20) DEFAULT NULL,
  PRIMARY KEY (`name`)
) default charset=utf8;


INSERT INTO account VALUES ('q112', '123123');

SELECT * FROM account;




CREATE TABLE answer (
  answer_classes varchar(20) DEFAULT NULL,
  answer_stu_num float(8,2) DEFAULT NULL,
  answer_weeks float(8,2) DEFAULT NULL,
  answer_huanjie float(8,2) DEFAULT NULL,
  answer_result float(8,2) DEFAULT NULL
) default charset=utf8;


INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');
INSERT INTO answer VALUES ('软工文1、2班', '2.00', '0.12', '1.00', '0.25');

SELECT * FROM answer;




CREATE TABLE design (
  design_course_name varchar(20) DEFAULT NULL,
  design_course_classes varchar(20) DEFAULT NULL,
  design_course_weeks float(8,2) DEFAULT NULL,
  design_course_zhidao float(8,2) DEFAULT NULL,
  design_course_week_hour float(8,2) DEFAULT NULL,
  design_course_zhuanye float(8,2) DEFAULT NULL,
  design_course_huanjie float(8,2) DEFAULT NULL,
  design_course_result float(8,2) DEFAULT NULL
) default charset=utf8;


INSERT INTO design VALUES ('JAVA程序设计', '电商1、2班', '2.00', '1.20', '16.00', '1.10', '1.00', '42.24');
INSERT INTO design VALUES ('JAVA程序设计', '电商1、2班', '2.00', '1.20', '16.00', '1.10', '1.00', '42.24');
INSERT INTO design VALUES ('JAVA程序设计', '电商1、2班', '2.00', '1.20', '16.00', '1.10', '1.00', '42.24');
INSERT INTO design VALUES ('JAVA程序设计', '电商1、2班', '2.00', '1.20', '16.00', '1.10', '1.00', '42.24');
INSERT INTO design VALUES ('JAVA程序设计', '电商1、2班', '2.00', '1.20', '16.00', '1.10', '1.00', '42.24');
INSERT INTO design VALUES ('JAVA程序设计', '电商1、2班', '2.00', '1.20', '16.00', '1.10', '1.00', '42.24');
INSERT INTO design VALUES ('JAVA程序设计', '电商1、2班', '2.00', '1.20', '16.00', '1.10', '1.00', '42.24');
INSERT INTO design VALUES ('JAVA程序设计', '电商1、2班', '2.00', '1.20', '16.00', '1.10', '1.00', '42.24');
INSERT INTO design VALUES ('JAVA程序设计', '电商1、2班', '2.00', '1.20', '16.00', '1.10', '1.00', '42.24');
INSERT INTO design VALUES ('JAVA程序设计', '电商1、2班', '2.00', '1.20', '16.00', '1.10', '1.00', '42.24');
INSERT INTO design VALUES ('JAVA程序设计', '电商1、2班', '2.00', '1.20', '16.00', '1.10', '1.00', '42.24');
INSERT INTO design VALUES ('C++', '电商1、2', '2.00', '1.20', '16.00', '1.10', '1.00', '42.24');
INSERT INTO design VALUES ('C++', '电商1、2', '2.00', '1.20', '16.00', '1.10', '1.00', '42.24');
INSERT INTO design VALUES ('C++', '电商1、2', '2.00', '1.20', '16.00', '1.10', '1.00', '42.24');

SELECT * FROM design;




CREATE TABLE graduate_design (
  g_d_name varchar(20) DEFAULT NULL,
  g_d_students float(8,2) DEFAULT NULL,
  g_d_weeks float(8,2) DEFAULT NULL,
  g_d_xuwshi float(8,2) DEFAULT NULL,
  g_d_huanjie float(8,2) DEFAULT NULL,
  g_d_result float(8,2) DEFAULT NULL
) default charset=utf8;


INSERT INTO graduate_design VALUES ('电商1、2班', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');
INSERT INTO graduate_design VALUES ('17软工', '4.00', '16.00', '0.50', '1.00', '32.00');

SELECT * FROM graduate_design;




CREATE TABLE guanli (
  id varchar(255) NOT NULL,
  pw varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) default charset=utf8;


INSERT INTO guanli VALUES ('123', '12');

SELECT * FROM guanli;



CREATE TABLE info (
  T_id int(11) NOT NULL,
  T_name varchar(20) DEFAULT NULL,
  T_gender varchar(2) DEFAULT NULL,
  T_jobtitle varchar(20) DEFAULT NULL,
  T_course varchar(20) DEFAULT NULL,
  T_classes varchar(20) DEFAULT NULL,
  T_classnum int(11) DEFAULT NULL,
  T_theory float(8,2) DEFAULT NULL,
  T_experimental float(8,2) DEFAULT NULL,
  PRIMARY KEY (`T_id`)
) default charset=utf8;


INSERT INTO info VALUES ('1001', '张三', '男', '高级教师', 'JAVA程序设计', '电商1、2班', '2', '55.00', '24.00');
INSERT INTO info VALUES ('1002', '李四', '男', '高级教师', 'C++程序设计', '机电1、2班', '2', '52.00', '22.00');
INSERT INTO info VALUES ('1003', '李红', '女', '高级教师', '多媒体技术', '电商1、2班', '2', '52.00', '20.00');
INSERT INTO info VALUES ('1004', '王五', '男', '高级教师', 'C语言程序设计', '电商1、2班', '2', '12.00', '12.00');
INSERT INTO info VALUES ('1005', '李红', '女', '高级教师', '软件工程导论', '17软工', '2', '24.00', '24.00');
INSERT INTO info VALUES ('1006', '李红', '女', '高级教师', '软件工程导论', '17软工', '2', '24.00', '24.00');
INSERT INTO info VALUES ('1007', '李红', '女', '高级教师', '软件工程导论', '17软工', '2', '24.00', '24.00');
INSERT INTO info VALUES ('1008', '李红', '女', '高级教师', '软件工程导论', '17软工', '2', '24.00', '24.00');
INSERT INTO info VALUES ('1009', '李红', '女', '高级教师', '软件工程导论', '17软工', '2', '24.00', '24.00');
INSERT INTO info VALUES ('1010', '李红', '女', '高级教师', '软件工程导论', '17软工', '2', '24.00', '24.00');
INSERT INTO info VALUES ('1011', '李红', '女', '高级教师', '软件工程导论', '17软工', '2', '24.00', '24.00');
INSERT INTO info VALUES ('1012', '李红', '女', '高级教师', '软件工程导论', '17软工', '2', '24.00', '24.00');
INSERT INTO info VALUES ('1013', '李红', '女', '高级教师', '软件工程导论', '17软工', '2', '24.00', '24.00');
INSERT INTO info VALUES ('1014', '李红', '女', '高级教师', '软件工程导论', '17软工', '2', '24.00', '24.00');
INSERT INTO info VALUES ('1015', '李红', '女', '高级教师', '软件工程导论', '17软工', '2', '24.00', '24.00');
INSERT INTO info VALUES ('1016', '李红', '女', '高级教师', '软件工程导论', '17软工', '2', '24.00', '24.00');
INSERT INTO info VALUES ('1017', '李红', '女', '高级教师', '软件工程导论', '17软工', '2', '24.00', '24.00');
INSERT INTO info VALUES ('1018', '李红', '女', '高级教师', '软件工程导论', '17软工', '2', '24.00', '24.00');

SELECT * FROM info;




CREATE TABLE single (
  single_course_name varchar(20) DEFAULT NULL,
  single_classes varchar(20) DEFAULT NULL,
  single_hour float(8,2) DEFAULT NULL
) default charset=utf8;


INSERT INTO single VALUES ('数据结构', '计科7、8、9班', '68.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
INSERT INTO single VALUES ('Java程序设计', '电商1、2班', '12.00');
 
SELECT * FROM single;





CREATE TABLE theory_experimental (
  t_e_course varchar(20) DEFAULT NULL,
  t_e_classes varchar(20) DEFAULT NULL,
  t_e_xueshi float(8,2) DEFAULT NULL,
  t_e_kecheng float(8,2) DEFAULT NULL,
  t_e_heban float(8,2) DEFAULT NULL,
  t_e_huanjie float(8,2) DEFAULT NULL,
  t_e_result float(8,2) DEFAULT NULL
) default charset=utf8;


INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');
INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');
INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');
INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');
INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');
INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');
INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');
INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');
INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');
INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');
INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');
INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');
INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');
INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');
INSERT INTO theory_experimental VALUES ('程序设计', '17软工', '48.00', '1.00', '1.40', '1.00', '67.20');

SELECT * FROM theory_experimental;