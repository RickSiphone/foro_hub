package com.aluracursos.forohub.domain.repository;

import com.aluracursos.forohub.domain.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    UserDetails findByNombreUsuario(String username);
}
