INSERT INTO "public".roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO "public".roles (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO "public".users (id, active, email, name, password) VALUES (10000, true, 'admin@admin.hu', 'admin', '$2a$10$7z8ZcKhYICuyb5rkKhqo7uUh4je3HQocio0f5onZdugR0QTrxHDLi');
INSERT INTO "public".users_role (user_id, roles_id) INSERT (10000, 1);
INSERT INTO "public".users_role (user_id, roles_id) INSERT (10000, 2);
