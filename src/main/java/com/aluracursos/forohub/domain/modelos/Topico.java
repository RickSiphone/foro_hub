package com.aluracursos.forohub.domain.modelos;

import com.aluracursos.forohub.domain.dto.RegistroTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topico_id")
    private Long id;

    @Column(unique = true)
    private String titulo;

    @Column(unique = true)
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    //Tengo que indicar el nombre de la columna en la base de datos de la tabla Topico, no de Usuario
    //No tengo que poner el nombre de la columna de la tabla Usuario
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico(RegistroTopico registroTopico, LocalDateTime fechaCreacion, Usuario autor, Curso curso){
        this.titulo = registroTopico.titulo();
        this.mensaje = registroTopico.mensaje();
        this.fechaCreacion = fechaCreacion;
        this.status = "ABIERTO";
        this.autor = autor;
        this.curso = curso;
    }
}
