package com.BookStoreManagament.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterBetweenByDateSaleRequestDto {

    @NotNull(message = "startCaseDate is null")
    LocalDateTime startCaseDate;
    @NotNull(message = "endCaseDate is null")
    LocalDateTime endCaseDate;

}
