package com.jorge.EP1_Martinez.controller;

import com.jorge.EP1_Martinez.model.Alumno;
import com.jorge.EP1_Martinez.model.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1/alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @PostMapping("/newalumno")
    public ResponseEntity<Map<String,String>> addAlumno(
            @RequestBody Alumno alumno
    ){
        HashMap<String,String> response = new HashMap<>();
        try{
                alumnoService.addAlumno(alumno);
                response.put("result","Alumno añadido correctamente");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);

        }catch (Exception e){
            response.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PutMapping("/update-alumno")
    public ResponseEntity<Map<String,String>> updateAlumno(
          @RequestBody  Alumno alumno, @RequestParam(name = "id",defaultValue = "") Long id ){
        HashMap<String,String> response = new HashMap<>();
        try {
            alumnoService.updateAlumno(alumno,id);
            response.put("result","Alumno actualizado correctamente");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping({"/",""})
    public ResponseEntity<List<Alumno>> getAll(){
        return ResponseEntity.ok(alumnoService.findAllAlumnos());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String,String>> deleteAlumno(
            @RequestParam(name = "id",defaultValue = "") Long id
    ){
        HashMap<String, String> response = new HashMap<>();
        try{
            alumnoService.deleteAlumno(id);
            response.put("result","Alumno eliminado correctamente");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
