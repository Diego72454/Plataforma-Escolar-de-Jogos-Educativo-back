package br.edu.escola.jogos.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String matricula, @NotBlank String senha) {}
