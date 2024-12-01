package br.Projeto.Ecommerce.response;

import br.Projeto.Ecommerce.model.ItemPedido;

import java.math.BigDecimal;

public record ItemPedidoResponseDTO(Integer id,
                                    String nomeUsuario,
                                    String status,
                                    BigDecimal precoItemPedido ,
                                    String nomeProduto,
                                    Integer quantidadeItemPedido,
                                    BigDecimal valorTotalPedido) {

    public  ItemPedidoResponseDTO(ItemPedido itens){
        this(itens.getId(), //id do item pedido
                itens.getPedidoId().getUsuario_id().getNome(),//nome do usuario
                itens.getPedidoId().getStatus(),//status do pedido
                itens.getPreco(),//preco de cada unidade
                itens.getProdutoId().getName(), //nome do produto
                itens.getQuantidade()// quantidade do pedido
                ,itens.getPedidoId().getValorTotal());// valor total do pedido
    }
}
