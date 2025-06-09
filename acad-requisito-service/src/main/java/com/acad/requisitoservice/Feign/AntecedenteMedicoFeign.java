package com.acad.requisitoservice.Feign;

import com.acad.requisitoservice.Dto.AntecedenteMedicoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "acad-antecedentemedico-service", path = "/antecedentes-medicos")
public interface AntecedenteMedicoFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "antecedenteMedicoListarPorIdCB", fallbackMethod = "fallbackAntecedenteMedicoById")
    ResponseEntity<AntecedenteMedicoDto> buscarAntecedenteMedico(@PathVariable Long id);

    default ResponseEntity<AntecedenteMedicoDto> fallbackAntecedenteMedicoById(Long id, Throwable e) {
        AntecedenteMedicoDto antecedenteMedico = new AntecedenteMedicoDto();
        antecedenteMedico.setEstadoPsicologico("Servicio de antecedente médico no disponible");
        return ResponseEntity.ok(antecedenteMedico);
    }
}
