package br.Projeto.Ecommerce.repository;

import br.Projeto.Ecommerce.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria ,Integer> {
}
