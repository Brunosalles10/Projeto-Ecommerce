package br.Projeto.Ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Produto")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Ignora as propriedades
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String name;

    @Column
    private String descricao;

    @Column
    private BigDecimal preco;

    @Column
    private Integer estoque;


    @ManyToMany(mappedBy = "produtos", fetch = FetchType.LAZY)
    @JsonBackReference
     private List<Categoria> categoria = new ArrayList<>();

    @OneToMany(mappedBy = "produtoId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itempedido = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Pagamento> pagamentos;

    public Produto() {}

    public Produto(Integer id, String name, String descricao, BigDecimal preco, Integer estoque,
                   List<Categoria> categoria) {
        this.id = id;
        this.name = name;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public List <Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria (List <Categoria> categoria) {
        this.categoria = categoria;
    }


}
