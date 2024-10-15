package com.finan.orcamento.model;

import com.finan.orcamento.model.enums.IcmsEstados;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orcamento")
public class OrcamentoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private IcmsEstados icmsEstados;

    @Column(name="valor_orcamento")
    private BigDecimal valorOrcamento;

    @Column(name="valor_icms")
    private BigDecimal valorICMS;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="usuario_id", referencedColumnName = "id")
    private UsuarioModel usuario;

    @Column(name="nome_comprador")
    private String nomeComprador;

    @Column(name="atendente")
    private String atendente;

    @Column(name="cpf", nullable = true)
    private String cpf;

    @Column(name="cnpj", nullable = true)
    private String cnpj;

    @Column(name="endereco")
    private String endereco;

    public void calcularIcms() {
        if (this.icmsEstados == null) {
            throw new IllegalStateException("O campo icmsEstados não pode ser nulo.");
        }
        this.valorICMS = this.icmsEstados.getStrategy().calcular(this.valorOrcamento);
    }
}