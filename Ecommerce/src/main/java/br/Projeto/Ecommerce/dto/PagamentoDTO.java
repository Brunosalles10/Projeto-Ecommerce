package br.Projeto.Ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PagamentoDTO (@NotNull Integer pedido,
                            @NotBlank String formaPagamento,
                            @NotNull @Positive BigDecimal valor,
                            LocalDateTime dataPagamento
                           ) {
}
