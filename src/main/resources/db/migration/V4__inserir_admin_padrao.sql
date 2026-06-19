INSERT INTO tb_usuario (usuario_id, cpf_usuario, nome_usuario, email_usuario, senha_usuario)
VALUES (
           '00000000-0000-4000-a000-000000000000',
           '00000000000',
           'Mestre Admin',
           'mestreadmin@dojo.com.br',
           '$2a$12$tdoxR1gkN/bddSTk3GrZRuLRWN1d2P4um2lAGFUtmkHoNcNqpuq1W'
);


INSERT INTO tb_usuario_roles (usuario_id, role_user)
VALUES (
           '00000000-0000-4000-a000-000000000000',
           'ADMIN'
       );