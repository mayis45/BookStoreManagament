package com.BookStoreManagament.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterBetweenByDateDepotRequestDto {

    @NotNull(message = "startDate is null")
    LocalDate startCaseDate;
    @NotNull(message = "endDate is null")
    LocalDate endCaseDate;

}
