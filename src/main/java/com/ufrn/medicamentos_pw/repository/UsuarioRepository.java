package com.ufrn.medicamentos_pw.repository;

import com.ufrn.medicamentos_pw.domain.DomainUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<DomainUsuario, Long> {
    Optional<DomainUsuario> findByUsername(String username);
}
