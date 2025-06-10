package com.acad.matriculaservice.Service.ServiceImpl;

import com.acad.matriculaservice.Dto.RequisitosDto;
import com.acad.matriculaservice.Entity.Matricula;
import com.acad.matriculaservice.Feign.RequisitoFeign;
import com.acad.matriculaservice.Repository.MatriculaRepositorio;
import com.acad.matriculaservice.Service.MatriculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatriculaServicioImpl implements MatriculaServicio {

    @Autowired
    private MatriculaRepositorio matriculaRepositorio;

    @Autowired
    private RequisitoFeign requisitoFeign;

    @Override
    public List<Matricula> listar() {
        return matriculaRepositorio.findAll().stream()
                .map(matricula -> {
                    matricula.setRequisito(requisitoFeign.buscarRequisito(matricula.getIdRequisitos()).getBody());
                    return matricula;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Matricula buscar(Long id) {
        Optional<Matricula> matriculaOptional = matriculaRepositorio.findById(id);
        if (matriculaOptional.isPresent()) {
            Matricula matricula = matriculaOptional.get();
            matricula.setRequisito(requisitoFeign.buscarRequisito(matricula.getIdRequisitos()).getBody());
            return matricula;
        }
        return null;
    }

    @Override
    public Matricula guardar(Matricula matricula) {
        // Validar existencia del requisito
        RequisitosDto requisito = requisitoFeign.buscarRequisito(matricula.getIdRequisitos()).getBody();
        if (requisito == null) {
            throw new IllegalArgumentException("El requisito no existe.");
        }

        // Guardar la matrícula
        Matricula matriculaGuardada = matriculaRepositorio.save(matricula);
        matriculaGuardada.setRequisito(requisito);

        return matriculaGuardada;
    }

    @Override
    public Matricula actualizar(Matricula matricula) {
        Matricula actualizada = matriculaRepositorio.save(matricula);
        actualizada.setRequisito(requisitoFeign.buscarRequisito(actualizada.getIdRequisitos()).getBody());
        return actualizada;
    }

    @Override
    public void eliminar(Long id) {
        matriculaRepositorio.deleteById(id);
    }
}
