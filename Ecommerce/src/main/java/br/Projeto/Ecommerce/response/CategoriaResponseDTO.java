package br.Projeto.Ecommerce.response;

import br.Projeto.Ecommerce.model.Categoria;

import java.util.List;

public record CategoriaResponseDTO(Integer id,String nome, String descricao) {
    public CategoriaResponseDTO(Categoria categoria){
        this(categoria.getId(), categoria.getNome(),categoria.getDescricao());
    }


}
