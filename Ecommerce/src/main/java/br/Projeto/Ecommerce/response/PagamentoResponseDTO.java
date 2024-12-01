package br.Projeto.Ecommerce.response;

import br.Projeto.Ecommerce.model.Pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PagamentoResponseDTO(Integer id,
                                   Integer idPedido,
                                   String pedidoNome,
                                   String statusPedido,

                                   String formaPagamento,
                                   LocalDateTime dataPagamento,
                                   BigDecimal valorPagamento) {
    public PagamentoResponseDTO(Pagamento pagamento){
        this(pagamento.getId(), //id do pagamento
                pagamento.getPedido().getId(),// id do pedido
                pagamento.getPedido().getUsuario_id().getNome(),// nome do usuario
                pagamento.getPedido().getStatus(),// status do pedido

                pagamento.getFormaPagamento(),//forma de pagamento
                pagamento.getDataPagamento(),// data de pagamento
                pagamento.getValor()//valor do pagamento
                );
    }
}
