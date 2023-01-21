package com.jorge.EP1_Martinez.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService{
    @Autowired
    AlumnoRepository alumnoRepository;
    @Override
    public void addAlumno(Alumno alumno) throws Exception {
        try {
            validarAlumno(alumno);
            Optional<Alumno> alumnoDB = alumnoRepository.findAlumnoByDni(alumno.getDni());
            if(alumnoDB.isPresent()){
                throw new Exception("El Alumno con el dni "+ alumno.getDni() +" ya existe");
            }else{
                alumnoRepository.save(alumno);
            }

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Alumno> findAllAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    public void updateAlumno(Alumno alumno, Long id) throws Exception {
        try{
            if(id == null || id.toString().isBlank()){
                throw new Exception("El campo id no puede ser vacío");
            }
            validarAlumno(alumno);

            Optional<Alumno> alumnoOptional = alumnoRepository.findById(id);
            if(alumnoOptional.isPresent()){
                Alumno alumnoDB = alumnoOptional.get();
                alumnoDB.setNombre(alumno.getNombre());
                alumnoDB.setApellido(alumno.getApellido());
                alumnoDB.setDni(alumno.getDni());
                alumnoDB.setCurso(alumno.getCurso());
                alumnoRepository.save(alumnoDB);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteAlumno(Long id) throws Exception {
        if(id == null || id.toString().isBlank()){
            throw new Exception("El campo id no puede ser vacío");
        }
        try{
            Optional<Alumno> alumnoOptional = alumnoRepository.findById(id);
            if(alumnoOptional.isEmpty()){
                throw new Exception("El Alumno con el id "+id+" no existe");
            }else{
                alumnoRepository.deleteById(id);
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Alumno findAlumnoByDni(String dni) throws Exception {
        if(dni == null){
            throw  new Exception("El dni no ha sido enviado en el request");
        }
        if(dni.isBlank()){
            throw  new Exception("El dni no puede ser vacio");
        }
       return alumnoRepository.findAlumnoByDni(dni).orElse(null);
    }

    private  void validarAlumno(Alumno alumno) throws Exception {
        if(alumno.getNombre() == null || alumno.getNombre().isBlank() ){
            throw new Exception("El campo nombre no puede ser vacío");
        }
        if(alumno.getApellido() == null || alumno.getApellido().isBlank() ){
            throw new Exception("El campo apellido no puede ser vacío");
        }
        if(alumno.getDni() == null || alumno.getDni().isBlank() ){
            throw new Exception("El campo dni no puede ser vacío");
        }
        if(alumno.getCurso() == null || alumno.getCurso().isBlank() ){
            throw new Exception("El campo curso no puede ser vacío");
        }
    }
}
