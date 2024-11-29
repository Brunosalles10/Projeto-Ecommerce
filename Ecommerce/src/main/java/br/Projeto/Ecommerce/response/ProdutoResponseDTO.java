package br.Projeto.Ecommerce.response;

import br.Projeto.Ecommerce.model.Produto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(Integer id, String nome, BigDecimal preco, Integer estoque) {
    public ProdutoResponseDTO(Produto produto){
        this(produto.getId(),produto.getName(),produto.getPreco(),produto.getEstoque());
    }
}
