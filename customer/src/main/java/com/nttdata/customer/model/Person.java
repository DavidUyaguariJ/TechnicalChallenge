package com.nttdata.customer.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Person {

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank
    @Size(max = 10)
    @Column(nullable = false, length = 10)
    private String gender;

    @NotBlank
    @Size(max = 10)
    @Column(nullable = false, unique = true, length = 20)
    private String identification;

    @NotBlank
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String address;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String phone;

}
