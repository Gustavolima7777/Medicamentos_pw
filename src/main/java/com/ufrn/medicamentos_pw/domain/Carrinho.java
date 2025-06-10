package com.ufrn.medicamentos_pw.domain;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Carrinho implements Serializable { // Serializable é uma boa prática para objetos de sessão

    private final List<Medicamentos> itens;
    private double valorTotal;
    private int quantidadeItens;

    public Carrinho() {
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
        this.quantidadeItens = 0;
    }


    public void adicionarItem(Medicamentos medicamento) {
        this.itens.add(medicamento);
        recalcularTotais();
    }


    public void removerItem(Long medicamentoId) {
        this.itens.removeIf(item -> item.getId().equals(medicamentoId));
        recalcularTotais();
    }


    public void limpar() {
        this.itens.clear();
        recalcularTotais();
    }


    private void recalcularTotais() {
        this.valorTotal = this.itens.stream().mapToDouble(Medicamentos::getPreco).sum();
        this.quantidadeItens = this.itens.size();
    }
}