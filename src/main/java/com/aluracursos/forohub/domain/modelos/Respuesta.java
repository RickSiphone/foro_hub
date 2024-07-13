package com.aluracursos.forohub.domain.modelos;

import com.aluracursos.forohub.domain.dto.ResponderPublicacion;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "respuesta_id")
    private Long id;

    private String solucion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    public Respuesta(ResponderPublicacion responderPublicacion, LocalDateTime fechaCreacion, Topico topico, Usuario autor) {
        this.solucion = responderPublicacion.solucion();
        this.autor = autor;
        this.topico = topico;
        this.fechaCreacion = fechaCreacion;
    }
}
