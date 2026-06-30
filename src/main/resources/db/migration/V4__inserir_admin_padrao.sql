INSERT INTO tb_usuario (usuario_id, cpf_usuario, nome_usuario, email_usuario, senha_usuario)
VALUES (
           '00000000-0000-4000-a000-000000000000',
           '00000000000',
           'Mestre Admin',
           'mestreadmin@dojo.com.br',
           '$2a$12$Xq8w/ErkVaI39pDUwEPtEe2Dg1O58H8Lo.Kh7U2Ypqbvgzo5nzQ1e'
);


INSERT INTO tb_usuario_roles (usuario_id, role_user)
VALUES (
           '00000000-0000-4000-a000-000000000000',
           'ADMIN'
       );