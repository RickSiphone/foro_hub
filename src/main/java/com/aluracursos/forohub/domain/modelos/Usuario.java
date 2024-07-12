package com.aluracursos.forohub.domain.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column(unique = true, name = "nombre_usuario")
    private String nombreUsuario;

    @Column(unique = true)
    private String nombre;

    @Column(unique = true)
    private String email;

    private String contrasena;

}
