package com.acad.antecedentemedicoservice.Service;

import com.acad.antecedentemedicoservice.Entity.AntecedenteMedico;

import java.util.List;
import java.util.Optional;

public interface AntecedenteMedicoServicio {
    List<AntecedenteMedico> listar();
    Optional<AntecedenteMedico> buscar(Integer id);
    AntecedenteMedico guardar(AntecedenteMedico antecedente);
    AntecedenteMedico modificar(Integer id, AntecedenteMedico antecedente);
    void eliminar(Integer id);
}
