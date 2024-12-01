package br.Projeto.Ecommerce.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoRequestDTO(@NotBlank String nome, String descricao,
                                @NotNull BigDecimal preco, @NotNull Integer estoque,
                                List<Integer> categoriasIds) {
}
