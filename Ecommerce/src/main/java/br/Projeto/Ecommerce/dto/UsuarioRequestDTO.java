package br.Projeto.Ecommerce.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO (@NotBlank String nome,
                                 @Email @NotBlank String email,
                                 @NotBlank String senha,
                                @NotBlank String telefone,
                                 @NotBlank String endereco) {

}




