ALTER TABLE tb_aluno
    ADD COLUMN endereco_cep VARCHAR(8),
    ADD COLUMN endereco_logradouro VARCHAR(255),
    ADD COLUMN endereco_numero VARCHAR(20),
    ADD COLUMN endereco_complemento VARCHAR(255),
    ADD COLUMN endereco_bairro VARCHAR(100),
    ADD COLUMN endereco_localidade VARCHAR(100),
    ADD COLUMN endereco_uf VARCHAR(2),
    ADD COLUMN endereco_estado VARCHAR(100),
    ADD COLUMN endereco_regiao VARCHAR(50);

