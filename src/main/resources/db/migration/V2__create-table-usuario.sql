CREATE TABLE usuario (
    usuario_id bigint not null,
    nombre varchar(100) not null,
    email varchar(100) not null,
    contrasena varchar(100) not null,
    CONSTRAINT usuario_pk PRIMARY KEY(usuario_id),
    CONSTRAINT usuario_nombre_ak UNIQUE (nombre),
    CONSTRAINT usuario_email_ak UNIQUE(email)
);