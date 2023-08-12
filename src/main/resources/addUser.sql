insert into roles (id,name)
values (1,'ROLE_ADMIN'),
       (2,'ROLE_USER'),
       (3,'ROLE_TEACHER'),
       (4,'ROLE_STUDENT');

INSERT INTO groups (NAME, USER_ID, TASK_ID)
values ('Нет группы',1,1),
('javascript',2,1),
('java',2,1),
('Golang',2,1),
('Spring',2,1);

INSERT INTO users (USERNAME,PASSWORD,ROLE_ID,GROUP_ID)
values ('admin','qwerty',1,1),
       ('teacher','teacher',3,2),
       ('user','user',2,1),
       ('student','student',4,1);

INSERT INTO TEACHER (EMAIL,NAME,ROLE)
values ('test','test','test');

insert into TASKS (IS_ACTIVE,NAME,RESULT,GROUP_ID,TEACHER_ID)
values ('true','test','true',1,1);


