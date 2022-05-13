INSERT INTO tb_role(authority) VALUES ('ROLE_VISITOR');
INSERT INTO tb_role(authority) VALUES ('ROLE_MEMBER');

INSERT INTO tb_user(name, email, password) VALUES ('Ana Julia', 'ana@gmail.com', '$2a$10$goiK7dVQoRGlnGwxndoSJe/ytXq2C3C5iOzpGLnWWeRNR7CGmc/QC');
INSERT INTO tb_user(name, email, password) VALUES ('Bob Brown', 'bob@gmail.com', '$2a$10$goiK7dVQoRGlnGwxndoSJe/ytXq2C3C5iOzpGLnWWeRNR7CGmc/QC');

INSERT INTO tb_user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role(user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role(user_id, role_id) VALUES (2, 1);

INSERT INTO tb_genre(name) VALUES ('Comedia');

INSERT INTO tb_movie(title, sub_title, year, img_url, synopsis, genre_id) VALUES ('As longas tranças de um careca', 'Como pode um careca ter tranças?', '2022', 'https://cdn.pixabay.com/photo/2013/07/12/15/24/bald-head-149857_1280.png', 'Veja como é legal a vida de um careca com tranças!', 1);

INSERT INTO tb_review(text, movie_id, user_id) VALUES ('Muito bom, legal', 1, 1);
