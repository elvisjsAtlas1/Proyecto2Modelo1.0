package com.acad.requisitoservice.Feign;

import com.acad.requisitoservice.Dto.ApoderadoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "acad-apoderado-service", path = "/apoderados")
public interface ApoderadoFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "apoderadoListarPorIdCB", fallbackMethod = "fallbackApoderadoById")
    ResponseEntity<ApoderadoDto> buscarApoderado(@PathVariable Integer id);

    default ResponseEntity<ApoderadoDto> fallbackApoderadoById(Integer id, Throwable e) {
        ApoderadoDto apoderadoDto = new ApoderadoDto();
        apoderadoDto.setNombres("Servicio de apoderado no disponible 😞");
        return ResponseEntity.ok(apoderadoDto);
    }
}
