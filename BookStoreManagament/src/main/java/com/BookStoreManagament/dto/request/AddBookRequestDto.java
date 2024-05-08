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
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddBookRequestDto {

    @NotBlank(message = "bookName is blank")
    @NotNull(message = "bookName is null")
    String bookName;
    @NotNull(message = "bookPrice is null")
    BigDecimal bookPrice;
    @NotBlank(message = "URL is blank")
    @NotNull(message = "URL is null")
    String bookPhotoUrl;
    @NotBlank(message = "bookDescription is blank")
    @NotNull(message = "bookDescription is null")
    String bookDescription;
    @NotBlank(message = "language is blank")
    @NotNull(message = "language is null")
    String bookLanguage;
    @NotBlank(message = "pages is blank")
    @NotNull(message = "pages is null")
    @Min(value = 1)
    String pagesOfBook;
    @NotBlank(message = "categoryId is blank")
    @NotNull(message = "categoryId is null")
    String fkCategoryId;
    @NotBlank(message = "publisherId is blank")
    @NotNull(message = "publisherId is null")
    String fkPublisherId;

}
