package com.ufrn.medicamentos_pw.repository;
import com.ufrn.medicamentos_pw.domain.Medicamentos;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public interface CarrinhoRepository {


    List<Medicamentos> getCarrinho(HttpSession session);


    void adicionarItem(Long idMedicamento, HttpSession session);


    void limparCarrinho(HttpSession session);


    double getValorTotal(HttpSession session);
}