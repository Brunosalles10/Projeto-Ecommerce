package br.Projeto.Ecommerce.repository;

import br.Projeto.Ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {


}
