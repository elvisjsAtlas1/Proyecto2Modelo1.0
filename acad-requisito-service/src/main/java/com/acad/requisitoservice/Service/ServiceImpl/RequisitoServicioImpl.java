package com.acad.requisitoservice.Service.ServiceImpl;

import com.acad.requisitoservice.Dto.AntecedenteMedicoDto;
import com.acad.requisitoservice.Dto.ApoderadoDto;
import com.acad.requisitoservice.Entity.Requisito;
import com.acad.requisitoservice.Feign.AntecedenteMedicoFeign;
import com.acad.requisitoservice.Feign.ApoderadoFeign;
import com.acad.requisitoservice.Repository.RequisitoRepositorio;
import com.acad.requisitoservice.Service.RequisitoServicio;
import jakarta.persistence.criteria.CriteriaBuilder;
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
                    requisito.setApoderado(apoderadoFeign.buscarApoderado(requisito.getIdApoderado()).getBody());
                    requisito.setAntecedenteMedico(antecedenteMedicoFeign.buscarAntecedenteMedico(requisito.getIdAntecedenteMedico()).getBody());
                    return requisito;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Requisito buscar(Long id) {
        Optional<Requisito> requisitoOptional = requisitoRepositorio.findById(id);
        if (requisitoOptional.isPresent()) {
            Requisito requisito = requisitoOptional.get();
            requisito.setApoderado(apoderadoFeign.buscarApoderado(requisito.getIdApoderado()).getBody());
            requisito.setAntecedenteMedico(antecedenteMedicoFeign.buscarAntecedenteMedico(requisito.getIdAntecedenteMedico()).getBody());
            return requisito;
        }
        return null;
    }

    @Override
    public Requisito guardar(Requisito requisito) {
        // Validar existencia del apoderado
        ApoderadoDto apoderado = apoderadoFeign.buscarApoderado(requisito.getIdApoderado()).getBody();
        if (apoderado == null) {
            throw new IllegalArgumentException("El apoderado no existe.");
        }

        // Validar existencia del antecedente médico
        AntecedenteMedicoDto antecedenteMedico = antecedenteMedicoFeign.buscarAntecedenteMedico(requisito.getIdAntecedenteMedico()).getBody();
        if (antecedenteMedico == null) {
            throw new IllegalArgumentException("El antecedente médico no existe.");
        }

        // Guardar el requisito
        Requisito requisitoGuardado = requisitoRepositorio.save(requisito);

        requisitoGuardado.setApoderado(apoderado);
        requisitoGuardado.setAntecedenteMedico(antecedenteMedico);

        return requisitoGuardado;
    }



    @Override
    public Requisito actualizar(Requisito requisito) {
        Requisito actualizado = requisitoRepositorio.save(requisito);
        actualizado.setApoderado(apoderadoFeign.buscarApoderado(actualizado.getIdApoderado()).getBody());
        actualizado.setAntecedenteMedico(antecedenteMedicoFeign.buscarAntecedenteMedico(actualizado.getIdAntecedenteMedico()).getBody());
        return actualizado;
    }

    @Override
    public void eliminar(Long id) {
        requisitoRepositorio.deleteById(id);
    }
}
