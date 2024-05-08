package com.BookStoreManagament.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MostPopularBookResponseDto {

    String bookName;
    BigDecimal bookPrice;
    String bookPhotoUrl;
    BigDecimal bookSoldQuantity;
    CategoryResponseDto fkCategoryId;

}
