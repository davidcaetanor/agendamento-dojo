ALTER TABLE tb_aluno
    ADD COLUMN status_matricula VARCHAR(20)
        DEFAULT 'ATIVA'
        CHECK (status_matricula IN ('ATIVA', 'DESATIVADA', 'INADIMPLENTE'));

ALTER TABLE tb_agendamento
    ADD COLUMN status_agendamento VARCHAR(20)
        CHECK (status_agendamento IN ('CANCELADA', 'CONFIRMADA'));