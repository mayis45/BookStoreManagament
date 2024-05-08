package com.BookStoreManagament.dto.response;

import jakarta.persistence.Column;
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
public class AllSalesResponseDto {

    String senderCardNumber;
    LocalDateTime dateOfPayment;
    BigDecimal quantityOfBooks;
    BigDecimal totalPrice;
    BigDecimal totalProfit;
    BookResponseDto fkBookId;

}
