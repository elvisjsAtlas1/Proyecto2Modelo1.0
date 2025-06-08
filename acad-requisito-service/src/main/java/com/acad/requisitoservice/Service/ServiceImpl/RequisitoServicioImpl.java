package com.acad.requisitoservice.Service.ServiceImpl;

import com.acad.requisitoservice.Entity.Requisito;
import com.acad.requisitoservice.Feign.AntecedenteMedicoFeign;
import com.acad.requisitoservice.Feign.ApoderadoFeign;
import com.acad.requisitoservice.Repository.RequisitoRepositorio;
import com.acad.requisitoservice.Service.RequisitoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequisitoServicioImpl implements RequisitoServicio {

    @Autowired
    private RequisitoRepositorio requisitoRepositorio;

    @Autowired
    private ApoderadoFeign apoderadoFeign;

    @Autowired
    private AntecedenteMedicoFeign antecedenteMedicoFeign;

    @Override
    public List<Requisito> listar() {
        return requisitoRepositorio.findAll().stream()
                .map(requisito -> {
                    if (requisito.getApoderado() != null && requisito.getApoderado().getIdApoderado() != null) {
                        var apoderadoDto = apoderadoFeign.buscarApoderado(requisito.getApoderado().getIdApoderado()).getBody();
                        requisito.setApoderado(apoderadoDto);
                    }
                    if (requisito.getAntecedenteMedico() != null && requisito.getAntecedenteMedico().getIdAntecedenteMedico() != null) {
                        var antecedenteDto = antecedenteMedicoFeign.buscarAntecedenteMedico(requisito.getAntecedenteMedico().getIdAntecedenteMedico()).getBody();
                        requisito.setAntecedenteMedico(antecedenteDto);
                    }
                    return requisito;
                })
                .collect(Collectors.toList());
    }


    @Override
    public Requisito buscar(Long id) {
        Optional<Requisito> requisitoOptional = requisitoRepositorio.findById(id);
        if (requisitoOptional.isPresent()) {
            Requisito requisito = requisitoOptional.get();
            requisito.setApoderado(apoderadoFeign.buscarApoderado(requisito.getApoderado().getIdApoderado()).getBody());
            requisito.setAntecedenteMedico(antecedenteMedicoFeign.buscarAntecedenteMedico(requisito.getAntecedenteMedico().getIdAntecedenteMedico()).getBody());
            return requisito;
        }
        return null;
    }

    @Override
    public Requisito guardar(Requisito requisito) {
        // Validación de existencia
        if (apoderadoFeign.buscarApoderado(requisito.getApoderado().getIdApoderado()).getBody() == null) {
            throw new IllegalArgumentException("El apoderado no existe.");
        }

        if (antecedenteMedicoFeign.buscarAntecedenteMedico(requisito.getAntecedenteMedico().getIdAntecedenteMedico()).getBody() == null) {
            throw new IllegalArgumentException("El antecedente médico no existe.");
        }

        Requisito guardado = requisitoRepositorio.save(requisito);

        // Asociar DTOs
        guardado.setApoderado(apoderadoFeign.buscarApoderado(requisito.getApoderado().getIdApoderado()).getBody());
        guardado.setAntecedenteMedico(antecedenteMedicoFeign.buscarAntecedenteMedico(requisito.getAntecedenteMedico().getIdAntecedenteMedico()).getBody());

        return guardado;
    }

    @Override
    public Requisito actualizar(Requisito requisito) {
        Requisito actualizado = requisitoRepositorio.save(requisito);
        actualizado.setApoderado(apoderadoFeign.buscarApoderado(actualizado.getApoderado().getIdApoderado()).getBody());
        actualizado.setAntecedenteMedico(antecedenteMedicoFeign.buscarAntecedenteMedico(actualizado.getAntecedenteMedico().getIdAntecedenteMedico()).getBody());
        return actualizado;
    }

    @Override
    public void eliminar(Long id) {
        requisitoRepositorio.deleteById(id);
    }
}
