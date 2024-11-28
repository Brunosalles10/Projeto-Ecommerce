package br.Projeto.Ecommerce.response;

import br.Projeto.Ecommerce.model.Usuario;

public record UsuarioResponseDTO(Integer id, String nome, String email, String telefone, String endereco) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getEndereco());
    }
}
