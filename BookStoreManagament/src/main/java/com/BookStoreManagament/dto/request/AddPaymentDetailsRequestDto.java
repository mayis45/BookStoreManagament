package com.BookStoreManagament.dto.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddPaymentDetailsRequestDto {

    @NotBlank(message = "id is blank")
    @NotNull(message = "id is null")
    String id;
    @NotBlank(message = "cardNumber is blank")
    @NotNull(message = "cardNumber is null")
    @Max(value = 16)
    String cardNumber;
    @NotBlank(message = "cvv code is blank")
    @NotNull(message = "cvv code is null")
    @Max(value = 3)
    String cardCVVCode;

}