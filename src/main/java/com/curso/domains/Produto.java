package com.curso.domains;

import com.curso.domains.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
    private long idProduto;

    @NotBlank
    @NotNull
    private String descricao;

    @NotNull
    @Digits(integer = 15, fraction = 3)
    private BigDecimal saldoEstoque;

    @NotNull
    @Digits(integer = 15, fraction = 3)
    private BigDecimal valorUnitario;

    @NotNull
    @Digits(integer = 15, fraction = 2)
    private BigDecimal valorEstoque;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro = LocalDate.now();

    @ManyToOne
    @JoinColumn(name="idgrupoproduto")
    private GrupoProduto grupoProduto;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "status")
    private Status status;

    // Construtor padrão
    public Produto() {
        this.saldoEstoque = BigDecimal.ZERO;
        this.valorUnitario = BigDecimal.ZERO;
        this.valorEstoque = BigDecimal.ZERO;
        this.status = Status.ATIVO;
    }

    // Construtor com todos os parâmetros, incluindo GrupoProduto
    public Produto(long idProduto, String descricao, BigDecimal saldoEstoque, BigDecimal valorUnitario,
                   LocalDate dataCadastro, GrupoProduto grupoProduto, Status status) {
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.saldoEstoque = saldoEstoque != null ? saldoEstoque : BigDecimal.ZERO;
        this.valorUnitario = valorUnitario;
        this.valorEstoque = saldoEstoque != null ? saldoEstoque.multiply(valorUnitario) : BigDecimal.ZERO;
        this.dataCadastro = dataCadastro != null ? dataCadastro : LocalDate.now();
        this.grupoProduto = grupoProduto;
        this.status = status;
    }

    // Getters e Setters
    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getSaldoEstoque() {
        return saldoEstoque;
    }

    public void setSaldoEstoque(BigDecimal saldoEstoque) {
        this.saldoEstoque = saldoEstoque;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorEstoque() {
        return valorEstoque;
    }

    public void setValorEstoque(BigDecimal valorEstoque) {
        this.valorEstoque = valorEstoque;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return idProduto == produto.idProduto && Objects.equals(descricao, produto.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduto, descricao);
    }
}
