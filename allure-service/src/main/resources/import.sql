INSERT INTO sys_account (id, version, email, password, nick_name) VALUES (1, 1, '412837184@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 'Terrorblade');

INSERT INTO sys_role (id, version, role, description) VALUES (1, 1, 'ROLE_ADMIN', '');
INSERT INTO sys_role (id, version, role, description) VALUES (2, 1, 'ROLE_USER', '');

INSERT INTO sys_account_role (account, role) VALUES (1, 1);
INSERT INTO sys_account_role (account, role) VALUES (1, 2);

