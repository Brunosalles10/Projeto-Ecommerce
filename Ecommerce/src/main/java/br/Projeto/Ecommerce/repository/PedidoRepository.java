package br.Projeto.Ecommerce.repository;

import br.Projeto.Ecommerce.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
