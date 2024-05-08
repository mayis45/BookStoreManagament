package com.BookStoreManagament.dto.response;

import jakarta.persistence.Column;
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
public class PublisherResponseDto {

    String publisherAccountName;
    String publisherAccountDescription;
    String publisherAccountEmail;
    BigDecimal publisherAccountTotalFollowers;
    BigDecimal totalBookCount;

}
