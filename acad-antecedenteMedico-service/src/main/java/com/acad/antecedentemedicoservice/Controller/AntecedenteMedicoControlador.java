package com.acad.antecedentemedicoservice.Controller;

import com.acad.antecedentemedicoservice.Entity.AntecedenteMedico;
import com.acad.antecedentemedicoservice.Service.AntecedenteMedicoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/antecedentes-medicos")
public class AntecedenteMedicoControlador {

    @Autowired
    private AntecedenteMedicoServicio antecedenteMedicoServicio;

    // Listar todos los antecedentes médicos
    @GetMapping
    public ResponseEntity<List<AntecedenteMedico>> listar() {
        return ResponseEntity.ok(antecedenteMedicoServicio.listar());
    }

    // Buscar antecedente médico por ID
    @GetMapping("/{id}")
    public ResponseEntity<AntecedenteMedico> buscar(@PathVariable Integer id) {
        Optional<AntecedenteMedico> antecedente = antecedenteMedicoServicio.buscar(id);
        return antecedente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Guardar nuevo antecedente médico
    @PostMapping
    public ResponseEntity<AntecedenteMedico> guardar(@RequestBody AntecedenteMedico antecedente) {
        AntecedenteMedico nuevo = antecedenteMedicoServicio.guardar(antecedente);
        return ResponseEntity.ok(nuevo);
    }

    // Modificar antecedente médico existente
    @PutMapping("/{id}")
    public ResponseEntity<AntecedenteMedico> modificar(@PathVariable Integer id, @RequestBody AntecedenteMedico antecedente) {
        AntecedenteMedico actualizado = antecedenteMedicoServicio.modificar(id, antecedente);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar antecedente médico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        antecedenteMedicoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
