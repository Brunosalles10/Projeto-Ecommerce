package br.Projeto.Ecommerce.response;

import br.Projeto.Ecommerce.model.Categoria;
import br.Projeto.Ecommerce.model.Produto;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoResponseDTO(Integer id, String nome, BigDecimal preco, Integer estoque, List<String> categorias) {
    public ProdutoResponseDTO(Produto produto){
        this(produto.getId(),
                produto.getName(),
                produto.getPreco(),
                produto.getEstoque()
        ,produto.getCategoria().stream().map(Categoria::getNome).toList());// Extrai os nomes das categorias
    }
}
