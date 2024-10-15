package com.finan.orcamento.controller;

import com.finan.orcamento.model.OrcamentoModel;
import com.finan.orcamento.model.enums.IcmsEstados;
import com.finan.orcamento.service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/orcamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    // Exibir a página de orçamentos
    @GetMapping
    public String getOrcamentoPage(Model model) {
        List<OrcamentoModel> orcamentos = orcamentoService.buscarCadastro();
        model.addAttribute("orcamentos", orcamentos);
        model.addAttribute("orcamentoModel", new OrcamentoModel()); // Para o formulário
        model.addAttribute("icmsEstados", IcmsEstados.values()); // Adiciona os estados de ICMS ao modelo
        return "orcamentoPage"; // Nome do arquivo HTML
    }

    // Cadastrar novo orçamento
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastraOrcamento(@ModelAttribute OrcamentoModel orcamentoModel, Model model) {
        // Verifica se o campo icmsEstados foi preenchido
        if (orcamentoModel.getIcmsEstados() == null) {
            throw new RuntimeException("O campo icmsEstados é obrigatório.");
        }

        // Verifica se é Pessoa Física ou Jurídica e valida os campos correspondentes
        if (orcamentoModel.getCpf() != null && !orcamentoModel.getCpf().isEmpty()) {
            orcamentoService.validarPessoaFisica(orcamentoModel);
        } else if (orcamentoModel.getCnpj() != null && !orcamentoModel.getCnpj().isEmpty()) {
            orcamentoService.validarPessoaJuridica(orcamentoModel);
        } else {
            throw new RuntimeException("CPF ou CNPJ deve ser informado.");
        }

        // Cadastra o orçamento
        orcamentoService.cadastrarOrcamento(orcamentoModel);

        // Atualiza a lista de orçamentos e retorna para a página
        List<OrcamentoModel> orcamentos = orcamentoService.buscarCadastro();
        model.addAttribute("orcamentos", orcamentos);
        model.addAttribute("orcamentoModel", new OrcamentoModel()); // Para o formulário
        model.addAttribute("icmsEstados", IcmsEstados.values()); // Adiciona os estados de ICMS ao modelo
        return "orcamentoPage"; // Redireciona para a página de orçamentos com a lista atualizada
    }
}