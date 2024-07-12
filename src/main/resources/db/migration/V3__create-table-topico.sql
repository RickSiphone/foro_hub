CREATE TABLE topico (
    topico_id bigint not null,
    titulo varchar(100) not null,
    mensaje varchar(300) not null,
    fechaCreacion datetime not null,
    status varchar(100) not null,
    autor bigint not null,
    curso_id bigint not null,
    CONSTRAINT topico_pk PRIMARY KEY(topico_id),
    CONSTRAINT topico_autor_fk FOREIGN KEY(autor)
    REFERENCES usuario(usuario_id),
    CONSTRAINT topico_curso_fk FOREIGN KEY(curso_id)
    REFERENCES curso(curso_id)
);