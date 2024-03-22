CREATE DATABASE springbootwebforms CHARACTER SET utf8;
use springbootwebforms;
select * from course;
insert into course (id,name) values (1,'course1');
insert into course (id,name) values (2,'course2');
insert into course (id,name) values (3,'course3');

insert into student (id,name) values (1,'student1');
insert into student (id,name) values (2,'student2');
insert into student (id,name) values (3,'student3');
select * from student;


insert into student_course (student_id,course_id) values (1,1);
insert into student_course (student_id,course_id) values (1,2);
insert into student_course (student_id,course_id) values (1,3);

insert into student_course (student_id,course_id) values (2,1);
insert into student_course (student_id,course_id) values (2,2);
insert into student_course (student_id,course_id) values (3,3);
select * from student_course;


USE springbootwebforms;
SHOW TABLES;

select * from users;
insert into users(id,password,username) values(1,'123','user1');
insert into users(id,password,username) values(2,'123','user2');

select * from roles;
insert into roles(role_id,authority)values(1,'admin');
insert into roles(role_id,authority)values(2,'user');
update roles set authority = 'ROLE_ADMIN' where role_id=1;
update roles set authority = 'ROLE_USER' where role_id=2;

select * from roles_seq;
select * from user_role_junction;
insert into user_role_junction(user_id,role_id)values(1,2);
insert into user_role_junction(user_id,role_id)values(2,1);
insert into user_role_junction(user_id,role_id)values(2,2);
