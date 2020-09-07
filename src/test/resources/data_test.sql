insert into genres (genre_name) values ('Drama'), ('Thriller'), ('Adventures'), ('Historical');
insert into authors (name) values ('Lev Tolstoy'), ('Mark Twen'), ('Daria Dontsova');
insert into books (name, author_id, genre_id) values ('War and Piece', 1, 1), ('Tom Soyer', 2, 3), ('Lovers killer', 3, 2);
insert into comments (text, book_id) values ('Book that you will never forget, 5 stars!', 1),
    ('Honestly I did not like that, 3 stars', 1), ('Be sure you will like it!', 2),
    ('Really nice book about friendship', 2);

-- ПОЛЛЬЗОВАТЕЛИ И РОЛИ
insert into users (USERNAME, PASSWORD, ENABLED) values ('Alex', '$2y$12$VvpTyU0YjQuDaW8JHZr3DOhLxy9oNHhgb1PdJYOEGadNKa1yyay/u', 1),
    ('badUser', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 0),
    ('Otus', '$2y$12$kXNI5KRCqmteL4rZYppnmOL3PvhrLbf9rjO0KTymw3S8gFl9SH42m', 1),
    ('User', '$2y$12$.cS41ui.B7VvZw7WgGreDORHrhyWSBFR/hkSxlwhGMYWVt7GFqV8u', 1);
insert into AUTHORITIES (USERNAME, AUTHORITY) values ('Alex', 'ROLE_ADMIN'), ('Otus', 'ROLE_USER'), ('badUser', 'ROLE_ADMIN'),
    ('User', 'ROLE_USER');

-- SPRING SECURITY ACL
INSERT INTO acl_sid (principal, sid) VALUES
('true', 'Alex'),
('true', 'Otus'),
('false', 'ROLE_ADMIN'),
('false', 'ROLE_USER'),
('true', 'User');

INSERT INTO acl_class (class) VALUES
('ru.otus.akutsev.books.model.Book');

INSERT INTO acl_object_identity (object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, DEFAULT, 3, 'false'),
(1, 2, DEFAULT, 3, 'false'),
(1, 3, DEFAULT, 3, 'false');

INSERT INTO acl_entry (acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 5, 4, 2, 'false', 'true', 'true'),
(2, 5, 4, 2, 'true', 'true', 'true'),
(3, 5, 4, 2, 'true', 'true', 'true'),
(1, 1, 5, 2, 'true', 'true', 'true'),
(2, 1, 5, 2, 'true', 'true', 'true'),
(3, 1, 5, 2, 'true', 'true', 'true'),
(1, 1, 3, 2, 'true', 'true', 'true'),
(2, 1, 3, 2, 'true', 'true', 'true'),
(3, 1, 3, 2, 'true', 'true', 'true');