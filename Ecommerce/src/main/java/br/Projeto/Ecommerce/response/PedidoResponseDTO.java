package br.Projeto.Ecommerce.response;

import br.Projeto.Ecommerce.model.Pedido;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoResponseDTO(Integer id, String usuario, LocalDateTime dataPedido, String status, BigDecimal
                                valorTotal) {
    public PedidoResponseDTO(Pedido pedido){
        this(pedido.getId(), pedido.getUsuario_id().getNome() , pedido.getDataPedido(),pedido.getStatus(),
                pedido.getValorTotal());
    }

}
