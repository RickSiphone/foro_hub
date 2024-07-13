CREATE TABLE respuesta (
    respuesta_id bigint not null auto_increment,
    solucion varchar(300) not null,
    topico_id bigint not null,
    fecha_creacion datetime not null,
    autor_id bigint not null,
    CONSTRAINT respuesta_pk PRIMARY KEY(respuesta_id),
    CONSTRAINT respuesta_topico_fk FOREIGN KEY(topico_id)
    REFERENCES topico(topico_id) ON DELETE CASCADE,
    CONSTRAINT respuesta_autor_fk FOREIGN KEY(autor_id)
    REFERENCES usuario(usuario_id)
);