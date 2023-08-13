insert into roles (id,name)
values (1,'ROLE_ADMIN'),
       (2,'ROLE_USER'),
       (3,'ROLE_TEACHER'),
       (4,'ROLE_STUDENT');

INSERT INTO groups (NAME)
values ('Нет группы'),
       ('javascript'),
       ('java'),
       ('Golang'),
       ('Spring');
/**
  admin-qwerty
  teacher-teacher
  user-user
  student-student
  student2-123
 */
INSERT INTO users (USERNAME,PASSWORD,ROLE_ID,GROUP_ID)
values ('admin','$2a$12$vl.6pLePAvAmHNpMG1HXD./kH.dSWjSaWa92nOmyABKpnG0.xzBGm',1,1),
       ('teacher','$2a$12$65eXIU3QrGkTOXPNfwbjs.F0unHmx5i2H/68NRhZ0kr6ZeUuzDrc2',3,1),
       ('user','$2a$12$BOgs512JNQuhpJJKunluU.8zqCgJmhiAxcDckke94XruRR42MAEhq',2,1),
       ('student','$2a$12$s82m4XrjJ6pWX9Wae6Rs5OG/zIz7.aEcqlKWSmUoPUus6Hc9Cxslu',4,1),
       ('student2','$2a$12$8mo566iGXVaWxTqvtSHefe.8ozheLd3xVvgQoNEEOju4hcif0GPbi',4,5);

insert into TASKS (IS_ACTIVE,NAME,GROUP_ID,TEACHER_ID)
values ('true','lesson Java',1,2),
       ('false','lesson Spring',5,2),
       ('true','lesson Docker',2,2),
       ('true','lesson Hibernate',5,2),
       ('true','lesson kafka',5,2);

insert into TASKSRESP (answer,task_id,user_id)
values('ответ на первую задачу',1,4),
      ('ответ на третью задачу',3,4),
      ('333',5,5),
      ('333',4,5);

