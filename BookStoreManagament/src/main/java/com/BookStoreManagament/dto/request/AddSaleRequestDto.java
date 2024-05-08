package com.BookStoreManagament.dto.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddSaleRequestDto {

    @NotBlank(message = "senderCardNumber is blank")
    @Max(value = 16)
    String senderCardNumber;
    @NotNull(message = "dateOfPayment is null")
    LocalDateTime dateOfPayment;
    @NotNull(message = "quantityOfBooks is null")
    @Min(value = 1)
    BigDecimal quantityOfBooks;
    @NotNull(message = "totalPrice is null")
    BigDecimal totalPrice;
    @NotNull(message = "totalProfit is null")
    BigDecimal totalProfit;
    @NotBlank(message = "bookId is blank")
    String fkBookId;

}
