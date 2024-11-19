package br.Projeto.Ecommerce.dto;

import br.Projeto.Ecommerce.model.Usuario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoRequestDTO (@NotNull Integer usuario_id, @NotNull LocalDateTime dataPedido,
                                @NotNull  String status, @NotNull @Positive BigDecimal valorTotal){
}
