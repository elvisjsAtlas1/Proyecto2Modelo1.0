package com.acad.matriculaservice.Feign;

import com.acad.matriculaservice.Dto.RequisitosDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "acad-requisito-service", path = "/requisitos")
public interface RequisitoFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "requisitoListarPorIdCB", fallbackMethod = "fallbackRequisitoById")
    ResponseEntity<RequisitosDto> buscarRequisito(@PathVariable Long id);

    default ResponseEntity<RequisitosDto> fallbackRequisitoById(Long id, Throwable e) {
        RequisitosDto requisitosDto = new RequisitosDto();
        requisitosDto.setDniEstudiante("Servicio de matricula no disponible");
        return ResponseEntity.ok(requisitosDto);
    }
}
