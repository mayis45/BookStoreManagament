package com.BookStoreManagament.dto.response;

import com.BookStoreManagament.entity.BookEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponseDto {

    List<AllBooksResponseDto> allBooks;
    Integer totalPages;
    Long totalElements;
    Boolean hasNext;

}
