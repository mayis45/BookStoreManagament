package com.BookStoreManagament.dto.response;

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
public class PublisherAccountResponseDto {

    String publisherAccountName;
    String publisherAccountDescription;
    String publisherAccountProfilePhotoUrl;
    String publisherAccountEmail;
    BigDecimal publisherAccountTotalFollowers;
    BigDecimal totalBookCount;

}
