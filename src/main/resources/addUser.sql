insert into roles (id,name)
values (1,'ROLE_ADMIN'),
       (2,'ROLE_USER'),
       (3,'ROLE_TEACHER'),
       (4,'ROLE_STUDENT');

INSERT INTO users (USERNAME,PASSWORD,ROLE_ID)
values ('admin','qwerty',1),
       ('teacher','teacher',3),
       ('user','user',2),
       ('student','student',4);

INSERT INTO groups (NAME, USER_ID)
values ('Нет группы',1),
('javascript',2),
('java',2),
('Golang',2),
('Spring',2);

insert into TASKS (IS_ACTIVE,NAME,RESULT,TEACHER_ID, GROUP_ID)
values ('true','test','true',1, 5);
