CREATE TABLE respuesta (
    respuesta_id bigint not null,
    mensaje varchar(300) not null,
    topico_id bigint not null,
    fechaCreacion datetime not null,
    autor bigint not null,
    solucion varchar(300) not null,
    CONSTRAINT respuesta_pk PRIMARY KEY(respuesta_id),
    CONSTRAINT respuesta_topico_fk FOREIGN KEY(topico_id)
    REFERENCES topico(topico_id) ON DELETE CASCADE,
    CONSTRAINT respuesta_autor_fk FOREIGN KEY(autor)
    REFERENCES usuario(usuario_id)
);