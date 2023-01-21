package com.jorge.EP1_Martinez.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(unique = true)
    private String dni;
    private String curso;

}
