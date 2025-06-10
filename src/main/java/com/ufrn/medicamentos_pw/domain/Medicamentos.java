package com.ufrn.medicamentos_pw.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Medicamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco de dados vai gerar o ID automaticamente
    private Long id;

    @NotBlank(message = "O nome comercial não pode estar em branco.")
    @Size(min = 3, message = "O nome comercial deve ter no mínimo 3 caracteres.")
    private String nomeComercial;

    @NotBlank(message = "O laboratório não pode estar em branco.")
    private String laboratorio;

    @NotNull(message = "O preço não pode ser nulo.")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero.")
    private Double preco;

    @NotBlank(message = "O princípio ativo não pode estar em branco.")
    private String principioAtivo;

    @NotBlank(message = "A dosagem é obrigatória. Ex: 50mg, 10ml")
    private String dosagem;

    @NotNull(message = "Indique se o medicamento necessita de receita.")
    private boolean necessitaReceita;

    private String imgUrl;

    private Date isDeleted;
}