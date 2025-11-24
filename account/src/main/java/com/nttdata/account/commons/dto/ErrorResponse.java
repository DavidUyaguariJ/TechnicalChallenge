package com.nttdata.account.commons.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ErrorResponse(
        @NotBlank String message,
        @NotNull Integer status,
        @NotBlank String path) {
}
