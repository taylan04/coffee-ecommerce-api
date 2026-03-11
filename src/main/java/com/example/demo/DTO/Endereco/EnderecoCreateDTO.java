package com.example.demo.DTO.Endereco;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoCreateDTO(
        @NotNull
        Long idUsuario,
        @NotBlank
        String logradouro,
        //@NotEmpty verifica se o valor não é null e se o tamanho é maior que zero.
        @NotNull @Min(1)
        Integer numero,
        String complemento,
        String referencia,
        @NotBlank String bairro,
        @NotBlank String cep,
        @NotBlank String cidade,
        @NotBlank String estado,
        @NotBlank String pais
) {}
