package com.ufrn.medicamentos_pw.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DomainAdmin extends DomainUsuario {

    private String departamento;
}
