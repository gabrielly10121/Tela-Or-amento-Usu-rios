package com.finan.orcamento.service;

import com.finan.orcamento.model.OrcamentoModel;
import com.finan.orcamento.repositories.OrcamentoRepository;
import com.finan.orcamento.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Buscar todos os orçamentos
    public List<OrcamentoModel> buscarCadastro() {
        return orcamentoRepository.findAll();
    }

    // Buscar orçamento por ID
    public OrcamentoModel buscaId(Long id) {
        Optional<OrcamentoModel> obj = orcamentoRepository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        } else {
            throw new RuntimeException("Orçamento não encontrado");
        }
    }

    // Cadastrar novo orçamento
    public OrcamentoModel cadastrarOrcamento(OrcamentoModel orcamentoModel) {
        // Verifica se o usuário já está salvo
        if (orcamentoModel.getUsuario().getId() == null) {
            usuarioRepository.save(orcamentoModel.getUsuario());
        }

        // Calcula ICMS
        orcamentoModel.calcularIcms();
        return orcamentoRepository.save(orcamentoModel);
    }

    // Atualizar orçamento existente
    public OrcamentoModel atualizaCadastro(OrcamentoModel orcamentoModel, Long id) {
        OrcamentoModel newOrcamentoModel = buscaId(id);

        // Atualiza os campos
        newOrcamentoModel.setValorOrcamento(orcamentoModel.getValorOrcamento());
        newOrcamentoModel.setValorICMS(orcamentoModel.getValorICMS());
        newOrcamentoModel.setNomeComprador(orcamentoModel.getNomeComprador());
        newOrcamentoModel.setEndereco(orcamentoModel.getEndereco());
        newOrcamentoModel.setCpf(orcamentoModel.getCpf());
        newOrcamentoModel.setCnpj(orcamentoModel.getCnpj());

        // Recalcula ICMS
        newOrcamentoModel.calcularIcms();
        return orcamentoRepository.save(newOrcamentoModel);
    }

    // Deletar orçamento
    public void deletaOrcamento(Long id) {
        orcamentoRepository.deleteById(id);
    }

    // Validação para Pessoa Física
    public void validarPessoaFisica(OrcamentoModel orcamentoModel) {
        if (orcamentoModel.getCpf() == null || orcamentoModel.getCpf().isEmpty()) {
            throw new RuntimeException("CPF é obrigatório para Pessoa Física.");
        }
        if (orcamentoModel.getNomeComprador() == null || orcamentoModel.getNomeComprador().isEmpty()) {
            throw new RuntimeException("Nome do Comprador é obrigatório.");
        }
    }

    // Validação para Pessoa Jurídica
    public void validarPessoaJuridica(OrcamentoModel orcamentoModel) {
        if (orcamentoModel.getCnpj() == null || orcamentoModel.getCnpj().isEmpty()) {
            throw new RuntimeException("CNPJ é obrigatório para Pessoa Jurídica.");
        }
        if (orcamentoModel.getNomeComprador() == null || orcamentoModel.getNomeComprador().isEmpty()) {
            throw new RuntimeException("Nome do Comprador é obrigatório.");
        }
    }
}