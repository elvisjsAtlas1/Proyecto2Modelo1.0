package com.acad.antecedentemedicoservice.Service.ServiceImpl;

import com.acad.antecedentemedicoservice.Entity.AntecedenteMedico;
import com.acad.antecedentemedicoservice.Repository.AntecedenteMedicoRepositorio;
import com.acad.antecedentemedicoservice.Service.AntecedenteMedicoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AntecedenteMedicoServicioImpl implements AntecedenteMedicoServicio {

    @Autowired
    private AntecedenteMedicoRepositorio antecedenteMedicoRepositorio;

    @Override
    public List<AntecedenteMedico> listar() {
        return antecedenteMedicoRepositorio.findAll();
    }

    @Override
    public Optional<AntecedenteMedico> buscar(Integer id) {
        return antecedenteMedicoRepositorio.findById(id);
    }

    @Override
    public AntecedenteMedico guardar(AntecedenteMedico antecedente) {
        return antecedenteMedicoRepositorio.save(antecedente);
    }

    @Override
    public AntecedenteMedico modificar(Integer id, AntecedenteMedico antecedente) {
        antecedente.setIdAntecedenteMedico(id);
        return antecedenteMedicoRepositorio.save(antecedente);
    }

    @Override
    public void eliminar(Integer id) {
        antecedenteMedicoRepositorio.deleteById(id);
    }
}
