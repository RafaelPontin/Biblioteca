insert into usuario (id, username, password) values (1,'basico','$2a$10$O6jDBlpb/.6Hr2fMn5vKmeOJ71jvbtJE/koxj5rOcmwIeNq1QwvYi');
insert into usuario (id, username, password) values (2,'admin','$2a$10$O6jDBlpb/.6Hr2fMn5vKmeOJ71jvbtJE/koxj5rOcmwIeNq1QwvYi');
insert into usuario (id, username, password) values (3,'super','$2a$10$O6jDBlpb/.6Hr2fMn5vKmeOJ71jvbtJE/koxj5rOcmwIeNq1QwvYi');
insert into usuario (id, username, password) values (4,'rafael','$2a$10$gieb4eSVlchb2YFlXdWjYum.u9zeSC/C5vEIiL62kfCVqhWGvlr5K');


INSERT INTO role ( ID, ROLE, USUARIO_ID ) VALUES (1,'ROLE_BASIC',4);
INSERT INTO role ( ID, ROLE, USUARIO_ID ) VALUES (2,'ROLE_ADMIN',4);
INSERT INTO role ( ID, ROLE, USUARIO_ID ) VALUES (3,'ROLE_BASIC',3);
INSERT INTO role ( ID, ROLE, USUARIO_ID ) VALUES (4,'ROLE_ADMIN',3);
INSERT INTO role ( ID, ROLE, USUARIO_ID ) VALUES (5,'ROLE_BASIC',2);
INSERT INTO role ( ID, ROLE, USUARIO_ID ) VALUES (6,'ROLE_ADMIN',2);
INSERT INTO role ( ID, ROLE, USUARIO_ID ) VALUES (7,'ROLE_BASIC',1);
INSERT INTO role ( ID, ROLE, USUARIO_ID ) VALUES (8,'ROLE_ADMIN',1);


INSERT INTO AUTOR ( ID, NOME ) VALUES ( 1, 'autor teste 1');

INSERT INTO LIVRO ( ID, FOTO, ISBN, QUANTIDADE, TITULO, AUTOR_ID ) VALUES ( 1 , '' , 'dasjidasdjskal',  10, 'livro teste 1' ,1);


INSERT INTO EMPRESTIMO ( ID, DATA_DEVOLUCAO, DATA_EMPRESTIMO, LIVRO_ID, USUARIO_ID ) VALUES ( 1,	'2018-02-10', '2018-02-03' ,1	,4 );

