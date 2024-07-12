CREATE TABLE curso (
    curso_id bigint not null auto_increment,
    nombre varchar(100) not null,
    categoria varchar(100) not null,
    CONSTRAINT curso_pk PRIMARY KEY(curso_id),
    CONSTRAINT curso_ak UNIQUE(nombre)
);