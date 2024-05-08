package com.BookStoreManagament.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
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
public class AddBookToDepotRequestDto {

    @Min(value = 1)
    @NotNull(message = "quantity is null")
    BigDecimal quantityOfBooks;
    @NotBlank(message = "id is blank")
    String fkBookId;

}
