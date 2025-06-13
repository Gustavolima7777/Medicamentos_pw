package com.ufrn.medicamentos_pw.controller;

import com.ufrn.medicamentos_pw.domain.Medicamentos;
import com.ufrn.medicamentos_pw.service.CarrinhoService;
import com.ufrn.medicamentos_pw.service.MedicamentoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Random;

@Controller
public class MedicamentoController {

    private final MedicamentoService medicamentoService;
    private final CarrinhoService carrinhoService;

    private final List<String> imagensDisponiveis = List.of(
            "static/remedio1.png"
            //"/images/remedio2.jpg", "/images/remedio3.jpg", "/images/remedio4.png"
    );
    public MedicamentoController(MedicamentoService medicamentoService, CarrinhoService carrinhoService) {
        this.medicamentoService = medicamentoService;
        this.carrinhoService = carrinhoService;
    }

    @GetMapping({"/", "/index"})
    public String getIndex(Model model, HttpSession session) {
        model.addAttribute("medicamentos", medicamentoService.listarMedicamentosNaoDeletados());
        model.addAttribute("tamanhoCarrinho", carrinhoService.getCarrinho(session).size());
        return "index";
    }

    @GetMapping("/admin")
    public String getAdmin(Model model) {
        model.addAttribute("medicamentos", medicamentoService.listAllMedicamentos());
        return "admin";
    }

    @GetMapping("/cadastro")
    public String getCadastro(Model model) {
        model.addAttribute("medicamento", new Medicamentos());
        return "cadastroMedicamento";
    }

    @GetMapping("/editar/{id}")
    public String getEditar( Model model, @PathVariable Long id) {
        medicamentoService.findById(id).ifPresent(medicamento -> model.addAttribute("medicamento", medicamento));
        return "cadastroMedicamento";
    }

    @PostMapping("/salvar")
    public String postSalvar(
            @ModelAttribute("medicamento") @Valid Medicamentos medicamento,
            BindingResult result,
            RedirectAttributes attrs) {
        System.out.println("Entrou no postSalvar, medicamento: " + medicamento);

        if (result.hasErrors()) {
            return "cadastroMedicamento";
        }

        if (medicamento.getId() == null) {
            Random random = new Random();
            medicamento.setImgUrl(imagensDisponiveis.get(random.nextInt(imagensDisponiveis.size())));
        }

        medicamentoService.save(medicamento);
        attrs.addFlashAttribute("success", "Medicamento salvo com sucesso!");
        return "cadastroMedicamento";
    }


    @GetMapping("/deletar/{id}")
    public String getDeletar(@PathVariable Long id, RedirectAttributes attrs) {
        medicamentoService.softDelete(id);
        attrs.addFlashAttribute("success", "Medicamento deletado com sucesso.");
        return "redirect:/index";
    }

    @GetMapping("/restaurar/{id}")
    public String getRestaurar(@PathVariable Long id, RedirectAttributes attrs) {
        medicamentoService.restore(id);
        attrs.addFlashAttribute("success", "Medicamento restaurado com sucesso.");
        return "redirect:/admin";
    }

    @GetMapping("/adicionarItemcarrinho/{id}")
    public String adicionarCarrinho(@PathVariable Long id, HttpSession session) {
        carrinhoService.adicionarItem(id, session);
        return "redirect:/index";
    }

    @GetMapping("/verCarrinho")
    public String getCarrinho(HttpSession session, Model model, RedirectAttributes attrs) {
        List<Medicamentos> carrinho = carrinhoService.getCarrinho(session);
        if (carrinho.isEmpty()) {
            attrs.addFlashAttribute("warning", "Seu carrinho está vazio.");
            return "redirect:/index";
        }
        model.addAttribute("carrinho", carrinho);
        model.addAttribute("total", carrinhoService.getValorTotal(session));
        return "carrinho";
    }
    //falta fazer
    @GetMapping("/finalizarCompra")
    public String finalizarCompra(HttpSession session) {
        carrinhoService.limparCarrinho(session);
        return "redirect:/index";
    }
}