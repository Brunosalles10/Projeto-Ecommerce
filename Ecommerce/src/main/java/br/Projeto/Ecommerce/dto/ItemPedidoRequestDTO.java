package br.Projeto.Ecommerce.dto;

import br.Projeto.Ecommerce.model.Pedido;
import br.Projeto.Ecommerce.model.Produto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ItemPedidoRequestDTO(@NotNull Integer pedidoId,
                                   @NotNull Integer produtoId,
                                   @NotNull Integer quantidade,
                                   @NotNull @Positive BigDecimal preco

                                 ){
}
