package com.acad.matriculaservice.Dto;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RequisitosDto {
    private Long idRequisitos;
    //falta comprobar la obtencion total de los valores para que se listen completos

    private String dniEstudiante;
    private String partidaNacimientoOriginal;
    private String resolucionRectoralDeTraslado;
    private String certificadoDeEstudio;
    private String fichaMatriculaGeneradoPorSIAGE;
    private String constanciaDeComportamiento;
    private String constanciaDeNoAdeudo;
    private String familiarMilitar;
    //valores por defecto en los Requisitos
}
