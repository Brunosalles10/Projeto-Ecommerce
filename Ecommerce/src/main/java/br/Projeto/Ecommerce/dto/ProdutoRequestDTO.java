package br.Projeto.Ecommerce.dto;

import br.Projeto.Ecommerce.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequestDTO(@NotBlank String nome, String descricao, @NotNull BigDecimal preco, @NotNull Integer estoque
,Categoria categoria) {
}
