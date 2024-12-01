package br.Projeto.Ecommerce.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


import java.math.BigDecimal;

@Entity
@Table(name = "itempedido")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Ignora as propriedades
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)// carrega sob demanda
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    @JsonBackReference
    private Pedido pedidoId;

    @ManyToOne(fetch = FetchType.LAZY)// carrega sob demanda
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produtoId;

    @Column
    private Integer quantidade;

    @Column
    private BigDecimal preco;

    public ItemPedido(){}

    public ItemPedido( Pedido pedidoId, Produto produtoId, Integer quantidade, BigDecimal preco) {
        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Pedido pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Produto getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Produto produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
