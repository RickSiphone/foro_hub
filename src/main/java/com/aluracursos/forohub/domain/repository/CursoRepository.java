package com.aluracursos.forohub.domain.repository;

import com.aluracursos.forohub.domain.modelos.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CursoRepository extends JpaRepository<Curso,Long> {
}
