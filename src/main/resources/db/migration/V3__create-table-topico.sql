CREATE TABLE topico (
    topico_id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(300) not null,
    fecha_creacion datetime not null,
    status varchar(100) not null,
    autor_id bigint not null,
    curso_id bigint not null,
    CONSTRAINT topico_pk PRIMARY KEY(topico_id),
    CONSTRAINT topico_autor_fk FOREIGN KEY(autor_id)
    REFERENCES usuario(usuario_id),
    CONSTRAINT topico_curso_fk FOREIGN KEY(curso_id)
    REFERENCES curso(curso_id),
    CONSTRAINT topico_titulo_ak UNIQUE(titulo),
    CONSTRAINT topico_mensaje_ak UNIQUE(mensaje)
);