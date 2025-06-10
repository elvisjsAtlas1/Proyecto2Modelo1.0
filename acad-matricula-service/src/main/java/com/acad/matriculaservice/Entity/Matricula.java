package com.acad.matriculaservice.Entity;


import com.acad.matriculaservice.Dto.RequisitosDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "matricula")

public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long idMatricula;

    private String planAcademico;

    private String institucion;

    private String usuario;

    private String codigoMatricula;

    //este es el que debe estar dentro de matricula como por defecto, los demas son tablas independientes donde jalaremos los ID;
    //primero esta simulado

    private String nivel;


    private Long idRequisitos;

    @Transient
    private RequisitosDto requisito;
}
