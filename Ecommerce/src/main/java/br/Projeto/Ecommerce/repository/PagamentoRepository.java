package br.Projeto.Ecommerce.repository;

import br.Projeto.Ecommerce.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository  extends JpaRepository <Pagamento, Integer> {
}
