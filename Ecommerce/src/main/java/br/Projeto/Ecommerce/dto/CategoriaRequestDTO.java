package br.Projeto.Ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CategoriaRequestDTO(@NotBlank String nome, @NotBlank String descricao){

}
