package br.Projeto.Ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDTO(@NotBlank String nome, @NotBlank String descricao) {
}
