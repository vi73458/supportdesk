package br.com.victorhugo.supportdesk.dto;

import br.com.victorhugo.supportdesk.domain.Prioridade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChamadoRequest(
    @NotBlank String titulo,
    @NotBlank String descricao,
    @NotNull Prioridade prioridade,
    @NotNull Long usuarioId,
    @NotNull Long categoriaId,
    Long equipamentoId
) {}
