package com.jorge.EP1_Martinez.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlumnoRepository  extends JpaRepository<Alumno,Long> {
         Optional<Alumno> findAlumnoByDni(String dni);
}
