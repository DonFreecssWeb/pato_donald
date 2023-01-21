package com.jorge.EP1_Martinez.model;

import java.util.List;

public interface AlumnoService {
    void addAlumno(Alumno customer) throws Exception;
    List<Alumno> findAllAlumnos();

    Alumno findAlumnoByDni(String dni) throws Exception;
    void updateAlumno(Alumno alumno, Long id) throws Exception;
    void deleteAlumno(Long id) throws Exception;
}
