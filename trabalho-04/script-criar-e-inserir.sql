CREATE TABLE tbcargos (
    cd_cargo  SMALLINT  NOT NULL,
    ds_cargo  CHAR(20)  NULL,
    CONSTRAINT PK_tbcargos PRIMARY KEY (cd_cargo)
);
GO

CREATE TABLE tbfuncs (
    cod_func   DECIMAL(9,0) NOT NULL,
    nome_func  CHAR(30)     NULL,
    sal_func   MONEY        NULL,
    cod_cargo  SMALLINT     NULL,
    CONSTRAINT PK_tbfuncs PRIMARY KEY (cod_func),
    CONSTRAINT FK_tbfuncs_tbcargos FOREIGN KEY (cod_cargo)
        REFERENCES tbcargos (cd_cargo)
);
GO

INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (1, 'Estagiário Desenv.');
INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (2, 'Desenvolvedor');
INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (3, 'Estagiário Analista');
INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (4, 'Analista');
GO

INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (1001, 'Ana Paula Souza',      1800.00, 1);
INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (1002, 'Bruno Henrique Lima',  1800.00, 1);
INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (1003, 'Carla Mendes',         6500.00, 2);
INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (1004, 'Daniel Ferreira',      7200.00, 2);
INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (1005, 'Eduarda Nascimento',   1700.00, 3);
INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (1006, 'Felipe Araújo',        1700.00, 3);
INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (1007, 'Gabriela Rocha',       8500.00, 4);
INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (1008, 'Henrique Costa',       8900.00, 4);
INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (1009, 'Isabela Martins',      6800.00, 2);
INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (1010, 'João Pedro Alves',     9200.00, 4);
GO

SELECT f.cod_func, f.nome_func, f.sal_func, c.ds_cargo
FROM tbfuncs f
INNER JOIN tbcargos c ON c.cd_cargo = f.cod_cargo;