package com.nttdata.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequest(
        Long id,

        @NotBlank
        @Size(max = 100)
        String name,

        @NotBlank
        @Size(max = 10)
        String gender,

        @NotBlank
        @Size(max = 10)
        String identification,

        @NotBlank
        @Size(max = 200)
        String address,

        @NotBlank
        @Size(max = 20)
        String phone,

        @NotBlank
        @Size(max = 100)
        String password,

        Boolean status

) {
}
