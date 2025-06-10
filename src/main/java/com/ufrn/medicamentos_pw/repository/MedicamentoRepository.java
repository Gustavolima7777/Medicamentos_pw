package com.ufrn.medicamentos_pw.repository;

import com.ufrn.medicamentos_pw.domain.Medicamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamentos, Long> {

    List<Medicamentos> findAllByIsDeletedNull();
}