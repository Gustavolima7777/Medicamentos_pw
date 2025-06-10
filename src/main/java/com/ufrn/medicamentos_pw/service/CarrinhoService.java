package com.ufrn.medicamentos_pw.service;

import com.ufrn.medicamentos_pw.domain.Medicamentos;
import com.ufrn.medicamentos_pw.repository.MedicamentoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CarrinhoService {

    private final MedicamentoRepository medicamentoRepository;

    public CarrinhoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }


    public void adicionarItem(Long idMedicamento, HttpSession session) {
        medicamentoRepository.findById(idMedicamento).ifPresent(medicamento -> {
            List<Medicamentos> carrinho = getCarrinho(session);
            carrinho.add(medicamento);
            session.setAttribute("carrinho", carrinho);
        });
    }


    public List<Medicamentos> getCarrinho(HttpSession session) {
        List<Medicamentos> carrinho = (List<Medicamentos>) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new ArrayList<>();
            session.setAttribute("carrinho", carrinho);
        }
        return carrinho;
    }


    public double getValorTotal(HttpSession session) {
        List<Medicamentos> carrinho = getCarrinho(session);
        return carrinho.stream().mapToDouble(Medicamentos::getPreco).sum();
    }


    public void limparCarrinho(HttpSession session) {
        session.removeAttribute("carrinho");
    }
}