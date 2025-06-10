package com.ufrn.medicamentos_pw.service;

import com.ufrn.medicamentos_pw.domain.Medicamentos;
import com.ufrn.medicamentos_pw.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    private final MedicamentoRepository repository;

    public MedicamentoService(MedicamentoRepository repository) {
        this.repository = repository;
    }


    public Optional<Medicamentos> findById(Long id) {
        return repository.findById(id);
    }


    public Medicamentos save(Medicamentos m) {
        return repository.save(m);
    }


    public List<Medicamentos> listAvailableMedicamentos() {
        return repository.findAllByIsDeletedNull();
    }


    public List<Medicamentos> listAllMedicamentos() {
        return repository.findAll();
    }


//    public void softDelete(Long id) {
//        repository.findById(id).ifPresent(medicamento -> {
//            medicamento.setIsDeleted(new Date());
//            repository.save(medicamento);
//        });
//    }


    public void restore(Long id) {
        repository.findById(id).ifPresent(medicamento -> {
            medicamento.setIsDeleted(null);
            repository.save(medicamento);
        });
    }
}