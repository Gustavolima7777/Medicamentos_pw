package com.ufrn.medicamentos_pw.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor

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

    private String isDeleted;

    public Medicamentos(String dosagem, String imgUrl, String laboratorio, boolean necessitaReceita, String nomeComercial, Double preco, String principioAtivo) {
        this.dosagem = dosagem;
        this.imgUrl = imgUrl;
        this.isDeleted = null;
        this.laboratorio = laboratorio;
        this.necessitaReceita = necessitaReceita;
        this.nomeComercial = nomeComercial;
        this.preco = preco;
        this.principioAtivo = principioAtivo;
    }

    public Medicamentos() {
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public boolean isNecessitaReceita() {
        return necessitaReceita;
    }

    public void setNecessitaReceita(boolean necessitaReceita) {
        this.necessitaReceita = necessitaReceita;
    }

    public String getNomeComercial() {
        return nomeComercial;
    }

    public void setNomeComercial(String nomeComercial) {
        this.nomeComercial = nomeComercial;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(String principioAtivo) {
        this.principioAtivo = principioAtivo;
    }
}