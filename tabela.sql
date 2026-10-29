CREATE TABLE pokemon (
    codigo          NUMBER(5)    PRIMARY KEY,
    nome            VARCHAR2(50) NOT NULL,
    altura          NUMBER(4,2),
    peso            NUMBER(6,2),
    categoria       VARCHAR2(50),
    data_da_captura DATE
);
