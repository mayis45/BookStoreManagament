package com.BookStoreManagament.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookInfoForDepotResponseDto {

    @NotNull(message = "initialPrice is null")
    BigDecimal initialPrice;
    @NotBlank(message = "categoryId is null")
    String categoryId;

}
