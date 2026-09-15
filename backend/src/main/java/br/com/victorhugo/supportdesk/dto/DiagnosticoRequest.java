package br.com.victorhugo.supportdesk.dto;

import jakarta.validation.constraints.NotBlank;

public record DiagnosticoRequest(
    @NotBlank String etapa,
    @NotBlank String resultado,
    String observacao
) {}
