package com.example.demo.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotNull(message = "Fishni To`ldiring")
    private String fullName;

    @NotNull(message = "nomerni to`ldiring")
    private String phoneNumber;

    @NotNull(message = "addressni To`ldiring:")
    private String address;
}
